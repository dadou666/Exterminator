package monde;

import dadou.ModelClasse;

public class EtatChoixSupprimerModel extends EtatChoix {
	public void init(construction s) {
		if (s.i.choixEnCours()) {
			return;
		}
		s.etatChoix= null;
		ModelClasse mc = s.i.selectionModel();
		
		if (mc == null) {
			  return;
		}
		s.i.supprimerModel(mc);
		s.i.supprimerChoixModel();
		
		
	}
}
