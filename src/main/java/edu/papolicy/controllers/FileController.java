package edu.papolicy.controllers;

import edu.papolicy.daos.FileDAO;
import edu.papolicy.models.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Files")
public class FileController {
    @Autowired
    private FileDAO FileDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<File> getFiles(){
        List<File> Files = FileDAO.list();
        return Files;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postFiles(){}
}
