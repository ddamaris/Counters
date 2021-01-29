package com.ddamaris.counters.service;

import com.ddamaris.counters.item.Counter;
import com.ddamaris.counters.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CounterService {

    private final CounterRepository repository;
    @Autowired
    public CounterService(CounterRepository repository) {
        this.repository = repository;
    }

    public boolean newCounter(String name){
        try {
            repository.save(new Counter(name, 0L));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public Long incrementCounter(String name) {

        try {
            Counter counter = repository.findByName(name);
            Long value = counter.getValue() + 1;
            counter.setValue(value);
            repository.save(counter);
            return value;
        }
        catch (Exception e) {
            return -1L;
        }
    }

    public Long getCounterValue(String name)
    {
        try {
            Counter counter = repository.findByName(name);
            return counter.getValue();
        }
        catch (Exception e) {
            return -1L;
        }
    }

    public boolean deleteCounter (String name)
    {
        try {
            repository.delete(repository.findByName(name));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Long getTotal() {

        Iterable<Counter> counters = repository.findAll();
        if (counters.iterator().hasNext()) {
            return StreamSupport.stream(counters.spliterator(), false)
                    .map(Counter::getValue)
                    .reduce(0L, Long::sum);
        }
        return -1L;
    }

    public Set<String> getSet()
    {
        Iterable<Counter> counters = repository.findAll();
        return StreamSupport.stream(counters.spliterator(), false)
                .map(Counter::getName)
                .collect(Collectors.toSet());
    }
}
