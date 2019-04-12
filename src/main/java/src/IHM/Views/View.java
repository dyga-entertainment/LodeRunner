package IHM.Views;

import IHM.Buttons.ContextTransitionButton;
import controleur.VueManager;
import helper.Images;
import helper.ResourcesPaths;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class View extends ImagePanel {

    private VueManager vueManager;

    /** JPanel in charge of buttons layout */
    protected JPanel contentPanel;
    protected JPanel buttonsPanel;

    /** List of buttons presents in the view */
    protected JButton[] buttons = new JButton[0];

    public View(VueManager vueManager) {
        this(vueManager, "");
    }

    public View(VueManager vueManager, String urlImage) {
        super(urlImage);

        this.vueManager = vueManager;

        // Default Settings for most of the menu
        this.setLayout(new BorderLayout());

        // Default initialization
        this.setupContentPanel();
        this.setupButtonsPanel();
    }

    public void setupContentPanel() {
        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(new BorderLayout());
        // Make the Container invisible by default
        this.contentPanel.setOpaque(false);
    }

    public void setupButtonsPanel() {
        this.buttonsPanel = new ImagePanel(ResourcesPaths.SPRITE_UI_PATH + "bandeau_menu.png");
        this.buttonsPanel.setLayout(new BorderLayout());
        //ImagePanel imagePanel = new ImagePanel(ResourcesPaths.SPRITE_UI_PATH + "bandeau_menu.png");
        //imagePanel.setBackground(Color.GREEN);
        //imagePanel.setLayout(new BorderLayout());

        //this.buttonsPanel = new JPanel();
        //imagePanel.add(this.buttonsPanel, BorderLayout.CENTER);
        //this.buttonsPanel.setBackground(new Color(0,0,0,0));

        //this.add(imagePanel, BorderLayout.SOUTH);

        // Make the Container invisible by default
        this.buttonsPanel.setOpaque(false);
    }

    protected JButton AddContextTransitionButton(String text, String standardImage, String selectedImage, VueManager.ViewType nextView) {
        return AddContextTransitionButton(text, standardImage, selectedImage, nextView, true, false);
    }

    protected JButton AddContextTransitionButton(String text, String standardImage, String selectedImage, VueManager.ViewType nextView, boolean isBackButton) {
        return AddContextTransitionButton(text, standardImage, selectedImage, nextView, true, isBackButton);
    }

    protected JButton AddContextTransitionButton(String text, String standardImage, String selectedImage, VueManager.ViewType nextView, boolean isEnable, boolean isBackButton) {
        ContextTransitionButton button = new ContextTransitionButton(text, standardImage, selectedImage, isEnable);
        button.setNextView(nextView);
        if(isBackButton) {
            button.addActionListener(this.vueManager.getReturnContextActionListener());
        }
        else
        {
            button.addActionListener(this.vueManager.getContextTransitionActionListener());
        }
        return button;
    }

    public void setup(int windowWidth, int windowHeight) {
        setup(windowWidth, windowHeight, 0, 0);
    }

    public void setup(int windowWidth, int windowHeight, int contentPanelWidth, int contentPanelHeight) {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.contentPanel.setPreferredSize(new Dimension(contentPanelWidth, contentPanelHeight));
    }

    /** Surcharge de la fonction paint() pour afficher notre image */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Centered content
        //this.contentPanel.paintComponents(g);
    }
}