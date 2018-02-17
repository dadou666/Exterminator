package monde;

import com.jme.math.Vector3f;

import dadou.ObjetMobile;
import dadou.SauvegardeMonde;
import dadou.VoxelTexture3D.CouleurErreur;
import dadou.mutator.Animation;
import dadou.mutator.AnimationEtape;

public class tourel extends elementDecor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -727057411631587810L;
	public static Animation ae;

	enum Etat {
		Orientation, Projection,PreparationTire,
	}

	public Etat etat;
	public Vector3f cible;

	public float echelle() {
		return 0.25f;
	}

	public void charger(SauvegardeMonde monde) {
		this.c = (construction) monde.monde;
	

		om.initTranslationRotationEchelle(position, rotation, echelle());
		i.vitesseRotation(om.donnerNom(), 0.25f);

	}

	public void dirigerVersJoueur() {
		
		if (i.estAccessibleDepuis(i.position(om.donnerNom()), i.position(null),
				0.25f, 0.25f,(e)->{
					return e != om;
				})) {
			i.orienterVersJoueur(om.donnerNom(), true);
			etat = Etat.Orientation;
			cible = new Vector3f();
			cible.set(i.position(null));
		}

	}

	@Override
	public void finOrientation(ObjetMobile om, Vector3f destination) {
		etat = Etat.PreparationTire;
		if (ae == null) {
		 ae= i.creerAnimation(om.mc.nomModele, "tourel","tourel2","tourel3","tourel4","tourel3","tourel2","tourel");
		}
		i.lancerAnimation(om.donnerNom(), ae, 10, false);


	}
	
	@Override
	public void finAnimation(ObjetMobile om, Animation animation) {
		projectile pr = new projectile();
		pr.tourel = this;
		i.tirerDepuisObjet(om.donnerNom(), cible, c.pd, null, pr);

	}
	
	@Override
	public void tire(ObjetMobile om, String position) {

		this.etatJeux = new etatJeuxNonVisible();
		this.detruire(om);
		
	}
	
	@Override
	public void boucle(ObjetMobile om) {
		if (om.listPO != null && om.mutator == null) {
			om.listPO = null;
			i.supprimer(om.donnerNom());
		}
	

	}
	
	public void detruire(ObjetMobile om) {
		try {
		
			i.decomposerAvecPhysique(om.donnerNom(), new Vector3f(25, 18, 25),
					4, 0.085f, 60, true);
			om.activePhysique();
			om.annulerCollisionCamera = true;
		

		} catch (CouleurErreur e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
