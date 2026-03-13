import java.util.*;

class Autocomplete {

    private Map<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query,
                frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(
                        (a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {

            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            result.add(pq.poll().getKey());
        }

        return result;
    }
}
