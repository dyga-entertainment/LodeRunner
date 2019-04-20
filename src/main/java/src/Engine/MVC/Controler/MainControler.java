package MVC.Controler;

import MVC.Model.MainModel;
import MVC.Model.MainModelLegacy;
import MVC.Model.ViewType;
import Utils.exceptions.BlocNonCreusableException;
import Utils.Tuple;
import MVC.View.Buttons.ContextTransitionButton;
import MVC.View.Buttons.StandardButton;
import MVC.View.MainView;
import MVC.View.Menu.ViewButton;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainControler implements KeyListener, MouseListener, ActionListener  {

	// NEW META
	private MainView mainView;
	private MainModel mainModel;

	private static String INPUT_PATH = "Inputs.ButtonsCallback"; // Temporary

	public static Dictionary<String, Tuple<Class, Method>> controlerMethods = new Hashtable<>();

	// END NEW META

	private MainModelLegacy model;
	private boolean actionCreuser;

	private List<ActionListener> actionsListener = new LinkedList<>();

	/*
	public MainControler(MainModelLegacy m) {
		this.model = m;

		LoadGameScripts();
	}*/

	public MainControler(MainModel mainModel) {
		this.mainModel = mainModel;

		LoadGameScripts();
	}

	public void addView(MainView view) {
		this.mainView = view;
	}

	public void notifyView() {
		// TODO
		//this.view.notifyChanges();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.model.setUp(false);
			this.model.setLeft(true);
			this.model.setRight(false);
			this.model.setDown(false);
			break;
		case 38:
			this.model.setUp(true);
			this.model.setLeft(false);
			this.model.setRight(false);
			this.model.setDown(false);
			break;
		case 39:
			this.model.setUp(false);
			this.model.setLeft(false);
			this.model.setRight(true);
			this.model.setDown(false);
			break;
		case 40:
			this.model.setUp(false);
			this.model.setLeft(false);
			this.model.setRight(false);
			this.model.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.model.setLeft(false);
			break;
		case 38:
			this.model.setUp(false);
			break;
		case 39:
			this.model.setRight(false);
			break;
		case 40:
			this.model.setDown(false);
			break;
		}
		//System.out.println("arret");
		/* On recupere le heros et on le met a l'arret */
		// for (Personnage courant : this.model.getListPerso()) {
		if (this.actionCreuser) {
			this.actionCreuser = false;
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(MainControler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (this.model.getHeros().estVivant()) {
			this.model.getHeros().setMouvement(false);
			if (this.model.getHeros().isRegardeAGauche()) {
				//this.model.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_left.png");
			} else {
				//this.model.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_right.png");
			}
		}
		// this.vue.getPerso().getSprite().repaint();
		// this.vueJeu.getPerso().repaint();
		/* force la remise a niveau du sprite principal */
		// this.vueJeu.getPerso().getSprite().playSequence(Images.explosion,
		// false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		switch (arg0.getKeyChar()) {
		case 'c':
			/* Creuser un bloc du niveau */
			try {
				this.model.creuser();
				this.actionCreuser = true;
			} catch (BlocNonCreusableException e) {
				System.out.println(e);
			}
			break;
		case 'r':
			/* Recommencer un niveau */
			this.model.rebootNiveau();
			this.model.chargerNiveau();
			break;
		case 'v':
			/* Ramasser un gadget */
			this.model.ramasserGadget();
		case 'g':
		case 'p':
		case 'x':
			/* Utilser un gadget */
			this.model.utiliserGadget(arg0.getKeyChar());
		default:
			//System.out.println("rien");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.toString());
		System.out.println("mouseClicked " + e.getButton() + ", " + e.getComponent() + " !");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed " + e.getButton() + " !");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered " + e.toString() + " !");
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Called the action Performed of the MVC.Controler " + e.toString());

		System.out.println("[DEBUG] menu = " + ((ViewButton)e.getSource()).getListenersMap().toString());

		Enumeration<String> enumaration = ((ViewButton)e.getSource()).getListenersMap().elements();
		while(enumaration.hasMoreElements()) {
			String key = enumaration.nextElement();
			System.out.println(key);

			if(this.controlerMethods.get(key) != null) {
				System.out.println(((Method)this.controlerMethods.get(key).second).getName());
				try {
					// Debug
					System.out.println(((Method)this.controlerMethods.get(key).second).getName());
					System.out.println(((Class)this.controlerMethods.get(key).first).getDeclaredConstructor().newInstance());

					// Get the method that we need to call
					Method callbackMethod = ((Method)this.controlerMethods.get(key).second);
					// Get the class we need to call the method on
					Class callbackClass = this.controlerMethods.get(key).first;
					// Call it !
					callbackMethod.invoke(callbackClass.getDeclaredConstructor().newInstance(), (ViewButton)e.getSource());
				} catch (InstantiationException ex) {
					ex.printStackTrace();
				} catch (IllegalAccessException ex) {
					ex.printStackTrace();
				} catch (InvocationTargetException ex) {
					ex.printStackTrace();
				} catch (NoSuchMethodException ex) {
					ex.printStackTrace();
				}
			}
		}

		// OLD VERSION
		//test.get(((ContextTransitionButton)e.getSource()).getButtonName()).actionPerformed(e);
	}

	/**####################### Menu Function #######################*/

	private Dictionary<String, ActionListener> test = new Hashtable<>();

	public void addContextTransitionActionListener(StandardButton button) {
		// Add it in a dict
		test.put(button.getButtonName(), actionEvent -> ChangeView((ContextTransitionButton) actionEvent.getSource()));
	}

	public void addReturnContextActionListener(StandardButton button) {
		// Add it in a dict
		test.put(button.getButtonName(), actionEvent -> ReturnLastView());
	}

	public void addActionParamActionListener(StandardButton button) {
		// Add it in a dict
		test.put(button.getButtonName(), actionEvent -> HandleActionParams((StandardButton) actionEvent.getSource()));
	}


	public void ChangeView(ContextTransitionButton button) {
		ChangeView(ViewType.values()[button.getNextView().ordinal()]);
	}

	public void ChangeView(ViewType newView) {
		ChangeView(newView, false);
	}

	public void ChangeView(ViewType newView, boolean isBackButton) {
		System.out.println("[Controller function] Context transition : NewView = " + newView);
		// Update the model
		this.model.ChangeView(newView, isBackButton);

		// Notify the view that it needs to update
		//this.view.refreshView();
	}

	public void ReturnLastView() {
		System.out.println("[Controller function] Context transition : Return to last view");
		// Update the model
		this.model.ReturnLastView();

		// Notify the view that it needs to update
		//this.view.refreshView();
	}

	public void HandleActionParams(StandardButton button) {
		System.out.println("[Controller function] Action with parameters");

		this.model.Update();
		button.getStandardImage();

	}

	public void changeView(String nextView) {
		// Update the model
		this.mainModel.updateView(nextView);

		// notify the view
		this.mainView.paint();
	}



	public static void LoadGameScripts() {
		//ClassLoader classLoader = MainControler.class.getClassLoader();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		/*
		try {
			Class aClass = classLoader.loadClass("GameCode");
			System.out.println("aClass.getName() = " + aClass.getName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}*/

		/*
		assert classLoader != null;
		//String path = new String(INPUTS_FOLDER_NAME).replace('.', '/');
		Enumeration resources = null;
		try {
			resources = classLoader.getResources("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<File> dirs = new ArrayList();
		while (resources.hasMoreElements()) {
			URL resource = (URL) resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}*/

		ArrayList<Class> classes = new ArrayList();
		// TODO : Find a better solution for that..
		try {
			classes.add(Class.forName(INPUT_PATH));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		for (File directory : dirs) {
			try {
				//classes.addAll(findClasses(directory, INPUTS_FOLDER_NAME));
				// TODO : Find a better solution for that..
				classes.add(Class.forName(INPUT_PATH));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}*/

		for(Class currentClass : classes.toArray(new Class[classes.size()])) {
			System.out.println("[HERE] " + currentClass.getName());

			// Add all the methods for that class
			for (Method method : currentClass.getDeclaredMethods()) {
				String key = currentClass.getName() + "." + method.getName();
				System.out.println("[DEBUG] Added to the methods dicts = <" + key + ", " + currentClass.getName() + ">");
				controlerMethods.put(key, new Tuple<>(currentClass, method));
			}
		}
	}

	/*/
	private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
		List classes = new ArrayList();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			System.out.println(file.getName().contains(packageName));
			if(file.getName().contains(packageName)) {
				if (file.isDirectory()) {
					assert !file.getName().contains(".");
					classes.addAll(findClasses(file, ""));
				} else if (file.getName().endsWith(".class")) {
					classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				}
			}
		}
		return classes;
	}*/

	/**####################### End Menu Function #######################*/
}
