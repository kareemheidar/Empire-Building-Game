package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import gui.map;
import engine.Game;
import engine.Player;
public class testhere extends JFrame{
	JPanel map = new JPanel();
	JPanel playerInfo = new JPanel();
	JLabel info = new JLabel();
	double food = 0;
	double money = 0;
	JPanel lol = new JPanel();
	JPanel lmao = new JPanel();
	JLabel lolLabel = new JLabel();
	JButton cairoInfo = new JButton("Cairo"); 
	JButton spartaInfo = new JButton("Sparta"); 
	JButton romeInfo = new JButton("Rome");
	Player player = null;
	Game game = null;
	public testhere() {
		
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Start Game");
		
		map.setLayout(null);
		JLabel mapLabel = new JLabel();
		mapLabel.setLayout(null);
		lmao.setBounds(0,0,1400,1080);
		lol.setBounds(1400,0,520,1080);
		lol.setLayout(new BorderLayout());
		
		
		lolLabel.setBackground(Color.red);
		JLabel x = new JLabel();
		x.setPreferredSize(new Dimension(50,50));
		x.setBounds(1500,500,100,100);
		x.setBackground(Color.red);
		
		lol.add(x,BorderLayout.CENTER);
		
		cairoInfo.setBackground(new Color(0xFFCC00));
		spartaInfo.setBackground(new Color(0xFFCC00));
		romeInfo.setBackground(new Color(0xFFCC00));
	
		cairoInfo.setBounds(630, 790, 130, 30);
		mapLabel.add(cairoInfo);
		spartaInfo.setBounds(400, 55, 130, 30);
		mapLabel.add(spartaInfo);
		romeInfo.setBounds(690, 125, 130, 30);
		mapLabel.add(romeInfo);

		ImageIcon m = new ImageIcon("source/lol.png");
		lmao.setBackground(Color.black);
		mapLabel.setIcon(m);
		lmao.add(mapLabel);
		map.add(lmao);
		map.add(lol);
		this.add(map);
		this.setVisible(true);

		revalidate();
		repaint();

		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame shit = new testhere();
	}
	public void actionPerformed(ActionEvent e) {
	
	}

}
