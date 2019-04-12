package IHM.Views;

import IHM.Views.View;
import controleur.VueManager;

import java.awt.*;

import javax.swing.*;

public class SettingsView extends View {
	
	private JLabel title;
	
	public SettingsView(VueManager vueManager) {
		super(vueManager);

		String[] resolution = {"resolution 1", "resolution 2", "resolution 3"};
		
		this.setLayout(new GridLayout(5,1,0,0));

		this.title = new JLabel("Settings");
		Font pf = title.getFont();
		title.setFont(new Font(pf.getName(), Font.PLAIN, 22));
		this.add(this.title, BorderLayout.CENTER);
		
		JPanel resolutionComboBox = new JPanel();
		resolutionComboBox.add(new JLabel("Resolution"));
		JComboBox<String> combo = new JComboBox<String>(resolution);
		combo.setPreferredSize(new Dimension(150, 30));
		combo.setForeground(Color.blue);
		resolutionComboBox.add(combo);
		this.contentPanel.add(resolutionComboBox);


		// Volume UI slider
		JSlider volumeUISlider = new JSlider();
		this.contentPanel.add(volumeUISlider);

		// Volume SFX slider
		JSlider volumeSFXSlider = new JSlider();
		this.add(volumeSFXSlider);

		// Volume Music slider
		JSlider volumeMusicSlider = new JSlider();
		this.add(volumeMusicSlider);

		// Others settings ?
	}

}