package gui;

import java.awt.*;

import gui.shit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import buildings.*;
import gui.map;
import units.*;
import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

import java.util.*;

public class cityView extends JFrame implements ActionListener {

	JPanel cont = new JPanel();
	JPanel jp = new JPanel();
	JComboBox recruit = new JComboBox();
	JComboBox upgrade = new JComboBox();
	JComboBox build = new JComboBox();
	JComboBox initiateArmy = new JComboBox();
	JComboBox relocateArmy = new JComboBox();
	JComboBox selectArmy;
	JTextArea tef = new JTextArea();
	JTextArea tof = new JTextArea();
	JTextArea tex = new JTextArea();
	String tmp = "";
	String tmp2 = "";
	String tmp3 = "\n \n \n \n";
	String tmpx = "";
	Game game = null;
	City city = null;
	Player player = null;
	String ss = "";
	ArrayList<Unit> listofunits = null;
	JFrame selectArmyjf;
//	String tmp3 = "";

	public cityView(String s, Game g) {
		this.ss = s;
		game = g;
		player = g.getPlayer();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(1300, 1000));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		this.setSize(1300, 1000);
		this.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jp.setBounds(10, 5, 1100, 900);
		jp.setBackground(new Color(0xFFCC00));

//		jp.setPreferredSize(new Dimension(1000,900));
		upgrade.addActionListener(this);
		upgrade.setPreferredSize(new Dimension(200, 30));
		upgrade.addItem("~~~Upgrade~~~");
		build.addActionListener(this);
		build.setPreferredSize(new Dimension(120, 30));
		build.addItem("~~~Build~~~");
		recruit.addActionListener(this);
		recruit.setPreferredSize(new Dimension(250, 30));
		recruit.addItem("~~~Recruit~~~");
		initiateArmy.addActionListener(this);
		initiateArmy.setPreferredSize(new Dimension(150, 30));
		initiateArmy.addItem("Initiate Army");
		relocateArmy.addActionListener(this);
		relocateArmy.setPreferredSize(new Dimension(150, 30));
		relocateArmy.addItem("Relocate Army");
		tef.setBackground(new Color(0xFFCC00));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(200, 200));
		tef.setEditable(false);
		tof.setBackground(Color.cyan);
		tof.setFont(new Font("Verdana", Font.BOLD, 16));
		tof.setPreferredSize(new Dimension(500, 700));
		tof.setEditable(false);
		tex.setBackground(Color.cyan);
		tex.setFont(new Font("Verdana", Font.BOLD, 16));
		tex.setPreferredSize(new Dimension(500, 700));
		tex.setEditable(false);
		listofunits = new ArrayList<Unit>();

