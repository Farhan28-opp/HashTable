import java.util.*;

public class UsernameChecker {
    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public void register(String username, int id) {
        users.put(username, id);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> list = new ArrayList<>();
        for(int i=1;i<=3;i++) list.add(username+i);
        list.add(username.replace("_","."));
        return list;
    }

    public String getMostAttempted() {
        return attempts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public static void main(String[] args) {
        UsernameChecker u = new UsernameChecker();
        u.register("john_doe",1);
        System.out.println(u.checkAvailability("john_doe"));
        System.out.println(u.checkAvailability("jane_smith"));
        System.out.println(u.suggestAlternatives("john_doe"));
        System.out.println(u.getMostAttempted());
    }
}
