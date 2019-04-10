package IHM;

import helper.Images;
import helper.ResourcesPaths;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHMCredit extends JPanel {
	
	private JLabel pa;
	private Image bg;
	
	public IHMCredit() {
		JPanel jpa = new JPanel();
		this.add(jpa);
		this.setLayout(new BorderLayout());
		this.pa = new JLabel();

		// Useful ?
		//this.pa.setIcon(new ImageIcon("SW.jpg"));
		//this.add(this.pa, BorderLayout.CENTER);

		this.bg = Images.getImageFromPath(ResourcesPaths.SPRITE_UI_PATH + "credits.png");
	}

	public void paint(Graphics g) {
		g.drawImage(bg,0,0,getSize().width, getSize().height, this);
	}
}
