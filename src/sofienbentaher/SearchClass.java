/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dblp.search.engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mondher
 */
public class SearchClass {
    String format;
    public SearchClass(){
        format = "json";
    }
    public String[] getAuthor(JSONObject obj){
        if(obj.has("authors")){
            try {
                obj = obj.getJSONObject("authors");
                if(obj.get("author").getClass().isArray()) return (String[]) obj.get("author");
                else return new String[]{obj.getString("author")};
            } catch (JSONException ex) {
                Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new String[]{"non trouvé"};
    }
    public String getUrl(JSONObject obj){
        if(obj.has("url")){
            try {
                return obj.getString("url");
            } catch (JSONException ex) {
                Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "non trouvé";
    }
    public String getYear(JSONObject obj){
        if(obj.has("year")){
            try {
                return obj.getString("year");
            } catch (JSONException ex) {
                Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "non trouvé";
    }
    public String getTitle(JSONObject obj){
        if(obj.has("title")){
            try {
                return obj.getString("title");
            } catch (JSONException ex) {
                Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "non trouvé";
    }
    private InputStream search(String ur){
        HttpURLConnection con = null;
        try {
            URL url = new URL(ur);
            con = (HttpURLConnection) url.openConnection();
            return con.getInputStream();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(SearchClass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
    public InputStream searchMotCle(String mot){
        return search("http://dblp.org/search/publ/api?q="+mot+"&format="+format);
    }
    public InputStream searchAuthor(String mot){
        return search("http://dblp.org/search/author/api?q="+mot+"&format="+format);
    }
}
