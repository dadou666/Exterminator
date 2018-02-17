package monde;

import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.ObjetMobilePourModelInstance;
import dadou.SauvegardeMonde;
import dadou.mutator.Animation;

public class fenetre extends elementDecor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9171721465050681647L;
	public construction c;
	@Override
	public void entree(ObjetMobile o, String position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortie(ObjetMobile om) {
		// TODO Auto-generated method stub

	}
	public void charger(SauvegardeMonde monde) {
		super.charger(monde);
		om.transparence =0.35f;
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
		om.transparence = 0.25f;

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
