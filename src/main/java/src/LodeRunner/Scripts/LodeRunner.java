import Main.Game;

public class LodeRunner {

	public static void main(String[] args) {

		// Create the game
		Game lodeRunner = new Game("LodeRunner");

		// Give JSON view to the game
		// Or give the right folder to do that ?
		/*
		lodeRunner.addJsonViews(new String[] {
			"Data/Menu/HomeView.json",
			"Data/Menu/WorldSelectionView.json",
			"Data/Menu/Test2.json",
		});*/

		lodeRunner.addJsonViews(new String[] {
			"Data/Menu/HomeView.json",
			"Data/Menu/WorldSelectionView.json",
			"Data/Menu/CreditsView.json",
			//"Data/Menu/SettingsView.json",	// TODO : sliders & co.
			"Data/Menu/LevelSelectionView.json",
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
