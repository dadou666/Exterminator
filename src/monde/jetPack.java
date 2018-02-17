package monde;

import dadou.ObjetMobile;


public class jetPack extends pouvoir {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void activer() {
	

	}
	public String nomPouvoir() {
		return "Jetpack";
	}
	@Override
	public void boucle(ObjetMobile om) {
		this.gererHauteur();

	}
}
