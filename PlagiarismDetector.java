import java.util.*;

class PlagiarismDetector {

    private Map<String, Set<String>> ngramIndex = new HashMap<>();

    public List<String> extractNgrams(String text, int n) {

        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                sb.append(words[i + j]).append(" ");
            }

            ngrams.add(sb.toString().trim());
        }

        return ngrams;
    }

    public void addDocument(String docId, String text) {

        List<String> ngrams = extractNgrams(text, 5);

        for (String ngram : ngrams) {

            ngramIndex
                    .computeIfAbsent(ngram, k -> new HashSet<>())
                    .add(docId);
        }
    }

    public int checkSimilarity(String text) {

        List<String> ngrams = extractNgrams(text, 5);
        int matches = 0;

        for (String ngram : ngrams) {
            if (ngramIndex.containsKey(ngram)) {
                matches++;
            }
        }

        return matches;
    }
}
