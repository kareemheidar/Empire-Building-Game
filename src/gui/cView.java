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
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

import java.util.*;

public class cView extends JPanel implements ActionListener {

	
	JPanel jp = new JPanel();
	JComboBox recruit = new JComboBox();
	JComboBox upgrade = new JComboBox();
	JComboBox build = new JComboBox();
	JTextArea tef = new JTextArea();
	JTextArea tof = new JTextArea();
	JTextArea tex = new JTextArea();
	String tmp = "";
	String tmp2 = "";
	String tmp3 = "\n \n \n \n";
	String tmpx = "";
	static Game game = null;
	City city = null;
	Player player = null;
	String s = "";
	static Game go = kickOff.game;
	
	

	public cView(String s, Game g) {
		game = g;
		player = g.getPlayer();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1300, 1000));
		this.setVisible(true);
		this.setBackground(new Color(0xFFCC00));
//		this.setSize(1300, 1000);
//		this.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jp.setBounds(10, 5, 1100, 900);
		jp.setBackground(new Color(0xFFCC00));
		
//		jp.setPreferredSize(new Dimension(1000,900));
		upgrade.addActionListener(this);
		upgrade.setPreferredSize(new Dimension(200, 30));
		upgrade.addItem("~~~Upgrade~~~");
		build.addActionListener(this);
		build.setPreferredSize(new Dimension(150, 30));
		build.addItem("~~~Build~~~");
		recruit.addActionListener(this);
		recruit.setPreferredSize(new Dimension(300, 30));
		recruit.addItem("~~~Recruit~~~");
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
		
		build.addItem("Farm");
		build.addItem("Market");
		build.addItem("Archery Range");
		build.addItem("Barracks");
		build.addItem("Stable");
		
		for (City c : g.getAvailableCities()) {
			if (c.getName().equals(s)) {
				city = c;
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
						tmpx = total1 + "\n";

					}
				}
				tmpx = "                            Player's Controlled Army \n" + tmpx;
				tex.setText(tmpx);

			}
		}

		jp.add(tef);
		jp.add(build);
		jp.add(upgrade);
		jp.add(recruit);
		jp.add(tof);
		jp.add(tex);
		jp.setVisible(true);
//		this.add(jp);
//		this.add(this, BorderLayout.CENTER);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Game game =null;
		try {
			game = new Game("Kareem", "Sparta");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		City city = new City("Sparta");
		ArcheryRange a = new ArcheryRange();
		Barracks bb = new Barracks();
		Stable cd = new Stable();
		Farm f = new Farm();
		Market m = new Market();
		city.getEconomicalBuildings().add(f);
		city.getEconomicalBuildings().add(m);
		city.getMilitaryBuildings().add(a);
		city.getMilitaryBuildings().add(bb);
		city.getMilitaryBuildings().add(cd);
		game.getAvailableCities().add(city);
		game.getPlayer().setTreasury(5000);
		
		Army army2 = new Army("Sparta");

	    int i=0;
	    while(i <10) {
	        Archer x = new Archer(1,60,0.4,0.5,0.6);
	        Cavalry c = new Cavalry(1,60,0.4,0.5,0.6);
	        Infantry in = new Infantry(1,60,0.4,0.5,0.6);
	        x.setParentArmy(army2);
	        c.setParentArmy(army2);
	        in.setParentArmy(army2);
	        army2.getUnits().add(x);
	        army2.getUnits().add(c);
	        army2.getUnits().add(in);

	        i++;

	    }
	    game.getPlayer().getControlledArmies().add(army2);
	    city.setDefendingArmy(army2);
//
		
		JFrame x = new JFrame();
		x.setVisible(true);
		x.setSize(1300, 1000);
		cView pan = new cView("Sparta",game);
		x.add(pan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == recruit) {
			if (recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 400") || recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 450") || recruit.getSelectedItem().equals("Archery Range, Recruitment Cost: 500")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {
					
						try {
							player.recruitUnit("Archer", city.getName());
							gui.kickOff.setTreasury();
						} catch (BuildingInCoolDownException e1) {
							// TODO Auto-generated catch block
							toast t = new toast("Building is in CoolDown", 850, 950);
							t.showtoast();
//							e1.printStackTrace();
						} catch (MaxRecruitedException e1) {
							toast t = new toast("Maximum Recruit Reached", 850, 950);
							t.showtoast();
						} catch (NotEnoughGoldException e1) {
							toast t = new toast("Not Enough Gold", 850, 950);
							t.showtoast();
						}
					}
				}
			} else if (recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 500") || recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 550") || recruit.getSelectedItem().equals("Barracks, Recruitment Cost: 600")) {
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
			} else if (recruit.getSelectedItem().equals("Stable, Recruitment Cost: 600") || recruit.getSelectedItem().equals("Stable, Recruitment Cost: 650") || recruit.getSelectedItem().equals("Stable, Recruitment Cost: 700")) {
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
			
			
			if (upgrade.getSelectedItem().equals("Farm, UpgradeCost: 500") || upgrade.getSelectedItem().equals("Farm, UpgradeCost: 700") ) {
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
			if (upgrade.getSelectedItem().equals("Market, UpgradeCost: 700") || upgrade.getSelectedItem().equals("Market, UpgradeCost: 1000")) {
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
			if (upgrade.getSelectedItem().equals("Archery Range, UpgradeCost: 800") || upgrade.getSelectedItem().equals("Archery Range, UpgradeCost: 700")) {
				for (MilitaryBuilding b : city.getMilitaryBuildings()) {
					if (b instanceof ArcheryRange) {

						if(b.getLevel()==1) {
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
					}
						else if(b.getLevel()==2) {
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
						}
						else if(b.getLevel()==3) {
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
		if(e.getSource() == build) {
			if(build.getSelectedItem().equals("Farm")) {
				try {
					player.build("Farm", city.getName());
					build.removeItem("Farm");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}
			}
		}
		if(e.getSource() == build) {
			if(build.getSelectedItem().equals("Market")) {
				try {
					player.build("Market", city.getName());
					build.removeItem("Market");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}
			}
		}
		if(e.getSource() == build) {
			if(build.getSelectedItem().equals("Archery Range")) {
				try {
					player.build("ArcheryRange", city.getName());
					build.removeItem("Archery Range");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}
			}
		}
		if(e.getSource() == build) {
			if(build.getSelectedItem().equals("Barracks")) {
				try {
					player.build("Barracks", city.getName());
					build.removeItem("Barracks");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}
			}
		}
		if(e.getSource() == build) {
			if(build.getSelectedItem().equals("Stable")) {
				try {
					player.build("Stable", city.getName());
					build.removeItem("Stable");
					build.setSelectedIndex(0);
					gui.kickOff.setTreasury();
				} catch (NotEnoughGoldException e1) {
					toast t = new toast("Not Enough Gold", 850, 950);
					t.showtoast();
				}
			}
		}
		

	}
	

}
