package graphical_interface;

import java.io.*;
import javax.swing.filechooser.FileFilter;

/**
 * @author alex
 * extends the class FileFilter in order to show the option that user has chosen.
 */
public class FileTypeFilter extends FileFilter{

	private final String extension;
	private final String description;
	
	public FileTypeFilter(String extension,String description)
	{
		this.extension=extension;
		this.description=description;
	}
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
		{
			return true;
		}
		return f.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		return description+ String.format(" (*%s)", extension);
	}

}