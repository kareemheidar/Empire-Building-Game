package gui;

import java.awt.*;

import gui.shit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import buildings.*;
import gui.map;
import units.*;
import engine.*;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;

import java.util.*;

public class kickOff extends JFrame implements ActionListener,FocusListener {
	JPanel NamePanel = new JPanel();
	JButton submitName = new JButton("Submit Name");
	JButton cairo = new JButton("Cairo");
	JButton sparta = new JButton("Sparta");
	JButton rome = new JButton("Rome");
	JTextField name = new JTextField();
	String playerName = "";
	String city = "";
	JPanel sec = new JPanel();
	JPanel tri = new JPanel();
	JButton start = new JButton();
	JPanel startingPanel = new JPanel();
	// world map panel attributes
	JPanel map = new JPanel();
	JPanel playerInfo = new JPanel();
	JLabel info = new JLabel();
	double food = 0;
	double money = 0;
	static JPanel lol = new JPanel();
	JPanel lmao = new JPanel();
	JButton cairoInfo = new JButton("Cairo");
	JButton spartaInfo = new JButton("Sparta");
	JButton romeInfo = new JButton("Rome");
	static Player player = null;
	static Game game = null;
	JButton endturn = new JButton("End Turn");
	boolean cairoFlag = false;
	boolean spartaFlag = false;
	boolean romeFlag = false;
	static JTextArea tres;
	static JTextArea tres1;
	static JTextArea tres2;
	JPanel view;
	JPanel smallp;
	JComboBox controlledArmies;
	JButton attack = new JButton("Attack");
	JButton lay = new JButton("Lay Siege");
	JLabel mapLabel = null;
	String targetedcity;
	cityView cityview;
	JFrame attackjframe;
	JComboBox selectArmy;
	JComboBox selectArmy2;
	JFrame gameover;
	JButton endgame;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	JButton idleArmy = new JButton("Idle Army");
	JButton marchingArmy = new JButton("Marching Army");
	JButton besiegingArmy = new JButton("Besieging Army");

