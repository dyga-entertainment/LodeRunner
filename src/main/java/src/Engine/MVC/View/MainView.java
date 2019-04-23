package MVC.View;

import MVC.Controler.MainControler;
import MVC.Model.MainModel;
import MVC.Model.Menu.Component.ModelButton;
import MVC.Model.Menu.Component.ModelLabel;
import MVC.Model.Menu.Component.ModelPanel;
import MVC.Model.Menu.Component.ModelComponent;
import MVC.Model.Menu.ModelView;
import MVC.Model.Menu.Structs.Layouts.Layout;
import MVC.View.Menu.Component.ViewPanel;
import Utils.WrapLayout;
import MVC.View.Menu.Component.ViewButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Main MVC.View - VIEW
 * Takes care of showing to the player the UI describe by the model.
 * The controler will be attached to it in order to react to user events.
 */
public class MainView {

    /** The view know the model in order to get information from him */
    private MainModel mainModel;
    /** The view know the controler as well to attach him to each view. */
    private MainControler mainControler;

    private JFrame gameFrame;
    private ViewPanel activeGameView;

    /** This let the user get view component by using UUID from the model components */
    private static Dictionary<UUID, JComponent> activeViewComponentsByModel;

    /**
     * Constructor
     * @param gameName
     * @param mainModel
     * @param mainControler
     */
    public MainView(String gameName, MainModel mainModel, MainControler mainControler) {
        this.activeViewComponentsByModel = new Hashtable<>();
        this.mainModel = mainModel;
        this.mainControler = mainControler;
        this.gameFrame = new JFrame(gameName);

        // This info should be getted somewhere else ? Or default ?
        this.gameFrame.setSize(800, 800);

        // Should be the controler jobs.. ?
        this.gameFrame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                gameFrame.requestFocus();
            }
        });
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method used to repaint the view.
     * Can be called when an input has been received or (??) when it has been tag explicitly by the game loop
     */
    // Private ? Public ?
    public void paint() {

        // Get the current modelView display thanks to the model
        ModelView menuView = this.mainModel.getCurrentView();

        if(menuView != null) {
            if(this.mainModel.isDirty()) {
                if(this.activeGameView == null || !menuView.getName().equals(this.activeGameView.getName())) {
                    // Remove previous panel it
                    this.gameFrame.getContentPane().removeAll();
                    this.gameFrame.getContentPane().validate();

                    System.out.println("[DEBUG] Build a new ViewPanel to display = " + menuView.getName());
                    ModelPanel mainComponent = menuView.getModelComponent();

                    // Create the associated Panel
                    this.activeGameView = createView(mainComponent, this.mainControler);
                    // Useful in order to avoid rebuilding the same windows over and over..
                    this.activeGameView.setName(menuView.getName());

                    // Add it
                    this.gameFrame.getContentPane().add(this.activeGameView);
                }
            }
        }

        this.activeGameView.repaint();
        this.gameFrame.repaint();
        this.gameFrame.setVisible(true);
    }

    /**
     * Helper method to create a swing view from scratch
     * @param mainComponent
     * @param c
     * @return
     */
    private static ViewPanel createView(ModelPanel mainComponent, MainControler c) {
        ViewPanel currentView = new ViewPanel(mainComponent.getBackgroundImageUrl());
        if(mainComponent.getBackgroundPreferredSize() != null)
            currentView.setPreferredSize(mainComponent.getBackgroundPreferredSize().width, mainComponent.getBackgroundPreferredSize().height);
        if(mainComponent.getBackgroundStartingPoint() != null)
            currentView.setStartingCoordinate(mainComponent.getBackgroundStartingPoint().x, mainComponent.getBackgroundStartingPoint().y);

        Layout layout = mainComponent.getLayout();
        switch (layout.name) {
            case BorderLayout: currentView.setLayout(new BorderLayout()); break;
            case GridLayout:
                MVC.Model.Menu.Structs.Layouts.GridLayout gridLayout = (MVC.Model.Menu.Structs.Layouts.GridLayout)layout;
                currentView.setLayout(new GridLayout(gridLayout.rows,gridLayout.cols, gridLayout.hgap, gridLayout.vgap));
                break;
            case WrapLayout: currentView.setLayout(new WrapLayout()); break;
            case CardLayout: currentView.setLayout(new CardLayout()); break;
            default: currentView.setLayout(new FlowLayout()); break;
        }

        // Children
        for(ModelComponent child : mainComponent.getChildrenComponents()) {
            JComponent childComponent = null;
            /** Panel section */
            if(child instanceof ModelPanel) {
                ModelPanel childPanel = (ModelPanel) child;
                childComponent = createView((ModelPanel)child, c);
            /** Label section */
            } else if (child instanceof ModelLabel) {
                ModelLabel childLabel = (ModelLabel)child;
                JLabel label = new JLabel(childLabel.getText());
                MVC.Model.Menu.Structs.Font font = childLabel.getFont();
                label.setFont(new Font(font.name, font.style, font.size));
                label.setForeground(new Color(font.color.red, font.color.green, font.color.blue));

                //this.title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
                //this.title.setBackground(Color.BLACK);

                childComponent = label;
            /** Button section */
            } else if (child instanceof ModelButton) {
                ModelButton childButton = (ModelButton)child;
                ViewButton button = new ViewButton(
                    childButton.getText(),
                    childButton.getPressedImageUrl(),
                    childButton.isEnable(),
                    childButton.getNextView()
                );

                button.setPreferredSize(childButton.getPreferredSize());//new Dimension(150, 40));
                // Does the image need to be scaled , tiled, etc.
                button.setDisplayOption(childButton.getBackgroundOptions());
                // Set the background image
                button.setBackgroundImage(childButton.getBackgroundImageUrl(),
                    childButton.getBackgroundStartingPoint(),
                    childButton.getBackgroundPreferredSize()
                );

                button.addActionListener(c);
                button.addMouseListener(c);
                button.addKeyListener(c);

                // Listeners
                button.setListenerMethodsDict(childButton.getListenersMap());

                childComponent = button;
            } else {
                childComponent = null; // Unknown
            }

            childComponent.setOpaque(child.isOpaque());

            if(child.hasLayoutConstraint()) {
                if(child.hasBorderLayoutLayoutConstraint()) {
                    currentView.add(childComponent, child.getBorderLayoutContraints());
                } else {
                    currentView.add(childComponent, child.getIndexLayout());
                }
            } else {
                currentView.add(childComponent);
            }

            // Add a link to have the view Component from the model UUID
            activeViewComponentsByModel.put(child.getUuid(), childComponent);

        }
        return currentView;
    }

    public JComponent getViewComponentByUuid (UUID uuid) {
        return activeViewComponentsByModel.get(uuid);
    }

    public void show() {
        paint();
    }
}
