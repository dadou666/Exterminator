package monde;

public class nidZombie extends nid {
	/**
	 * 
	 */
	private static final long serialVersionUID = -537971683929288169L;

	public compteurPerso compteur() {
		return this.c.compteurZombie;
	}
	public String model() {
		 return "perso#mdl";
	}
	public Class<? extends perso> clazz() {
		return zombi.class;
	}

}
