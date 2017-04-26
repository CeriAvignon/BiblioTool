package textmining;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download
{
    public static void getFile(String host)
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
            //le nom du pdf 
            String fileName =url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream(fileName);
           
           
            System.out.println(fileName);
           //lire sur le buffer 
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
               writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        //generer une exception en cas de probleme 
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
    
    
    

    
}