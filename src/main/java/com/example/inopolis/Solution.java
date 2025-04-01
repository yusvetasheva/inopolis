package com.example.inopolis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Нужнно удалить из списка анаграммы, в итоговом списке анаграмм быть не должно
 */
public class Solution {
    public static void main(String[] args) {
        List<String> anagrams = List.of("Race", "NighT", "Angle", "CaRe", "angel", "ThiNG", "agnel");

        System.out.println(removeAnagrams(anagrams));
    }


    static List<String> removeAnagrams(List<String> anagrams) {
        //new sorted value, old init value
        HashMap<String, String> map = new HashMap<>();
        String sorted = "";

        for (String i : anagrams) {
            char[] array = i.toLowerCase().toCharArray();
            Arrays.sort(array);
            sorted = new String(array);

            map.putIfAbsent(sorted, i);
        }

        return new ArrayList<>(map.values());

    }
}