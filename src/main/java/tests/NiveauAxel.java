package tests;

import jeu.Monde;
import jeu.Niveau;
import levels.MondeJungle;

public class NiveauAxel {
	
	private static Monde axel;
	
	/** Demo
	 * Permet de construite le niveau.
	 */
	private static void Axel(){
		axel = MondeJungle.Jungle();
	}
	
	/** getDemo
	 * Permet de recuperer le niveau construit.
	 * @return le niveau construit
	 */
	public static Niveau getAxel() {
		NiveauAxel.Axel();
		return axel.getNiveau(0);
	}

}