	public kickOff() {

		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Start Game");
		setLayout(new BorderLayout());
		startingPanel.setPreferredSize(new Dimension(1920, 1080));
		startingPanel.setLayout(new BorderLayout());

		name.setPreferredSize(new Dimension(400, 40));
		name.setText("Enter Your Name");
		name.addFocusListener(this);

		JLabel select = new JLabel("Select A City To Start");
		select.setBounds(850, 150, 300, 50);
		select.setForeground(new Color(0xFFCC00));
		select.setFont(new Font("Verdana", Font.BOLD, 20));

		submitName.setFocusable(false);
		submitName.setPreferredSize(new Dimension(200, 40));
		submitName.addActionListener(this);
		submitName.setBackground(new Color(0xFFCC00));
		submitName.setBorder(BorderFactory.createEtchedBorder());

		cairo.addActionListener(this);
		sparta.addActionListener(this);
		rome.addActionListener(this);

		NamePanel.setBackground(new Color(0x1C1105));
//		NamePanel.setBounds(0, 0, 1980, 100);
		NamePanel.setPreferredSize(new Dimension(400, 200));
		NamePanel.setLayout(null);
		name.setBounds(700, 80, 400, 40);
		submitName.setBounds(1120, 80, 100, 40);
		NamePanel.add(name);
		NamePanel.add(submitName);
		NamePanel.add(select);

		ImageIcon c = new ImageIcon("source/cairos40.jpg");
		ImageIcon s = new ImageIcon("source/spartas2.jpg");
		ImageIcon r = new ImageIcon("source/romas500.jpg");
		cairo.setIcon(c);
		sparta.setIcon(s);
		rome.setIcon(r);

		sec.setBackground(new Color(0xFFCC00));
		sec.setPreferredSize(new Dimension(400, 100));
		sec.setLayout(new GridLayout(1, 3, 25, 0));
		sec.add(cairo);
		sec.add(sparta);
		sec.add(rome);

		ImageIcon startb = new ImageIcon("source/pppp.png");
		start.addActionListener(this);
		start.setPreferredSize(new Dimension(300, 110));
		start.setBackground(new Color(0x1C1105));
		start.setBorder(BorderFactory.createEtchedBorder());
		start.setFocusable(false);
		start.setIcon(startb);
//		0x1C1105
		tri.setBackground(new Color(0x1C1105));
		tri.setPreferredSize(new Dimension(400, 250));
		tri.add(start);

		startingPanel.add(NamePanel, BorderLayout.NORTH);
		startingPanel.add(sec, BorderLayout.CENTER);
		startingPanel.add(tri, BorderLayout.SOUTH);
		add(startingPanel, BorderLayout.CENTER);
		setVisible(true);

// world map panel stuff
		map.setLayout(null);
		mapLabel = new JLabel();
		mapLabel.setLayout(null);
		lmao.setBounds(0, 0, 1400, 1080);
		lol.setBounds(1400, 0, 520, 1080);

		attack.setPreferredSize(new Dimension(200, 50));
		attack.addActionListener(this);
		attack.setFocusable(false);
		attack.setBackground(new Color(0xDCDCDC));
		attack.setFont(new Font("Verdana", Font.BOLD, 18));

		lay.setPreferredSize(new Dimension(200, 50));
		lay.addActionListener(this);
		lay.setFocusable(false);
		lay.setBackground(new Color(0xDCDCDC));
		lay.setFont(new Font("Verdana", Font.BOLD, 18));

		idleArmy.setPreferredSize(new Dimension(220, 50));
		idleArmy.addActionListener(this);
		idleArmy.setFocusable(false);
		idleArmy.setBackground(new Color(0xDCDCDC));
		idleArmy.setFont(new Font("Verdana", Font.BOLD, 18));

		marchingArmy.setPreferredSize(new Dimension(220, 50));
		marchingArmy.addActionListener(this);
		marchingArmy.setFocusable(false);
		marchingArmy.setBackground(new Color(0xDCDCDC));
		marchingArmy.setFont(new Font("Verdana", Font.BOLD, 18));

		besiegingArmy.setPreferredSize(new Dimension(220, 50));
		besiegingArmy.addActionListener(this);
		besiegingArmy.setFocusable(false);
		besiegingArmy.setBackground(new Color(0xDCDCDC));
		besiegingArmy.setFont(new Font("Verdana", Font.BOLD, 18));

		endturn.setPreferredSize(new Dimension(405, 100));
		endturn.addActionListener(this);
		endturn.setFocusable(false);
		endturn.setBackground(Color.red);
		endturn.setForeground(new Color(0xFFCC00));
		endturn.setFont(new Font("Verdana", Font.BOLD, 30));

		cairoInfo.setBackground(new Color(0xFFCC00));
		spartaInfo.setBackground(new Color(0xFFCC00));
		romeInfo.setBackground(new Color(0xFFCC00));

		cairoInfo.setBounds(630, 790, 130, 30);
		mapLabel.add(cairoInfo);
		spartaInfo.setBounds(400, 55, 130, 30);
		mapLabel.add(spartaInfo);
		romeInfo.setBounds(690, 125, 130, 30);
		mapLabel.add(romeInfo);
		cairoInfo.addActionListener(this);
		spartaInfo.addActionListener(this);
		romeInfo.addActionListener(this);

		ImageIcon m = new ImageIcon("source/lol.png");
		lmao.setBackground(Color.black);
		mapLabel.setIcon(m);
		lmao.add(mapLabel);
		map.add(lmao);

		revalidate();
		repaint();

	}
//	public void viewCity(String s) {
//		
//		cView view = new cView(s, game);
////		view = new JPanel();
////		view.setBounds(50, 50, 100, 100);
////		view.setVisible(true);
////		view.setBackground(new Color(0xFFCC00));
////		lmao.setOpaque(false);
////		lmao.add(view);
////		map.add(lmao);
////		this.add(map);
//		mapLabel.add(view);
//		revalidate();
//		repaint();
//	}

	public static void main(String[] args) {
		JFrame j = new kickOff();

	}

	public static void setTreasury() {
		tres.setText("Treasury: " + player.getTreasury());

	}

	public static void setFood() {
		tres2.setText("Food: " + player.getFood());

	}

