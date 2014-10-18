package edu.papolicy.controllers;

import edu.papolicy.daos.FilterDAO;
import edu.papolicy.models.Filter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Filters")
public class FilterController {
    @Autowired
    private FilterDAO FilterDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<Filter> getFilters(){
        List<Filter> Filters = FilterDAO.list();
        return Filters;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postFilters(){}
}
