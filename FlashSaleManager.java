import java.util.*;

public class FlashSaleManager {
    private HashMap<String,Integer> stock = new HashMap<>();
    private HashMap<String,Queue<Integer>> waiting = new HashMap<>();

    public void addProduct(String id,int count){
        stock.put(id,count);
        waiting.put(id,new LinkedList<>());
    }

    public synchronized String purchase(String id,int user){
        int s = stock.get(id);
        if(s>0){
            stock.put(id,s-1);
            return "Success "+(s-1);
        }else{
            Queue<Integer> q = waiting.get(id);
            q.add(user);
            return "Waiting position "+q.size();
        }
    }

    public int checkStock(String id){
        return stock.get(id);
    }

    public static void main(String[] args){
        FlashSaleManager f = new FlashSaleManager();
        f.addProduct("IPHONE15",100);
        System.out.println(f.checkStock("IPHONE15"));
        System.out.println(f.purchase("IPHONE15",123));
    }
}
