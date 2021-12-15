package Logic.Factory.Behaviours;

import Logic.Factory.Factory;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Receive extends CyclicBehaviour {
//    TODO: Handle if the Clint send another thing except "apple-3"
    private String name=null;
    private int amount=0;
    
    public String getName() {
        return name;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    @Override
    public void action() {
//      TODO: search method to extract product name & amount ✅
//      TODO: Check availability ✅
//      TODO: get sender AID ✅
//      TODO: if available send ACCEPT_PROPOSAL ✅
//      TODO: if not available send REJECT_PROPOSAL ✅
//
        ACLMessage msg = this.getAgent().receive();
        if(msg != null ) {
            extractContent(msg.getContent()); // set requested name & requested amount
            boolean available = Factory.checkAvailability(getName(),getAmount()); //check availability
            var AID = msg.getSender().getLocalName(); // get AID
//            ---------------Print current status------------------
            System.out.println("------------------");
            System.out.println("Product availability: "+available);
            System.out.println("Sender AID: "+AID);
            System.out.println("------------------");
//            --------------------------------------------
            Factory factory = new Factory(); //TODO: handle this in the future, this is a purly new class
            if (available) {
                System.out.println("Before Proposal accept sent ---------");
                factory.acceptProposal(AID);
                System.out.println("After Proposal accept sent ---------");
            }
             else {
                System.out.println("Before Proposal reject sent ---------");
                factory.rejectProposal(AID);
                System.out.println("After Proposal reject sent ---------");
            }
        }
    }
    
    private void extractContent(String content) {
        String needle = "-";//apple-3
        int indexOfNeedle = content.indexOf(needle); //get the index of -
        if (indexOfNeedle != -1) { // check if the index is exists
            setAmount(Integer.parseInt(content.substring(indexOfNeedle+1)));
            setName(content.substring(0,indexOfNeedle));
        } else {
            setName(null);
            setAmount(0);
        }
    }
}
