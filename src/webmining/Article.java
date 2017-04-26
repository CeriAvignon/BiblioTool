/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webMining;

/**
 *
 * @author SOFIEN
 */
public class Article {
    private int id;
    private String title, year, type, url, pages;
    private String[] authorss;

    public Article(int id, String title, String year, String type, String url, String pages, String[] authors) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.type = type;
        this.url = url;
        this.pages = pages;
        this.authorss = authors;
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String[] getAuthors() {
        return authorss;
    }

    public void setAuthors(String[] authors) {
        this.authorss = authors;
    }
    
}
