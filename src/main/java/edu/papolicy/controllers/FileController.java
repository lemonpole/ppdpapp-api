package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.daos.FileDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.File;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.papolicy.models.FileWrapper;
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
    @Autowired private BatchDAO batchDAO;
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
    @RequestMapping(method=RequestMethod.GET, value="/{id}/batches")
    public ResponseEntity getBatchByFileID(@PathVariable int id, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);}
        return new ResponseEntity<Object>(fileDAO.findBatchByFileID(id), HttpStatus.OK);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity postFile(@RequestBody Object o, @RequestParam(value="token") String token){
        // File fileObj
        // MultipartFile data
        // Batch batchObj
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }

        FileWrapper fileWrapper = (FileWrapper) o;
        MultipartFile data = ((FileWrapper) o).getData();
        File fileObj = ((FileWrapper) o).getFileObj();
        Batch batchObj = ((FileWrapper) o).getBatchObj();
        System.out.println("YIS");
        System.out.println(batchObj.getName());

        if (!data.isEmpty()) {
            try {
                byte[] bytes = data.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new java.io.File(fileObj.getName())));
                stream.write(bytes);
                stream.close();
                //fileObj = fileDAO.save(fileObj);
                fileObj.setCreator(user.getEmail());
                fileObj.setFileURL(data.getName());
                Integer fileID = fileDAO.create(fileObj);

                batchObj.setFileID(fileID.toString());
                batchObj.setCreator(user.getEmail());
                batchDAO.create(batchObj);

                return  new ResponseEntity<String>("file uploaded, bloke!", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>("file NOT upload", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("file NOT upload", HttpStatus.NOT_FOUND);
        }
    }
}
