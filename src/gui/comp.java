package gui;

import javax.swing.JComponent;
import java.awt.*;


public class comp extends JComponent {
	
	private Image image;
	public comp (Image image) {
		this.image = image;
	}
	protected void paintComp(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
