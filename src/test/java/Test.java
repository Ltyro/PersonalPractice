package test.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * by Lnstark
 * 2021/5/10
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1); l.add(2);
        l.stream()
                .filter(i -> i > 0)
                .map(i -> ++i)
                .collect(Collectors.toList());
    }
}
