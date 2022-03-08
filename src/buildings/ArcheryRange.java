package buildings;

import exceptions.BuildingInCoolDownException;
import buildings.Building;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.MinLevelException;
import units.*;


public class ArcheryRange extends MilitaryBuilding {
	
	private int downgradeGoldBack;

	public int getDowngradeGoldBack() {
		return downgradeGoldBack;
	}

	public void setDowngradeGoldBack(int downgradeGoldBack) {
		this.downgradeGoldBack = downgradeGoldBack;
	}

	public ArcheryRange() {
		super(1500, 800, 400);

	}
	
	public void downgrade() throws BuildingInCoolDownException, MinLevelException{
		if (this.isCoolDown() == false) {
			if (this.getLevel() == 2) {
				this.setLevel(1);
				this.setUpgradeCost(800);
				this.setRecruitmentCost(400);
				this.setDowngradeGoldBack(500);
				this.setCoolDown(true);
			} else {
				if (this.getLevel() == 3) {
					this.setLevel(2);
					this.setUpgradeCost(700);
					this.setRecruitmentCost(450);
					this.setDowngradeGoldBack(300);
					this.setCoolDown(true);
				}
				else{
					throw new MinLevelException();
				}
			}
		} 
		else {
			throw new BuildingInCoolDownException();
		}
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		if (this.isCoolDown() == false) {
			if (this.getLevel() == 1) {
				this.setLevel(2);
				this.setUpgradeCost(700);
				this.setRecruitmentCost(450);
				this.setCoolDown(true);
			} else {
				if (this.getLevel() == 2) {
					this.setLevel(3);
					this.setUpgradeCost(0);
					this.setRecruitmentCost(500);
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
					return new Archer (1,60,0.4,0.5,0.6);
				}
				else if (this.getLevel()==2) {
					return new Archer (2,60,0.4,0.5,0.6);
			}
				else {
					return new Archer (3,70,0.5,0.6,0.7);
					}
			}
		}
		else {
			throw new BuildingInCoolDownException();
		}
	}

}
