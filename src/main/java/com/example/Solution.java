package com.example;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {

    public static void main(String[] args) {

        isValid("()");
    }


    public static boolean isValid(String s) {

        StringBuilder openSign = new StringBuilder();

        String[]array = s.split("");

        for (int i=0; i<array.length; i++){
            if (array[i].equals("(") ||
                    array[i].equals("{") ||
                    array[i].equals("[")) {
                openSign.append(array[i]);
                continue;
            }

            int len = openSign.length();

            String reverse = new StringBuilder(String.valueOf(openSign.charAt(len - 1))).reverse().toString();


            if (!array[i].equals(reverse))
                return false;

            openSign.deleteCharAt(len-1);
        }

        if (openSign.length()==0) return true;
        return false;

    }

    /**
     * Перевернуть массив.
     */

    public static void reverseArray() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 5};
        int tenp = -1;
        int index = array.length - 1;
        for (int i = 0; i < array.length / 2; i++) {
            tenp = array[i];
            array[i] = array[index];
            array[index] = tenp;
            index--;
        }

        IntStream.of(array).forEach(System.out::println);

    }

    /**
     * Дан целочисленный массив nums. Необходимо переместить все нулевые элементы в конец массива,
     * сохраняя относительный порядок элементов, не являющихся нулем.
     * Решение должно производиться на месте, без использования дополнительного массива,
     * а также должно иметь минимальную сложность по времени и пространству.
     */

    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length == 0) return;

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

    }

    /**
     * Given List names. Удалите первую букву из каждого имени и поверните отсортированный список.
     */

    public static void getNanes(List<String> input) {
        if (input == null || input.isEmpty()) return;

        List<String> result = input.stream()
                .map(i -> i.substring(1))
                .sorted(Comparator.reverseOrder())
                .toList();

        result.forEach(System.out::println);
    }

    /**
     * Дана квадратная матрица. Найти сумму элементов на ее диагонали.
     */

    public static int diagonalSum(int[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j)
                    sum += matrix[i][j];
            }
        }

        return sum;

    }

    public static int strStr(String haystack, String needle) {
        String[] big = haystack.split("");
        String[] small = needle.split("");

        int result = -1;
        int index = 0;

        for (int j = 0; j < big.length; j++) {
            if (big[j].equals(small[index])) {
                index++;
                if (result == -1)
                    result = j;
            }

            if (index == small.length) break;

            if (!big[j].equals(small[index])) {
                result = -1;
                index = 0;
            }
        }

        return result;

    }


    public static int[] twoSum(int[] nums, int target) {

        List<Integer> list = IntStream.of(nums)
                .boxed()
                .toList();

        int currIndex = 0;
        int nextIndex = -1;

        for (Integer i : list) {
            if (list.contains(target - i)) {
                nextIndex = list.indexOf(target - i);
                break;
            }
            currIndex++;
        }

        return new int[]{currIndex, nextIndex};
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

        if (result.size() >= k)
            return result.get(k - 1);
        else return "";

    }

    public static void getMyString(String curr, List<String> result, int max) {
        if (curr.length() == max) {
            result.add(curr);
            curr = "";
            return;
        }

        int len = curr.length();

        String[] array = curr.split("");
        String last = array[len - 1];

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