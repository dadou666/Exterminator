package monde;

import com.jme.math.Quaternion;
import com.jme.math.Vector3f;

public class etatJeuxPosition extends etatJeux {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector3f position;
	public Quaternion rotation;
	public void appliquer(elementDecor ed) {
		ed.om.initTranslationRotationEchelle(position, rotation, 1.0f);
	}
}
