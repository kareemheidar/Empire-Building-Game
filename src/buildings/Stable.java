package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.*;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);

	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		if (this.isCoolDown() == false) {
			if (this.getLevel() == 1) {
				this.setLevel(2);
				this.setUpgradeCost(2000);
				this.setRecruitmentCost(650);
				this.setCoolDown(true);
			} else {
				if (this.getLevel() == 2) {
					this.setLevel(3);
					this.setUpgradeCost(0);
					this.setRecruitmentCost(700);
					this.setCoolDown(true);
				}
				else{
					
					
					
					
					
					
					
					
					
					
					throw new MaxLevelException();
				}
			}
		} else {
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
					return new Cavalry (1,40,0.6,0.7,0.75);
				}
				else if (this.getLevel()==2) {
					return new Cavalry (2,40,0.6,0.7,0.75);
			}
				else {
					return new Cavalry (3,60,0.7,0.8,0.9);
					}
				}
			
		}
		else {
			throw new BuildingInCoolDownException();
		}
	}
}
