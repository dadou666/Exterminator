package main;


import java.io.IOException;

import dadou.Game;
import dadou.tools.BrickEditor;
import dadou.tools.BrickEditorSwing;

public class LancerModeEditeur {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Game.logError = null;
		Game.logOut = null;
		BrickEditor.main(new String[] { "f:\\GitHub\\Constructeur" , "editeur=true" });

	}

}
