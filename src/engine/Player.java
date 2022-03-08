package engine;

import java.util.ArrayList;
import buildings.*;
import units.*;
import exceptions.*;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;
	private int downgradeLimit;

	public int getDowngradeLimit() {
		return downgradeLimit;
	}

	public void setDowngradeLimit(int downgradeLimit) {
		this.downgradeLimit = downgradeLimit;
	}

	public Player(String name) {
		treasury = 5000.0;
		food = 0.0;
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
		this.downgradeLimit = 2;
	}
	
	public void downgradeBuilding(ArcheryRange b) throws BuildingInCoolDownException,
	MinLevelException, DowngradeLimitExeption{
		if(this.downgradeLimit > 0) {
			b.downgrade();
			this.treasury = this.treasury + b.getDowngradeGoldBack();
			
			this.downgradeLimit = this.downgradeLimit - 1;
		}
		else {
			throw new DowngradeLimitExeption();
		}
	}
	

	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
	
	private boolean ContainArchery(ArrayList<MilitaryBuilding> array) {
		boolean flag = true;
		for(MilitaryBuilding b : array) {
			if(b instanceof ArcheryRange)
				flag = false;
		}
		return flag;
		
	}
	private boolean ContainBarrack(ArrayList<MilitaryBuilding> array) {
		boolean flag = true;
		for(MilitaryBuilding b : array) {
			if(b instanceof Barracks)
				flag = false;
		}
		return flag;
		
	}
	private boolean ContainStable(ArrayList<MilitaryBuilding> array) {
		boolean flag = true;
		for(MilitaryBuilding b : array) {
			if(b instanceof Stable)
				flag = false;
		}
		return flag;
		
	}
	private boolean ContainFarm(ArrayList<EconomicBuilding> array) {
		boolean flag = true;
		for(EconomicBuilding b : array) {
			if(b instanceof Farm)
				flag = false;
		}
		return flag;
		
	}
	private boolean ContainMarket(ArrayList<EconomicBuilding> array) {
		boolean flag = true;
		for(EconomicBuilding b : array) {
			if(b instanceof Market)
				flag = false;
		}
		return flag;
		
	}
	public void recruitUnit(String type, String cityName)
            throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
        for (City c : this.getControlledCities()) {
            if (c.getName().equals(cityName)) {
                if (type.equals("Archer")) {
                    for (MilitaryBuilding b : c.getMilitaryBuildings()) {
                        if (b.isCoolDown() == true) {
                            throw new BuildingInCoolDownException();
                        } else if (((ArcheryRange)b).getUpgradeCost() > this.getTreasury()) {
                            throw new NotEnoughGoldException();
                        }

                        else if (b.getCurrentRecruit() == b.getMaxRecruit()) {
                            throw new MaxRecruitedException();
                        } else {
                            if (b instanceof ArcheryRange) {
                                Unit u = ((ArcheryRange) b).recruit();
                                Army a = c.getDefendingArmy();
                                a.getUnits().add(u);
                                u.setParentArmy(c.getDefendingArmy());
                                this.setTreasury(this.getTreasury() - ((ArcheryRange) b).getRecruitmentCost());
                            }
                        }
                    }

                }

             else if (type.equals("Infantry")) {
                for (MilitaryBuilding b : c.getMilitaryBuildings()) {
                    if (b.isCoolDown() == true) {
                        throw new BuildingInCoolDownException();
                    } else if (b.getUpgradeCost() > this.getTreasury()) {
                        throw new NotEnoughGoldException();
                    }

                    else if (b.getCurrentRecruit() == b.getMaxRecruit()) {
                        throw new MaxRecruitedException();
                    } else {
                        if (b instanceof Barracks) {
                            Unit u = ((Barracks) b).recruit();
                            Army a = c.getDefendingArmy();
                            a.getUnits().add(u);
                            u.setParentArmy(c.getDefendingArmy());
                            this.setTreasury(this.getTreasury() - ((Barracks) b).getRecruitmentCost());

                        }
                    }
                }
                }

             else {
                 for (MilitaryBuilding b : c.getMilitaryBuildings()) {
                        if (b.isCoolDown() == true) {
                            throw new BuildingInCoolDownException();
                        } else if (b.getUpgradeCost() > this.getTreasury()) {
                            throw new NotEnoughGoldException();
                        }

                        else if (b.getCurrentRecruit() == b.getMaxRecruit()) {
                            throw new MaxRecruitedException();
                        } else {
                            if (b instanceof Stable) {
                                Unit u = ((Stable) b).recruit();
                                Army a = c.getDefendingArmy();
                                a.getUnits().add(u);
                                u.setParentArmy(c.getDefendingArmy());
                                this.setTreasury(this.getTreasury() - ((Stable) b).getRecruitmentCost());

                            }
                        }
                    }
                }

            }
        }
	
	}
	
	
	
	
	public void build(String type,String cityName) throws NotEnoughGoldException{
		for(City c : this.getControlledCities()) {
			if(c.getName().equals(cityName)) {
				if(type.equals("ArcheryRange") && ContainArchery(c.getMilitaryBuildings())) {
					ArcheryRange t = new ArcheryRange();
					if (t.getCost() > this.getTreasury()) {
                        throw new NotEnoughGoldException();
                    }
					else {
					t.setCoolDown(true);
					c.getMilitaryBuildings().add(t);
					this.setTreasury(this.getTreasury()-1500);
					}
					}
				else if(type.equals("Barracks") && ContainBarrack(c.getMilitaryBuildings())) {
					Barracks t = new Barracks();
					if (t.getCost() > this.getTreasury()) {
                        throw new NotEnoughGoldException();
                    }
					else {
					t.setCoolDown(true);
					c.getMilitaryBuildings().add(t);
					this.setTreasury(this.getTreasury()-2000);
					}
				}
				else if(type.equals("Stable") && ContainStable(c.getMilitaryBuildings())) {
					Stable t = new Stable();
					if (t.getCost() > this.getTreasury()) {
                        throw new NotEnoughGoldException();
					}
					else {
					t.setCoolDown(true);
					c.getMilitaryBuildings().add(t);
					this.setTreasury(this.getTreasury()-2500);
					}
				}
				else if(type.equals("Farm") && ContainFarm(c.getEconomicalBuildings())) {
						Farm t = new Farm();
						if (t.getCost() > this.getTreasury()) {
	                        throw new NotEnoughGoldException();
	                    }
						else {
						t.setCoolDown(true);
						c.getEconomicalBuildings().add(t);
						this.setTreasury(this.getTreasury()-1000);
					}
				}
				else if(type.equals("Market") && ContainMarket(c.getEconomicalBuildings())) {
						Market t = new Market();
						if (t.getCost() > this.getTreasury()) {
	                        throw new NotEnoughGoldException();
	                    }
						else {
						t.setCoolDown(true);
						c.getEconomicalBuildings().add(t);
						this.setTreasury(this.getTreasury()-1500);
					}
				}
		}
	}
}
	
	
	public void upgradeBuilding(Building b) throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
        if (b.getLevel() == 3) {
            throw new MaxLevelException();
        } else if (b.getUpgradeCost() > this.treasury) {
                throw new NotEnoughGoldException();
            } else if (b.isCoolDown()) {
                    throw new BuildingInCoolDownException();
                } else {
                	double newMoney = this.getTreasury() - b.getUpgradeCost();
                	b.upgrade();
                    this.setTreasury(newMoney);
                }
                	
            }
	public void initiateArmy(City city, Unit unit) {
        Army newArmy = new Army(city.getName());
        newArmy.getUnits().add(unit);
        city.getDefendingArmy().getUnits().remove(unit);
        unit.setParentArmy(newArmy);
        this.controlledArmies.add(newArmy);
    }
	public void laySiege(Army army,City city) throws TargetNotReachedException,
    FriendlyCityException{
		for (City c : this.getControlledCities()) {
			if (army.getCurrentLocation().equals(c.getName())) {
	            throw new FriendlyCityException();
	        }
		}
       if (!(army.getCurrentLocation().equals(city.getName()))) {
            throw new TargetNotReachedException();
        }
        else {
        	city.setTurnsUnderSiege(0);
            army.setCurrentStatus(Status.BESIEGING);
            city.setUnderSiege(true);
        }
    }
}
    

