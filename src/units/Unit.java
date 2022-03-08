package units;

import exceptions.FriendlyFireException;

public abstract class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;

	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierCount;

	}
	
	public Army getParentArmy() {
		return parentArmy;
	}
	public String toString() {
		String s = "";
		if(this instanceof Archer) {
			s = "Archer, Level: "+level+", Soldier Count: "+currentSoldierCount+", Max: "+maxSoldierCount;
		}
		if(this instanceof Infantry) {
			s = "Infantry, Level: "+level+", Soldier Count: "+currentSoldierCount+", Max: "+maxSoldierCount;
		}
		if(this instanceof Cavalry) {
			s = "Cavalry, Level: "+level+", Soldier Count: "+currentSoldierCount+", Max: "+maxSoldierCount;
		}
		return s;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	}


	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
	
	public abstract void attack(Unit target) throws FriendlyFireException;
}
