import Main.Game;

public class LodeRunner {

	public static void main(String[] args) {

		// Create the game
		Game lodeRunner = new Game("LodeRunner");

		// Give JSON view to the game
		// Or give the right folder to do that ?
		/*
		lodeRunner.addJsonViews(new String[] {
			"Data/Views/HomeView.json",
			"Data/Views/WorldSelectionView.json",
			"Data/Views/Test2.json",
		});*/

		lodeRunner.addJsonViews(new String[] {
			"Data/Views/HomeView.json",
			"Data/Views/WorldSelectionView.json",
			"Data/Views/CreditsView.json",
			//"Data/Views/SettingsView.json",	// TODO : sliders & co.
			"Data/Views/LevelSelectionView.json",
			"Data/Views/GameView.json",
		});

		lodeRunner.addJsonLevels(new String[] {
			"Data/Levels/Level_1-1.json",
		});

		lodeRunner.addJsonEntities(new String[] {
			"Data/Gameplay/Enemy/DogeMasque.json",
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
