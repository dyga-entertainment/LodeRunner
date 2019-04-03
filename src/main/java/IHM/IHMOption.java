package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHMOption extends JPanel {
	
	private JLabel pa;
	
	public IHMOption() {
		
		String[] resolution = {"resolution 1", "resolution 2", "resolution 3"};
		
		GridLayout g = new GridLayout(5,1,0,0);
		this.setLayout(g);
		this.pa = new JLabel("Options");
		Font pf = pa.getFont();
		pa.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.add(this.pa, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Resolution"));
		JComboBox<String> combo = new JComboBox<String>(resolution);
		combo.setPreferredSize(new Dimension(150, 30));
		//combo.setForeground(Color.blue);
		p1.add(combo);

		this.add(p1);
		// this.add(volume);
	}

}