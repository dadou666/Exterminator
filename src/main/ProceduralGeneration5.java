package main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monde.ObjetItem;
import monde.joueur;
import monde.joueurGeneration;

import com.jme.math.Vector3f;

import dadou.CameraPosition;
import dadou.DecorDeBriqueDataElement;
import dadou.Habillage;
import dadou.Log;
import dadou.Lumiere;
import dadou.MondeEventInterface;
import dadou.VoxelTexture3D.CouleurErreur;
import dadou.Zone;
import dadou.procedural.ListeAleatoire;
import dadou.procedural.Monde;
import dadou.procedural.Piece;
import dadou.tools.BrickEditor;
import dadou.tools.graphics.ConfigValues;

public class ProceduralGeneration5 {
	static public void placerObjet(String nom, ListeAleatoire<Piece> la, int n) {
		while (n > 0) {
			Piece p = la.donnerElement();
			if (p != null) {
				p.objet = nom;
				
			} else {
				Log.print(" impossible de placer " + nom);
			}
			n--;
		}

	}

	static public List<Piece> listePiecesSansIntersection(
			dadou.procedural.Zone zone, Monde monde) {
		List<Piece> rs = new ArrayList<Piece>();
		for (Piece p : zone.pieces) {

			if (p.objet == null && zone.verifier(rs, p, monde)) {
				rs.add(p);
			}

		}
		return rs;

	}

	static public List<Piece> listePiecesAvecVoisin(dadou.procedural.Zone zone,
			Monde monde, int nbVoisin) {
		List<Piece> rs = new ArrayList<Piece>();
		for (Piece p : zone.pieces) {

			if (p.proches.size() > nbVoisin && p.objet == null
					&& zone.verifier(rs, p, monde)) {
				rs.add(p);
			}

		}
		return rs;

	}

