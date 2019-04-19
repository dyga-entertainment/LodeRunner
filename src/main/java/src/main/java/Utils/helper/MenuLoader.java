package Utils.helper;

import Controler.ControleurJeu;
import Model.Menu.*;
import Model.Menu.Component.ModelButton;
import Model.Menu.Component.ModelLabel;
import Model.Menu.Component.ModelPanel;
import Model.ViewType;
import Utils.WrapLayout;
import View.Buttons.ContextTransitionButton;
import View.Buttons.StandardButton;
import View.Buttons.ViewButton;
import View.MenuViews.ImagePanel;
import View.MenuViews.View;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class MenuLoader {

    public static void LoadMenuesModels() throws IOException, ParseException {

        String[] menusJson = new String[] {
            "Menu/HomeMenu.json",
            "Menu/Test1.json",
            "Menu/Test2.json",
        };

        ModelView[] menuViews = new ModelView[menusJson.length];

        JSONParser parser = new JSONParser();

        for (int i = 0; i < menusJson.length; i++) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(menusJson[0]);
            FileReader jsonFile = new FileReader(url.getPath());

            JSONObject menuObject = (JSONObject) parser.parse(jsonFile);

            System.out.println(menuObject.keySet());
            ModelView finalView = new ModelView((String) menuObject.get("name"));

            // Add the content of the view
            finalView.addView(constructComponent((JSONObject) menuObject.get("view")));

            menuViews[i] = finalView;
        }


        /** This is for test purpose */
        //for (int i = 0; i < menusJson.length; i++) {
        paintView(menuViews[0]);
    }

    private static ModelComponent constructComponent(JSONObject contentArray) {
        ModelComponent component;

        // Compute specific parameters
        switch ((String) contentArray.get("type")) {
            /** Here we add a JPanel to the parent view */
            case "Panel":
                System.out.println("[DEBUG] Create a Panel");
                component = new ModelPanel();

                // Options
                if(contentArray.containsKey("options"))
                    addOptions((ModelPanel)component, (JSONArray) contentArray.get("options"));

                // children components
                JSONArray childComponents = (JSONArray)contentArray.get("components");
                if(childComponents  != null) {
                    for (Object childComponent : childComponents) {
                        ((ModelPanel)component).add(constructComponent((JSONObject) childComponent));
                    }
                }

                break;
            /** Here we add a JButton to the parent view */
            case "Button":
                System.out.println("[DEBUG] Create a button");
                component = new ModelButton();

                // ActionListener
                if(contentArray.containsKey("onclick"))
                    component.addActionListener((String)contentArray.get("onclick"));
                // Options
                if(contentArray.containsKey("options"))
                    addOptions((ModelButton)component, (JSONArray) contentArray.get("options"));

                break;
            default:
                component = null;
                break;
        }

        // Basic setup
        InitComponent(component, contentArray);

        //if(contentArray.containsKey("borderLayoutConstraints"))
        //    component.addBorderLayoutConstraints((String) contentArray.get("borderLayoutConstraints"));

        System.out.println("[DEBUG] Component = " + component.toString());
        return component;
    }

    private static void InitComponent(ModelComponent component, JSONObject contentArray) {
        // Background image
        if(contentArray.containsKey("backgroundImage")) {
            JSONObject jsonImage = (JSONObject)contentArray.get("backgroundImage");

            // Get the url
            component.addBackgroundImageUrl((String) jsonImage.get("url"));

            // Get the origin point
            JSONObject originPtsJson = (JSONObject)jsonImage.get("startingCoordinate");
            if(originPtsJson != null) {
                component.setBackgroundStartingPoint(Math.toIntExact((long)originPtsJson.get("x")), Math.toIntExact((long)originPtsJson.get("y")));
            }

            // Get the preferred Size
            JSONObject dimensionJson = (JSONObject)jsonImage.get("PreferredSize");
            if(dimensionJson != null) {
                component.setBackgroundPreferredSize(Math.toIntExact((long)dimensionJson.get("width")), Math.toIntExact((long)dimensionJson.get("height")));
            }

            // Get additionnal information
            // TODO ?
        }

        // Others
        if(contentArray.containsKey("borderLayoutConstraints"))
            component.addBorderLayoutConstraints((String) contentArray.get("borderLayoutConstraints"));

    }

    private static void addOptions(ModelPanel component, JSONArray options) {
        if(options  != null) {
            for (Object option : options) {
                JSONObject jsonOption = (JSONObject) option;
                switch(jsonOption.keySet().toArray()[0].toString()) {
                    case "isOpaque":
                        component.setOpaque((boolean)jsonOption.get("isOpaque"));
                        break;
                    case "layout":
                        component.setLayout((String)jsonOption.get("layout"));
                        break;
                    case "isEnable":
                        component.setEnable((boolean)jsonOption.get("isEnable"));
                        break;
                }
                //finalModelView.add(constructComponent((JSONObject) component));
                //JSONObject jsonComponent = (JSONObject) component;
                //System.out.println(jsonComponent.keySet());
            }
        }
    }

    private static void addOptions(ModelButton component, JSONArray options) {
        if(options  != null) {
            for (Object option : options) {
                JSONObject jsonOption = (JSONObject) option;
                switch(jsonOption.keySet().toArray()[0].toString()) {
                    case "isOpaque":
                        component.setOpaque((boolean)jsonOption.get("isOpaque"));
                        break;
                    case "layout":
                        component.setLayout((String)jsonOption.get("layout"));
                        break;
                    case "text":
                        component.addText((String)jsonOption.get("text"));
                        break;
                    case "isEnable":
                        component.setEnable((boolean)jsonOption.get("isEnable"));
                        break;
                    case "pressedImage":
                        component.addPressedImageUrl((String)jsonOption.get("pressedImage"));
                        break;
                    case "nextView":
                        component.setNextView((String)jsonOption.get("nextView"));
                        break;
                    case "PreferredSize":
                        JSONObject dimensionJson = (JSONObject)jsonOption.get("PreferredSize");
                        component.setPreferredSize(Math.toIntExact((long)dimensionJson.get("width")), Math.toIntExact((long)dimensionJson.get("height")));
                        break;
                }
                //finalModelView.add(constructComponent((JSONObject) component));
                //JSONObject jsonComponent = (JSONObject) component;
                //System.out.println(jsonComponent.keySet());
            }
        }
    }

    private static void paintView(ModelView menuView) {
        JFrame j = new JFrame("Test");
        j.setSize(800, 800);
        j.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                j.requestFocus();
            }
        });
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** TODO use menuView to add Swing parameters and see what it looks like */

        ModelPanel mainComponent = menuView.getModelComponent();
        // Create the associated Panel
        //View mainView = new View(mainComponent.getBackgroundImageUrl(), c);

        // Dummy Controller
        ControleurJeu c = new ControleurJeu(null);
        //View mainView = new View(mainComponent.getBackgroundImageUrl(), c);
        ImagePanel mainView = createView(mainComponent);



        j.getContentPane().add(mainView);
        mainView.repaint();
        j.repaint();

        //j.pack();
        j.setVisible(true);


    }

    private static ImagePanel createView(ModelPanel mainComponent) {
        ImagePanel currentView = new ImagePanel(mainComponent.getBackgroundImageUrl());
        if(mainComponent.getBackgroundPreferredSize() != null)
            currentView.setPreferredSize(mainComponent.getBackgroundPreferredSize().width, mainComponent.getBackgroundPreferredSize().height);
        if(mainComponent.getBackgroundStartingPoint() != null)
            currentView.setStartingCoordinate(mainComponent.getBackgroundStartingPoint().x, mainComponent.getBackgroundStartingPoint().y);

        switch (mainComponent.getLayout()) {
            case BorderLayout:
                currentView.setLayout(new BorderLayout());
                break;
            case GridLayout:
                currentView.setLayout(new GridLayout(1,1));
                break;
            case WrapLayout:
                currentView.setLayout(new WrapLayout());
                break;
            default:
                break;
        }

        // Children
        for(ModelComponent child : mainComponent.getChildrenComponents()) {
            if(child instanceof ModelPanel) {
                System.out.println("A panel !");
                ModelPanel childPanel = (ModelPanel) child;
                ImagePanel subChildPanel = createView((ModelPanel)child);

                // Refactor
                if(childPanel.hasLayoutConstraint()) {
                    if(childPanel.hasBorderLayoutLayoutConstraint()) {
                        currentView.add(subChildPanel, childPanel.getBorderLayoutContraints());
                    } else {
                        currentView.add(subChildPanel, childPanel.getIndexLayout());
                    }
                } else {
                    currentView.add(subChildPanel);
                }

            } else if (child instanceof ModelLabel) {
                System.out.println("A Label !");
            } else if (child instanceof ModelButton) {
                System.out.println("A button !");
                ModelButton childButton = (ModelButton)child;
                ViewButton button = new ViewButton(
                    childButton.getText(),
                    childButton.getPressedImageUrl(),
                    childButton.isEnable(),
                    childButton.getNextView()
                );

                button.setPreferredSize(childButton.getPreferredSize());//new Dimension(150, 40));
                button.setBackgroundImage(childButton.getBackgroundImageUrl());

                // Refactor
                if(childButton.hasLayoutConstraint()) {
                    if(childButton.hasBorderLayoutLayoutConstraint()) {
                        currentView.add(button, childButton.getBorderLayoutContraints());
                    } else {
                        currentView.add(button, childButton.getIndexLayout());
                    }
                } else {
                    currentView.add(button);
                }
            }
        }

        return currentView;
    }

    private static void AddComponent(ImagePanel currentView, ModelComponent childPanel) {

    }
}
