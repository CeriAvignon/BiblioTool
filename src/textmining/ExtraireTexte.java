package textmining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExtraireTexte {
	
	
	

	public static void main(String[] args) throws IOException {
	
		// TODO Auto-generated method stub
		int numLine=0;
		int nombreDeLignes=5;
		BufferedReader in = new BufferedReader(new FileReader("aa"));
		String line;
		/*while ((line = in.readLine()) != null)
		{
	      // Afficher le contenu du fichier
			  System.out.println (line);
		}*/
		int[] var = new int[nombreDeLignes ];
		while ((line = in.readLine()) != null || numLine <5) 
		{ 
		numLine++; 
		System.out.println(line); 
		//chaine += ligne + "\n"; 
		int ind = line.indexOf(" "); 
		//var[numLine -1] = Integer.parseInt(line.substring(0, ind)); 
		} 
		for (int i = 0; i < var.length; i++) 
		{ 
		System.out.println("var[" + i + "] = " + var[i]); 
		} 
		in.close();

	}

}
