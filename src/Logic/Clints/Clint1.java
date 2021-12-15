package Logic.Clints;

import Logic.Clints.Behaviours.Receive;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Clint1 extends Agent {
   private static double deposit = 1000.0;
    
    public void setDeposit(double newDeposit) {
        deposit = newDeposit;
    }
    
    public double getDeposit() {
        return deposit;
    }
    //    TODO: add a CyclycBehaviour to recive ACCEPT or REJECT PROPOSAL
//    TODO: if ACCEPT_PROPOSAL -> deposit -= 10$;
//    TODO: if REJECT_PROPOSAL -> print "OH, Thanks - maybe next time"
    
    @Override
    protected void setup() {
//        request("apple",3);
        this.addBehaviour(new Receive());
    }
    
    public void request(String requestName, int amount) {
        ACLMessage requestProduct = new ACLMessage();
        requestProduct.addReceiver(new AID("Factory", false));
        requestProduct.setContent(requestName+"-"+amount);// apple-3
        requestProduct.setPerformative(ACLMessage.REQUEST);
        this.send(requestProduct);
    }
}
