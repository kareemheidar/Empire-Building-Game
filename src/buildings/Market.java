package buildings;

import exceptions.BuildingInCoolDownException;
import engine.Player;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	public Market() {
		super(1500, 700);
	}
	
	public void upgrade() throws BuildingInCoolDownException , MaxLevelException{
		if (this.isCoolDown()==false) {
			if (this.getLevel() == 1) {
				this.setLevel(2);
				this.setUpgradeCost(1000);
				this.setCoolDown(true);
			}
			else if (this.getLevel() == 2) {
					this.setLevel(3);
					this.setUpgradeCost(0);
					this.setCoolDown(true);
				}
			else{
				throw new MaxLevelException();
			}	
		}
		else {
			throw new BuildingInCoolDownException();
		}

	}

	public int harvest() {
		if (this.getLevel()==1) {
			return 1000;
		}
		else if (this.getLevel()==2) {
			return 1500;
		}
		else {
			return 2000;
		}
		
	}
		

}
