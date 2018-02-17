package monde;

import dadou.ObjetMobile;

public class arme extends pouvoir {
	private static final long serialVersionUID = -1213700215748451128L;

	public void activer() {

	
		this.c.joueur.energie += Constante.energieAugmentation;
		
		c.i.tireActif(true);
		c.i.continuerApresImpactSurObjetMobile(true);
		i.ecrire("objet#cercle", ""+this.c.joueur.energie);
	

	}
	public String nomPouvoir() {
		return "energie";
	}
	public boolean afficherMessage() {
		return false;
	}
	@Override
	public void boucle(ObjetMobile om) {
		this.gererHauteur();

	}
}
