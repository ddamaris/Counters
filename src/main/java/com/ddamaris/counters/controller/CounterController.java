package com.ddamaris.counters.controller;

import com.ddamaris.counters.repository.CounterRepository;
import com.ddamaris.counters.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CounterController {

    @Autowired
    public CounterService service;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createCounter (@RequestParam String name) {
        if (service.newCounter(name)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Counter already exists",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/increment")
    public @ResponseBody Long incrementCounter(@RequestParam String name) {
        return service.incrementCounter(name);
    }

    @GetMapping("/getvalue")
    public @ResponseBody Long getCounterValue(@RequestParam String name)
    {
        return service.getCounterValue(name);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCounter (@RequestParam String name)
    {
        if (service.deleteCounter(name)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Counter does not exist",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/gettotal")
    public @ResponseBody Long getTotal() {
        return service.getTotal();
    }

    @GetMapping("/getset")
    public @ResponseBody Set<String> getSet()
    {
        return service.getSet();
    }
}

