package com.example.owner.birdmemory;

/**
 * Created by Owner on 04/02/2018.
 */

public class MemoryImageComparer {

    public boolean compare(MemoryImage m1, MemoryImage m2) {

        return (m1.getBirdType() == m2.getBirdType());

    }
}
