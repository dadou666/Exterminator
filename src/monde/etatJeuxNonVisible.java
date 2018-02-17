package monde;

public class etatJeuxNonVisible extends etatJeux {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void appliquer(elementDecor ed) {
		ed.om.estVisible =false;
		
	}

}
