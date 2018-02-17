package monde;

import java.lang.management.GarbageCollectorMXBean;

import com.jme.bounding.OrientedBoundingBox;
import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.Log;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.VoxelTexture3D.CouleurErreur;
import dadou.exploration.GrapheExplorationContext;
import dadou.mutator.Animation;

public class zombi extends perso {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5261248027308102026L;
	public int vie;

	public attaquer attaquer;

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
		if (gec == null) {
			return;
		}
		vie--;
		if (vie <= 0) {
			this.nid.c.joueur.augmenterVie();
			this.detruire(om);
		}
	}

	@Override
	public void collisionCamera(ObjetMobile om) {

		if (gec == null) {
			return;
		}
		joueur j = this.nid.c.joueur;

		j.vie -= Constante.degatEnemi;

		if (j.vie < 0) {
			i.chargerSauvegarde();
			return;
		}

		this.detruire(om);

	}

	@Override
	public void finDeplacement(ObjetMobile om) {
		// TODO Auto-generated method stub
		if (gec == null) {
			return;
		}
		if (attaquer != null) {
			if (attaquer.estFini()) {
				gec.initScan();
				attaquer = null;
				return;

			}
			attaquer.deplacer(this);

			return;
		}
		gec.finDeplacement(om);

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
		if (this.nid.c.joueur.possedeRadar) {
			i.initialiserCouleurObjectifPourObjetMobile(om.donnerNom(), "RED",
					1.0f, 50.0f, 0.08f);
		}
		i.vitesseTranslation(om.donnerNom(), 0.9f);
		i.vitesseRotation(om.donnerNom(), 0.10f);
		vie = Constante.vieEnemi;
		naissance = System.currentTimeMillis();
		if (this.om.getBoundingSphere().radius > this.nid.gge.rayon) {
			throw new Error(" enemi mauvaise echelle");
		}
		nid.compteur().totalEncours++;
		nid.compteur().total++;

	}

	@Override
	public void boucle(ObjetMobile om) {
		if (gec == null) {
			if (om.mutator == null) {
				om.listPO = null;
				i.supprimer(om.donnerNom());
			}
			return;
		}
		if (attaquer == null) {
			i.modifierModelClasse(om.donnerNom(), "mdl");
		} else {
			i.modifierModelClasse(om.donnerNom(), "mdlAttaque");
		}

		if (this.nid.c.joueur.possedeRadar) {
			if (om.cad == null) {
				i.initialiserCouleurObjectifPourObjetMobile(om.donnerNom(),
						"RED", 1.0f, 50.0f, 0.08f);
			}

		}

		i.vitesseTranslation(om.donnerNom(), Constante.vitesseEnemi);

		if (attaquer != null) {

			attaquer.acutaliserAttaque(this);

			return;
		}
		if (attaquer != null) {
			i.vitesseTranslation(om.donnerNom(), Constante.vitesseAttaque);
		}

		if (gec != null) {

			gec.boucle(om);
			if (!gec.alive) {
				this.detruire(om);
			}

		}

	}

	@Override
	public void finEchelle(ObjetMobile om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionObjetMobile(ObjetMobile om1, ObjetMobile om2) {
		if (gec == null) {
			return;
		}
		if (attaquer != null) {
			attaquer = null;
			gec.initScan();
		}
		if (gec == null) {
			return;
		}
		if (!(om2.mei instanceof zombi)) {
			Log.print(" collision non zombie ");
		}
		// this.detruire(om);
		gec.collisionObjetMobile(om1);

	}

	@Override
	public void collisionZoneDetection(ObjetMobile om1, ObjetMobile om2) {
		// TODO Auto-generated method stub

	}

	public void detruire(ObjetMobile om) {
		try {

			i.decomposerAvecPhysique(om.donnerNom(), new Vector3f(25, 18, 25),
					4, 0.085f, 60, true);
			om.activePhysique();
			om.annulerCollisionCamera = true;
			nid.compteur().totalEncours--;
			;
			if (gec != null) {
				if (!gec.alive) {

					nid.compteur().total--;
				} else {
					nid.compteur().totalDetruit--;
				}
				gec = null;
			}
		} catch (CouleurErreur e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void collisionDecor(ObjetMobile om) {
		// TODO Auto-generated method stub
		this.detruire(om);
	}

	@Override
	public void finOrientation(ObjetMobile om, Vector3f destination) {
		// TODO Auto-generated method stub

		if (attaquer == null) {
			gec.finOrientation(om);
		} else {
			attaquer.deplacer(this);
		}

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
