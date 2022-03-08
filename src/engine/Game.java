package engine;

import java.io.BufferedReader;
import java.util.Random;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");

		}
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {

				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			resultArmy.getUnits().add(u);
			u.setParentArmy(resultArmy);
			currentLine = br.readLine();
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	public void targetCity(Army army, String targetName) {
		String armyCityName = army.getCurrentLocation();
		army.setTarget(targetName);
		if (!army.getCurrentStatus().equals(Status.MARCHING)) {
			for (Distance d : this.distances) {
				if (d.getFrom().equals(armyCityName) && d.getTo().equals(targetName)) {
					army.setDistancetoTarget(d.getDistance());
				} else if (d.getTo().equals(armyCityName) && d.getFrom().equals(targetName)) {
					army.setDistancetoTarget(d.getDistance());
				}
			}
		}
		army.setCurrentStatus(Status.MARCHING);
		return;
	}

	public void endTurn() {
		currentTurnCount++;
		controlledBuildings();
		controlledArmies();
		controlledCities();
	}

	private void controlledBuildings() {
		double gainM = 0;
		double gainF = 0; 
		for (City c : player.getControlledCities()) {
			for (EconomicBuilding eb : c.getEconomicalBuildings()) {
				eb.setCoolDown(false);
				if(eb instanceof Market) {
					gainM += ((Market)eb).harvest();
				}
				else {
					gainF += ((Farm)eb).harvest();
				}
			}
			for (MilitaryBuilding mb : c.getMilitaryBuildings()) {
				mb.setCoolDown(false);
				mb.setCurrentRecruit(0);
			}
		}
		player.setTreasury(player.getTreasury() + gainM);
		player.setFood(player.getFood()+gainF);
	}

	private void controlledArmies() {
		double totalFood = 0;
		for (Army a : player.getControlledArmies()) {
			if (!a.getTarget().equals("")) {
				if (a.getDistancetoTarget() > 0) {
					a.setDistancetoTarget(a.getDistancetoTarget() - 1);
				}
				if (a.getDistancetoTarget() == 0) {
					a.setCurrentLocation(a.getTarget());
					a.setTarget("");
				}
			}
			totalFood += a.foodNeeded();
		}
		double difference = this.player.getFood()-totalFood;
		if (difference<=0) {
			player.setFood(0);
			for (Army a : player.getControlledArmies()) {
				for (Unit u : a.getUnits()){
					u.setCurrentSoldierCount((int)(u.getCurrentSoldierCount()-(u.getCurrentSoldierCount()*0.1)));
				}
			}
		}
		else {
		player.setFood(difference);
		}
	}

	private void controlledCities() {
		for (City c : this.availableCities) {
			if (c.isUnderSiege() && (c.getTurnsUnderSiege() < 3)) {
				c.setTurnsUnderSiege(c.getTurnsUnderSiege() + 1);
				for (Unit u : c.getDefendingArmy().getUnits()) {
					u.setCurrentSoldierCount((int) (u.getCurrentSoldierCount() - u.getCurrentSoldierCount() * 0.1));
				}
			}
		}
	}

	public void occupy(Army a, String cityName) {
		for (City c : this.availableCities) {
			if (c.getName().equals(cityName)) {
				player.getControlledCities().add(c);
				c.setDefendingArmy(a);
				c.setUnderSiege(false);
				c.setTurnsUnderSiege(-1);
			}
		}
	}

	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
		Random r = new Random();
		while (attacker.getUnits().size() != 0 && defender.getUnits().size() != 0) {
			Unit a1 = attacker.getUnits().get((int) (r.nextInt(attacker.getUnits().size())));
			Unit d1 = defender.getUnits().get((int) (r.nextInt(defender.getUnits().size())));
					a1.attack(d1);
					if (defender.getUnits().size()==0) {
						this.occupy(attacker,defender.getCurrentLocation());
						break;
					}
					
			Unit a2 = defender.getUnits().get((int) (r.nextInt(defender.getUnits().size())));
			Unit d2 = attacker.getUnits().get((int) (r.nextInt(attacker.getUnits().size())));
					a2.attack(d2);
					if (attacker.getUnits().size()==0) {
						break;
					}
					
		}
	}

	public boolean isGameOver() {
		if (this.currentTurnCount > 50) {
			return true;
		} else if (player.getControlledCities().size() == this.availableCities.size()) {
			return true;
		} else {
			return false;
		}
	}

}