	static public void placerObjets(Monde monde) {

		for (String clef : monde.zones.keySet()) {
			if (!clef.equals("porte")) {

				dadou.procedural.Zone zone = monde.zones.get(clef);

				int size = zone.pieces.size();
				if (size >= 2) {

					if (size > 10) {
						ListeAleatoire<Piece> la = new ListeAleatoire<Piece>(
								listePiecesAvecVoisin(zone, monde, 5));
						placerObjet("reine", la, 1);

						la = new ListeAleatoire<Piece>(
								listePiecesSansIntersection(zone, monde));
						placerObjet("exterminateur", la, 1);
						Integer i = Integer.parseInt(clef);
						if (i > 0) {
							placerObjet("generateurBrouillard", la, 1);
						}
						placerObjet("celluleEnergie", la, 5);

						// placerObjet("zoneProtection", la, 5);

						placerObjet("transportVie", la, 5);
						placerObjet("jetPack", la, 10);

						placerObjet("celluleEnergie", la, 10);

						placerObjet("tourel", la, 4);

					}
				}
			}

		}

	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException, CouleurErreur {
		BrickEditor.niveau++;
		int dim = (int) Math.pow(2, BrickEditor.niveau)
				* BrickEditor.elementTaille;
		Log.print("dim =" + dim);
		Monde monde = new Monde(dim, dim);
		monde.minPiece = 16;
		monde.generate();
		placerObjets(monde);

		String cheminRessources = "f:\\GitHub\\Constructeur";
		int idxNoeud = 0;
		File file = new File(cheminRessources + "\\nid" + idxNoeud + ".bin");
		while (file.exists()) {
			file.delete();
			Log.print(" delete " + file);
			idxNoeud++;
			file = new File(cheminRessources + "\\nid" + idxNoeud + ".bin");
		}

		BrickEditor.cheminRessources = cheminRessources;
		/*
		 * DecorDeBriqueData decorDeBriqueData = (DecorDeBriqueData)
		 * SerializeTool .load(cheminRessources + "/base.bin");
		 */

		DecorDeBriqueDataElement dbe = new DecorDeBriqueDataElement(
				BrickEditor.niveau, BrickEditor.elementTaille);
		dbe.nomHabillage = "hd.hab";
		dbe.configValues = new ConfigValues();
		dbe.configValues.brouillard = 0.009f;
		dbe.cameraPositions = new HashMap<>();
		dbe.skyBox = "skybox3";
		Habillage hab = Habillage.charger("hd.hab");

		dbe.cameraPosition = new CameraPosition();
		dbe.cameraPosition.translation.set(8, 100, 8);

		Map<String, Color> murs = new HashMap<String, Color>();
		murs.put("0", hab.valeurs.get("briqueBlanche"));
		murs.put("1", hab.valeurs.get("brique2"));

		murs.put("2", hab.valeurs.get("brique3"));
		murs.put("3", hab.valeurs.get("brique4"));
		murs.put("4", hab.valeurs.get("brique5"));
		murs.put("5", hab.valeurs.get("brique6"));
		murs.put("6", hab.valeurs.get("brique7"));
		murs.put("7", hab.valeurs.get("brique8"));
		murs.put("8", hab.valeurs.get("brique9"));

		murs.put("9", hab.valeurs.get("brique_losange2"));

		murs.put("porte", hab.valeurs.get("octogone"));

		Map<String, Color> sols = new HashMap<String, Color>();
		sols.put("0", hab.valeurs.get("sol2"));
		sols.put("1", hab.valeurs.get("sol"));

		sols.put("2", hab.valeurs.get("sol3"));
		sols.put("3", hab.valeurs.get("sol4"));
		sols.put("4", hab.valeurs.get("pave2"));
		sols.put("5", hab.valeurs.get("pave3"));
		sols.put("6", hab.valeurs.get("pave4"));
		sols.put("7", hab.valeurs.get("metal"));
		sols.put("8", hab.valeurs.get("carellage2"));

		sols.put("9", hab.valeurs.get("metal6"));
		sols.put("porte", hab.valeurs.get("pave_rouge"));

		int hauteur = 8;
		List<Piece> pieces = monde.pieces();
		Piece depart = monde.zones.get("0").pieces.get(0);

		int px = (depart.cx()) - dim / 2;
		int pz = (depart.cy()) - dim / 2;
		dbe.cameraPosition.translation.set(px, -dim / 2 + 10 + depart.h, pz);
		dbe.data.put("position", new Vector3f(px, -dim / 2 + 30 + depart.h, pz));
		List<ObjetItem> objets = new ArrayList<ObjetItem>();
		dbe.data.put("objets", objets);
		boolean creerArche;
		for (Piece p : pieces) {
			creerArche = true;
			p.h = 1;
			if (p.objet != null) {
				
				if (p.objet.equals("reine")) {
					creerArche = false;
				}
				if (p.color.equals("porte")) {
					ObjetItem oi = new ObjetItem();
					oi.color = "porte";
					oi.type = p.objet;
					oi.pos = new Vector3f(p.cx() - dim / 2, -dim / 2 + 4 + p.h,
							p.cy() - dim / 2);
					Log.print(" porte " + p.dx + " - " + p.dy);

					objets.add(oi);

				}
			
			
				if (p.objet.equals("zoneProtection")) {
					Zone zone = new Zone();
					zone.pos.set(p.cx() - dim / 2, -dim / 2 + 5 + p.h, p.cy()
							- dim / 2);
					zone.rayon = 20.0f;
					dbe.zones.add(zone);
				} else {
					ObjetItem obj = new ObjetItem();
					obj.pos = new Vector3f(p.cx() - dim / 2,
							-dim / 2 + 5 + p.h, p.cy() - dim / 2);
					obj.type = p.objet;
					obj.color = p.color;
					
					objets.add(obj);
				}

			}
			Color sol = sols.get(p.color);
			Color mur = murs.get(p.color);
			if (mur == null) {
				Log.print(" mur null" + mur + " " + p.color);
			}
			if (p.color.equals("porte")) {
				creerArche = false;
			}

			for (int x = p.minX(); x <= p.maxX(); x++) {
				if (creerArche && !testPosition(p.minX() - 1, p.cy(), monde)
						&& !testPosition(p.maxX() + 1, p.cy(), monde)) {
					int gx = (p.maxX() - x);
					int u = Math.min(Math.min(Math.abs(p.dx) - gx, gx) / 3, 4);

					dbe.ecrireCouleur(x, hauteur + p.h + u, p.cy(), mur);
				}
				for (int y = 0; y < hauteur; y++) {

					if (!testPosition(x, p.minY() - 1, monde)) {

						dbe.ecrireCouleur(x, y + p.h, p.minY(), mur);

					}
					if (!testPosition(x, p.maxY() + 1, monde)) {
						dbe.ecrireCouleur(x, y + p.h, p.maxY(), mur);

					}

				}

			}

			for (int z = p.minY(); z <= p.maxY(); z++) {

				if (creerArche && !testPosition(p.cx(), p.minY() - 1, monde)
						&& !testPosition(p.cx(), p.maxY() + 1, monde)) {
					int gz = (p.maxY() - z);
					int u = Math.min(Math.min(Math.abs(p.dy) - gz, gz) / 3, 4);
					dbe.ecrireCouleur(p.cx(), hauteur + p.h + u, z, mur);
				}
				for (int y = 0; y < hauteur; y++) {
					if (!testPosition(p.minX() - 1, z, monde)) {
						dbe.ecrireCouleur(p.minX(), y + p.h, z, mur);

					}
					if (!testPosition(p.maxX() + 1, z, monde)) {
						dbe.ecrireCouleur(p.maxX(), y + p.h, z, mur);

					}

				}

			}

			if (sol == null) {
				Log.print(" sol null" + mur + " " + p.color);
			}
			for (int x = p.minX(); x <= p.maxX(); x++) {
				for (int z = p.minY(); z <= p.maxY(); z++) {
					for (int h = 0; h <= p.h; h++) {
						dbe.ecrireCouleur(x, h, z, sol);
					}
					// dbe.ecrireCouleur(x, hauteur, z, plafond);

				}

			}

		}

		Log.print(" occupation =" + monde.occupation());

		dbe.sauvegarder(cheminRessources + "/mondeDePiece512.wld");
		joueur j = new joueurGeneration();
		j.generation = true;
		BrickEditor.startHeadless(j);
		MondeCanvas.open(monde);

	}

	static public boolean testPosition(int x, int y, Monde monde) {
		if (x < 0) {
			return false;
		}
		if (y < 0) {
			return false;
		}
		if (x >= monde.dx) {
			return false;
		}
		if (y >= monde.dy) {
			return false;

		}
		return monde.estDansPiece(x, y);

	}

}
