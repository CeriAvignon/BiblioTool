package net.codejava.networking;

import java.io.IOException;

public class SOL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Article.recupurl();
		 java.util.Iterator<String> it=Article.recupurl().iterator();

		 String saveDir= "	C:/Users/admin/Desktop"; 
		 
		 while (it.hasNext()){
			 try{
			 Article.downloadFile(it.next(),saveDir);
			 
			 } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			 }
}
}


