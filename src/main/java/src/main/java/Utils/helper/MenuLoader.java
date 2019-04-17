package Utils.helper;

import Model.Menu.ModelButton;
import Model.Menu.ModelComponent;
import Model.Menu.ModelPanel;
import Model.Menu.ModelView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class MenuLoader {

    public static void LoadMenuesModels() throws IOException, ParseException {



        String[] menusJson = new String[] {
            "HomeMenu.json"
        };

        ModelView[] menuViews = new ModelView[menusJson.length];

        JSONParser parser = new JSONParser();

        for (int i = 0; i < menusJson.length; i++) {
            URL url = Thread.currentThread().getContextClassLoader().getResource("Menu/HomeMenu.json");
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
            case "Panel":
                System.out.println("[DEBUG] Create a Panel");
                component = new ModelPanel();

                // Background image
                if(contentArray.containsKey("backgroundImage"))
                    component.addBackgroundImageUrl((String) contentArray.get("backgroundImage"));
                // children components
                JSONArray childComponents = (JSONArray)contentArray.get("components");
                if(childComponents  != null) {
                    for (Object childComponent : childComponents) {
                        component.add(constructComponent((JSONObject) childComponent));
                        //JSONObject jsonComponent = (JSONObject) component;
                        //System.out.println(jsonComponent.keySet());
                    }
                }
                break;

            case "Button":
                System.out.println("[DEBUG] Create a button");
                component = new ModelButton();

                // ActionListener
                if(contentArray.containsKey("onclick"))
                    component.addActionListener((String)contentArray.get("onclick"));
                break;
            default:
                component = null;
                break;
        }
        // Options
        if(contentArray.containsKey("options"))
            addOptions(component, (JSONArray) contentArray.get("options"));

        System.out.println("[DEBUG] Component = " + component.toString());
        return component;
    }

    private static void addOptions(ModelComponent component, JSONArray options) {
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
                }
                //finalModelView.add(constructComponent((JSONObject) component));
                //JSONObject jsonComponent = (JSONObject) component;
                //System.out.println(jsonComponent.keySet());
            }
        }
    }

    private static void paintView(ModelView menuView) {
        JFrame j = new JFrame("Test");

        /** TODO use menuView to add Swing parameters and see what it looks like */

        j.setSize(800, 800);
        j.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                j.requestFocus();
            }
        });
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        j.setVisible(true);


    }
}
