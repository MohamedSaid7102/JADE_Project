package Logic.Factory.Behaviours;

import Logic.Factory.Factory;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Offer extends TickerBehaviour {
    public Offer(Agent a, long period) {
        super(a, period);
    }
    
    @Override
    protected void onTick() {
        Factory.printProducts();
    }
}
