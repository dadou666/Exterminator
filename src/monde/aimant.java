package monde;

public class aimant extends pouvoir {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void activer() {
		this.c.joueur.possedeAimant = true;
	}
	public String nomPouvoir() {
		return "Magnet";
	}
}
