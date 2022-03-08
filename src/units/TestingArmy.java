package units;

import engine.Player;
import units.*;

public class TestingArmy{



public TestingArmy(String playerName,String currentLocation) {
    Player player = new Player(playerName);
    Army army1 = new Army(currentLocation);
    Army army2 = new Army("Sparta");
    Army army3 = new Army("Rome");

    int i=0;
    while(i <50) {
        Archer a = new Archer(1,60,0.4,0.5,0.6);
        Cavalry c = new Cavalry(1,60,0.4,0.5,0.6);
        Infantry in = new Infantry(1,60,0.4,0.5,0.6);
        a.setParentArmy(army1);
        c.setParentArmy(army1);
        in.setParentArmy(army1);
        army1.getUnits().add(a);
        army1.getUnits().add(c);
        army1.getUnits().add(in);
        i++;

    }
    while(i <50) {
        Archer a = new Archer(1,60,0.4,0.5,0.6);
        Cavalry c = new Cavalry(1,60,0.4,0.5,0.6);
        Infantry in = new Infantry(1,60,0.4,0.5,0.6);
        a.setParentArmy(army2);
        c.setParentArmy(army2);
        in.setParentArmy(army2);
        army2.getUnits().add(a);
        army2.getUnits().add(c);
        army2.getUnits().add(in);

        i++;

    }
    army2.setCurrentStatus(Status.MARCHING);
    army2.setDistancetoTarget(4);
    army2.setTarget("Sparta");

    while(i <50) {
        Archer a = new Archer(1,60,0.4,0.5,0.6);
        Cavalry c = new Cavalry(1,60,0.4,0.5,0.6);
        Infantry in = new Infantry(1,60,0.4,0.5,0.6);
        a.setParentArmy(army3);
        c.setParentArmy(army3);
        in.setParentArmy(army3);
        army3.getUnits().add(a);
        army3.getUnits().add(c);
        army3.getUnits().add(in);
        i++;

    }
    army3.setCurrentStatus(Status.BESIEGING);

    player.getControlledArmies().add(army1);
    player.getControlledArmies().add(army2);
    player.getControlledArmies().add(army3);



}

}