//		build.addItem("Farm");
//		build.addItem("Market");
//		build.addItem("Archery Range");
//		build.addItem("Barracks");
//		build.addItem("Stable");

		for (City c : g.getAvailableCities()) {
			if (c.getName().equals(s)) {
				city = c;
				int arc = 0;
				int inf = 0;
				int cav = 0;
				String total = "";
				String str = "";
				int j = 1;
				for (Unit u : c.getDefendingArmy().getUnits()) {
					String str2 = "";
					if (u instanceof Archer) {
						arc = u.getCurrentSoldierCount();
						str2 = "#" + j + " Archer        " + arc + " units               " + u.getLevel()
								+ "           " + u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Infantry) {
						inf = u.getCurrentSoldierCount();
						str2 = "#" + j + " Infantry      " + inf + " units              " + u.getLevel() + "           "
								+ u.getMaxSoldierCount() + " units";
					}
					if (u instanceof Cavalry) {
						cav = u.getCurrentSoldierCount();
						str2 = "#" + j + " Cavalry       " + cav + " units               " + u.getLevel()
								+ "           " + u.getMaxSoldierCount() + " units";
					}
					total += str2 + "\n";
					j++;
				}
				String tmp4 = "                            City's Defending Army \n" + total + "\n";
				tof.setText(tmp4);

				for (EconomicBuilding b : c.getEconomicalBuildings()) {
					if (b instanceof Farm) {
						tmp = "Farm, UpgradeCost: " + b.getUpgradeCost();
						tmp3 = tmp3 + "Farm, Level " + b.getLevel() + "\n";
					}
					if (b instanceof Market) {
						tmp = "Market, UpgradeCost: " + b.getUpgradeCost();
						tmp3 = tmp3 + "Market, Level " + b.getLevel() + "\n";
					}
					upgrade.addItem(tmp);
				}
				for (MilitaryBuilding b : c.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {
						tmp = "Archery Range, UpgradeCost: " + b.getUpgradeCost();
						tmp3 = tmp3 + "ArcheryRange, Level " + b.getLevel() + "\n";
					}
					if (b instanceof Barracks) {
						tmp = "Barracks, UpgradeCost: " + b.getUpgradeCost();
						tmp3 = tmp3 + "Barracks, Level " + b.getLevel() + "\n";
					}
					if (b instanceof Stable) {
						tmp = "Stable, UpgradeCost: " + b.getUpgradeCost();
						tmp3 = tmp3 + "Stable, Level " + b.getLevel() + "\n";
					}
					upgrade.addItem(tmp);
				}
				for (MilitaryBuilding b : c.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {
						tmp2 = "Archery Range, Recruitment Cost: " + b.getRecruitmentCost();
					}
					if (b instanceof Barracks) {
						tmp2 = "Barracks, Recruitment Cost: " + b.getRecruitmentCost();
					}
					if (b instanceof Stable) {
						tmp2 = "Stable, Recruitment Cost: " + b.getRecruitmentCost();
					}
					recruit.addItem(tmp2);
				}
				tef.setText(tmp3);
				int i = 1;
				for (Army a : g.getPlayer().getControlledArmies()) {
					if (a.getCurrentLocation().equals(s)) {

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
						tmpx = tmpx + "Army   #" + i + "\n" + total1 + "\n";
						i++;

					}
				}
				tmpx = "                            Player's Controlled Army \n" + tmpx;
				tex.setText(tmpx);

			}
		}

		boolean archX = true;
		boolean farmX = true;
		boolean markX = true;
		boolean baraX = true;
		boolean stabX = true;

		for (MilitaryBuilding b : city.getMilitaryBuildings()) {
			if (b instanceof ArcheryRange) {
				archX = false;
			}
			if (b instanceof Barracks) {
				baraX = false;
			}
			if (b instanceof Stable) {
				stabX = false;
			}
		}

		for (EconomicBuilding b : city.getEconomicalBuildings()) {
			if (b instanceof Farm) {
				farmX = false;
			}
			if (b instanceof Market) {
				markX = false;
			}

		}

		if (farmX) {
			build.addItem("Farm");
		}
		if (markX) {
			build.addItem("Market");
		}
		if (archX) {
			build.addItem("Archery Range");
		}
		if (baraX) {
			build.addItem("Barracks");
		}
		if (stabX) {
			build.addItem("Stable");
		}

		int k = 1;
		for (Unit u : city.getDefendingArmy().getUnits()) {
			if (u.getParentArmy().equals(city.getDefendingArmy())) {
				if (u instanceof Archer) {
					initiateArmy.addItem("#" + k + " Archer");
					relocateArmy.addItem("#" + k + " Archer");
					listofunits.add(u);
				}
				if (u instanceof Infantry) {
					initiateArmy.addItem("#" + k + " Infantry");
					relocateArmy.addItem("#" + k + " Infantry");
					listofunits.add(u);

				}
				if (u instanceof Cavalry) {
					initiateArmy.addItem("#" + k + " Cavalry");
					relocateArmy.addItem("#" + k + " Cavalry");
					listofunits.add(u);

				}
				k++;
			}
		}

		jp.add(tef);
		jp.add(build);
		jp.add(upgrade);
		jp.add(recruit);
		jp.add(initiateArmy);
		jp.add(relocateArmy);
		jp.add(tof);
		jp.add(tex);
		jp.setVisible(true);
		cont.add(jp);
		this.add(cont, BorderLayout.CENTER);
		this.setVisible(true);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == recruit) {
			if (recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 400")
					|| recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 450")
					|| recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 500")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {

						try {
							player.recruitUnit("Archer", city.getName());
							gui.kickOff.setTreasury();
						} catch (BuildingInCoolDownException e1) {
							// TODO Auto-generated catch block
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxRecruitedException e1) {
							toast t = new toast("Maximum Recruit Reached", 850, 950);
							t.showtoast();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						}
					}
				}
			} else if (recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 500")
					|| recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 550")
					|| recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 600")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof Barracks) {
						try {
							player.recruitUnit("Infantry", city.getName());
							gui.kickOff.setTreasury();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxRecruitedException e1) {
							toast t = new toast("Maximum Recruit Reached", 850, 950);
							t.showtoast();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						}
					}
				}
			} else if (recruit.getSelectedItem().equals("Stable, Recruitment Cost: 600")
					|| recruit.getSelectedItem().equals("Stable, Recruitment Cost: 650")
					|| recruit.getSelectedItem().equals("Stable, Recruitment Cost: 700")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof Stable) {

						try {
							player.recruitUnit("Cavalry", city.getName());
							gui.kickOff.setTreasury();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxRecruitedException e1) {
							toast t = new toast("Maximum Recruit Reached", 850, 950);
							t.showtoast();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						}

					}
				}
			}


		}
		if (e.getSource() == upgrade) {

			if (upgrade.getSelectedItem().equals("Farm, UpgradeCost: 500")
					|| upgrade.getSelectedItem().equals("Farm, UpgradeCost: 700")) {
				for (EconomicBuilding b : city.getEconomicalBuildings()) {
					if (b instanceof Farm) {

						try {
							player.upgradeBuilding(((Farm) b));
							gui.kickOff.setTreasury();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxLevelException e1) {
							toast t = new toast("Maximum Level Reached", 850, 950);
							t.showtoast();
						}

					}
				}
			}
			if (upgrade.getSelectedItem().equals("Market, UpgradeCost: 700")
					|| upgrade.getSelectedItem().equals("Market, UpgradeCost: 1000")) {
				for (EconomicBuilding b : city.getEconomicalBuildings()) {
					if (b instanceof Market) {

						try {
							player.upgradeBuilding(((Market) b));
							gui.kickOff.setTreasury();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxLevelException e1) {
							toast t = new toast("Maximum Level Reached", 850, 950);
							t.showtoast();
						}

					}
				}
			}
			if (upgrade.getSelectedItem().equals("Archery Range, UpgradeCost: 800")
					|| upgrade.getSelectedItem().equals("Archery Range, UpgradeCost: 700")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {

						if (b.getLevel() == 1) {
							try {
								player.upgradeBuilding(((ArcheryRange) b));
								gui.kickOff.setTreasury();
								upgrade.setSelectedItem("Archery Range, UpgradeCost: 700");
							} catch (NotEnoughGoldException e1) {
								toast t = new toast("Not Enough Gold", 850, 950);
								t.showtoast();
							} catch (BuildingInCoolDownException e1) {
								toast t = new toast("Building is in CoolDown", 850, 950);
								t.showtoast();
							} catch (MaxLevelException e1) {
								toast t = new toast("Maximum Level Reached", 850, 950);
								t.showtoast();
							}
						} else if (b.getLevel() == 2) {
							try {
								player.upgradeBuilding(((ArcheryRange) b));
								gui.kickOff.setTreasury();
								upgrade.setSelectedItem("Archery Range, UpgradeCost: 700");
							} catch (NotEnoughGoldException e1) {
								toast t = new toast("Not Enough Gold", 850, 950);
								t.showtoast();
							} catch (BuildingInCoolDownException e1) {
								toast t = new toast("Building is in CoolDown", 850, 950);
								t.showtoast();
							} catch (MaxLevelException e1) {
								toast t = new toast("Maximum Level Reached", 850, 950);
								t.showtoast();
							}
						} else if (b.getLevel() == 3) {
							try {
								player.upgradeBuilding(((ArcheryRange) b));
								gui.kickOff.setTreasury();
								upgrade.removeItemAt(upgrade.getSelectedIndex());
							} catch (NotEnoughGoldException e1) {
								toast t = new toast("Not Enough Gold", 850, 950);
								t.showtoast();
							} catch (BuildingInCoolDownException e1) {
								toast t = new toast("Building is in CoolDown", 850, 950);
								t.showtoast();
							} catch (MaxLevelException e1) {
								toast t = new toast("Maximum Level Reached", 850, 950);
								t.showtoast();
							}
						}
					}
				}
			}
			if (upgrade.getSelectedItem().equals("Barracks, UpgradeCost: 1000")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof Barracks) {

						try {
							player.upgradeBuilding(((Barracks) b));
							gui.kickOff.setTreasury();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxLevelException e1) {
							toast t = new toast("Maximum Level Reached", 850, 950);
							t.showtoast();
						}

					}
				}
			}
			if (upgrade.getSelectedItem().equals("Stable, UpgradeCost: 1500")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof Stable) {

						try {
							player.upgradeBuilding(((Stable) b));
							gui.kickOff.setTreasury();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						} catch (BuildingInCoolDownException e1) {
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
						} catch (MaxLevelException e1) {
							toast t = new toast("Maximum Level Reached", 850, 950);
							t.showtoast();
						}

					}
				}
			}

			this.revalidate();
			this.repaint();
		}

		if (e.getSource().equals(initiateArmy)) {
			if (listofunits != null) {
				if (listofunits.size() != 0) {
					int x = initiateArmy.getSelectedIndex();
					for (int i = 0; i < listofunits.size(); i++) {
						if ((i + 1) == x) {
							player.initiateArmy(city, listofunits.get(i));
							listofunits.remove(i);
						}
					}
				}

			}

		}

		if (e.getSource().equals(relocateArmy)) {
			if (listofunits != null) {
				if (listofunits.size() != 0) {
					if (player.getControlledArmies().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You Don't Have an Existing Army!");
					} else {

						selectArmyjf = new JFrame();
						selectArmyjf.setBounds(750, 450, 400, 200);
						selectArmyjf.setLayout(null);
						selectArmy = new JComboBox();
						selectArmy.addActionListener(this);
						selectArmy.setBounds(50, 40, 300, 40);
						selectArmy.addItem("Choose Army");
						int x = 0;
						for (Army a : player.getControlledArmies()) {
							x++;
							selectArmy.addItem("Army " + x);
						}
						selectArmyjf.add(selectArmy);
						selectArmyjf.setVisible(true);
					}
				}
			}

		}

		if (e.getSource().equals(selectArmy)) {

			int x = selectArmy.getSelectedIndex();
			for (int i = 0; i < player.getControlledArmies().size(); i++) {
				if ((i + 1) == x) {
					if (listofunits != null) {
						if (listofunits.size() != 0) {
							int y = relocateArmy.getSelectedIndex();
							for (int c = 0; c < listofunits.size(); c++) {
								if ((c + 1) == x) {
									try {
										player.getControlledArmies().get(i).relocateUnit(listofunits.get(i));
										selectArmyjf.dispose();

									} catch (MaxCapacityException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									listofunits.remove(i);
								}
							}
						}
					}
				}
			}

		}

		if (e.getSource() == build) {
			String zinka = "";

			if (build.getSelectedItem().equals("Farm")) {
				try {
					player.build("Farm", city.getName());
					build.removeItem("Farm");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
					for (EconomicBuilding b : city.getEconomicalBuildings()) {
						if (b instanceof Farm) {
							zinka = "Farm, UpgradeCost: " + b.getUpgradeCost();
							tmp3 = tmp3 + "Farm, Level " + b.getLevel() + "\n";
							upgrade.addItem(zinka);
						}
					}
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();

				}

			}
		}

		if (e.getSource() == build) {
			String zinka = "";
			if (build.getSelectedItem().equals("Market")) {
				try {
					player.build("Market", city.getName());
					build.removeItem("Market");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
					for (EconomicBuilding b : city.getEconomicalBuildings()) {
						if (b instanceof Market) {
							zinka = "Market, UpgradeCost: " + b.getUpgradeCost();
							tmp3 = tmp3 + "Market, Level " + b.getLevel() + "\n";
							upgrade.addItem(zinka);
						}

					}
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}

			}


		}
		if (e.getSource() == build) {
			String zinka = "";
			String zinka2 = "";
			if (build.getSelectedItem().equals("Archery Range")) {
				try {
					player.build("ArcheryRange", city.getName());
					build.removeItem("Archery Range");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
					for (MilitaryBuilding b : city.getMilitaryBuildings()) {
						if (b instanceof ArcheryRange) {
							zinka = "Archery Range, UpgradeCost: " + b.getUpgradeCost();
							tmp3 = tmp3 + "ArcheryRange, Level " + b.getLevel() + "\n";
							zinka2 = "Archery Range, Recruitment Cost: " + b.getRecruitmentCost();
							upgrade.addItem(zinka);
							recruit.addItem(zinka2);
						}
					}
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}

			}


		}
		if (e.getSource() == build) {
			String zinka = "";
			String zinka2 = "";
			if (build.getSelectedItem().equals("Barracks")) {
				try {
					player.build("Barracks", city.getName());
					build.removeItem("Barracks");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
					for (MilitaryBuilding b : city.getMilitaryBuildings()) {
						if (b instanceof Barracks) {
							zinka = "Barracks, UpgradeCost: " + b.getUpgradeCost();
							tmp3 = tmp3 + "Barracks, Level " + b.getLevel() + "\n";
							zinka2 = "Barracks, Recruitment Cost: " + b.getRecruitmentCost();
							upgrade.addItem(zinka);
							recruit.addItem(zinka2);
						}
					}
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}

			}


		}
		if (e.getSource() == build) {
			String zinka = "";
			String zinka2 = "";
			if (build.getSelectedItem().equals("Stable")) {
				try {
					player.build("Stable", city.getName());
					build.removeItem("Stable");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
					for (MilitaryBuilding b : city.getMilitaryBuildings()) {
						if (b instanceof Stable) {
							zinka = "Stable, UpgradeCost: " + b.getUpgradeCost();
							tmp3 = tmp3 + "Stable, Level " + b.getLevel() + "\n";
							zinka2 = "Stable, Recruitment Cost: " + b.getRecruitmentCost();
							upgrade.addItem(zinka);
							recruit.addItem(zinka2);
						}
					}
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}

			}


		}
		tef.setText(tmp3);
		cont.revalidate();
		cont.repaint();
		jp.revalidate();
		jp.repaint();

	}

}
