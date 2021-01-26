package com.ddamaris.counters.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CounterLong implements Counter<Long> {

    private Map<String, Long> counters = new HashMap<>();

    public CounterLong() {}

    public void addCounter (String name) {
        counters.put(name, 0L);
    }

    public void increment (String name) {
        counters.computeIfPresent(name, (k, v) -> v + 1);
    }

    public Long getCounterValue(String name) {
        if (counters.containsKey(name)){
            return counters.get(name);
        }
        return -1L;
    }

    public void removeCounter(String name) {
        counters.remove(name);
    }

    public Long totalCounters() {
        return counters.values().stream().reduce(0L, Long::sum);
    }

    public Set<String> allNames() {
        return counters.keySet();
    }
}
