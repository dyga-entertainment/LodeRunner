package Utils;

import MVC.Model.Menu.*;
import MVC.Model.Menu.Component.ModelButton;
import MVC.Model.Menu.Component.ModelComponent;
import MVC.Model.Menu.Component.ModelLabel;
import MVC.Model.Menu.Component.ModelPanel;
import MVC.Model.Menu.Enums.ModelLayout;
import MVC.Model.Menu.Structs.Color;
import MVC.Model.Menu.Structs.Font;
import MVC.Model.Menu.Structs.Layouts.CardLayout;
import MVC.Model.Menu.Structs.Layouts.GridLayout;
import MVC.Model.Menu.Structs.Layouts.Layout;
import MVC.View.MenuViews.View;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class MenuLoader extends JsonLoader<ModelView> {

    @Override
    public ModelView parse(String path) {
        ModelView finalView = null;
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            FileReader fileReader = new FileReader(url.getPath());
            finalView = CreateNewView(fileReader);
        } catch (IOException | ParseException e) {
            System.out.println("Cannot load the following json file = " + path);
            finalView = null;
        }
        return finalView;
    }

    ///====================================
    /// HELPERS FUNCTIONS
    ///====================================

    private static ModelView CreateNewView(FileReader fileReader) throws IOException, ParseException {
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
            /** Add a JPanel to the parent view */
            case "Panel":
                System.out.println("[DEBUG] Create a Panel");
                component = new ModelPanel();


                // children components
                JSONArray childComponents = (JSONArray)contentArray.get("components");
                if(childComponents  != null) {
                    for (Object childComponent : childComponents) {
                        ((ModelPanel)component).add(constructComponent((JSONObject) childComponent));
                    }
                }

                break;
            /** Add a JButton to the parent view */
            case "Button":
                System.out.println("[DEBUG] Create a button");
                component = new ModelButton();

                // Listeners
                if(contentArray.containsKey("listeners"))
                    addListeners((ModelButton)component, (JSONArray) contentArray.get("listeners"));

                break;
            /** Add a JButton to the parent view */
            case "Label":
                System.out.println("[DEBUG] Create a Label");
                component = new ModelLabel();

                // Font
                if(contentArray.containsKey("font"))
                    addFont(component, (JSONObject) contentArray.get("font"));

                break;
            default:
                component = null;
                break;
        }

        // Basic setup
        InitComponent(component, contentArray);

        // Options
        if(contentArray.containsKey("options"))
            addOptions(component, (JSONArray) contentArray.get("options"));

        System.out.println("[DEBUG] Component = " + component.toString());
        return component;
    }

    private static void addFont(ModelComponent component, JSONObject jsonFont) {
        Font font = new Font();

        // Get the name
        font.name = (String) jsonFont.get("name");
        // Get the size
        font.size = Math.toIntExact((long)jsonFont.get("size"));
        // Get the style (Bold, Italic, etc.)
        switch ((String)jsonFont.get("style")) {
            case "Bold":
                font.style = java.awt.Font.BOLD;
                break;
            case "Italic":
                font.style = java.awt.Font.ITALIC;
                break;
            default:
                font.style = java.awt.Font.PLAIN;
                break;
        }
        // Get the color
        JSONObject fontColor = (JSONObject) jsonFont.get("color");
        font.color = new Color();
        font.color.red = Math.toIntExact((long)fontColor.get("r"));
        font.color.green = Math.toIntExact((long)fontColor.get("g"));
        font.color.blue = Math.toIntExact((long)fontColor.get("b"));

        component.addFont(font);
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

            // Get special option
            if((String)jsonImage.get("displayOptions") != null) {
                component.setBackgroundOptions((String)jsonImage.get("displayOptions"));
            }

            // Get additionnal information
            // TODO ?
        }

        // Others
        if(contentArray.containsKey("borderLayoutConstraints"))
            component.addBorderLayoutConstraints((String) contentArray.get("borderLayoutConstraints"));

    }

    private static void addOptions(ModelComponent component, JSONArray options) {
        if(options  != null) {
            for (Object option : options) {
                JSONObject jsonOption = (JSONObject) option;
                switch(jsonOption.keySet().toArray()[0].toString()) {
                    case "isOpaque": component.setOpaque((boolean)jsonOption.get("isOpaque")); break;
                    case "layout": createLayout(component,(JSONObject)jsonOption.get("layout")); break;
                    case "text": component.addText((String)jsonOption.get("text")); break;
                    case "isEnable": component.setEnable((boolean)jsonOption.get("isEnable")); break;
                    case "pressedImage": component.addPressedImageUrl((String)jsonOption.get("pressedImage"));break;
                    case "nextView": component.setNextView((String)jsonOption.get("nextView")); break;
                    case "PreferredSize":
                        JSONObject dimensionJson = (JSONObject)jsonOption.get("PreferredSize");
                        component.setPreferredSize(Math.toIntExact((long)dimensionJson.get("width")), Math.toIntExact((long)dimensionJson.get("height")));
                        break;
                }
            }
        }
    }

    private static void createLayout(ModelComponent component, JSONObject jsonLayout) {
        String layoutName = (String)jsonLayout.get("name");
        if(layoutName.equals(ModelLayout.GridLayout.name())) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.name = getLayoutName((String)jsonLayout.get("name"));
            gridLayout.rows = Math.toIntExact((long)jsonLayout.get("rows"));
            gridLayout.cols = Math.toIntExact((long)jsonLayout.get("cols"));
            gridLayout.hgap = Math.toIntExact((long)jsonLayout.get("hgap"));
            gridLayout.vgap = Math.toIntExact((long)jsonLayout.get("vgap"));
            component.setLayout(gridLayout);
        } else if(layoutName.equals(ModelLayout.CardLayout.name())) {
            CardLayout cardLayout = new CardLayout();
            cardLayout.name = getLayoutName((String)jsonLayout.get("name"));
            cardLayout.currentActiveView = 0;
            component.setLayout(cardLayout);
        } else {
            Layout layout = new Layout();
            layout.name = getLayoutName((String)jsonLayout.get("name"));
            component.setLayout(layout);
        }
    }

    private static ModelLayout getLayoutName(String layoutName) {
        switch (layoutName) {
            case "BorderLayout":
                return ModelLayout.BorderLayout;
            case "WrapLayout":
                return ModelLayout.WrapLayout;
            case "GridLayout":
                return ModelLayout.GridLayout;
            case "CardLayout":
                return ModelLayout.CardLayout;
            default:
                return ModelLayout.FlowLayout;
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
                // TODO
                break;
            case "onReleased":
                // TODO
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


}
