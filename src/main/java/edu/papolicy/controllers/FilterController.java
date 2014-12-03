package edu.papolicy.controllers;

import edu.papolicy.daos.FilterDAO;
import edu.papolicy.models.Filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filters")
public class FilterController {
    @Autowired private FilterDAO FilterDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<Filter> getFilters(){ return FilterDAO.list();}

    @RequestMapping(method=RequestMethod.GET, value="/{id:.+}")
    public Filter getFilter(@PathVariable Integer id){
        return FilterDAO.find(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public Filter postFilter(@RequestBody Filter filterObj){ return FilterDAO.save(filterObj); }
}

