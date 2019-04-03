package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;

public class IHMProfil extends JPanel {
	
	private JLabel pa;
	private String nomChoisi;
	private JLabel apercu;
	private int imageChoisie;
	private int diffChoisie;
	private String[] imagesB;
	
	public IHMProfil() {
		
		String[] images = {"image 1", "image 2", "image 3","image 4"};
		this.imagesB = new String[] {"avatar_axel.png","avatar_yann.png","avatar_damien.png","avatar_greg.png"};
		String[] difficultes = {"Debutant", "Intermediaire", "Expert"};
		
		GridLayout g = new GridLayout(6,1,0,0);
		this.setLayout(g);
		this.pa = new JLabel("Veuillez creer votre profil");
		Font pf = pa.getFont();
		pa.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.add(this.pa, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Nom de profil"));
		
		JTextField jtf = new JTextField("");
		jtf.setPreferredSize(new Dimension(200,30));
        jtf.addCaretListener(new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                nomChoisi = text.getText();
                System.out.println("Nom : "+nomChoisi);
            }
        });
        
		p1.add(jtf);
		p1.setPreferredSize(new Dimension(200,150));
		p1.validate();
		
		JPanel p2 = new JPanel();
		//p2.setLayout(new GridLayout());
		p2.add(new JLabel("Image"));
		JComboBox<String> comboim = new JComboBox<String>(images);
		comboim.setPreferredSize(new Dimension(150, 30));
		comboim.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				imageChoisie = comboim.getSelectedIndex();
				apercu.setIcon(new ImageIcon(this.getClass().getResource("/ressources/Images/"+imagesB[imageChoisie])));
				apercu.repaint();
				System.out.println("Image n�"+imageChoisie);
			}
		});
	    //combo.setForeground(Color.blue);
		p2.add(comboim);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1,4,0,0));
		p3.add(new JLabel(""));
		p3.add(new JLabel("Apercu"));
		this.apercu = new JLabel(new ImageIcon(this.getClass().getResource("/ressources/Images/"+imagesB[this.imageChoisie])));
		p3.add(this.apercu);
		p3.add(new JLabel(""));
		p3.validate();
		
		JPanel p4 = new JPanel();
		p4.add(new JLabel("Difficulte"));
		
		JComboBox<String> combodiff = new JComboBox<String>(difficultes);
		combodiff.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				diffChoisie = combodiff.getSelectedIndex();
				System.out.println("Difficult� : "+diffChoisie);
			}
		});
		p4.add(combodiff);
				
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
	}
	
	public String getNomChoisi() {
		return this.nomChoisi;
	}
	
	public String getImageChoisie() {
		return this.imagesB[this.imageChoisie];
	}
	
	public int getDiffChoisie() {
		return this.diffChoisie;
	}
}
