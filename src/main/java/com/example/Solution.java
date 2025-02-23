package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {

    public static void main(String[]args){

        strStr("abc", "ab");



       // System.out.println(findDifferentBinaryString(new String [] {"00", "01"}));
    }

    public static int strStr(String haystack, String needle) {
        String [] big = haystack.split("");
        String [] small = needle.split("");

        int result =-1;
        int index =0;

        for (int j=0; j<big.length; j++){
            if (big[j].equals(small[index])){
                index++;
                if (result==-1)
                    result=j;
            }

            if (index==small.length) break;

            if (!big[j].equals(small[index])) {
                result=-1;
                index=0;
            }
        }

        return result;

    }


    public static int[] twoSum(int[] nums, int target) {

        List<Integer> list = IntStream.of(nums)
                .boxed()
                .toList();

        int currIndex =0;
        int nextIndex = -1;

        for (Integer i : list){
            if (list.contains(target-i)){
                nextIndex = list.indexOf(target-i);
                break;
            }
            currIndex++;
        }

        return new int [] {currIndex, nextIndex};
    }

    public static String findDifferentBinaryString(String[] nums) {
        Set<String> exist = new HashSet<>(List.of(nums));
        return generate(exist, "", nums[0].length());
    }

    private static String generate(Set<String> exist, String current, int max) {
        if (current.length() == max) {
            if (!exist.contains(current)) {
                return current;
            }
            return null;
        }

        String res = generate(exist, current + "0", max);
        if (res != null) return res; // Если найден результат, сразу возвращаем

        res = generate(exist, current + "1", max);
        return res; // Если найдено в правой ветке, возвращаем
    }

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