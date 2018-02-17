package monde;

import com.jme.math.Vector3f;

public class tirer {
	public boolean enCours =false;
	public Vector3f posCible = new Vector3f();
	
	public void orienter(zombi e) {
		enCours= true;
		posCible.set(e.i.position(null));
		e.i.orienterVersPosition(e.om.donnerNom(), posCible);
	}

}
