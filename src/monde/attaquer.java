package monde;

import java.util.ArrayList;
import java.util.List;

import com.jme.bounding.BoundingSphere;
import com.jme.math.Vector3f;

import dadou.Log;

public class attaquer {
	public Vector3f posCible = new Vector3f();
	public Vector3f posEnemi = new Vector3f();
	public Vector3f direction = new Vector3f();

	public BoundingSphere bs = new BoundingSphere();
	public List<Vector3f> chemin = new ArrayList<>();
	public int idx = 0;

	public void init(zombi e) {
		
		posCible.set(e.i.position(null));
		e.nid.gge.cheminDepuisVers(e.om, bs, posCible, chemin);
		if (chemin.size() <= 1) {

			e.attaquer = null;
			return;
		}
		float distAvecJoueur = chemin.get(chemin.size() - 1).distance(
				posCible);
		if (distAvecJoueur >= 4.5) {
	
			e.attaquer = null;
			return;
		}
		chemin.add(posCible.clone());
		idx=0;
		e.i.orienterVersPosition(e.om.donnerNom(), chemin.get(idx));
	
		e.attaquer = this;
		posCible.set(e.i.position(null));

	}

	public void acutaliserAttaque(zombi e) {
		if (e.i.position(null).distance(posCible) > Constante.distPourActualiserAttaque) {
	
			e.attaquer = null;
			return;
		}
		posEnemi.set(e.i.position(e.om.donnerNom()));
		if (e.i.position(null).distance(posEnemi) > Constante.rayonDetectionEnemi) {
			// Log.print("plus detecte");
			e.attaquer = null;
		}

	}

	public void deplacer(zombi e) {

		e.i.deplacerVers(e.om.donnerNom(), chemin.get(idx));
		idx++;

	}

	public boolean estFini() {
		return chemin.size() <= idx;
	}

}
