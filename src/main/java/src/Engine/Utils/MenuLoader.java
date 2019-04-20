package Utils;

import MVC.Model.Menu.*;
import MVC.Model.Menu.Component.ModelButton;
import MVC.Model.Menu.Component.ModelPanel;
import MVC.View.Menu.ImagePanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class MenuLoader {

    public static void LoadMenuesModels() throws IOException, ParseException {

        String[] menusJson = new String[] {
            "Data/Menu/HomeMenu.json",
            "Data/Menu/WorldView.json",
            "Data/Menu/Test2.json",
        };


        ModelView[] menuViews = new ModelView[menusJson.length];



        for (int i = 0; i < menusJson.length; i++) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(menusJson[0]);
            FileReader fileReader = new FileReader(url.getPath());

            menuViews[i] = CreateNewView(fileReader);
        }


        /** This is for test purpose */
        //for (int i = 0; i < menusJson.length; i++) {
        paintView(menuViews[0]);
    }

    public static ModelView CreateNewView(FileReader fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject menuObject = (JSONObject) parser.parse(fileReader);

        System.out.println(menuObject.keySet());
        ModelView finalView = new ModelView((String) menuObject.get("name"));

        // Add the content of the view
        finalView.addView(constructComponent((JSONObject) menuObject.get("view")));

        return finalView;
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

                // Listeners
                if(contentArray.containsKey("listeners"))
                    addListeners((ModelButton)component, (JSONArray) contentArray.get("listeners"));


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
        if(contentArray.containsKey("background")) {
            JSONObject jsonImage = (JSONObject)contentArray.get("background");

            // Get the url
            component.addBackgroundImageUrl((String) jsonImage.get("imageUrl"));

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

    private static void addListeners(ModelButton component, JSONArray listeners) {
        if(listeners  != null) {
            for (Object listener : listeners) {
                CreateListener(component, listener);
            }
        }
    }

    private static void CreateListener(ModelButton component, Object listener) {
        JSONObject jsonListener = (JSONObject) listener;
        switch((String)jsonListener.get("name")) {
            case "mouse":
                for (Object method : (JSONArray)jsonListener.get("methods")) {
                    addMethods(component, (JSONObject)method);
                }
                break;
            case "onPressed":
                // todo
                break;
            case "onReleased":
                // todo
                break;
        }
    }

    private static void addMethods(ModelButton component, JSONObject method) {
        String name = (String)method.get("name");
        String functionName = (String)method.get("functionName");

        if(name != null && functionName != null) {
            component.addListenerMethod(name, functionName);
        }
    }

    private static void paintView(ModelView menuView) {
        JFrame j = new JFrame("ButtonsCallback");
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
        //MVC.View MainView = new MVC.View(mainComponent.getBackgroundImageUrl(), c);

        // Dummy Controller
        /*MainControler c = new MainControler(null);
        //MVC.View MainView = new MVC.View(mainComponent.getBackgroundImageUrl(), c);
        ImagePanel mainView = createView(mainComponent, c);



        j.getContentPane().add(mainView);
        mainView.repaint();
        j.repaint();

        //j.pack();
        j.setVisible(true);*/


    }

    private static void AddComponent(ImagePanel currentView, ModelComponent childPanel) {

    }
}
