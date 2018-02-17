package monde;

import com.jme.math.Vector3f;

import dadou.ObjetMobile;
import dadou.VoxelTexture3D.CouleurErreur;

public class generateurBrouillard extends elementDecor {

	public String niveau;
	public boolean actif = false;
	private static final long serialVersionUID = -4373941416619633887L;
	
	@Override
	public void boucle(ObjetMobile om) {
	
	   
	   if (actif) {
		   i.brouillard(0.025f);
	   } else {
			nid nid=this.c.joueur.nids.get(niveau);
			   if (nid != null ) {
				   if (!nid.actif) {
					   return;
				   }
				actif = true;
			   }
		   
	   }
		
		

	}
	@Override
	public void tire(ObjetMobile om, String position) {

		if (actif) {
			i.brouillard(0.0f);
		}
	 
		this.detruire(om);
		
	}
	public void detruire(ObjetMobile om) {
		try {
		
			i.decomposerAvecPhysique(om.donnerNom(), new Vector3f(25, 18, 25),
					4, 0.085f, 60, true);
			om.activePhysique();
			om.annulerCollisionCamera = true;
		

		} catch (CouleurErreur e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
