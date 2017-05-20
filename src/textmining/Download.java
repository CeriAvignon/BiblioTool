package textmining;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download
{
	public String fileName;
	public String name;
    public  void getFile(String host)
    {
        InputStream input = null;
        FileOutputStream writeFile = null;

        try
        {//connection avec url
            URL url = new URL(host);
            URLConnection connection = url.openConnection();
            int fileLength = connection.getContentLength();

            if (fileLength == -1)
            {
                System.out.println("Invalide URL or file.");
                return;
            }

            input = connection.getInputStream();
            fileName =url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream(fileName);
           
           
            System.out.println(fileName);
           
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
               writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        catch (IOException e)
        {
            System.out.println("erreur de telechargement de pdf.");
            
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writeFile.close();
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    

    public static void main(String[] args)
    {
       /* if (args.length != 1)
        {
            System.out.println("faut donner url de document � t�l�charger");
            return;
        }*/
    	
    	

      //  getFile("http://exo7.emath.fr/ficpdf/ficall.pdf");
    }
}