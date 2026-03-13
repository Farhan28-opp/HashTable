import java.util.*;

class DNSCache {

    class DNSEntry {
        String ipAddress;
        long expiryTime;

        DNSEntry(String ip, long ttl) {
            ipAddress = ip;
            expiryTime = System.currentTimeMillis() + ttl;
        }
    }

    private Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && entry.expiryTime > System.currentTimeMillis()) {
            return "Cache HIT: " + entry.ipAddress;
        }

        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 300000));

        return "Cache MISS: " + ip;
    }

    private String queryUpstream(String domain) {
        return "192.168.1." + new Random().nextInt(255);
    }
}
