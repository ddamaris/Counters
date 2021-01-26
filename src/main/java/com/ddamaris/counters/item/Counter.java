package com.ddamaris.counters.item;

import java.util.Map;
import java.util.Set;

public class Counter {

    private Map<String, Long> counters;

    public Counter() {}

    public void addCounter (String name) {
        counters.put(name, 0L);
    }

    public void increment (String name) {
        counters.computeIfPresent(name, (k, v) -> v++);
    }

    public Long getCounterValue(String name) {
        return counters.get(name);
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
