package com.ddamaris.counters.item;

import java.util.Set;

public interface Counter<T> {
    public void addCounter (String name);
    public void increment (String name);
    public T getCounterValue(String name);
    public void removeCounter(String name);
    public T totalCounters();
    public Set<String> allNames();

}
