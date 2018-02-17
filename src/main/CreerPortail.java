package main;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import dadou.DecorDeBriqueData;
import dadou.Habillage;
import dadou.ModelClasse;
import dadou.tools.BrickEditor;

public class CreerPortail {

	public static void main(String[] args) throws FileNotFoundException,
			ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		BrickEditor.cheminRessources = "h:\\GitHub\\Constructeur";
		DecorDeBriqueData data = DecorDeBriqueData.charger();
		Habillage hab = Habillage.charger("8bit.hab");
		int dim = 11;

		String couleur = "RED0";

		String degrade[] = new String[] { "RED0", "RED1", "RED2" };
		creerPortail(data, null, hab, couleur, dim, degrade);
		couleur = "GREEN6";
		degrade = new String[] { "GREEN6", "GREEN7", "GREEN8" };
		creerPortail(data, null, hab, couleur, dim, degrade);
		data.sauvegarder();

	}

	static String couleur(String[] degrade, int dim, int i) {
		return degrade[i % degrade.length];

	}

	static public void creerPortail(DecorDeBriqueData data, String croix[],
			Habillage hab, String couleur, int dim, String[] degrade) {
		ModelClasse portail = null;

		;
		for (int i = 0; i < dim; i++) {
			portail = data.creerModeleClasse(couleur + "_" + i, "portail",
					hab.nomHabillage, dim, dim, dim);

			if (portail != null) {
				for (int x = 0; x < dim; x++) {
					for (int z = 0; z < dim; z++) {

						int ux = Math.abs(x - dim / 2) + 1;
						int uy = Math.abs(z - dim / 2) + 1;
						int o = (int) Math.sqrt((double) (ux * ux + uy * uy));
						if (o <= dim / 2 + 1) {
							String s = couleur(degrade, dim, o);
							if (croix != null) {
								if (x == dim / 2 && z < dim / 2) {
									s = croix[2];
								}
								if (x == dim / 2 && z > dim / 2) {
									s = croix[0];
								}
								if (z == dim / 2 && x > dim / 2) {
									s = croix[1];
								}
								if (z == dim / 2 && x < dim / 2) {
									s = croix[3];
								}

							}
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
			portail = data.creerModeleClasse(couleur + "_" + (dim+v), "portail",
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
							if (croix != null) {
								if (x == dim / 2 && z < dim / 2) {
									s = croix[2];
								}
								if (x == dim / 2 && z > dim / 2) {
									s = croix[0];
								}
								if (z == dim / 2 && x > dim / 2) {
									s = croix[1];
								}
								if (z == dim / 2 && x < dim / 2) {
									s = croix[3];
								}

							}
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
							if (croix != null) {
								if (x == dim / 2 && z < dim / 2) {
									s = croix[2];
								}
								if (x == dim / 2 && z > dim / 2) {
									s = croix[0];
								}
								if (z == dim / 2 && x > dim / 2) {
									s = croix[1];
								}
								if (z == dim / 2 && x < dim / 2) {
									s = croix[3];
								}

							}
							portail.copie[x][dim-1][z] = hab.valeurPourHabillage(s);
						
						}
					}
				}
			}
		}

	}

}
