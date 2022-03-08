package gui;

import javax.swing.AbstractButton;
import engine.Game;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


import engine.Game;

public class Myframe1 extends JFrame implements ActionListener  {
	
	
	JButton submitname;
	JButton Cairo;
	JButton Rome;
	JButton Sparta;
	JTextField name;
	String y= "";
	JPanel map;
	Game g1 = null;
	JPanel map2;
	
    
	Myframe1(){
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1300,500);
		this.getContentPane().setBackground(Color.white);
		this.setTitle("CONQUER");
		
		map = new JPanel();
		
		
		submitname = new JButton();
		submitname.setText("submitname");
		submitname.setBounds(200,100,100,50);
		submitname.addActionListener(this);
		submitname.setActionCommand("submitname");
		
		Cairo = new JButton();
		Cairo.setText("CAIRO");
		Cairo.setBounds(200,100,100,50);
		Cairo.addActionListener(this);
		Cairo.setActionCommand("Cairo");
		
		Rome = new JButton();
		Rome.setText("ROME");
		Rome.setBounds(200,100,100,50);
		Rome.addActionListener(this);
		Rome.setActionCommand("Rome");
		
		Sparta = new JButton();
		Sparta.setText("SPARTA");
		Sparta.setBounds(100,100,100,50);
		Sparta.addActionListener(this);
		Sparta.setActionCommand("Sparta");
		
		JLabel label = new JLabel("Player Name");
		name = new JTextField(15);
		
		map.add(Cairo);
		map.add(Rome);
		map.add(Sparta);
		map.add(submitname);
		map.add(label);
		map.add(name);
		
		this.add(map);
		
		map.setVisible(true);
		this.setVisible(true);
		
		//map2 code goes here
		map2 = new JPanel();
		map2.setBackground(Color.GREEN);
		map2.setPreferredSize(new Dimension(500,500));
		map2.setVisible(false);
		
		
		this.revalidate();
		this.repaint();
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cairo") && !y.equals("")) {
			try {
				
				g1 = new Game(y, ((JButton) e.getSource()).getText());
				map.setVisible(false);
				map2.setVisible(true);
				this.add(map2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getActionCommand().equals("Rome") && !y.equals("")) {
			try {
				
				g1 = new Game(y, ((JButton) e.getSource()).getText());
				map.setVisible(false);
				map2.setVisible(true);
				this.add(map2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getActionCommand().equals("Sparta") && !y.equals("")) {
			try {
				
				g1 = new Game(y, ((JButton) e.getSource()).getText());
				map.setVisible(false);
				map2.setVisible(true);
				this.add(map2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(submitname)) {
			y= name.getText();	
		}
	}
	
	public static void main (String [] args) {
		Myframe1 x = new Myframe1();
		
	}
}