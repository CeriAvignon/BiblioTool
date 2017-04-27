package webmining;
import java.awt.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.ProgressMonitorInputStream;
public class URLDownload {  

	/*
	 * author :HADDOU Ayoub	
	 * 
	 * */
	
    private URL source;
    private URLConnection con = null;
    private Component parent;
    private String mimeType = null;
    private String content = null;
    private String encoding = null;




    public URLDownload(URL _source) {
        this.source = _source;
        this.parent = null;

    }


    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getMimeType() {
        return mimeType;
    }

    public URLConnection getURLConnection() {
        return con;
    }


    public String getStringContent() {
        return content;
    }



    public void download() throws IOException {

        if (con == null) {
            con = source.openConnection();
            con.setRequestProperty("User-Agent", "Jabref");
            mimeType = con.getContentType();
        }
    

            downloadToString();
    }

    protected void downloadToString() throws IOException {

    	InputStream input = new BufferedInputStream(con.getInputStream());
        Writer output = new StringWriter();

        try
          {
            copy(input, output);
          }
        catch (IOException e)
          {
            e.printStackTrace();
          }
        finally
          {
            try
              {
                input.close();
                output.close();
              }
            catch (Exception e)
              {
              }
          }

        content = output.toString();
    }
    public static Reader getReader(InputStream in)
    	    throws IOException {
    	    InputStreamReader reader;
    	    reader = new InputStreamReader(in);

    	    return reader;
    	  }

    public void copy(InputStream in, Writer out) throws IOException
      {
        InputStream _in = new ProgressMonitorInputStream(parent, "Downloading " + source.toString(), in);
        InputStreamReader BufferedReader;
		Reader r;
		if (encoding != null)
			r = new InputStreamReader(_in, encoding);
		else
			r = r=getReader(_in);
        BufferedReader read = new BufferedReader(r);

        byte[] buffer = new byte[512];
        String line;
        while ((line = read.readLine()) != null) {
            out.write(line);
            out.write("\n");
        }
      }
}
