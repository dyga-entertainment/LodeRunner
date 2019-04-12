package IHM.Views;

import IHM.IHM2;
import controleur.VueManager;
import jeu.Monde;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelsView extends View {

	private JLabel pa;

    public LevelsView(VueManager vueManager) {
            this(vueManager, new Monde("dd", null));
    }

	public LevelsView(VueManager vueManager, Monde m) {
            super(vueManager);
            int niveauChoisi = 1;
            this.setLayout(new BorderLayout());
            this.pa = new JLabel("Veuillez selectionner votre niveau");
            Font pf = pa.getFont();
            this.pa.setFont(new Font(pf.getName(), Font.PLAIN, 22));
            //this.add(this.pa, BorderLayout.NORTH);

            JPanel pAperçu = new JPanel();
            JPanel pBoutons = new JPanel();

            // Creation du panel aperçu des niveaux
            pAperçu.setLayout(new CardLayout());
            for(int i = 0 ; i < m.getNbNiveau() ; i++) {
                pAperçu.add(m.getNiveau(i).getApercu());
            }

            // Creation du panel boutons
            pBoutons.setLayout(new FlowLayout());
            JButton bd = new JButton("Suivant");
            JLabel n = new JLabel("jeu.Niveau : "+String.valueOf(niveauChoisi));
            JButton bg = new JButton("Precedent");
            pBoutons.add(bd);
            pBoutons.add(n);
            pBoutons.add(bg);

            JPanel apercu = new JPanel();
            apercu.add(new IHM2());

            this.add(this.pa, BorderLayout.NORTH);
            this.add(pAperçu, BorderLayout.CENTER);
            this.add(pBoutons, BorderLayout.SOUTH);
	}
}
