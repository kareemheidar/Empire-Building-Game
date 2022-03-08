package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketOptions;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import engine.City;
import engine.Game;
import engine.Player;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class BattleView extends JFrame implements ActionListener {
	JComboBox<String> myarmy = new JComboBox<String>();
	JComboBox<String> defendingarmy = new JComboBox<String>();
	Game game = null;
	City defendingCity = null;
	Army army = null;
	JButton attack = new JButton("ATTACK");
	Boolean myflag = true;
	JPanel cont;
	JPanel p3;
	JTextArea t3;
	String total ="";

	public BattleView(Game game, City defendingcity, Army army) {
		this.game = game;
		this.defendingCity = defendingcity;
		this.army = army;

		this.setTitle("Battle View");
		this.setSize(new Dimension(1500, 900));
		this.setResizable(false);
		this.setLocation(210, 90);
		cont = new JPanel();
		cont.setPreferredSize(this.getSize());
		cont.setVisible(true);
		cont.setBackground(new Color(0xFFCC00));
		ImagePanel img = new ImagePanel("source/sword1.png");
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p1.setPreferredSize(new Dimension(560, 400));
		p1.setLayout(null);
		JLabel head1 = new JLabel("Your Army");
		head1.setBounds(0, 0, 560, 30);
		head1.setFont(new Font("Verdana", Font.BOLD, 16));
		head1.setHorizontalAlignment(JLabel.CENTER);
		head1.setVerticalAlignment(JLabel.NORTH);
		head1.setForeground(Color.white);
		JTextArea t1 = new JTextArea();
		t1.setBackground(new Color(0xFFFFFF));
		t1.setFont(new Font("Verdana", Font.BOLD, 16));
		t1.setBounds(0, 30, 560, 370);
		t1.setEditable(false);
		JScrollPane s1 = new JScrollPane(t1);
		s1.setBounds(0,30,560,370);
		s1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p1.add(head1);
		p1.add(s1);
		cont.add(p1);
		cont.add(img);
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLUE);
		p2.setPreferredSize(new Dimension(560, 400));
		p2.setLayout(null);
		JLabel head2 = new JLabel("Enemy's Army");
		head2.setBounds(0, 0, 560, 30);
		head2.setFont(new Font("Verdana", Font.BOLD, 16));
		head2.setHorizontalAlignment(JLabel.CENTER);
		head2.setVerticalAlignment(JLabel.NORTH);
		head2.setForeground(Color.white);
		head2.setBackground(Color.black);
		JTextArea t2 = new JTextArea();
		t2.setBackground(new Color(0xFFFFFF));
		t2.setFont(new Font("Verdana", Font.BOLD, 16));
		t2.setBounds(0, 30, 560, 370);
		t2.setEditable(false);
		JScrollPane s2 = new JScrollPane(t2);
		s2.setBounds(0, 30, 560,370 );
		s2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p2.add(head2);
		p2.add(s2);
		cont.add(p2);
		p3 = new JPanel();
		p3.setBackground(Color.red);
		p3.setPreferredSize(new Dimension(1305, 300));
		p3.setLayout(null);
		JLabel head3 = new JLabel("Battle Log");
		head3.setBounds(0, 0, 1310, 30);
		head3.setFont(new Font("Verdana", Font.BOLD, 16));
		head3.setHorizontalAlignment(JLabel.CENTER);
		head3.setVerticalAlignment(JLabel.NORTH);
		head3.setForeground(Color.white);
		t3 = new JTextArea();
		t3.setBackground(new Color(0xFFFFFF));
		t3.setFont(new Font("Verdana", Font.BOLD, 16));
		t3.setBounds(0, 30, 1310, 270);
		t3.setEditable(false);
		JScrollPane s3 = new JScrollPane(t3);
		s3.setBounds(0,30,1310,270);
		s3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		myarmy.addActionListener(this);
		myarmy.setPreferredSize(new Dimension(270, 30));
		defendingarmy.addActionListener(this);
		defendingarmy.setPreferredSize(new Dimension(270, 30));
		myarmy.addItem("Player's Army");
		defendingarmy.addItem("Enemy's Army");
		attack.addActionListener(this);
		attack.setPreferredSize(new Dimension(100, 100));
		attack.setBackground(new Color(0xFF0000));
		attack.setForeground(Color.white);
		String y = "";
		for (Unit u : army.getUnits()) {
			myarmy.addItem(u.toString());
			String s = u.toString() + "\n";
			y = y + "\n" + s;
			t1.setText(y);
		}
		System.out.println(defendingCity.getDefendingArmy().getUnits().size());
		System.out.println(defendingCity.getName());
		System.out.println(defendingcity.getDefendingArmy().getUnits().size());
		System.out.println(defendingcity.getName());
		String x = "";
		for (Unit u : defendingCity.getDefendingArmy().getUnits()) {
			defendingarmy.addItem(u.toString());
			String s = u.toString() + "\n";
			x = x + "\n" + s;
			t2.setText(x);
		}

		p3.add(head3);
		p3.add(s3);
		cont.add(p3);
		cont.add(myarmy);
		cont.add(attack);
		cont.add(defendingarmy);
		this.add(cont);
		this.setVisible(true);
	}

	
	public void refresh(Game game, City defendingcity, Army army) {

//		this.setTitle("Battle View");
//		this.setSize(new Dimension(1500, 900));
//		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setLocation(210, 90);
		cont.setVisible(false);
		JPanel cont1 = new JPanel();
		cont1.setPreferredSize(this.getSize());
		cont1.setVisible(true);
		cont1.setBackground(new Color(0xFFCC00));
		ImagePanel img = new ImagePanel("source/sword1.png");
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p1.setPreferredSize(new Dimension(560, 400));
		p1.setLayout(null);
		JLabel head1 = new JLabel("Your Army");
		head1.setBounds(0, 0, 560, 30);
		head1.setFont(new Font("Verdana", Font.BOLD, 16));
		head1.setHorizontalAlignment(JLabel.CENTER);
		head1.setVerticalAlignment(JLabel.NORTH);
		head1.setForeground(Color.white);
		JTextArea t1 = new JTextArea();
		t1.setBackground(new Color(0xFFFFFF));
		t1.setFont(new Font("Verdana", Font.BOLD, 16));
		t1.setBounds(0, 30, 560, 370);
		t1.setEditable(false);
		p1.add(head1);
		p1.add(t1);
		cont1.add(p1);
		cont1.add(img);
		JPanel p2 = new JPanel();
		p2.setBackground(Color.BLUE);
		p2.setPreferredSize(new Dimension(560, 400));
		p2.setLayout(null);
		JLabel head2 = new JLabel("Enemy's Army");
		head2.setBounds(0, 0, 560, 30);
		head2.setFont(new Font("Verdana", Font.BOLD, 16));
		head2.setHorizontalAlignment(JLabel.CENTER);
		head2.setVerticalAlignment(JLabel.NORTH);
		head2.setForeground(Color.white);
		head2.setBackground(Color.black);
		JTextArea t2 = new JTextArea();
		t2.setBackground(new Color(0xFFFFFF));
		t2.setFont(new Font("Verdana", Font.BOLD, 16));
		t2.setBounds(0, 30, 560, 370);
		t2.setEditable(false);
		p2.add(head2);
		p2.add(t2);
		cont1.add(p2);
		JPanel p3 = new JPanel();
		p3.setBackground(Color.red);
		p3.setPreferredSize(new Dimension(1305, 300));
		p3.setLayout(null);
		JLabel head3 = new JLabel("Battle Log");
		head3.setBounds(0, 0, 1305, 30);
		head3.setFont(new Font("Verdana", Font.BOLD, 16));
		head3.setHorizontalAlignment(JLabel.CENTER);
		head3.setVerticalAlignment(JLabel.NORTH);
		head3.setForeground(Color.white);
		JTextArea t3 = new JTextArea();
		t3.setBackground(new Color(0xFFFFFF));
		t3.setFont(new Font("Verdana", Font.BOLD, 16));
		t3.setBounds(0, 30, 1305, 270);
		t3.setEditable(false);
		myarmy = new JComboBox<String>();
		myarmy.addActionListener(this);
		myarmy.setPreferredSize(new Dimension(270, 30));
		defendingarmy = new JComboBox<String>();
		defendingarmy.addActionListener(this);
		defendingarmy.setPreferredSize(new Dimension(270, 30));
		myarmy.addItem("Player's Army");
		defendingarmy.addItem("Enemy's Army");
		attack.addActionListener(this);
		attack.setPreferredSize(new Dimension(100, 100));
		attack.setBackground(new Color(0xFF0000));
		attack.setForeground(Color.white);
		String y = "";
		for (Unit u : army.getUnits()) {
			myarmy.addItem(u.toString());
			String s = u.toString() + "\n";
			y = y + "\n" + s;
			t1.setText(y);
		}
		System.out.println(defendingCity.getDefendingArmy().getUnits().size());
		System.out.println(defendingCity.getName());
		System.out.println(defendingcity.getDefendingArmy().getUnits().size());
		System.out.println(defendingcity.getName());
		String x = "";
		for (Unit u : defendingCity.getDefendingArmy().getUnits()) {
			defendingarmy.addItem(u.toString());
			String s = u.toString() + "\n";
			x = x + "\n" + s;
			t2.setText(x);
		}
		cont1.setVisible(true);
		p3.add(head3);
		p3.add(t3);
		cont1.add(p3);
		cont1.add(myarmy);
		cont1.add(attack);
		cont1.add(defendingarmy);
		this.add(cont1);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		new BattleView();
//		options();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(attack)) {
			if (army.getUnits().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You Lost this Battle  '_' ");
				attack.setEnabled(false);
				return;

			} else if(defendingCity.getDefendingArmy().getUnits().isEmpty()){
				JOptionPane.showMessageDialog(null, "You Won this Battle !!");
				game.occupy(army, defendingCity.getName());
				attack.setEnabled(false);
				return;
			}

			int myunit = 0;
			int defunit = 0;

			if ((myarmy.getSelectedIndex() == 0 || defendingarmy.getSelectedIndex() == 0) && myflag==true) {
				JOptionPane.showMessageDialog(null, "You Must Choose a Unit To Attack!");
				return;
			}

			else {


				
				for (int i = 0; i < army.getUnits().size(); i++) {
					if ((i + 1) == myarmy.getSelectedIndex()) {
						myunit = i;
						System.out.println("myunit " + myunit);
					}
				}
				for (int i = 0; i < defendingCity.getDefendingArmy().getUnits().size(); i++) {
					if ((i + 1) == defendingarmy.getSelectedIndex()) {
						defunit = i;
						System.out.println("defunit " + defunit);
					}
				}
				
				Unit me = army.getUnits().get(myunit);
				Unit def = defendingCity.getDefendingArmy().getUnits().get(defunit);
				System.out.println(me.toString() + me.getParentArmy());
				System.out.println(def.toString()+ def.getParentArmy());
				System.out.println(myflag);
				
				
				if (myflag) {
					int before = def.getCurrentSoldierCount();
				try {
					if (me instanceof Archer) {
						((Archer) me).attack(def);
						total = "   Your Enemy's Unit Lost "+(before - def.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = false;
					} else if (me instanceof Cavalry) {
						((Cavalry) me).attack(def);
						total = "   Your Enemy's Unit Lost "+(before - def.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = false;
					} else {
						((Infantry) me).attack(def);
						total = "   Your Enemy's Unit Lost "+(before - def.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = false;
					}
					if(def.getCurrentSoldierCount()<=0) {
						System.out.println("def unit matet w hanremove");
						System.out.println("def army size before remove"+defendingCity.getDefendingArmy().getUnits().size());
						total = "   Your Enemy's Unit Died "+"\n"+ total;
						defendingarmy.removeItem(defunit+1);
						defendingarmy.setSelectedIndex(0);
						defendingarmy.validate();
						defendingarmy.repaint();
						System.out.println("def army size after remove"+defendingCity.getDefendingArmy().getUnits().size());
						
					}
					else {
						defendingarmy.removeItemAt(defunit+1);
						defendingarmy.addItem(def.toString());
						defendingarmy.setSelectedIndex(0);

					}
					System.out.println(me.toString());
					System.out.println(def.toString());
				} catch (FriendlyFireException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					int myarmyrange = (army.getUnits().size()-1)+1;
					int defarmyrange = (defendingCity.getDefendingArmy().getUnits().size()-1)+1;
					def = defendingCity.getDefendingArmy().getUnits().get((int) (Math.random()*defarmyrange));
					me = army.getUnits().get((int) (Math.random()*myarmyrange));
					int before = me.getCurrentSoldierCount();
					if (def instanceof Archer) {
						((Archer) def).attack(me);
						total = "   Your Unit Lost "+ (before - me.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = true;
					} else if (def instanceof Cavalry) {
						((Cavalry) def).attack(me);
						total = "   Your Unit Lost "+(before - me.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = true;
					} else {
						((Infantry) def).attack(me);
						total = "   Your Unit Lost "+(before - me.getCurrentSoldierCount())+" Soldiers \n"+ total;
						myflag = true;
					}
					defendingarmy.setSelectedIndex(0);
					if(me.getCurrentSoldierCount()<=0) {
						System.out.println("my unit matet w hanremove");
						System.out.println(myarmy.getItemCount());
						total = "   Your Unit Died \n"+ total;
						myarmy.removeItemAt(myunit+1);
						myarmy.setSelectedIndex(0);
						System.out.println(myarmy.getItemCount());
						myarmy.validate();
						myarmy.repaint();
						myarmy.doLayout();
						System.out.println("my army size before remove"+army.getUnits().size());


					}
					else {
						myarmy.removeItemAt(myunit+1);
						myarmy.addItem(me.toString());
						myarmy.setSelectedIndex(0);

					}
					System.out.println(me.toString());
					System.out.println(def.toString());
				} catch (FriendlyFireException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		

		System.out.println(myflag);
		t3.setText(total);

	

			}	
				
			synchronized(this.getTreeLock()) {
			     this.validateTree();
			}
				
//				refresh(game, defendingCity, army);
				cont.revalidate();
				cont.repaint(1000);
				cont.validate();
	}
}
}
//		if (e.getSource().equals(attack)) {
//			int myunit = 0;
//			int defunit = 0;
//
//			if (myarmy.getSelectedIndex() != 0 && defendingarmy.getSelectedIndex() != 0) {
//				if (army != null) {
//					System.out.println("shaghal");
//					for (int i = 0; i < army.getUnits().size(); i++) {
//						if ((i + 1) == myarmy.getSelectedIndex()) {
//							myunit = i;
//							System.out.println("myunit " + myunit);
//						}
//					}
//						for (int i = 0; i < army.getUnits().size(); i++) {
//							if ((i + 1) == defendingarmy.getSelectedIndex()) {
//								defunit = i;
//								System.out.println("defunit " + defunit);
//							}
//						}
//				
//					Unit me = army.getUnits().get(myunit);
//					Unit def = defendingCity.getDefendingArmy().getUnits().get(defunit);
//					if (me.getCurrentSoldierCount()>0 && def.getCurrentSoldierCount()>0) {
//							if (myflag) {
//								try {
//									if (me instanceof Archer) {
//										((Archer) me).attack(def);
//										myflag = false;
//									} else if (me instanceof Cavalry) {
//										((Cavalry) me).attack(def);
//										myflag = false;
//									} else {
//										((Infantry) me).attack(def);
//										myflag = false;
//									}
//									System.out.println(me.toString());
//									System.out.println(def.toString());
//								} catch (FriendlyFireException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							} else {System.out.println("takhalof");
//								try {
//									if (def instanceof Archer) {
//										((Archer) def).attack(me);
//										myflag = true;
//									} else if (def instanceof Cavalry) {
//										((Cavalry) def).attack(me);
//										myflag = true;
//									} else {
//										((Infantry) def).attack(me);
//										myflag = true;
//									}
//									System.out.println(me.toString());
//									System.out.println(def.toString());
//								} catch (FriendlyFireException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}}
//						 if(defendingCity.getDefendingArmy().getUnits().isEmpty() && !army.getUnits().isEmpty()) {
//							JOptionPane.showConfirmDialog(null, "You Won this Battle !!");
//							game.occupy(army, defendingCity.getName());
//						}
//						 
//					} else {
//						JOptionPane.showConfirmDialog(null, "You Lost this Battle  '_' ");
//					}
//				} 
//			} else if (myarmy.getSelectedIndex() != 0 && defendingarmy.getSelectedIndex() == 0) {
//				JOptionPane.showConfirmDialog(null, "You Must Choose a Unit from the Defending Army To Attack!");
//			} else if (myarmy.getSelectedIndex() == 0 && defendingarmy.getSelectedIndex() != 0) {
//				JOptionPane.showConfirmDialog(null, "You Must Choose a Unit from Your Army To Attack!");
//			} else {
//				JOptionPane.showConfirmDialog(null, "You Must Choose a Unit To Attack!");
//
//			}
//		 if(defendingCity.getDefendingArmy().getUnits().isEmpty() && !army.getUnits().isEmpty()) {
//				JOptionPane.showConfirmDialog(null, "You Won this Battle !!");
//				game.occupy(army, defendingCity.getName());
//			}
//			 
//		 else {
//			JOptionPane.showConfirmDialog(null, "You Lost this Battle  '_' ");
//		}
