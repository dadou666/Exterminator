package monde;

import dadou.MondeInterfacePublic;

public class FermerYeux extends Yeux{

	public FermerYeux() {
		h=0.0f;
	}
	public void executer(joueur j,MondeInterfacePublic i,float speed) {
		i.initEffetEcran(h, 1, 1, 1, true);
		h=h- speed;
		if (h < 0.0f) {
			j.oy= new OuvrirYeux();
			j.oy.limit = 0.5f;
		}
		
	}
}
