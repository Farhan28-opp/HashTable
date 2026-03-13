import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class FlashSaleInventory {

    private Map<String, AtomicInteger> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, new AtomicInteger(quantity));
    }

    public int checkStock(String productId) {
        return stock.get(productId).get();
    }

    public String purchaseItem(String productId, int userId) {

        AtomicInteger currentStock = stock.get(productId);

        if (currentStock.get() > 0) {

            currentStock.decrementAndGet();
            return "Success. Remaining: " + currentStock.get();

        } else {

            waitingList
                    .computeIfAbsent(productId, k -> new LinkedList<>())
                    .add(userId);

            return "Added to waiting list. Position: " +
                    waitingList.get(productId).size();
        }
    }
}
