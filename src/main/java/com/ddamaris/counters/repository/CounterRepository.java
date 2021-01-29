package com.ddamaris.counters.repository;

import com.ddamaris.counters.item.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Long> {
    Counter findByName(String name);

}
