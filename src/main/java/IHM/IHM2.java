package IHM;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHM2 extends JPanel {
	
	private JLabel pa;
    private Image bg; 

    /** Surcharge de la fonction paint() pour afficher notre image */ 
     public void paint(Graphics g) { 
    	 g.drawImage(bg,0,0,null); 
    }
    
	public IHM2() {
		this.setLayout(new BorderLayout());
		this.pa = new JLabel();
		//this.pa.setIcon(new ImageIcon("doge.jpg"));
		this.add(this.pa, BorderLayout.CENTER);
		try {
                    URL url = getClass().getResource("/ressources/Images/menu.png");
                    this.bg = ImageIO.read(new File(url.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
