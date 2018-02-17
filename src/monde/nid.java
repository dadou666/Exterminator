package monde;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jme.bounding.BoundingSphere;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;

import dadou.ElementJeux;
import dadou.Log;
import dadou.ModeleEventInterface;
import dadou.ObjetMobile;
import dadou.ObjetMobilePourModelInstance;
import dadou.Octree;
import dadou.SauvegardeMonde;
import dadou.VoxelTexture3D.CouleurErreur;
import dadou.exploration.GestionGrapheExploration;
import dadou.exploration.GrapheExploration;
import dadou.exploration.GrapheExplorationContext;
import dadou.exploration.OctreeExploration;
import dadou.graphe.GrapheElement;
import dadou.mutator.Animation;
import dadou.parallele.Traitement;
import dadou.tools.BrickEditor;

public class nid extends elementDecor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9203947455056797951L;

	transient public int t;
	public String color;

	public boolean estNidKuboid() {
		return false;
	}

	public compteurPerso compteur() {
		return null;
	}

	public String model() {
		return null;
	}

	public Class<? extends perso> clazz() {
		return null;
	}

	transient public GestionGrapheExploration gge;

	public void charger(SauvegardeMonde monde) {

		super.charger(monde);
		t = 0;
		gge = null;
		om.testCollisionAvecDecor = false;
		om.annulerCollisionCamera = false;
		om.testCollisionAvecAutreObjetMobile = false;

		i.vitesseRotation(om.donnerNom(), 0.05f);
		i.rotation(om.donnerNom(), 0, 1, 0);

		c.joueur.nombreNidTotal++;
		c.joueur.nombreNidEnCours++;
		i.ecrire("nid#model2", "" + (c.joueur.nombreNidEnCours) + "/"
				+ c.joueur.nombreNidTotal);

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

		if (!actif) {
			return;
		}
		if (this.c.joueur.pouvoirs.contientCroixRouge() && !init) {
			this.c.joueur.pouvoirs.supprimerCroixRouge();

			try {

				porte p = c.portes.get(this.color);
				if (p == null) {
					i.modifierTableau(Constante.tableauMessage);
					i.nombreElement(1);
					i.centrerMessage(true);
					i.ajouterMessage(" Vous avez gagné ");
					i.activerTableauAvecTransition(Constante.tableauMessage);
					c.joueur.afficherMessagePouvoir = new afficherMessagePouvoir();
				} else {
					p.ouvrir();
				}
				int idx = Integer.parseInt(c.joueur.nidCourant);
				c.joueur.nidCourant = "" + (idx + 1);
				nidZombie suivant = c.joueur.nidCourant();

				if (suivant != null) {
					suivant.actif = true;
					Log.print(" nid suivant null " + idx);
				} else {
					Log.print(" nid suivant null");
				}
				om.annulerCollisionCamera = true;
				c.joueur.nombreNidEnCours--;
				i.ecrire("nid#model2", "" + (c.joueur.nombreNidEnCours) + "/"
						+ c.joueur.nombreNidTotal);
				i.decomposerAvecPhysique(om.donnerNom(), new Vector3f(25, 18,
						25), 4, 0.085f, 60, true);
				om.activePhysique();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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

	Traitement traitement;

	@Override
	public void demarer(ObjetMobile om, Object args) {
		om.testCollisionAvecAutreObjetMobile = false;
		Vector3f pos = new Vector3f(om.getBox().getCenter());
		pos.y -= 4.0f;

		this.gge = i.creerGestionGrapheExploration(om.getBox().getCenter(),
				2.5f, 15, new ArrayList<>());
		if (!c.joueur.generation) {
			traitement = i.ajouter(() -> {
				this.chargeNid();
				this.gge.enregistrerTous();
				Log.print(" fin charger ");
			});
		}
	}

	public void creerGrapheExploration() {

	}

	boolean init = true;
	boolean actif = false;
	int idxCible = 0;
	GrapheExploration cible = null;

	GrapheExploration donnerCible() {

		/*
		 * GrapheExploration cible = this.gge.listeFeuilles.get(idxCible);
		 * idxCible++; if (idxCible >= this.gge.listeFeuilles.size()) { idxCible
		 * = 0; }
		 */

		GrapheExploration cibleTmp = this.gge.donnerGrapheExplorationJoueur();
		if (cibleTmp == null) {
			return cible;
		}
		cible = cibleTmp;
		return cible;
	}

	GrapheExploration donnerCible(GrapheExploration ancetre) {
		for (GrapheExploration cible : this.gge.listeFeuilles) {
			if (ancetre.estAncetreDe(cible))
				return cible;
		}
		return null;
	}

	public void chargeNid() {

		String fichierNid = "nid" + this.color + ".bin";

		try {
			if (gge.lire(fichierNid)) {
				// Log.print(" lire " + fichierNid);
				this.gge.listeFeuillesTrie();

				this.gge.filtrerAvecProfondeurAncetreCommun(0, 4);
				init = false;
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init = false;

	}

	public void calculerGrapheExplorationEnLargeur() {
		if (this.gge != null) {

			if (!this.gge.calculerGrapheExplorationEnLargeur(4) && init) {

				this.gge.listeFeuillesTrie();

				this.gge.filtrerAvecProfondeurAncetreCommun(0, 4);
				if (gge.grapheDebug != null) {
					gge.creerGrapheDebug();
					i.debugGraphe(gge.grapheDebug);
				}
				if (gge.listeFeuilles.size() == 1) {
					return;
				}

				this.gge.ecrire("nid" + this.color + ".bin");
				init = false;

			}

		}
		return;

	}

	@Override
	public void boucle(ObjetMobile om) {
		if (this.traitement != null) {
			if (!this.traitement.estTermine) {
				return;
			}
			this.i.liberer(traitement);
			traitement = null;
		}
		
		if (this.c.joueur.generation) {
			return;
		}
		if (init) {
			return;
		}
		if (!actif) {
			return;
		}
		if (this.gge.grapheDebug == null) {
			this.gge.creerGrapheDebug();
		}
		// i.debugGraphe(this.gge.grapheDebug);

		if (t == 0) {
			if (compteur().totalEncours >= Constante.nombreTeteMaxiEncours) {
				return;
			}
			if (this.donnerCible() == null) {
				return;
			}
			Vector3f posObjet = new Vector3f(om.getBox().getCenter());

			perso p = null;
			p = i.creerObjet(this.model(), gge.racine.pos, 0.25f, null, true,
					this.clazz());

			if (p != null) {
				p.nid = this;
				// p.tirer = new tirer();

				p.gec = new GrapheExplorationContext(i, this.gge.racine);

				p.gec.cible = this.donnerCible();

				t = Constante.periodeCreationTete;

				;
			}

		} else {
			t--;

		}
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
