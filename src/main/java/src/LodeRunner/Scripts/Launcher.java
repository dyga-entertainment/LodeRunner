import Main.Game;

public class Launcher {

	public static void main(String[] args) {

		// Create the game
		Game lodeRunner = new Game("LodeRunner");

		// Give JSON view to the game
		// Or give the right folder to do that ?
		/*
		lodeRunner.addJsonViews(new String[] {
			"Data/Menu/HomeMenu.json",
			"Data/Menu/WorldView.json",
			"Data/Menu/Test2.json",
		});*/

		lodeRunner.addJsonViews(new String[] {
			"Data/Menu/HomeMenu.json",
			"Data/Menu/WorldView.json",
		});

		// TODO : Should add more stuffs here

		lodeRunner.Run();

		// useless ??
		//modele.addObserver(gameVue);

		//launchWindow(); // ??



		//MainControler.LoadGameScripts();

		/*
		try {
			MenuLoader.LoadMenuesModels();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}*/
	}

}
