package units;

import units.*;
import exceptions.FriendlyFireException;
import engine.*;

public class Archer extends Unit {

	public Archer(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	public void attack(Unit target) throws FriendlyFireException{
		int level = this.getLevel();
		int tmp;
		if (target.getCurrentSoldierCount()<=0) {
			  target.getParentArmy().handleAttackedUnit(target);
			  return;
		 }
		if (!this.getParentArmy().equals(target.getParentArmy())){
		if(target instanceof Archer) {
			switch(level) {
			case 1:
				tmp = (int)( 0.3*this.getCurrentSoldierCount());
				if (tmp >= ((Archer)target).getCurrentSoldierCount()) {
					((Archer)target).setCurrentSoldierCount(0);
					((Archer)target).getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Archer)target).setCurrentSoldierCount(((Archer)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 2:
				tmp = (int)( 0.4*this.getCurrentSoldierCount());
				if (tmp >= ((Archer)target).getCurrentSoldierCount()) {
					((Archer)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Archer)target).setCurrentSoldierCount(((Archer)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 3:
				tmp = (int)( 0.5*this.getCurrentSoldierCount());
				if (tmp >= ((Archer)target).getCurrentSoldierCount()) {
					((Archer)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Archer)target).setCurrentSoldierCount(((Archer)target).getCurrentSoldierCount() - tmp);
				}
				break;
			}
		}
		else if(target instanceof Infantry) {
			switch(level) {
			case 1:
				tmp = (int)( 0.2*this.getCurrentSoldierCount());
				if (tmp >= ((Infantry)target).getCurrentSoldierCount()) {
					((Infantry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Infantry)target).setCurrentSoldierCount(((Infantry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 2:
				tmp = (int)( 0.3*this.getCurrentSoldierCount());
				if (tmp >= ((Infantry)target).getCurrentSoldierCount()) {
					((Infantry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Infantry)target).setCurrentSoldierCount(((Infantry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 3:
				tmp = (int)( 0.4*this.getCurrentSoldierCount());
				if (tmp >= ((Infantry)target).getCurrentSoldierCount()) {
					((Infantry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Infantry)target).setCurrentSoldierCount(((Infantry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			}
			
		}
		else if(target instanceof Cavalry) {
			switch(level) {
			case 1:
				tmp = (int)( 0.1*this.getCurrentSoldierCount());
				if (tmp >= ((Cavalry)target).getCurrentSoldierCount()) {
					((Cavalry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Cavalry)target).setCurrentSoldierCount(((Cavalry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 2:
				tmp = (int)( 0.1*this.getCurrentSoldierCount());
				if (tmp >= ((Cavalry)target).getCurrentSoldierCount()) {
					((Cavalry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Cavalry)target).setCurrentSoldierCount(((Cavalry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			case 3:
				tmp = (int)( 0.2*this.getCurrentSoldierCount());
				if (tmp >= ((Cavalry)target).getCurrentSoldierCount()) {
					((Cavalry)target).setCurrentSoldierCount(0);
					target.getParentArmy().handleAttackedUnit(target);
				}
				else {
				((Cavalry)target).setCurrentSoldierCount(((Cavalry)target).getCurrentSoldierCount() - tmp);
				}
				break;
			}
		}
		}
		else {
			throw new FriendlyFireException();
		}
	}
}