	public static void setTurn() {
		tres1.setText("Turn Count: " + game.getCurrentTurnCount());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitName) {
			playerName = name.getText();
			submitName.setEnabled(false);

		}
		if (e.getSource().equals(cairo)) {
			city = "Cairo";
			System.out.println("h");
		} else if (e.getSource().equals(sparta)) {
			city = "Sparta";
		} else if (e.getSource().equals(rome)) {
			city = "Rome";
		}
		if (e.getSource().equals(start)) {

			if (playerName.equals("") && city.equals("")) {
				toast t = new toast("Please enter your name and choose a city!", 850, 950);
				t.showtoast();
			} else if (playerName.equals("")) {
				toast t = new toast("Please enter your name!", 850, 950);
				t.showtoast();
			} else if (city.equals("")) {
				toast t = new toast("Please choose a city!", 850, 950);
				t.showtoast();
			} else {
				if (city.equals("Cairo")) {
					try {
						game = new Game(playerName, "Cairo");
						System.out.println("i");

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (city.equals("Sparta")) {
					try {
						game = new Game(playerName, "Sparta");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						game = new Game(playerName, "Rome");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				player = game.getPlayer();
				JLabel p1 = new JLabel("Player Name: " + player.getName());
				p1.setPreferredSize(new Dimension(400, 100));
				p1.setFont(new Font("Verdana", Font.BOLD, 20));
				lol.add(p1);
				JLabel p0 = new JLabel("Selected City: " + city);
				p0.setPreferredSize(new Dimension(400, 100));
				p0.setFont(new Font("Verdana", Font.BOLD, 20));
				lol.add(p0);
				tres1 = new JTextArea();
				tres1.setPreferredSize(new Dimension(400, 100));
				tres1.setFont(new Font("Verdana", Font.BOLD, 20));
				tres1.setText("Current Turn: " + game.getCurrentTurnCount());
				tres1.setBackground(new Color(0xCD7F32));
				tres1.setEditable(false);
				lol.add(tres1);

				tres = new JTextArea();
				tres.setPreferredSize(new Dimension(400, 100));
				tres.setFont(new Font("Verdana", Font.BOLD, 20));
				tres.setText("Treasury: " + player.getTreasury());
				tres.setBackground(new Color(0xCD7F32));
				tres.setEditable(false);
				lol.add(tres);
				tres2 = new JTextArea();
				tres2.setPreferredSize(new Dimension(400, 100));
				tres2.setFont(new Font("Verdana", Font.BOLD, 20));
				tres2.setText("Food: " + player.getFood());
				tres2.setBackground(new Color(0xCD7F32));
				tres2.setEditable(false);
				lol.add(tres2);

				lol.add(idleArmy);
				lol.add(besiegingArmy);
				lol.add(marchingArmy);
				lol.add(endturn);
				lol.add(attack);
				lol.setBackground(new Color(0xCD7F32));
				map.add(lol);
				startingPanel.setVisible(false);
				map.setVisible(true);
				this.add(map);

			}

		}

		if (player != null) {
			for (City c : player.getControlledCities()) {
				System.out.println(c.getName());
				if (c.getName().equals("Cairo")) {
					cairoFlag = true;
					System.out.println("j");
				} else if (c.getName().equals("Sparta")) {
					spartaFlag = true;
				} else if (c.getName().equals("Rome")) {
					romeFlag = true;
				}
			}
		}

		if (e.getSource() == idleArmy) {
			shit.getIdleArmy(player);
		}
		if (e.getSource() == marchingArmy) {
			shit.getMarchingArmy(player, game);
		}
		if (e.getSource() == besiegingArmy) {
			shit.getSiegingArmy(player, game);
		}
		if (e.getSource().equals(attack)) {
			attackjframe = new JFrame("Choose an Army to Start Attack");
			attackjframe.setBounds(750, 450, 400, 200);
			attackjframe.setLayout(null);
			selectArmy = new JComboBox();
			selectArmy.addActionListener(this);
			selectArmy.setBounds(50, 40, 300, 40);
			selectArmy.addItem("Choose Army");
			for (Army a : player.getControlledArmies()) {
				if (a.getCurrentStatus().equals(Status.BESIEGING)) {
					selectArmy.addItem("Army at" + a.getCurrentLocation());
				}
			}
			attackjframe.add(selectArmy);
			attackjframe.setVisible(true);
		}
		if(e.getSource().equals(selectArmy)) {

			int x = selectArmy.getSelectedIndex();
			for (int i = 0; i < player.getControlledArmies().size(); i++) {
				if ((i + 1) == x) {
					for(City c : game.getAvailableCities()) {
						if(c.getName().equals(player.getControlledArmies().get(i).getCurrentLocation())) {
							new BattleView(game, c, player.getControlledArmies().get(i));

						}
					}
				}
			}
		}
		if (e.getSource().equals(cairoInfo)) {
			if (cairoFlag) {
				cityview = new cityView("Cairo", game);
			} else {
				toast t = new toast("You Don't Own This City", 850, 950);
				t.showtoast();
				int j = JOptionPane.showConfirmDialog(null, "Do You Want to Target this City?", "Cairo",
						JOptionPane.YES_NO_OPTION);
				if (j == JOptionPane.YES_OPTION) {
					if (player.getControlledArmies().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You Don't Have an army!");
					} else {
						JFrame jf = new JFrame();
						jf.setBounds(750, 450, 400, 200);
						jf.setLayout(null);
						controlledArmies = new JComboBox();
						controlledArmies.addActionListener(this);
						controlledArmies.setBounds(50, 40, 300, 40);
						controlledArmies.addItem("Choose Army");
						int x = 0;
						for (Army a : player.getControlledArmies()) {
							x++;
							controlledArmies.addItem("Army " + x);
						}
						jf.add(controlledArmies);
						jf.setVisible(true);
						targetedcity = "Cairo";
					}
				}
			}

		}

		if (e.getSource().equals(controlledArmies)) {
			int x = controlledArmies.getSelectedIndex();
			for (int i = 0; i < player.getControlledArmies().size(); i++) {
				if ((i + 1) == x) {
					game.targetCity(player.getControlledArmies().get(i), targetedcity);
				}
			}

		}
		if (e.getSource().equals(spartaInfo)) {
			if (spartaFlag) {
				cityview = new cityView("Sparta", game);
//				viewCity("Sparta");
//				JFrame x = new JFrame();
//				x.setVisible(true);
//				x.setSize(1300, 1000);
//				cView pan = new cView("Sparta",game);
//				x.add(pan);
			} else {
				toast t = new toast("You Don't Own This City", 850, 950);
				t.showtoast();
				int j = JOptionPane.showConfirmDialog(null, "Do You Want to Target this City?", "Sparta",
						JOptionPane.YES_NO_OPTION);
				if (j == JOptionPane.YES_OPTION) {
					if (player.getControlledArmies().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You Don't Have an army!");
					} else {
						JFrame jf = new JFrame();
						jf.setBounds(750, 450, 400, 200);
						jf.setLayout(null);
						controlledArmies = new JComboBox();
						controlledArmies.addActionListener(this);
						controlledArmies.setBounds(50, 40, 300, 40);
						controlledArmies.addItem("Choose Army");
						int x = 0;
						for (Army a : player.getControlledArmies()) {
							x++;
							controlledArmies.addItem("Army " + x);
						}
						jf.add(controlledArmies);
						jf.setVisible(true);
						targetedcity = "Sparta";
					}
				}

			}

		}
		if (e.getSource().equals(romeInfo)) {
			if (romeFlag) {
				cityview = new cityView("Rome", game);
			} else {
				toast t = new toast("You Don't Own This City", 850, 950);
				t.showtoast();
				int j = JOptionPane.showConfirmDialog(null, "Do You Want to Target this City?", "Rome",
						JOptionPane.YES_NO_OPTION);
				if (j == JOptionPane.YES_OPTION) {
					if (player.getControlledArmies().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You Don't Have an army!");
					} else {
						JFrame jf = new JFrame();
						jf.setBounds(750, 450, 400, 200);
						jf.setLayout(null);
						controlledArmies = new JComboBox();
						controlledArmies.addActionListener(this);
						controlledArmies.setBounds(50, 40, 300, 40);
						controlledArmies.addItem("Choose Army");
						int x = 0;
						for (Army a : player.getControlledArmies()) {
							x++;
							controlledArmies.addItem("Army " + x);
						}
						jf.add(controlledArmies);
						jf.setVisible(true);
						targetedcity = "Rome";
					}
				}
			}

		}
		if (e.getSource() == endturn) {
			for (Army a : player.getControlledArmies()) {
				if (a.getCurrentStatus().equals(Status.MARCHING)) {
					if (a.getDistancetoTarget() == 0) {
						String[] str = { "Attack", "Lay Siege" };
						ImageIcon icon = new ImageIcon();
						int j = JOptionPane.showOptionDialog(null,
								"Target Reached, Choose Either to Attack or Lay Siege on " + a.getCurrentLocation(),
								a.getCurrentLocation(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
								icon, str, 0);
						if (j == 0) {
							for (City defendingcity : game.getAvailableCities()) {
								if (defendingcity.getName().equals(a.getCurrentLocation())) {
									new BattleView(game, defendingcity, a);
								}
							}
						} else if (j == 1) {
							System.out.println("shagha;");
							for (City c : game.getAvailableCities()) {
								if (c.getName().equals(a.getCurrentLocation())) {
									System.out.println(c.getName());
									System.out.println("okkkk");
									try {
										player.laySiege(a, c);
									} catch (TargetNotReachedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (FriendlyCityException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}
							}
						}
					}

				}
			}
			for (City c : game.getAvailableCities()) {
				if (c.isUnderSiege()) {
					if (c.getTurnsUnderSiege() == 3) {
						String[] str = { "Attack", "Simulate" };
						ImageIcon icon = new ImageIcon();
						int j = JOptionPane.showOptionDialog(null,
								"You Have To Choose To Either Attack Or Simulate on " + c.getName(), c.getName(),
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, str, 0);
						System.out.println(j);
						if (j == 0) {
							for (Army a : player.getControlledArmies()) {
								if (a.getCurrentStatus().equals(Status.BESIEGING)) {
									if (a.getCurrentLocation().equals(c.getName())) {
										new BattleView(game, c, a);
									}
								}
							}
						} else if (j == 1) {
							for (Army a : player.getControlledArmies()) {
								if (a.getCurrentStatus().equals(Status.BESIEGING)) {
									if (a.getCurrentLocation().equals(c.getName())) {
										try {
											game.autoResolve(a, c.getDefendingArmy());
											if (a.getUnits().isEmpty()) {
												JOptionPane.showMessageDialog(null, "You Have Lost The Battle!");
												c.setUnderSiege(false);
											} else if (c.getDefendingArmy().getUnits().isEmpty()) {
												JOptionPane.showMessageDialog(null, "You Have Won The Battle!");
												c.setUnderSiege(false);
											}
										} catch (FriendlyFireException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
							}
						}

					}
				}
			}
			game.endTurn();
			cityview.dispose();
			setTreasury();
			setFood();
			setTurn();
			this.revalidate();
			this.repaint();
			if(game.isGameOver()) {
				endturn.setEnabled(false);
				if(player.getControlledCities().size()==game.getAvailableCities().size()) {
					gameover = new JFrame();
					gameover.setBounds(660, 340, 600, 400);
					gameover.setLayout(null);
					ImagePanel img = new ImagePanel("source/lost1.jpeg");
					img.setBounds(0,-45,600,400);
					gameover.add(img);
					gameover.setVisible(true);
				}
				else {
					gameover = new JFrame();
					gameover.setBounds(660, 340, 600, 400);
					gameover.setLayout(null);
					ImagePanel img = new ImagePanel("source/lost1.jpeg");
					img.setBounds(0,-45,600,400);
					gameover.add(img);
					gameover.setVisible(true);
					endgame = new JButton("End Game");
					endgame.setBounds(200, 280, 200, 40);
					gameover.add(endgame);
					endgame.addActionListener(this);
				}

				
			}
		}
		if(e.getSource().equals(endgame)) {
			this.dispose();
			this.setVisible(false);
			gameover.dispose();
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource().equals(name)) {
//			name.setText("");
//		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

		
	}

}
