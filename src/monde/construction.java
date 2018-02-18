package monde;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jme.math.Vector3f;

import dadou.CameraPosition;
import dadou.ElementJeux;
import dadou.Kube;
import dadou.Log;
import dadou.MondeEventInterface;
import dadou.ProjectileDef;
import dadou.SauvegardeJoueur;
import dadou.exploration.GestionExploration;
import dadou.menu.MenuJeux;

public class construction extends MondeEventInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1006809121275697773L;
	transient public EtatChoix etatChoix;
	public joueur joueur;

	public compteurPerso compteurZombie;

	public int totalTeteDetruite = 0;
	public int nombreCollisionKuboid = 0;

	transient public ProjectileDef pd;

	public void chargerDebut(String nomMonde, SauvegardeJoueur sj) {
		this.demarer();

	}

	public Map<String, porte> portes = new HashMap<>();

	@Override
	public void demarer() {
		compteurZombie = new compteurPerso();

		Color c = new Color(Color.GRAY.getRed(), Color.GRAY.getGreen(),
				Color.GRAY.getBlue(), 150);
		i.tableauTransparent(false);
		i.initialiserTableau(Constante.tableauMessage, 600, 100, c);
		i.marcher();
		i.initPhysiqueMonde();
		i.hauteurSaut(100);
		pd = new ProjectileDef();
		pd.distanceDebut = 0.0f;
		pd.distance = Constante.rayonDetectionEnemi;
		pd.echelle = 1.0f;
		pd.nomClasse = "projectile#m1";
		pd.vitesse = 3.0f;

		Vector3f pos = (Vector3f) i.getData("position");
		if (pos != null) {
			CameraPosition cp = new CameraPosition();
			cp.translation = pos;
			i.camera(cp);

		}
		List<ObjetItem> objets = (List<ObjetItem>) i.getData("objets");

		if (objets != null) {

			for (ObjetItem obj : objets) {
				if (obj.color.equals("porte")) {
					if (portes.get(obj.type) == null) {

						obj.pos.z -= 5;
						obj.pos.x += 0.5f;
						// obj.pos.y += 0.01f;
						porte a = new porte();

						a.testCollisionAvecDecor = false;
						i.creerObjet("porte#porte", obj.pos, 1.0f, null, false,
								a);
						a.c = this;
						this.portes.put(obj.type, a);
					}
				}
				if (obj.type.equals("celluleEnergie") && !joueur.generation) {
					// Log.print("celluleEnergie");
					arme a = i.creerObjet("objet#cercle", obj.pos, 0.50f, null,
							true, arme.class);
					a.hauteurInit = obj.pos.y;
					a.om.testCollisionAvecAutreObjetMobile = false;
					a.c = this;
					// Log.print(" objet ="+obj.color);

				}

				if (obj.type.equals("tourel") && !joueur.generation) {
					obj.pos.y += 7.0f;
					tourel a = i.creerObjet("objet#tourel", obj.pos, 0.25f,
							null, true, tourel.class);
					a.hauteurInit = obj.pos.y;
					a.om.testCollisionAvecAutreObjetMobile = false;
					a.c = this;
					// Log.print(" objet ="+obj.color);

				}
				if (obj.type.equals("generateurBrouillard")
						&& !joueur.generation) {
					obj.pos.y += 7.0f;
					generateurBrouillard a = i.creerObjet(
							"objet#generateurBrouillard", obj.pos, 0.25f, null,
							true, generateurBrouillard.class);
					a.hauteurInit = obj.pos.y;
					a.om.testCollisionAvecAutreObjetMobile = false;
					a.c = this;
					a.niveau = obj.color;
					// Log.print(" objet ="+obj.color);

				}
				if (obj.type.equals("reine")) {

					obj.pos.y += 1.0f;
					nidZombie a = new nidZombie();
					// a.testCollisionAvecDecor = false;
					i.creerObjet("nid#model2", obj.pos, 1.0f, null, true, a);
					a.c = this;
					a.color = obj.color;
					joueur.nids.put(a.color, a);
					joueur.nombreNidEnCours++;
					Log.print(" objet =" + obj.color);

				}
				if (obj.type.equals("exterminateur") && !joueur.generation) {
					// Log.print("celluleEnergie");
					// obj.pos.y += 1.0f;
					croixRouge a = i.creerObjet("objet#croixRouge", obj.pos,
							1.0f, null, true, croixRouge.class);
					a.c = this;

					// Log.print(" objet =" + obj.color);

				}

				if (obj.type.equals("jetPack") && !joueur.generation) {
					// obj.pos.y += 1.0f;
					jetPack jp = i.creerObjet("objet#jetPack", obj.pos, 1.0f,
							null, true, jetPack.class);
					jp.hauteurInit = obj.pos.y;
					jp.om.testCollisionAvecAutreObjetMobile = false;
					jp.c = this;
				}
				if (obj.type.equals("radar") && !joueur.generation) {
					// obj.pos.y += 1.0f;
					radar jp = i.creerObjet("objet#radar", obj.pos, 1.0f, null,
							true, radar.class);
					jp.hauteurInit = obj.pos.y;
					jp.om.testCollisionAvecAutreObjetMobile = false;
					jp.c = this;
				}
				if (obj.type.equals("transportVie") && !joueur.generation) {
					// obj.pos.y = 1.0f;
					transportVie a = i.creerObjet("objet#coeur", obj.pos, 1.0f,
							null, true, transportVie.class);
					a.c = this;

				}

			}
		}
		nidZombie nidCourant = this.joueur.nids.get(this.joueur.nidCourant);
		nidCourant.actif = true;
		Log.print(" nids =" + this.joueur.nids);
		String nomModeles[] = new String[] { "objet#coeur", "objet#cercle",
				"nid#model2", "perso#mdl", "objet#croixRouge", "objet#jetPack" };
		Map<String, Float> distances = new HashMap<>();
		distances.put("nid#model2", 15.0f);
		distances.put("perso#mdl", 15.0f);
		distances.put("amelioration#mdl", 15.0f);
		i.creerAffichageDonneeVertical("jeux", nomModeles.length, 500);

		i.activerAffichageDonneeVertical("jeux");

		i.initialiserDimensionIcone(120, 60);
		i.sautSansDeplacement(true);
		int idx = 0;
		for (String nomModele : nomModeles) {
			Float dist = distances.get(nomModele);
			if (dist == null) {
				dist = 2.0f;
			}
			i.creerIcone(nomModele, nomModele, "BLACK", dist, 0.0f,
					(float) (Math.PI * (1.0 + 1.0 / 2.0)));
			i.afficherDonneeVertical(idx, nomModele, nomModele);
			i.ecrire(nomModele, "0/0");
			idx++;
		}

		i.ecrire("perso#mdl", "" + (this.compteurZombie.totalEncours) + "/"
				+ Constante.nombreTeteMaxiEncours);

		i.ecrire("objet#croixRouge", "0");
		compteurZombie.totalEncours = 0;
		i.brouillard(0);
		i.initialiserCouleurCible("RED", "losange");
		i.initGreffonBoite();
		i.creerAffichageDonneeHorizontal("jeux", 5, 0, 200);
		i.activerAffichageDonneeHorizontal("jeux");
		i.distanceTire(200);
		// i.afficherDonneeHorizontal(0, "POS", "POS");
		// i.ecrire("FPS", "0");

	}

	@Override
	public void finSon(String nom) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finaliser() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tomber(float hauteur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sautEnCours(boolean sautForce, float hauteurSaut) {
		if (this.joueur.energie > Constante.energieSaut) {
			this.joueur.energie -= Constante.energieSaut;
			joueur.energiePourRestitution += Constante.energieSaut;
		} else {

		}
	}

	Vector3f pos = null;

	@Override
	public void appuyerSurClavier(char character, int key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionnerInfoJeux(Integer idx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void arreter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tirer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void entreeZoneDetectionJoueur(ElementJeux om) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortieZoneDetectionJoueur(ElementJeux om) {
		// TODO Auto-generated method stub

	}

	transient Kube kube = new Kube();

	@Override
	public void boucler() {
		// TODO Auto-generated method stub

		i.ecrire("perso#mdl", "" + (compteurZombie.totalEncours));
		if (joueur.generation) {
			// Log.print(" generation ...");
			for (Map.Entry<String, nidZombie> e : joueur.nids.entrySet()) {
				nidZombie nid = e.getValue();
				while (nid.init) {
					
					nid.calculerGrapheExplorationEnLargeur();
					if (nid.gge != null) {
						Log.print(e.getKey() + "=" + nid.gge.totalGrapheExploration +" "+nid.gge.lsOld.size());
					}
					return;
				}
			}
			System.exit(0);	
			//i.exit();
		}

	}

	@Override
	public void collisionTire(Vector3f pos, Vector3f dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortieMonde() {
		i.chargerSauvegarde();

	}

	@Override
	public void finTireSansCollision() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finParcourGroupeCameraPosition() {
		// TODO Auto-generated method stub

	}
}
