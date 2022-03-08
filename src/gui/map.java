package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import engine.Player;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class map extends JPanel{
	JPanel playerInfo = new JPanel();
	JLabel info = new JLabel();
	double food = 0;
	double money = 0;
	JPanel lol = new JPanel();
	JPanel shoot = new JPanel();
	JButton cairoInfo = new JButton("Cairo"); 
	JButton spartaInfo = new JButton("Sparta"); 
	JButton romeInfo = new JButton("Rome"); 
	private kickOff ko = null;
	public kickOff getKo() {
		return ko;
	}
	public void setKo(kickOff ko) {
		this.ko = ko;
	}
	public double getFood() {
		return food;
	}
	public void setFood(double food) {
		this.food = food;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public JButton getCairoInfo() {
		return cairoInfo;
	}
	public void setCairoInfo(JButton cairoInfo) {
		this.cairoInfo = cairoInfo;
	}
	public JButton getSpartaInfo() {
		return spartaInfo;
	}
	public void setSpartaInfo(JButton spartaInfo) {
		this.spartaInfo = spartaInfo;
	}
	public JButton getRomeInfo() {
		return romeInfo;
	}
	public void setRomeInfo(JButton romeInfo) {
		this.romeInfo = romeInfo;
	}
	public map () {
	
		this.setLayout(null);
//		this.setBackground(Color.green);
		JLabel mapLabel = new JLabel();
		mapLabel.setLayout(null);
		lol.setBackground(Color.blue);
		shoot.setBounds(0,0,1400,1080);
		lol.setBounds(1400,0,520,1080);
//		lol.setPreferredSize(new Dimension(1900,100));
		
		cairoInfo.setBackground(new Color(0xFFCC00));
		spartaInfo.setBackground(new Color(0xFFCC00));
		romeInfo.setBackground(new Color(0xFFCC00));
		
		cairoInfo.setPreferredSize(new Dimension(100,20));
		spartaInfo.setPreferredSize(new Dimension(100,20));
		romeInfo.setPreferredSize(new Dimension(100,20));
		
		cairoInfo.setBounds(630, 790, 130, 30);
		mapLabel.add(cairoInfo);
		spartaInfo.setBounds(400, 55, 130, 30);
		mapLabel.add(spartaInfo);
		romeInfo.setBounds(690, 125, 130, 30);
		mapLabel.add(romeInfo);
		cairoInfo.addActionListener(ko);
		spartaInfo.addActionListener(ko);
		romeInfo.addActionListener(ko);

		
		
//		mapLabel.setPreferredSize(new Dimension(1083,1080));
		ImageIcon m = new ImageIcon("source/lol.png");
		shoot.setBackground(Color.black);
		mapLabel.setIcon(m);
		shoot.add(mapLabel);
		add(shoot);
		add(lol);
		
		
		
		this.setVisible(true);
	}
}
