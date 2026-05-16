//The Abstract Parent Class
package vaccination;

public abstract class InventoryItem {
    private String itemId;
    private String name;
    private int stockQuantity;

    //Constructor
    public InventoryItem(String itemId, String name, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    //Getter
    public String getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }

    //Smart Setter
    public void reduceStock(int amount) {
        if(amount > 0 && this.stockQuantity >= amount) {
            this.stockQuantity -= amount;
        }
        else {
            System.out.println("CRITICAL ERROR: Insuffcient stock for " + name);
        }
    }
    public abstract void displayDetails();
}
