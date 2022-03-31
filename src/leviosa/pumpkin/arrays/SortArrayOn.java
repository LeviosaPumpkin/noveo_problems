package leviosa.pumpkin.arrays;

import java.util.HashMap;
import java.util.Map;

//6. Есть массив int, в котором могут быть только цифры от 0 до 2. Реализовать сортировку этого массива за минимально
// возможное время. Надо решить за  O(N), где N - длина массива.
// Надо уметь по запросу сделать для более общего случая, где цифры могут быть от 0 до K.
public class SortArrayOn {
    public static void main(String[] args) {
        int [] array = {2, 1, 0, 0, 2, 2, 1, 1, 1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            if (map.containsKey(i)) {
                for (int j = 0; j < map.get(i); j++) {
                    array[k] = i;
                    k++;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
