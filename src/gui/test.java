package gui;

import java.awt.BorderLayout;
import gui.cityView;

import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;

import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.*;
import buildings.*;

public class test {

	public static void bitch(String s, Game g) {
		JFrame jf = new JFrame();
		jf.setTitle(s+" Info");
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(900,900));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		JPanel jp = new JPanel();
		jf.setSize(900, 900);
		jf.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jp.setBounds(10, 5, 800, 800);
		jp.setBackground(new Color(0xFFCC00));;
		jp.setPreferredSize(new Dimension(1000,900));
		String tmp = "";
		JComboBox upgrade = new JComboBox();
		upgrade.setPreferredSize(new Dimension(200,30));
		JComboBox recruit = new JComboBox();
		String tmp2 = "";
		JTextArea tef = new JTextArea();
		JTextArea tof = new JTextArea();
		tef.setBackground(new Color(0xFFCC00));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(200,200));
		tef.setEditable(false);
		tof.setBackground(new Color(0xFFCC00));
		tof.setFont(new Font("Verdana", Font.BOLD, 16));
		tof.setPreferredSize(new Dimension(500,700));
		tof.setEditable(false);
		String tmp3 = "\n \n \n \n";
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
				String tmp4 = "" + total + "\n";
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
				
			}
		}
		
		
		jp.add(tef);
		jp.add(upgrade);
		jp.add(recruit);
		jp.add(tof);
		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont,BorderLayout.CENTER);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		JFrame jf = new JFrame();
//		jf.setTitle("Idle Army");
//		JPanel cont = new JPanel();
//		cont.setLayout(null);
//		cont.setPreferredSize(new Dimension(900,900));
//		cont.setVisible(true);
//		cont.setBackground(new Color(0xFFCC00));
//		JPanel jp = new JPanel();
//		jf.setSize(900, 900);
//		jf.setLayout(new BorderLayout());
//		jp.setLayout(new GridLayout(10, 1));
//		jp.setBounds(100, 10, 800, 800);
//		jp.setBackground(new Color(0xFFCC00));;
//		jp.setPreferredSize(new Dimension(1000,900));
//		int i = 1;
////		for(Army a : p.getControlledArmies()) {
////			if(a.getCurrentStatus().equals(Status.IDLE)) {
////				JLabel l = new JLabel("Army"+i+" : "+a.getUnits().size()+" Units");
////				l.setVisible(true);
////				l.setPreferredSize(new Dimension(300,50));
////				l.setFont(new Font("Verdana", Font.BOLD, 20));
////				jp.add(l);
////				i++;
////				
////			}
//		for(int j =0; j<5;j++) {
//			JLabel l = new JLabel("FUCK");
//			l.setVisible(true);
//			l.setPreferredSize(new Dimension(300,10));
//			l.setFont(new Font("Verdana", Font.BOLD, 18));
//			jp.add(l);
//			i++;
//			
//		}
//		jp.setVisible(true);
//		cont.add(jp);
//		jf.add(cont,BorderLayout.CENTER);
//		jf.setVisible(true);

		Game game = new Game("Kareem", "Sparta");
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
//		gui.shit.cityInfo(city.getName(), game);
	    new BattleView(game,city,army2);
	//	cityView x = new cityView(city.getName(), game);
		
	}

}
