package monde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.jme.math.Vector3f;

import dadou.AbstractJoueur;
import dadou.Log;
import dadou.ObjetMobile;
import dadou.menu.MenuJeux;

public class joueur extends AbstractJoueur {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7351321195677679143L;

	public boolean teleportation = false;
	public boolean generation = false;
	public int nombreNidTotal = 0;
	public int nombreNidEnCours = 0;
	public int nombreCroixRouge = 0;
	public int periode = 0;
	public pouvoirs pouvoirs;
	public boolean possedeGrappin;
	public boolean possedeAimant;
	public boolean possedeRadar;
	public int vie = Constante.vieInitiale;
	public int energie = Constante.energieAugmentation;
	public String nidCourant = "0";
	public int energiePourRestitution = 0;
	public int viePourRestitution = 0;
	public Map<String, nidZombie> nids = new HashMap<>();

	public afficherMessagePouvoir afficherMessagePouvoir;
	public int degatEnmi = Constante.degatEnemi;
	transient public List<ObjetMobile> listeDetectionParEnemi = new ArrayList<>();
	transient public List<String> listeClefs;
	public boolean estVisible = true;
	public int nombreRessource = 0;

	public Yeux oy;

	public nidZombie nidCourant() {
		return nids.get(nidCourant);
	}

	public void initialiserPouvoirs() {
		oy = new OuvrirYeux();
		if (energie < 0) {
			energie = 0;
		}

		possedeGrappin = false;
		possedeAimant = false;
		possedeRadar = false;

		listeClefs = new ArrayList<>();
		if (pouvoirs == null) {
			pouvoirs = new pouvoirs();
		}
		for (pouvoir p : pouvoirs.pouvoirs) {
			p.activer();
		}
	}

	@Override
	public void demarer() {
		// TODO Auto-generated method stub
		oy = new OuvrirYeux();

	}

	public void chargerDebut() {

		initialiserPouvoirs();
		degatEnmi = Constante.degatEnemi;
		estVisible = true;

		i.ecrire("objet#coeur", vie);

		this.nombreNidTotal = 0;
		this.nombreNidEnCours = 0;
	}

	public void augmenterVie() {
		if (vie >= pouvoirs.vieMax()) {
			return;
		}
		vie += Constante.vieAugmentation;

	}

	boolean sautEnCours = false;

	@Override
	public void boucler() {

		if (oy != null) {
			oy.executer(this, i, 0.01f);
		}

		if (this.afficherMessagePouvoir != null) {
			this.afficherMessagePouvoir.process(this);
		}
		if (pouvoirs == null) {
			this.initialiserPouvoirs();
		}
		i.ecrire("objet#coeur", vie + "/" + pouvoirs.vieMax());
		i.ecrire("objet#croixRouge", "" + pouvoirs.nombreCroixRouge());
		i.ecrire("objet#jetPack", "" + pouvoirs.nombreJetPack());
		i.ecrire("objet#cercle", "" + energie);
		periode++;
		if (this.pouvoirs.nombreJetPack() <= 0) {
			if (!sautEnCours) {
				i.hauteurSaut(0);
			}
		} else {
			i.hauteurSaut(256);
		}

		if (periode > Constante.periodePerteVie) {
			periode = 0;
			vie -= nids.get(nidCourant).compteur().totalEncours;

		}
		if (vie <= 0) {

			i.chargerSauvegarde();
			return;
		}
		if (this.energiePourRestitution >= Constante.energieAugmentation) {
			arme arme = this.pouvoirs.donnerArme();
			if (arme != null) {
				this.energiePourRestitution -= Constante.energieAugmentation;
				arme.om.estVisible = true;
				arme.hauteur = Constante.hauteurApresRegeneration;
				pouvoirs.pouvoirs.remove(arme);
			}

		}

		i.figerCameraTranslation(teleportation);
		if (!teleportation) {
			if (!estVisible) {
				return;
			}
			if (listeDetectionParEnemi == null) {
				listeDetectionParEnemi = new ArrayList<>();
			}
			listeDetectionParEnemi.clear();
			attaquer a = null;

			i.collisionAvecObjetMobiles(i.position(null),
					Constante.rayonDetectionEnemi, listeDetectionParEnemi);

			for (ObjetMobile om : listeDetectionParEnemi) {
				if (om.mei instanceof zombi) {

					zombi e = (zombi) om.mei;
					if (e.attaquer == null) {

						a = new attaquer();

						a.init(e);

					}
				}

				if (om.mei instanceof tourel) {
					tourel t = (tourel) om.mei;
					if (t.etat == null) {
						t.dirigerVersJoueur();
					}
				}
			}

		}

	}

	@Override
	public void collisionTire(Vector3f pos, Vector3f direction) {

	}

	@Override
	public void tirer(Vector3f position, Vector3f direction) {
		if (energie == 0) {
			i.tireActif(false);
		}
		if (energie > 0) {
			energie--;
			energiePourRestitution++;
		}

	}

	@Override
	public void appuyerSurClavier(char character, int key) {
		// TODO Auto-generated method stub
		if (character == 'v') {
			estVisible = true;
			Log.print(" estVisible " + estVisible);
		}

		if (character == 'i') {
			estVisible = false;
			Log.print(" estVisible " + estVisible);
		}
		if (character == ' ') {
			jetPack jp = this.pouvoirs.donnerJetPack();
			if (jp != null) {
				sautEnCours = true;
				this.pouvoirs.pouvoirs.remove(jp);
				jp.om.estVisible = true;
				jp.hauteur = Constante.hauteurApresRegeneration;
			}
			// Log.print(" espace ");
		}

	}

	@Override
	public void tomber(float hauteur) {
		// jetPack jp = this.pouvoirs.donnerJetPack();
		// this.pouvoirs.pouvoirs.remove(jp);
		sautEnCours = false;
	}

	@Override
	public void sautEnCours(boolean sautForce, float hauteurSaut) {

	}

	@Override
	public void selectionnerInfoJeux(Integer idx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		this.nomMonde = "mondeDePiece512.wld";

		construction c = new construction();
		c.joueur = this;

		this.initMEI = c;

	}

}
