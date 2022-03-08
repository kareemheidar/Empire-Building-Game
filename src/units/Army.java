package units;

import java.util.ArrayList;

import units.Unit;
import exceptions.MaxCapacityException;

/**
 * @author mohammad.hussein
 *
 */
public class Army{
    private Status currentStatus;
    private ArrayList<Unit> units;
    private int distancetoTarget;
    private String target;
    private String currentLocation;
    @SuppressWarnings("unused")
    private final int maxToHold=10;

    public Army(String currentLocation) {
        this.currentLocation=currentLocation;
        currentStatus=Status.IDLE;
        units=new ArrayList<Unit>();
        distancetoTarget=-1;
        target="";
    }
    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public int getDistancetoTarget() {
        return distancetoTarget;
    }

    public void setDistancetoTarget(int distancetoTarget) {
        this.distancetoTarget = distancetoTarget;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    public int getMaxToHold() {
        return maxToHold;
    }
    
    public void relocateUnit(Unit unit) throws MaxCapacityException{
        if (units.size()==this.getMaxToHold()) {
            throw new MaxCapacityException();
        }
        else {
        unit.getParentArmy().units.remove(unit);
        this.units.add(unit);
        unit.setParentArmy(this);
        }
    }
    
    public void handleAttackedUnit(Unit u){
        if (u.getCurrentSoldierCount()<=0) {
            this.units.remove(u);
            u.setParentArmy(null);
        }
    }
    
    public double foodNeeded() {
        double totalFood = 0;
        for(Unit u : this.units) {
            double food;
            if(this.getCurrentStatus().equals(Status.IDLE)) {
                food = u.getCurrentSoldierCount()*u.getIdleUpkeep();
                totalFood += food;
            }
            else if(this.getCurrentStatus().equals(Status.MARCHING)){
                food = u.getCurrentSoldierCount()*u.getMarchingUpkeep();
                totalFood += food;
            }
            else{
                food = u.getCurrentSoldierCount()*u.getSiegeUpkeep();
                totalFood += food;
            }    
        }
        return totalFood;
    }
    
    
    
    
}