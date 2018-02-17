package monde;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class niveauEnemi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4389992581173681643L;
	public int niveau=0;
	public int niveauMax=Constante.niveauMax;
	public int t=0;
	public boolean ameliorer(){
		if (niveau == niveauMax) {
			return false;
		}
		if (t >= Constante.perioreAugmentationNiveau) {
			t=0;
			niveau++;
			return true;
			
		}
		return false;
	}
	
	public float vitesse() {
		  float r = 0.5f+((float)niveau)*0.2f;
		  return r;
		  
		  
		
	}
	
	public int defense() {
		return niveau+1;
	}
	public int attaque() {
		return niveau+1;
		
	}
	public float radar() {
		
		float rayonDetectionEnemi=Constante.rayonDetectionEnemi;
		
		return rayonDetectionEnemi+((float)niveau)*Constante.rayonDetectionEnemiAugmentation;
	}
	

}
