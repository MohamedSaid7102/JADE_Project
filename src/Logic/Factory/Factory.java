package Logic.Factory;

import Logic.Factory.Behaviours.Offer;
import Logic.Factory.Behaviours.Receive;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Iterator;

public class Factory extends Agent {
//    ArrayList<AgentTries> agentsTries = new ArrayList<AgentTries>();
//
//    public void increaseAgentTries(String agentName) {
//        agentsTries.add(new AgentTries(agentName,1));
//    }
//
//    private class AgentTries {
//        private String agentName;
//        private byte agentsTries;
//
//        public AgentTries(String agentName, int agentsTries) {
//            this.agentName = agentName;
//            this.agentsTries = (byte)agentsTries;
//        }
//
//        public String getName() {
//            return agentName;
//        }
//
//        public byte getTries() {
//            return agentsTries;
//        }
//    }
//
//    public boolean hasMoreTries(String agentName) {
//        for (Iterator i = agentsTries.iterator(); i.hasNext(); ) {
//            var currentAgent = (AgentTries) i.next();
//
//            if (currentAgent.getName().equalsIgnoreCase(agentName)) {
//                if(currentAgent.getTries() >= 2) return false;
//            }
//        }
//        return true;
//    }
    class Product {
        String name;
        int amount;
    
        public Product(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
        
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
        
        public void setAmount(int amount) {
            this.amount = amount;
        }
    
    public int getAmount() {
            return amount;
        }
    }
    
    static ArrayList<Product> products = new ArrayList<>();
    
    @Override
    protected void setup() {
        System.out.println("Hi, This is Factory here");
        initializeProducts();
        this.addBehaviour(new Offer(this, 4000));
        this.addBehaviour(new Receive());
    }
    
    @Override
    protected void takeDown() {
        System.out.println("قتلتني يا مجرم !");
    }
    
//    ---------
//    Methods |
//    ---------
    
    // TODO: Handle if we add apple again
    private void initializeProducts() {
        this.products.add(new Product("apple",4));
        this.products.add(new Product("mazen",1));
        this.products.add(new Product("teema",1));
        this.products.add(new Product("orange",40));
    }
    
    public static void printProducts() {
        if (products.size() == 0) {
            System.out.println("Agent doesn't have any product");
            return;
        }
        //        If agent has some products
        System.out.println("This clint has: ");
        for (Iterator<Product> i = products.iterator(); i.hasNext(); ) {
            var currentItem = i.next();
            System.out.println("                "
                    + currentItem.getAmount()
                    + " item"
                    + (currentItem.getAmount() > 1 ? "s" : "")
                    + " of "
                    + currentItem.getName());
        }
        System.out.println("----------------------");
    }
    
    public static boolean checkAvailability(String requestedProductName, int requestedAmount) {
        for(Product p : products)
            if(
                p.getName().equalsIgnoreCase(requestedProductName) // prodcut exists
                &&
                p.getAmount() >= requestedAmount
            ) {
                p.setAmount(p.getAmount()-requestedAmount);
                return true;
            }
        
            return false;
    }
    
    public void acceptProposal(String AID) {
        ACLMessage accept = new ACLMessage();
        accept.addReceiver(new AID(AID, false));
        accept.setContent("T-"+AID);
        accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
        System.out.println("Durring sending Accept to AID: "+AID);
        this.send(accept);
    }
    
    public void rejectProposal(String AID) {
        ACLMessage reject = new ACLMessage();
        reject.addReceiver(new AID(AID, false));
        reject.setContent("F-"+AID); // and in the clint if true so decrese deposite & if false printOH, Thanks - maybe
        // next time
        reject.setPerformative(ACLMessage.REJECT_PROPOSAL);
        System.out.println("Durring sending Reject to AID: "+AID);
        this.send(reject);
    }
}
