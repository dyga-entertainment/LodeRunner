package IHM.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import IHM.Views.View;
import controleur.VueManager;
import jeu.Joueur;
import jeu.ModeleJeu;

public class WorldsView extends View {
	
	private JLabel pa;
	private JLabel apercu;
	private JPanel jpa;
	private JButton[] mondes;
	private JComboBox<String> cbniveaux;
	private JPanel pcb;
	private String[] nomImages;
	private int nbMondes;
	private int mondeChoisi;
	private int niveauChoisi;
	private Joueur profilCourant;
	private Image bg;
	
	public WorldsView(VueManager vueManager) {
		super(vueManager);

		this.setLayout(new GridLayout(1,1,0,20));
		
		this.mondes = new JButton[] {new JButton("Personnalise"), new JButton("Jungle"), new JButton("Glace"), new JButton("Magma"), new JButton("Enfer")};
		this.nomImages = new String[] {"personnalise","jungle","snow","fire","hell"};
		this.nbMondes = 5;
		this.mondeChoisi = 0;
		apercu = new JLabel();
	}
	
	public void majIHM(Joueur j) {
		ModeleJeu m = new ModeleJeu();
		this.removeAll();
		this.jpa = new JPanel();
		this.jpa.setLayout(new GridLayout(6,3,0,20));
		this.profilCourant = j;
		this.pa = new JLabel("Veuillez selectionner votre monde et votre niveau");
		Font pf = pa.getFont();
		pa.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.mondeChoisi = 1;
		this.niveauChoisi = 1 ;
		try {
			bg = ImageIO.read(this.getClass().getResource("/ressources/Images/background_jungle.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		for (int i = 0; i < this.nbMondes; i++) {
			JButton b = this.mondes[i];
			if ( i >= profilCourant.getUnlockedWorld()) {
				b.setEnabled(false);
				b.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/monde_verrouille.png")));
			} else {
				b.setEnabled(true);
				b.setText(mondes[i].getText().split(" ")[0]);
			    b.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/background_"+nomImages[i]+".png")));
			}
			this.mondes[i].setDropTarget(null);
			this.mondes[i].setBorder(null);
			this.mondes[i].setOpaque(false);
			this.mondes[i].setVerticalTextPosition(SwingConstants.CENTER); 
			this.mondes[i].setHorizontalTextPosition(SwingConstants.CENTER);
			this.mondes[i].setForeground(Color.WHITE);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (int j = 0; j < profilCourant.getUnlockedWorld(); j++){
						if ( mondes[j].getText() == b.getText() ){
							mondes[j].setEnabled(false);
							mondeChoisi = j;
							System.out.println("jeu.Monde "+j+" choisi");
							//apercu.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/background_"+nomImages[j]+".png")));
							//apercu.repaint();
							try {
								bg = ImageIO.read(this.getClass().getResource("/ressources/Images/background_"+nomImages[j]+".png"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							repaint();
						} else {
							mondes[j].setEnabled(true);
						}
					}
					
				}		
			});
			JPanel pvide = new JPanel();
			pvide.setOpaque(false);
			
			this.jpa.add(pvide);
			this.jpa.add(this.mondes[i]);
			JPanel pvide2 = new JPanel();
			pvide2.setOpaque(false);
			this.jpa.add(pvide2);
		}
		
		int seuil = 5;
		/*if ( this.mondeChoisi == this.profilCourant.getUnlockedWorld()){
			seuil = this.profilCourant.getUnlockedLevel();
		} else {
			seuil = 5;
		}*/
		String[] niveaux = new String[seuil];
		
		for (int i = 1; i <= seuil; i++){
			niveaux[i-1] = "jeu.Niveau " + String.valueOf(i);
		}
		this.cbniveaux = new JComboBox<String>(niveaux);
		this.cbniveaux.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				niveauChoisi = cbniveaux.getSelectedIndex();
				System.out.println("jeu.Niveau " + (niveauChoisi+1) + " choisi");
				repaint();
			}
		});
		JPanel pvide = new JPanel();
		pvide.setOpaque(false);
		this.pcb = new JPanel();
		this.pcb.add(this.cbniveaux);
		this.pcb.setOpaque(false);
		this.pcb.setBackground(null);
		this.pcb.repaint();
		this.jpa.add(pvide);
		this.jpa.add(this.pcb);
		
		this.add(this.jpa);
		//this.repaint();
		//this.add(this.apercu);
	}
	public void setProfilCourant(Joueur p){
		this.profilCourant = p;
	}
	
	/** Surcharge de la fonction paint() pour afficher notre image */ 
    public void paint(Graphics g) { 
    	g.drawImage(bg,0,0,getSize().width, getSize().height, this);

    	for ( int i = 0; i < nbMondes; i++){
    		this.mondes[i].repaint();
    	}
    	this.pcb.repaint();
    }
		
}