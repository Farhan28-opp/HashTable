import java.util.*;

public class UsernameChecker {
    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();
    private int id = 1;

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public void register(String username) {
        users.put(username, id++);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> list = new ArrayList<>();
        for(int i=1;i<=3;i++) list.add(username+i);
        list.add(username.replace("_","."));
        return list;
    }

    public String getMostAttempted() {
        String maxUser = "";
        int max = 0;
        for(String u: attempts.keySet()){
            if(attempts.get(u)>max){
                max = attempts.get(u);
                maxUser = u;
            }
        }
        return maxUser;
    }

    public static void main(String[] args) {
        UsernameChecker u = new UsernameChecker();
        u.register("john_doe");
        System.out.println(u.checkAvailability("john_doe"));
        System.out.println(u.checkAvailability("jane_smith"));
        System.out.println(u.suggestAlternatives("john_doe"));
        System.out.println(u.getMostAttempted());
    }
}
