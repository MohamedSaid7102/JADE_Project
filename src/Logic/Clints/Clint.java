package Logic.Clints;

import java.util.ArrayList;
import java.util.Iterator;

public class Clint {
    
    //    ------------
    //    Attributes |
    //    ------------
    private ArrayList<Item> Items = new ArrayList<>();
    private double balance = 1000.0;//1000$
    //    Items data type
    private class Item  {
        private String name;
        private int amount;
        //        constructor
        Item(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
        //    Getters
        public String getName() {
            return name;
        }
        
        public int getAmount() {
            return amount;
        }
        //    Setters
        public void setName(String name) {
            this.name = name;
        }
        
        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
    //    ---------
    //    Methods |
    //    ---------
    
    //    ---------
    //    Private |
    //    ---------
    //    To Check if this item is added once before or not
    private boolean containsProduct(String productName) {
        for (Iterator<Item> i = Items.iterator(); i.hasNext(); ) {
            var currentItems = i.next();
            if (currentItems.getName().equalsIgnoreCase(productName)) return true; // found
        }
        return false;
    }
    //    To get the product to update it
    private Item getProduct(String productName) {
        for (Iterator<Item> i = Items.iterator(); i.hasNext(); ) {
            var currentItems = i.next();
            if (currentItems.getName().equalsIgnoreCase(productName)) return currentItems; // found
        }
        return null;
    }
    //    To Check balance
    private double getBalance(){
        return balance;
    }
    //    --------
    //    public |
    //    --------
    //    To add new product
    private void addProduct(String productName, int productAmount){
        if (containsProduct(productName)) { // if the is exists
            var product = getProduct(productName);
            product.setAmount(product.getAmount()+productAmount);
        } else {
            Items.add(new Item(productName, productAmount));
        }
    }
    //    Method for buying (decrease money amount & add new product
    public void buyProduct(String productName, int productAmount) throws Exception {
        //        Validate the input
        if ((productName == "" || productName == null))
            throw new Exception("Name is not valid !");
        if(productAmount == 0)
            throw new Exception("Amount is 0 !");
    }
    //    To print the clint data
    public void printProducts() {
        if (Items.size() == 0) {
            System.out.println("Agent doesn't have any product");
            return;
        }
        //        If agent has some products
        System.out.println("This clint has: ");
        for (Iterator<Item> i = Items.iterator(); i.hasNext(); ) {
            var currentItem = i.next();
            System.out.println("                "
                    +currentItem.getAmount()
                    +" item"
                    +(currentItem.getAmount()>1?"s":"")
                    + " of "
                    + currentItem.getName());
        }
    }
}
