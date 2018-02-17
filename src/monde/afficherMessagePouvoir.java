package monde;

public class afficherMessagePouvoir {

	long debut ;
	public afficherMessagePouvoir() {
		debut = System.currentTimeMillis();
	}
	public void process(joueur j) {
		if (System.currentTimeMillis()-debut > Constante.durreAffichageMessage) {
			j.afficherMessagePouvoir = null;
			j.i.activerTableauAvecTransition(null);
		}
		
	}

}
