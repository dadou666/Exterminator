package monde;

import com.jme.math.Vector3f;

public class porte extends elementDecor {
	
	public void ouvrir() {
		i.vitesseTranslation(om.donnerNom(), Constante.vitessePorte);
		Vector3f pos = new Vector3f();
		pos.set(om.getBox().getCenter());
		pos.y += 8.0f;
		om.testCollisionAvecDecor = false;
		i.deplacerVers(om.donnerNom(), pos);
	}

}
