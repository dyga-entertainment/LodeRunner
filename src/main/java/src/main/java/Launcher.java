import Utils.helper.MenuLoader;
import View.FenetrePrincipale;

public class Launcher {

	public static void main(String[] args) {
		//new FenetrePrincipale();

		try {
			MenuLoader.LoadMenuesModels();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
