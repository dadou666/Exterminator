package monde;

import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.exploration.GrapheExplorationContext;
import dadou.mutator.Animation;

public class perso extends ModeleEventInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4460721475705555L;
	public nid nid;
	public GrapheExplorationContext gec;
	public long naissance;

	@Override
	public void entree(ObjetMobile o, String position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortie(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void entreeZoneDetection(ElementJeux obj, ElementJeux oEntree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortieZoneDetection(ElementJeux obj, ElementJeux oSortie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tire(ObjetMobile om, String position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionCamera(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finDeplacement(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finSon(ObjetMobile om, String nomSon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finParcour(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demarer(ObjetMobile om, Object args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boucle(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finEchelle(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionObjetMobile(ObjetMobile om1, ObjetMobile om2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionZoneDetection(ObjetMobile om1, ObjetMobile om2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionDecor(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finOrientation(ObjetMobile om, Vector3f destination) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finAnimation(ObjetMobile om, Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finTrajectoire(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finDecomposition(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

}
