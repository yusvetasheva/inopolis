package com.example.inopolis;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List anagrams = List.of("Race", "NighT", "Angle", "CaRe", "angel", "ThiNG", "agnel");

        System.out.println(removeAnagrams(anagrams));
    }


    static List removeAnagrams(List anagrams) {



    }

    static boolean isAnagram(String first, String second){
        if (first.length()!=second.length()) return false;

        int [] array = new int [26];

        for (char c : first.toCharArray()){
            array[Character.toLowerCase(c) -'a']++;
        }

        for (char c : second.toCharArray()){
            array[Character.toLowerCase(c) -'a']--;
        }

        for (int i : array){
            if (i!=0) return false;
        }

        return true;
    }
}