package monde;


public class clef extends pouvoir {

	public String nom;
	private static final long serialVersionUID = 4067466305794116121L;
	public void activer() {
		this.c.joueur.listeClefs.add(nom);
	}

}
