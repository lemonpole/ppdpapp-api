package edu.papolicy.controllers;

import edu.papolicy.daos.FileDAO;
import edu.papolicy.models.File;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity postFile(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new java.io.File(name)));
                stream.write(bytes);
                stream.close();
                return  new ResponseEntity<String>("file upload", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>("file NOT upload", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("file NOT upload", HttpStatus.NOT_FOUND);
        }


                //fileDAO.save(fileObj);
    }
}
