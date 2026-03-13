import java.util.*;

class AnalyticsDashboard {

    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url,
                pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors
                .computeIfAbsent(url, k -> new HashSet<>())
                .add(userId);

        trafficSources.put(source,
                trafficSources.getOrDefault(source, 0) + 1);
    }

    public void printTopPages() {

        pageViews.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e ->
                        System.out.println(e.getKey() + " : " + e.getValue()));
    }
}
