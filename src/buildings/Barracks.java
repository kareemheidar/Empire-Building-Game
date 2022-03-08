package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.*;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		if (this.isCoolDown() == false) {
			if (this.getLevel() == 1) {
				this.setLevel(2);
				this.setUpgradeCost(1500);
				this.setRecruitmentCost(550);
				this.setCoolDown(true);
			} else {
				if (this.getLevel() == 2) {
					this.setLevel(3);
					this.setUpgradeCost(0);
					this.setRecruitmentCost(600);
					this.setCoolDown(true);
				}
				else{
					throw new MaxLevelException();
				}
			}
		} 
		else {
			throw new BuildingInCoolDownException();
		}

	}
	
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if (this.isCoolDown()==false) {
			if (this.getCurrentRecruit()>=this.getMaxRecruit()) {
				throw new MaxRecruitedException();
			}
			else {
				this.setCurrentRecruit(this.getCurrentRecruit()+1);
				if (this.getLevel()==1) {
					return new Infantry (1,50,0.5,0.6,0.7);
				}
				else if (this.getLevel()==2) {
					return new Infantry (2,50,0.5,0.6,0.7);
			}
				else {
					return new Infantry (3,60,0.6,0.7,0.8);
					}
				}
		}
		else {
			throw new BuildingInCoolDownException();
		}
	}

}
