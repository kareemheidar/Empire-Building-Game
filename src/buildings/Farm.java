package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);

	}
	
public void upgrade() throws BuildingInCoolDownException , MaxLevelException{
	if (this.isCoolDown()==false) {
		if (this.getLevel() == 1) {
			this.setLevel(2);
			this.setUpgradeCost(700);
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
		return 500;
	}
	else if (this.getLevel()==2) {
		return 700;
	}
	else {
		return 1000;
	}
	
}

}
