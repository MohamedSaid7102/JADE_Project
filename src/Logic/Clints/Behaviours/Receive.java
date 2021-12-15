package Logic.Clints.Behaviours;

import Logic.Clints.Clint1;
import Logic.Clints.Clint1;
import Logic.Clints.Clint2;
import Logic.Clints.Clint3;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Receive extends Behaviour {
    boolean respondeRecived = false;
    @Override
    public void action() {
//        System.out.println("We are in action mode for the clint");
        ACLMessage msg = this.getAgent().receive();
        if(msg != null ) {
            String res = msg.getContent().substring(0,1);
            String AID = msg.getContent().substring(2);
            System.out.println("We are in the agent res is: "+res+" and AID is: "+AID);
            if(res.equalsIgnoreCase("T")) {
                Clint1 c1 = new Clint1();
                Clint2 c2 = new Clint2();
                Clint3 c3 = new Clint3();
                if(AID.equalsIgnoreCase("Clint1")) c1.setDeposit(c1.getDeposit()-10);
//                if(AID.equalsIgnoreCase("Clint2")) c2.itemBuyed();
//                if(AID.equalsIgnoreCase("Clint3")) c3.itemBuyed();

//                --------print status-------------
                System.out.println("Deposit for "+AID+" is: "+c1.getDeposit());
                
            } else {
                System.out.println("OH, Thanks - maybe next time");
            }
            respondeRecived = true; // to end cyclic
        }
    }
    
    public Class getClass(String className) {
        Class<?> act = null;
        try {
            act = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return act;
    }
    
    @Override
    public boolean done() {
        return respondeRecived;
    }
}
