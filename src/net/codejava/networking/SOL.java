package net.codejava.networking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SOL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> f= new ArrayList<>();
		
		  f = Article.recupurl();
		  String saveDir= "C:/Users/admin/Desktop"; 
		  
		  try {
			  
			  Article.downloadFile(f,saveDir);
			   }catch(IOException e){};
		   }
}

