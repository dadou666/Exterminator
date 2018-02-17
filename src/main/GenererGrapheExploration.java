package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import monde.joueur;
import monde.joueurGeneration;
import dadou.Log;
import dadou.VoxelTexture3D.CouleurErreur;
import dadou.tools.BrickEditor;

public class GenererGrapheExploration {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, CouleurErreur {
		String cheminRessources = "h:\\GitHub\\Constructeur";
		BrickEditor.cheminRessources = cheminRessources;
		int idxNoeud = 0;
		File file = new File(cheminRessources + "\\nid" + idxNoeud + ".bin");
		while (file.exists()) {
			file.delete();
			Log.print(" delete "+file);
			idxNoeud++;
			file = new File(cheminRessources + "\\nid" + idxNoeud + ".bin");
		}
		joueur j= new joueurGeneration();
		j.generation = true;
		BrickEditor.startHeadless(j);
		
		

	}

}
