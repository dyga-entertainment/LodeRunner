package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IHMB3 extends Bandeau {
	
	private int nbBouton;
	// Normalement un tableau
	private JPButton quitter;	
	
	public IHMB3() {
		this.nbBouton = 1;
		
		JPanel jpa1 = new JPanel();
		jpa1.setLayout(new GridLayout(2,3,0,0));
		
		this.quitter = new JPButton("Retour", "bouton_retour1.png", "bouton_retour2.png");
		
		jpa1.add(new JPanel());
		jpa1.add(this.quitter);
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());
		jpa1.add(new JPanel());

		this.add(jpa1);
	}
	
	public JPButton getButton(int i) {
		return this.quitter;
	}
	
	public int getNbButton() {
		return this.nbBouton;
	}
}
