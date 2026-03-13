import java.util.*;

class MultiLevelCache {

    private LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true);

    private Map<String, String> L2 = new HashMap<>();
    private Map<String, String> database = new HashMap<>();

    public String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            return "L1 HIT: " + L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {

            String video = L2.get(videoId);
            L1.put(videoId, video);
            return "L2 HIT: Promoted to L1";
        }

        String video = database.get(videoId);

        if (video != null) {
            L2.put(videoId, video);
        }

        return "DB HIT";
    }
}
