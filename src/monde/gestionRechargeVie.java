package monde;

public class gestionRechargeVie extends gestionRecharge {
	private static final long serialVersionUID = 1L;
	public int tempRecharge() {
		return Constante.pauseRechargeVie;
		
	}
	public void recharge(joueur c) {
	//	c.vie=c.vieMax;
		//c.i.ecrire("objet#coeur", c.vie+"/"+c.vieMax);
		
	}
	public String modele() {
		return "coeur";
	}

}
