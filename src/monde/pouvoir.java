package monde;

import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.SauvegardeMonde;
import dadou.mutator.Animation;

public class pouvoir extends elementDecor {



	
	private static final long serialVersionUID = 2655497157940615066L;
	public boolean afficherMessage() {
		return false;
	}

	public void charger(SauvegardeMonde monde) {
		super.charger(monde);

		i.vitesseRotation(om.donnerNom(), 0.035f);
		i.rotation(om.donnerNom(), 0, 1, 0);

	}
	public String nomPouvoir() {
		return "Pouvoir";
	}

	@Override
	public void entree(ObjetMobile o, String position) {

		this.activer();

		this.c.joueur.pouvoirs.ajouter(this);
		this.etatJeux = new etatJeuxNonVisible();
		om.estVisible = false;
	}

	@Override
	public void tire(ObjetMobile om, String position) {
		// TODO Auto-generated method stub
		super.tire(om, position);

		if (!c.joueur.possedeAimant) {
			return;
		}
		Vector3f posCible = new Vector3f();
		posCible.set(i.position(null));
		Vector3f posObjet = new Vector3f();
		posObjet.set(om.getBox().getCenter());

		if (i.estAccessibleDepuis(posCible, posObjet, 0.25f, 0.25f, (o) -> {
			return o != om;
		})) {
			i.vitesseTranslation(om.donnerNom(), 2.0f);
			i.deplacerVers(om.donnerNom(), posCible);
		}
	}

	@Override
	public void finDeplacement(ObjetMobile om) {
		i.vitesseRotation(om.donnerNom(), 0.035f);
		i.rotation(om.donnerNom(), 0, 1, 0);

	}

	@Override
	public void collisionCamera(ObjetMobile om) {

		this.activer();

		this.c.joueur.pouvoirs.ajouter(this);
		this.etatJeux = new etatJeuxNonVisible();
		om.estVisible = false;
		// i.supprimer(om.donnerNom());

	}

	public void activer() {

	}

}
