package edu.papolicy.controllers;

import edu.papolicy.daos.FileDAO;
import edu.papolicy.models.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileDAO fileDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<File> getFiles(){
        List<File> files = fileDAO.list();
        return files;
    }

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public File getFile(@PathVariable int id){
		return fileDAO.find(id);
	}

    @RequestMapping(method=RequestMethod.POST)
    public File postFile(@RequestBody File fileObj){
	    return fileDAO.save(fileObj);
    }
}
