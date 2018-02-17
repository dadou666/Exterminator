package monde;

import com.jme.math.Quaternion;
import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.ObjetMobilePourModelInstance;
import dadou.SauvegardeMonde;
import dadou.mutator.Animation;

public class elementDecor extends ModeleEventInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6974382185211685842L;
	public Vector3f position;
	public Quaternion rotation;
	public etatJeux etatJeux;
	public construction c;
	public float hauteur = 0.0f;
	public float hauteurInit = 0.0f;
	public float echelle() {
		return 1.0f;
	}


	public void sauvegarder() {
		if (position == null) {
			position = new Vector3f(om.box.getCenter());
			rotation = om.getRotationLocal();

		}

	}

	public void charger(SauvegardeMonde monde) {
		this.c = (construction) monde.monde;
	
		om.initTranslationRotationEchelle(position, rotation, 1.0f);

	}

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

	public void supprimerElementDecor() {
		i.supprimer(om.donnerNom());
	}

	public void deplacerElementDecor(boolean pousser) {
		Vector3f dir = i.donnerDirectionCamera();

		om.translation(dir(pousser, dir));
		position = new Vector3f(om.box.getCenter());
		
	}
	
	public void annulerModification() {
		
	}

	public Vector3f dir(boolean pousser, Vector3f dir) {
		float max = Math.max(Math.max(Math.abs(dir.x), Math.abs(dir.y)),
				Math.abs(dir.z));
		float dep = 1.0f;
		if (!pousser) {
			dep = -dep;
		}
		if (Math.abs(dir.x) >= max) {
			if (dir.x > 0) {
				return new Vector3f(dep, 0.0f, 0.0f);
			}
			return new Vector3f(-dep, 0.0f, 0.0f);
		}
		if (Math.abs(dir.y) >= max) {
			if (dir.y > 0) {
				return new Vector3f(0.0f, dep, 0.0f);
			}
			return new Vector3f(0.0f, -dep, 0.0f);
		}
		if (Math.abs(dir.z) >= max) {
			if (dir.z > 0) {
				return new Vector3f(0.0f, 0.0f, dep);
			}
			return new Vector3f(0.0f, 0.0f, -dep);
		}
		return null;

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
		om.testCollisionAvecAutreObjetMobile = false;

	}

	@Override
	public void boucle(ObjetMobile om) {
		// TODO Auto-generated method stub

	}
	
	public void gererHauteur() {
		if (hauteur > 0.0f) {
			hauteur -= Constante.vitesseDescenteObjet;
			if (hauteur <0.0f) {
				hauteur = 0.0f;
			}
		} else {
			return;
		}
		Vector3f pos = new Vector3f();
		pos.set(i.position(om.donnerNom()));
		pos.y=this.hauteurInit+this.hauteur;
		
		i.initialiserPosition(om.donnerNom(), pos);
		
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
