package monde;

public class gestionRechargeMunition extends gestionRecharge {

	private static final long serialVersionUID = 1L;
	public int tempRecharge() {
		return Constante.pauseRechargeMunition;
	}
	public void recharge(joueur c) {
	//	c.munitions =c.munitionMax;
		c.i.tireActif(true);
		//c.i.ecrire("objet#cercle", +c.munitions+"/"+c.munitionMax);
		
		
	}
	public String modele() {
		return "cercle";
	}

}
