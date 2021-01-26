package com.ddamaris.counters.controller;

import com.ddamaris.counters.item.Counter;
import com.ddamaris.counters.item.CounterLong;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/counters")
public class CounterController {

    private Counter<Long> count = new CounterLong();

    @PostMapping("/create") //POST http://localhost:8080/counters/create?name=...
    public void createCounter (@RequestParam String name) {
        count.addCounter(name);
    }

    @PutMapping("/increment") //PUT http://localhost:8080/counters/increment?name=...
    public void incrementCounter(@RequestParam String name) {
        count.increment(name);
    }

    @GetMapping("/getvalue") //GET http://localhost:8080/counters/getvalue?name=...
    @ResponseBody
    public Long getCounterValue(@RequestParam String name) {
        return count.getCounterValue(name);
    }

    @DeleteMapping("/delete") //DELETE http://localhost:8080/counters/delete?name=...
    public void deleteCounter (@RequestParam String name) {
        count.removeCounter(name);
    }

    @GetMapping("/gettotal") //GET http://localhost:8080/counters/gettotal
    @ResponseBody
    public Long getTotal() {
        return count.totalCounters();
    }

    @GetMapping("/getset") //GET http://localhost:8080/counters/getset
    @ResponseBody
    public Set<String> getSet() {
        return count.allNames();
    }
}

