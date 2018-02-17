package monde;

import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.mutator.Animation;

public class projectile extends ModeleEventInterface {
	public tourel tourel;
	

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
		if (this.tourel == null) {
			return;
		}
		joueur j = this.tourel.c.joueur;
		j.viePourRestitution+= Math.min(j.vie,Constante.degatTourel);
		j.vie -= Constante.degatTourel;
		
		i.ecrire("objet#coeur", "" + j.vie );
		if (j.vie < 0) {
			i.chargerSauvegarde();
			return;
		}
		this.tourel.etat = null;
		this.tourel = null;
		

	}

	@Override
	public void finDeplacement(ObjetMobile om) {
		// TODO Auto-generated method stub
		this.tourel.etat = null;
		this.tourel = null;
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
		if (tourel == null)  {
			i.supprimer(om.donnerNom());
		}

	}

	@Override
	public void finEchelle(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionObjetMobile(ObjetMobile om1, ObjetMobile om2) {
		this.tourel.etat = null;
		this.tourel = null;

	}

	@Override
	public void collisionZoneDetection(ObjetMobile om1, ObjetMobile om2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionDecor(ObjetMobile om) {
		this.tourel.etat = null;
		this.tourel = null;

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
