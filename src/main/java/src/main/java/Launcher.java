import Controler.ControleurJeu;
import Utils.helper.MenuLoader;

public class Launcher {

	public static void main(String[] args) {
		//new FenetrePrincipale();


		ControleurJeu.LoadGameScripts();

		try {
			MenuLoader.LoadMenuesModels();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
