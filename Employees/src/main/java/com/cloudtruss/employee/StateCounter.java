package com.cloudtruss.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateCounter {
    public static void main(String[] args) {
        try {
            Map<String, Long> stateCount = new HashMap<>();
                    // parallel() - splits the processes into different groups that are processed simultaneously, and it reduces processing time.
                    Files.lines(Path.of("/Users/courage/Desktop/JavaTraining/Employees/Hr5m.csv"))
                            .skip(1)
                            //.limit(200)
                            .map(s -> s.split(","))
//                            .forEach(a -> stateCount.computeIfAbsent(a[32], key -> (long) key.length()));
//                            .forEach(a -> stateCount.compute(a[32], (k, v) -> v == null ? 1L : v + 1));
                            .forEach(a -> stateCount.merge(a[32], 1L, Long::sum)); // (x,y) -> x + y
            System.out.println(stateCount);

            List.of("cat", "dog").replaceAll(String::toUpperCase);
            List.of("cat", "dog").removeIf(s -> s.startsWith("c"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
