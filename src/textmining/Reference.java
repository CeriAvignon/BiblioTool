package textmining;

import java.util.*;

public class Reference {

	
	public List<String> authors;
	public String titre;
	public String pages;
	public String date;
	
	Reference(List<String> a,String t, String p, String d)
	{
		authors = a;
		titre = t;
		pages = p;
		date = d;
	}
	
	public String toString()
	{
		String s = "{";
		for(String s1 : authors)
		{
			s += s1 + ",";
		}
		s = s.substring(0,s.length()-1) + "}";
		
		s += " / " + titre;
		s += " / " + pages;
		s += " / " + date;
		
		return s;
	}
}
