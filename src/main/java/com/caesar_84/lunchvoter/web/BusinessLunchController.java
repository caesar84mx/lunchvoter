package com.caesar_84.lunchvoter.web;

import com.caesar_84.lunchvoter.dto.BusinessLunch;
import com.caesar_84.lunchvoter.service.implementations.BusinessLunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rest-api/v1")
public class BusinessLunchController {
    @Autowired
    private BusinessLunchService service;

    @RequestMapping(value = "/lunches", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BusinessLunch>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lunches/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BusinessLunch> get(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/lunches", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BusinessLunch> save(@RequestBody BusinessLunch businessLunch) {
        return new ResponseEntity<>(service.save(businessLunch), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/lunches/by", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BusinessLunch>> getBy(@RequestParam("date") LocalDate date) {
        return new ResponseEntity<>(service.getBy(date), HttpStatus.OK);
    }

    @RequestMapping(value = "/lunches/between", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BusinessLunch>> getBetween(@RequestParam("start") LocalDate start,
                                                   @RequestParam("end") LocalDate end) {
        return new ResponseEntity<>(service.getBetween(start, end), HttpStatus.OK);
    }
}