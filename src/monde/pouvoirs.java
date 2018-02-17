package monde;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class pouvoirs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5670607084966251508L;
	public List<pouvoir> pouvoirs = new ArrayList<>();

	public void ajouter(pouvoir p) {
		if (pouvoirs == null) {
			pouvoirs = new ArrayList<pouvoir>();
		}

	
		if (p.afficherMessage()) {
			p.i.modifierTableau(Constante.tableauMessage);
			p.i.nombreElement(1);
			p.i.centrerMessage(true);
			p.i.ajouterMessage("You got " + p.nomPouvoir());
			p.i.activerTableauAvecTransition(Constante.tableauMessage);
			p.c.joueur.afficherMessagePouvoir = new afficherMessagePouvoir();
		}
		pouvoirs.add(p);
	}

	public boolean contientCroixRouge() {

		for (pouvoir p : pouvoirs) {
			if (p.getClass() == croixRouge.class) {
				return true;
			}
		}
		return false;
	}

	public void supprimerCroixRouge() {
		pouvoirs.removeIf((p) -> {
			return p.getClass() == croixRouge.class;
		});
	}

  public int nombreJetPack()  {
		int r = 0;
		for (pouvoir p : pouvoirs) {
			if (p.getClass() == jetPack.class) {
				r++;
			}
		}
		return r;
  }
	public int nombreCroixRouge() {
		int r = 0;
		for (pouvoir p : pouvoirs) {
			if (p.getClass() == croixRouge.class) {
				r++;
			}
		}
		return r;
	}
	public int vieMax() {
		int r = 0;
		for (pouvoir p : pouvoirs) {
			if (p.getClass() == transportVie.class) {
				r++;
			}
		}
		return  r*Constante.vieParTransportVie+Constante.vieInitiale;
	}

	public arme donnerArme() {
		for (pouvoir p : pouvoirs) {
			if (p.getClass() == arme.class) {
				return (arme) p;
			}
		}
		return null;

	}

	public jetPack donnerJetPack() {
		for (pouvoir p : pouvoirs) {
			if (p.getClass() == jetPack.class) {
				return (jetPack) p;
			}
		}
		return null;

	}



}
