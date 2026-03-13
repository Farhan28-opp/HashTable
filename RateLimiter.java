import java.util.*;

class RateLimiter {

    class TokenBucket {

        int tokens;
        long lastRefill;
        int maxTokens;
        int refillRate;

        TokenBucket(int max, int rate) {
            maxTokens = max;
            refillRate = rate;
            tokens = max;
            lastRefill = System.currentTimeMillis();
        }
    }

    private Map<String, TokenBucket> clients = new HashMap<>();

    public boolean allowRequest(String clientId) {

        TokenBucket bucket = clients
                .computeIfAbsent(clientId,
                        k -> new TokenBucket(1000, 1000));

        refill(bucket);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    private void refill(TokenBucket bucket) {

        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefill;

        int refill = (int) (elapsed / 3600000.0 * bucket.refillRate);

        if (refill > 0) {

            bucket.tokens = Math.min(bucket.maxTokens,
                    bucket.tokens + refill);

            bucket.lastRefill = now;
        }
    }
}
