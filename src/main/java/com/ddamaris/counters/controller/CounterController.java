package com.ddamaris.counters.controller;

import com.ddamaris.counters.item.Counter;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/counters")
public class CounterController {

    private Counter count = new Counter();

    @PostMapping("/create")
    public void createCounter (@RequestParam String name) {
        count.addCounter(name);
    }

    @PutMapping("/increment")
    public void incrementCounter(@RequestParam String name) {
        count.increment(name);
    }

    @GetMapping("/getvalue")
    @ResponseBody
    public Long getCounterValue(@RequestParam String name) {
        return count.getCounterValue(name);
    }

    @DeleteMapping("/delete")
    public void deleteCounter (@RequestParam String name) {
        count.removeCounter(name);
    }

    @GetMapping("/gettotal")
    @ResponseBody
    public Long getTotal() {
        return count.totalCounters();
    }

    @GetMapping("/getset")
    @ResponseBody
    public Set<String> getSet() {
        return count.allNames();
    }
}

