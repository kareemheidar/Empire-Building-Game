package gui;

import java.awt.BorderLayout;
import gui.kickOff;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import engine.Player;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.TestingArmy;
import units.Unit;

public class shit {
	
	public static void getMarchingArmy(Player p, Game g) {
		frameX jf = new frameX();
		jf.setTitle("Marching Army");
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(900, 900));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		JPanel jp = new JPanel();
		jf.setSize(900, 900);
		jf.setLayout(new BorderLayout());
		// jp.setLayout(new GridLayout(10, 1));
		jp.setBounds(50, 10, 900, 800);
		jp.setBackground(new Color(0xFFCC00));
		jp.setPreferredSize(new Dimension(1000, 900));
		JTextArea tef = new JTextArea();
		tef.setBackground(new Color(0xFFFFFF));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(800, 800));
		tef.setEditable(false);
		String top = "Type      Current count     Level     Max Count    Targeting     Distance to target \n";
		tef.setText(top + "\n");
		int i = 1;
		for (Army a : p.getControlledArmies()) {
			int arc = 0;
			int inf = 0;
			int cav = 0;
			City siegedCity = null;
			if (!a.getCurrentStatus().equals(Status.IDLE) && !a.getCurrentStatus().equals(Status.BESIEGING)) {
			String total = "";

			if (!a.getCurrentStatus().equals(Status.IDLE)) {
				if (a.getCurrentStatus().equals(Status.MARCHING)) {
					for (Unit u : a.getUnits()) {
						String str = "";

						if (u instanceof Archer) {
							arc = u.getCurrentSoldierCount();
							str = "Archer          " + arc + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getTarget() + "              "
									+ a.getDistancetoTarget() + " Miles";
						}
						if (u instanceof Infantry) {
							inf = u.getCurrentSoldierCount();
							str = "Infantry        " + inf + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getTarget() + "              "
									+ a.getDistancetoTarget() + " Miles";
						}
						if (u instanceof Cavalry) {
							cav = u.getCurrentSoldierCount();
							str = "Cavalry         " + cav + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getTarget() + "              "
									+ a.getDistancetoTarget() + " Miles";
						}
						total += str + "\n";

					}

				} else if (a.getCurrentStatus().equals(Status.BESIEGING)) {

					for (City c : g.getAvailableCities()) {

						if (c.getName().equals(a.getCurrentLocation())) {
							siegedCity = c;
						}
					}

					for (Unit u : a.getUnits()) {
						String str = "";

						if (u instanceof Archer) {
							arc = u.getCurrentSoldierCount();
							str = "Archer          " + arc + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
									+ "              " + siegedCity.getTurnsUnderSiege();
						}
						if (u instanceof Infantry) {
							inf = u.getCurrentSoldierCount();
							str = "Infantry        " + inf + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
									+ "              " + siegedCity.getTurnsUnderSiege();
						}
						if (u instanceof Cavalry) {
							cav = u.getCurrentSoldierCount();
							str = "Cavalry         " + cav + " units               " + u.getLevel() + "           "
									+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
									+ "              " + siegedCity.getTurnsUnderSiege();
						}
						total += str + "\n";

					}


				}

			}
			String tmp = "" + total + "\n";
			tef.setText(tef.getText() + tmp);

		}}
		jp.add(tef);

		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont, BorderLayout.CENTER);
		jf.setVisible(true);
	}

	public static void getSiegingArmy(Player p, Game g) {
		frameX jf = new frameX();
		jf.setTitle("Besieging Army");
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(900, 900));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		JPanel jp = new JPanel();
		jf.setSize(900, 900);
		jf.setLayout(new BorderLayout());
		// jp.setLayout(new GridLayout(10, 1));
		jp.setBounds(50, 10, 800, 800);
		jp.setBackground(new Color(0xFFCC00));
		;
		jp.setPreferredSize(new Dimension(100, 900));
		JTextArea tef = new JTextArea();
		tef.setBackground(new Color(0xFFFFFF));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(800, 800));
		tef.setEditable(false);
		String top = "Type      Current count     Level     Max Count    Sieged city     Turns under siege \n";
		tef.setText(top + "\n");
		for (Army a : p.getControlledArmies()) {
			int arc = 0;
			int inf = 0;
			int cav = 0;
			City siegedCity = null;
			if (!a.getCurrentStatus().equals(Status.IDLE) && !a.getCurrentStatus().equals(Status.MARCHING)) {
				String total = "";
				if (!a.getCurrentStatus().equals(Status.IDLE)) {
					if (a.getCurrentStatus().equals(Status.BESIEGING)) {

						for (City c : g.getAvailableCities()) {

							if (c.getName().equals(a.getCurrentLocation())) {
								siegedCity = c;
							}
						}

						for (Unit u : a.getUnits()) {
							String str = "";

							if (u instanceof Archer) {
								arc = u.getCurrentSoldierCount();
								str = "Archer          " + arc + " units               " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
										+ "              " + siegedCity.getTurnsUnderSiege();
							}
							if (u instanceof Infantry) {
								inf = u.getCurrentSoldierCount();
								str = "Infantry        " + inf + " units               " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
										+ "              " + siegedCity.getTurnsUnderSiege();
							}
							if (u instanceof Cavalry) {
								cav = u.getCurrentSoldierCount();
								str = "Cavalry         " + cav + " units               " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units       " + a.getCurrentLocation()
										+ "              " + siegedCity.getTurnsUnderSiege();
							}
							total += str + "\n";

						}

					}
				}
				String tmp = "" + total + "\n";
				tef.setText(tef.getText() + tmp);
			}
		}
		jp.add(tef);

		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont, BorderLayout.CENTER);
		jf.setVisible(true);

	}

	public static void getIdleArmy(Player p) {
		frameX jf = new frameX();
		jf.setTitle("Idle Army");
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(900, 900));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		JPanel jp = new JPanel();
		jf.setSize(900, 900);
		jf.setLayout(new BorderLayout());
		// jp.setLayout(new GridLayout(10, 1));
		jp.setBounds(100, 10, 800, 800);
		jp.setBackground(new Color(0xFFCC00));
		;
		jp.setPreferredSize(new Dimension(1000, 900));
		JTextArea tef = new JTextArea();
		tef.setBackground(new Color(0xFFFFFF));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(400, 800));
		tef.setEditable(false);
		String top = "Type      Current count     Level     Max Count \n";
		tef.setText(top + "\n");
		int khara = 1;
		for (Army a : p.getControlledArmies()) {
			int arc = 0;
			int inf = 0;
			int cav = 0;
			String total = "";
			
			if (a.getCurrentStatus().equals(Status.IDLE)) {
				for (Unit u : a.getUnits()) {
					String str = "";
					if (u instanceof Archer) {
						arc = u.getCurrentSoldierCount();
						str = "Archer        " + arc + " units               " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Infantry) {
						inf = u.getCurrentSoldierCount();
						str = "Infantry      " + inf + " units              " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Cavalry) {
						cav = u.getCurrentSoldierCount();
						str = "Cavalry       " + cav + " units               " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					total += str + "\n";
				}



			}
			String tmp = "#"+khara+"\n" + total + "\n";
			khara++;
			tef.setText(tef.getText() + tmp);

		}
		jp.add(tef);

		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont, BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	
	public static void cityInfo(String s, Game g) {
		frameX jf = new frameX();
		JPanel cont = new JPanel();
		JPanel jp = new JPanel();
		jf.setTitle(s+" Info");
		JComboBox recruit = new JComboBox();
		JComboBox upgrade = new JComboBox();
		JTextArea tef = new JTextArea();
		JTextArea tof = new JTextArea();
		JTextArea tex = new JTextArea();
		String tmp = "";
		String tmp2 = "";
		String tmp3 = "\n \n \n \n";
		String tmpx = "";
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(1300,1000));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		jf.setSize(1300, 1000);
		jf.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jp.setBounds(10, 5, 1100, 900);
		jp.setBackground(new Color(0xFFCC00));;
//		jp.setPreferredSize(new Dimension(1000,900));
		upgrade.addActionListener(jf);
		upgrade.setPreferredSize(new Dimension(200,30));
		recruit.addActionListener(jf);
		recruit.setPreferredSize(new Dimension(300,30));
		tef.setBackground(new Color(0xFFCC00));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(200,200));
		tef.setEditable(false);
		tof.setBackground(Color.cyan);
		tof.setFont(new Font("Verdana", Font.BOLD, 16));
		tof.setPreferredSize(new Dimension(500,700));
		tof.setEditable(false);
		tex.setBackground(Color.cyan);
		tex.setFont(new Font("Verdana", Font.BOLD, 16));
		tex.setPreferredSize(new Dimension(500,700));
		tex.setEditable(false);
		for(City c : g.getAvailableCities()) {
			if(c.getName().equals(s)) {
				
				int arc = 0;
				int inf = 0;
				int cav = 0;
				String total = "";
				String str = "";
				for (Unit u : c.getDefendingArmy().getUnits()) {
					String str2 = "";
					if (u instanceof Archer) {
						arc = u.getCurrentSoldierCount();
						str2 = "Archer        " + arc + " units               " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Infantry) {
						inf = u.getCurrentSoldierCount();
						str2 = "Infantry      " + inf + " units              " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Cavalry) {
						cav = u.getCurrentSoldierCount();
						str2 = "Cavalry       " + cav + " units               " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					total += str2 + "\n";
				}
				String tmp4 = "                            City's Defending Army \n" + total + "\n";
				tof.setText(tmp4);

				
				for(EconomicBuilding b : c.getEconomicalBuildings()) {
					if(b instanceof Farm) {
						tmp = "Farm, UpgradeCost: "+b.getUpgradeCost();
						tmp3 = tmp3+"Farm, Level "+b.getLevel()+"\n";
					}
					if(b instanceof Market) {
						tmp = "Market, UpgradeCost: "+b.getUpgradeCost();
						tmp3 = tmp3+"Market, Level "+b.getLevel()+"\n";
					}
					upgrade.addItem(tmp);
				}
				for(MilitaryBuilding b : c.getMilitaryBuildings()) {
					if(b instanceof ArcheryRange) {
						tmp = "Archery Range, UpgradeCost: "+b.getUpgradeCost();
						tmp3 = tmp3+"ArcheryRange, Level "+b.getLevel()+"\n";
					}
					if(b instanceof Barracks) {
						tmp = "Barracks, UpgradeCost: "+b.getUpgradeCost();
						tmp3 = tmp3+"Barracks, Level "+b.getLevel()+"\n";
					}
					if(b instanceof Stable) {
						tmp = "Stable, UpgradeCost: "+b.getUpgradeCost();
						tmp3 = tmp3+"Stable, Level "+b.getLevel()+"\n";
					}
					upgrade.addItem(tmp);
				}
				for(MilitaryBuilding b : c.getMilitaryBuildings()) {
					if(b instanceof ArcheryRange) {
						tmp2 = "Archery Range, Recruitment Cost: "+b.getRecruitmentCost();
					}
					if(b instanceof Barracks) {
						tmp2 = "Barracks, Recruitment Cost: "+b.getRecruitmentCost();
					}
					if(b instanceof Stable) {
						tmp2 = "Stable, Recruitment Cost: "+b.getRecruitmentCost();
					}
					recruit.addItem(tmp2);
				}
				tef.setText(tmp3);
				
				for(Army a : g.getPlayer().getControlledArmies()) {
					if(a.getCurrentLocation().equals(s)) {
						
						int arc1 = 0;
						int inf1 = 0;
						int cav1 = 0;
						String total1 = "";
						for (Unit u : a.getUnits()) {
							String str2 = "";
							if (u instanceof Archer) {
								arc = u.getCurrentSoldierCount();
								str2 = "Archer        " + arc + " units               " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units";
							}
							if (u instanceof Infantry) {
								inf = u.getCurrentSoldierCount();
								str2 = "Infantry      " + inf + " units              " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units";
							}
							if (u instanceof Cavalry) {
								cav = u.getCurrentSoldierCount();
								str2 = "Cavalry       " + cav + " units               " + u.getLevel() + "           "
										+ u.getMaxSoldierCount() + " units";
							}
							total1 += str2 + "\n";
						}
						tmpx = total1 + "\n";
						
						
					}
				}
				tmpx = "                            Player's Controlled Army \n"+tmpx;
				tex.setText(tmpx);
				
				
			}
		}
		
		
		jp.add(tef);
		jp.add(upgrade);
		jp.add(recruit);
		jp.add(tof);
		jp.add(tex);
		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont,BorderLayout.CENTER);
		jf.setVisible(true);
	}

	public static Player tArmy(String playerName, String currentLocation) {
		Player player = new Player(playerName);
		Army army1 = new Army(currentLocation);
		Army army2 = new Army(currentLocation);
		Army army3 = new Army(currentLocation);
		Army army5 = new Army(currentLocation);
		Archer a = new Archer(1, 60, 0.4, 0.5, 0.6);
		Cavalry c = new Cavalry(1, 60, 0.4, 0.5, 0.6);
		Infantry i = new Infantry(2, 60, 0.4, 0.5, 0.6);

		army1.getUnits().add(a);
		army1.getUnits().add(a);
		army1.getUnits().add(c);
		army1.getUnits().add(i);
		army2.getUnits().add(c);
		army2.getUnits().add(i);
		army2.getUnits().add(c);
		army2.getUnits().add(c);
		army3.getUnits().add(i);
		army5.getUnits().add(i);
		army5.getUnits().add(c);
		army5.getUnits().add(i);
		army5.setTarget("khara");
		army1.setCurrentStatus(Status.MARCHING);
		army5.setCurrentStatus(Status.MARCHING);
		army1.setDistancetoTarget(4);
		army1.setTarget("London");
		player.getControlledArmies().add(army3);
		player.getControlledArmies().add(army3);
		player.getControlledArmies().add(army3);
		player.getControlledArmies().add(army1);
		player.getControlledArmies().add(army2);
		player.getControlledArmies().add(army5);

		return player;

	}

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		Player p = tArmy("Ahmed", "Cairo");
		Game g = new Game("Ahmed", "Cairo");
		getSiegingArmy(p, g);
//		getMarchingArmy(p, g);
		
	}

}
