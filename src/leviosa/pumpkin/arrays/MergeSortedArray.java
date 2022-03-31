package leviosa.pumpkin.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//4. Реализовать слияние уже отсортированных K массивов, потенциально бесконечных, то есть могут быть стримами.
// Надо сделать за O(N*K*Log(K)), где N - длина наибольшего из массивов, K - количество массивов.
public class MergeSortedArray {
    public static List<Integer> merge(List<List<Integer>> arrays) {
        List<Integer> result = new ArrayList<>();
        int k = arrays.size();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            indexes.add(0);
        }
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            sizes.add(arrays.get(i).size());
        }

        while (!isReachEnd(indexes, sizes)) {
            int currentMin = 1000;
            int currentArray = 0;
            for (int i = 0; i < k; i++) {
                if (indexes.get(i) < sizes.get(i) && currentMin >= arrays.get(i).get(indexes.get(i))) {
                    currentMin = arrays.get(i).get(indexes.get(i));
                    currentArray = i;
                }
            }
            indexes.set(currentArray, indexes.get(currentArray) + 1);
            result.add(currentMin);
        }

        return result;
    }

    private static Integer smallestElement(List<Integer> currentElements) {
        if (currentElements.size() == 1) {
            return currentElements.get(0);
        } else {
            Integer integer1 = smallestElement(currentElements.subList(1, currentElements.size() / 2));
            Integer integer2 = smallestElement(currentElements.subList(currentElements.size() / 2 + 1, currentElements.size()));
            return integer1 < integer2 ? integer1 : integer2;
        }
    }
    private static boolean isReachEnd(List<Integer> indexes, List<Integer> sizes) {
       for (int i = 0; i < indexes.size(); i++) {
           if (indexes.get(i) != sizes.get(i)) return false;
       }
       return true;
    }

    public static void main(String[] args) {
        merge(List.of(
            List.of(3, 4, 8, 9, 12),
            List.of(1, 7, 10, 11, 12, 13),
            List.of(2, 5, 6, 10, 14, 15))).forEach(System.out::println);
    }
}
