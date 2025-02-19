package com.example;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public static void main(String[]args){
        System.out.println(getHappyString(3,9));
    }

//    public static String getHappyString(int n, int k) {
//        List<String> result = new ArrayList<>();
//        generateHappyStrings(n, "", result);
//
//        if (k > result.size()) return ""; // Если K больше количества возможных строк, вернуть ""
//
//        return result.get(k - 1); // K-й элемент (1-индексация)
//    }
//
//    private static void generateHappyStrings(int n, String current, List<String> result) {
//        if (current.length() == n) {
//            result.add(current);
//            return;
//        }
//
//        for (char c : new char[]{'a', 'b', 'c'}) {
//            if (current.isEmpty() || current.charAt(current.length() - 1) != c) {
//                generateHappyStrings(n, current + c, result);
//            }
//        }
//    }

    public static String getHappyString(int n, int k) {

        List<String> result = new ArrayList<>();

        getMyString("a", result, n);
        getMyString("b", result, n);
        getMyString("c", result, n);

        if (result.size()>=k)
            return result.get(k-1);
        else return "";

    }

    public static void getMyString (String curr, List<String> result, int max){
        if (curr.length()==max){
            result.add(curr);
            curr="";
            return;
        }

        int len = curr.length();

        String [] array = curr.split("");
        String last = array[len-1];

        // Если последний символ "a"
        if (last.equals("a")) {
            getMyString(curr + "b", result, max);
            getMyString(curr + "c", result, max);
        }
        // Если последний символ "b"
        else if (last.equals("b")) {
            getMyString(curr + "a", result, max);
            getMyString(curr + "c", result, max);
        }
        // Если последний символ "c"
        else if (last.equals("c")) {
            getMyString(curr + "a", result, max);
            getMyString(curr + "b", result, max);
        }
    }


}