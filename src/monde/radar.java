package monde;


public class radar extends pouvoir {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3831180917718102123L;

	public void activer() {
		this.c.joueur.possedeRadar = true;
		
	}
}
