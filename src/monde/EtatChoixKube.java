package monde;

public class EtatChoixKube extends EtatChoix {
	
public void init(construction s){
	if (s.i.choixEnCours()) {
		return;
	}
	s.etatChoix= null;
	if (s.i.contientChoixKube()) {
		s.i.selectionKube(true);
		s.i.forceControlleurCameraDadou();
		return;
	}
	s.i.selectionKube(false);
	s.i.forceControlleurCameraStandard();
	
	}
}
