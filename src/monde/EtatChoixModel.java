package monde;

import dadou.ModeleEventInterface;

public class EtatChoixModel extends EtatChoix {
	public ModeleEventInterface mei;
	public EtatChoixModel(ModeleEventInterface mei) {
		this.mei=mei;
	}
public void init(construction s) {
	if (s.i.choixEnCours()) {
		return;
	}
	s.etatChoix= null;
	if (s.i.contientChoixModel()) {
		s.i.coler(mei);
		s.i.forceControlleurCameraDadou();
		return;
	}
	s.i.selectionKube(false);
	s.i.forceControlleurCameraStandard();
	
}
}
