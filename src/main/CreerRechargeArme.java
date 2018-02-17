package main;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import dadou.DecorDeBriqueData;
import dadou.Habillage;
import dadou.ModelClasse;
import dadou.tools.BrickEditor;

public class CreerRechargeArme {

	public static void main(String[] args) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		BrickEditor.cheminRessources = "h:\\GitHub\\Constructeur";
		DecorDeBriqueData data = DecorDeBriqueData.charger();
		Habillage hab = Habillage.charger("icone.hab");
		int dim = 11;

		String couleur = "coeur";

		String degrade[] = new String[] { "GWHITE33", "GWHITE34", "GWHITE35" };
		String croix[] = new String[] { "coeur", "coeur90", "coeur180",
				"coeur90" };
		creerPortail(data,"recharge", croix, hab, couleur, dim, degrade);
		couleur = "cercle";

		creerPortail(data,"recharge", new String[]{ "cercle"}, hab, couleur, dim, degrade);
		data.sauvegarder();

	}

	static String couleur(String[] degrade, int dim, int i) {
	
		return degrade[i % degrade.length];

	}
static String valeur(String []ls,int i) {
	if (ls.length == 1) {
		return ls[0];
	}
	return ls[i];
}
static public void creerPortail(DecorDeBriqueData data,String type, String croix[],
		Habillage hab, String couleur, int dim, String[] degrade) {
	ModelClasse portail = null;

	;
	for (int i = 0; i < dim; i++) {
		portail = data.creerModeleClasse(couleur + "_" + i, type,
				hab.nomHabillage, dim, dim, dim);

		if (portail != null) {
			for (int x = 0; x < dim; x++) {
				for (int z = 0; z < dim; z++) {

					int ux = Math.abs(x - dim / 2) + 1;
					int uy = Math.abs(z - dim / 2) + 1;
					int o = (int) Math.sqrt((double) (ux * ux + uy * uy));
					if (o <= dim / 2 + 1) {
						String s = couleur(degrade, dim, o);
						s=valeur(x,z,dim,croix,s);
						portail.copie[x][0][z] = hab.valeurPourHabillage(s);
					
					}
				}
			}
			if (i > 0) {

				for (int g = 1; g <= i; g++) {
					Color color = hab.valeurPourHabillage(couleur(degrade,
							dim, g));
					for (int x = 0; x < dim; x++) {
						for (int z = 0; z < dim; z++) {

							int ux = Math.abs(x - dim / 2) + 1;
							int uy = Math.abs(z - dim / 2) + 1;
							int o = (int) Math.sqrt((double) (ux * ux + uy
									* uy));
							if (o <= dim / 2 + 1 && o > dim / 2) {
								String s = couleur(degrade, dim, g);

								portail.copie[x][g][z] = hab
										.valeurPourHabillage(s);
							}
						}
					}
					for (int u = 0; u < dim; u++) {
						if (u == dim / 2) {
							color = hab.valeurPourHabillage(couleur);
							portail.copie[u][g][0] = color;
							portail.copie[u][g][dim - 1] = color;
							portail.copie[0][g][u] = color;
							portail.copie[dim - 1][g][u] = color;
						} else {
							color = hab.valeurPourHabillage(couleur(
									degrade, dim, g));
						}

					}

				}

			}

		}
	}

	for (int v = 0; v <= dim/2; v++) {
		portail = data.creerModeleClasse(couleur + "_" + (dim+v), type,
				hab.nomHabillage, dim, dim, dim);

		if (portail != null) {
			for (int g = 1; g < dim; g++) {
				Color color = hab.valeurPourHabillage(couleur(degrade, dim,
						g));
				for (int x = 0; x < dim; x++) {
					for (int z = 0; z < dim; z++) {

						int ux = Math.abs(x - dim / 2) + 1;
						int uy = Math.abs(z - dim / 2) + 1;
						int o = (int) Math
								.sqrt((double) (ux * ux + uy * uy));
						if (o <= dim / 2 + 1 && o > dim / 2) {
							String s = couleur(degrade, dim, g);

							portail.copie[x][g][z] = hab
									.valeurPourHabillage(s);
						}
					}
				}
				for (int u = 0; u < dim; u++) {
					if (u == dim / 2) {
						color = hab.valeurPourHabillage(couleur);
						portail.copie[u][g][0] = color;
						portail.copie[u][g][dim - 1] = color;
						portail.copie[0][g][u] = color;
						portail.copie[dim - 1][g][u] = color;
					} else {
						color = hab.valeurPourHabillage(couleur(degrade,
								dim, g));
					}

				}

			}
			for (int x = 0; x < dim; x++) {
				for (int z = 0; z < dim; z++) {

					int ux = Math.abs(x - dim / 2) + 1;
					int uy = Math.abs(z - dim / 2) + 1;
					int o = (int) Math.sqrt((double) (ux * ux + uy * uy));
					if (o <= dim / 2 + 1) {
						String s = couleur(degrade, dim, o);
						s=valeur(x,z,dim,croix,s);
						portail.copie[x][0][z] = hab.valeurPourHabillage(s);
				
					}
				}
			}
			for (int x = 0; x < dim; x++) {
				for (int z = 0; z < dim; z++) {

					int ux = Math.abs(x - dim / 2) + 1;
					int uy = Math.abs(z - dim / 2) + 1;
					int o = (int) Math.sqrt((double) (ux * ux + uy * uy));
					if (o <= dim / 2 + 1 && o > (dim/2-v)) {
						String s = couleur(degrade, dim, o);
						s=valeur(x,z,dim,croix,s);
					
						portail.copie[x][dim-1][z] = hab.valeurPourHabillage(s);
					
					}
				}
			}
		}
	}

}

static String valeur(int x,int z,int dim,String croix[],String s) {
	if (croix != null) {
		if (x == dim / 2 && z < dim / 2) {
			s = valeur(croix,2);
		}
		if (x == dim / 2 && z > dim / 2) {
			s = valeur(croix,0);
		}
		if (z == dim / 2 && x > dim / 2) {
			s = valeur(croix,1);
		}
		if (z == dim / 2 && x < dim / 2) {
			s = valeur(croix,3);
		}

	}
	return s;
	
}
}
