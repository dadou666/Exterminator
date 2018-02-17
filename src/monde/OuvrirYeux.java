package monde;

import dadou.MondeInterfacePublic;

public class OuvrirYeux extends Yeux {

	public OuvrirYeux() {
		h = 0.0f;
	}

	public void executer(joueur j, MondeInterfacePublic i, float speed) {
		i.initEffetEcran(h, 1, 1, 1, true);
		h = h + speed;
		if (h > limit) {
			if (limit != 0.5f) {
				j.oy = new FermerYeux();
				j.oy.limit = 0.0f;
				j.oy.h = limit;
			} else {
				j.oy = null;
			}

		}

	}

}
