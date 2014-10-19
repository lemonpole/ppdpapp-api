package edu.papolicy.daos;

import edu.papolicy.models.File;
import java.util.List;

public interface FileDAO {
    public List<File> list();
	public File find(int id);
	public File save(File fileObj);
}
