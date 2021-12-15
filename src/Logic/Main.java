package Logic;

import Logic.Factory.Factory;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {
    
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        Profile myProfile = new ProfileImpl("localhost",1000,"NewPlatform");
        ContainerController controller = runtime.createMainContainer(myProfile);
        try {
            AgentController rma = controller.createNewAgent("rma","jade.tools.rma.rma", null);
            rma.start();
            
//            New factory agent
            AgentController Factory = controller.createNewAgent("factory","Logic.Factory.Factory", null);
            Factory.start();
            
//            New Clint1 agent
            AgentController Clint1 = controller.createNewAgent("clint1","Logic.Clints.Clint1", null);
            Clint1.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        
    }
}
