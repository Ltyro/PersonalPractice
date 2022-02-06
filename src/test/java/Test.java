package test.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * by Lnstark
 * 2021/5/10
 */
public class Test {
    public static void main(String[] args) {
        testQuickSort();
    }

    static void testQuickSort() {
        int n = 100;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Random().nextInt(n * 10);
        }
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.asList(arr));
    }

    static void quickSort(Integer[] arr, int left, int right) {
        int l = left, r = right;
        if (r <= l) return;
        int temp = arr[l];
        while (l < r) {
            while (l < r && arr[r] > temp) r--;
            if (l < r) arr[l++] = arr[r];
            while (l < r && arr[l] <= temp) l++;
            if (l < r) arr[r--] = arr[l];
        }
        arr[l] = temp;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    static void testStream() {
        List<Integer> l = new ArrayList<>();
        l.add(1); l.add(2);
        l.stream()
                .filter(i -> i > 0)
                .map(i -> ++i)
                .collect(Collectors.toList());
    }
}
