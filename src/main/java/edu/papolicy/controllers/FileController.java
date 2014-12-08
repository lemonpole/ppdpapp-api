package edu.papolicy.controllers;

import edu.papolicy.daos.FileDAO;
import edu.papolicy.models.File;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired private FileDAO fileDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity getFiles(){
        return new ResponseEntity<List<File>>(fileDAO.list(), HttpStatus.OK);
    }

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity getFile(@PathVariable int id, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);}
		return new ResponseEntity<File>(fileDAO.find(id), HttpStatus.OK);
	}

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity postFile(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new java.io.File(name)));
                stream.write(bytes);
                stream.close();

                File fileObj = new File();
                fileObj.setName(name);
                fileObj.setFileURL(name); //todo: fix please
                fileObj.setDateAdded(new Date());
                fileObj.setCreator(user.getEmail());

                fileObj = fileDAO.save(fileObj);

                return  new ResponseEntity<File>(fileObj, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>("file NOT upload", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("file NOT upload", HttpStatus.NOT_FOUND);
        }
    }
}
