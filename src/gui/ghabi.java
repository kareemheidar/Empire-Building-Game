package gui;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import engine.Player;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class ghabi {
	
	public static void getIdleArmy(Player p) {
		JFrame jf = new JFrame();
		jf.setTitle("Idle Army");
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setPreferredSize(new Dimension(900,900));
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		JPanel jp = new JPanel();
		jf.setSize(900, 900);
		jf.setLayout(new BorderLayout());
		jp.setLayout(new GridLayout(10, 1));
		jp.setBounds(100, 10, 800, 800);
		jp.setBackground(new Color(0xFFCC00));;
		jp.setPreferredSize(new Dimension(1000,900));
		JTextArea tef = new JTextArea();
		tef.setBackground(new Color(0xFFCC00));
		tef.setFont(new Font("Verdana", Font.BOLD, 16));
		tef.setPreferredSize(new Dimension(200,200));
		tef.setEditable(false);
		String tmp = "";
		
		int i = 1;
		for(Army a : p.getControlledArmies()) {
			int arc = 0;
			int inf = 0;
			int cav = 0;
			ArrayList<String> list = new ArrayList<String>();
			String str = "";
			if(a.getCurrentStatus().equals(Status.IDLE)) {
				for(Unit u : a.getUnits()) {
					if(u instanceof Archer) {
						arc = u.getCurrentSoldierCount();
						str = "Archery, "+arc+"units, "+"Level: "+u.getLevel()+"Max: "+u.getMaxSoldierCount();
					}
					if(u instanceof Infantry) {
						inf = u.getCurrentSoldierCount();
						str = "Cavalry, "+inf+"units, "+"Level: "+u.getLevel()+"Max: "+u.getMaxSoldierCount();
					}
					if(u instanceof Cavalry) {
						cav = u.getCurrentSoldierCount();
						str = "Cavalry, "+cav+"units, "+"Level: "+u.getLevel()+"Max: "+u.getMaxSoldierCount();
					}
					tmp = tmp+" "+str+"\n";
				}
//				
//				JLabel l = new JLabel("Army"+i+" : "+list.toString()+" in "+a.getCurrentLocation());
//				l.setVisible(true);
//				l.setPreferredSize(new Dimension(400,50));
//				l.setFont(new Font("Verdana", Font.BOLD, 16));
				tef.setText(tmp);
				jp.add(tef);
				i++;
				
			}
	}
		jp.setVisible(true);
		cont.add(jp);
		jf.add(cont,BorderLayout.CENTER);
		jf.setVisible(true);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
