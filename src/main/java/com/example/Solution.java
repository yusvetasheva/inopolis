package com.example;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {

    public static void main(String[] args) {

        System.out.println("   f ff     f   f    f".trim());


    }

    public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        //значения id уникальны
        Map<Integer, Integer> map = new HashMap<>();
        int left1 = 0;
        int left2 = 0;
        while (left1 < nums1.length && left2 < nums2.length) {

            if (nums1[left1][0] == nums2[left2][0]) {
                map.put(nums1[left1][0], nums1[left1][1] + nums2[left2][1]);
                left1++;
                left2++;
            } else if (nums1[left1][0] > nums2[left2][0]) {
                map.put(nums2[left2][0], nums2[left2][1]);
                left2++;
            } else {
                map.put(nums1[left1][0], nums1[left1][1]);
                left1++;
            }
        }

        while (left1 < nums1.length) {
            map.put(nums1[left1][0], nums1[left1][1]);
            left1++;
        }
        while (left2 < nums2.length) {
            map.put(nums2[left2][0], nums2[left2][1]);
            left2++;
        }

        int[][] result = new int[map.size()][2];
        int index = 0;

        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            result[index][0] = i.getKey();
            result[index][1] = i.getValue();
        }

        return result;
    }


    public static int maximumSwap(int num) {

        char[] sign = String.valueOf(num).toCharArray();
        char[] sorted = sign.clone();
        char max;
        Arrays.sort(sorted);

        int index = 0;


        for (int i = 0; i < sign.length; i++) {
            max = sorted[sign.length - i - 1];

            if (sign[i] < max) {
                index = (new String(sign)).indexOf(max);
                sign[index] = sign[i];
                sign[i] = max;
                break;
            }
        }

        return Integer.parseInt(new String(sign));

    }

    public static int fact(int input) {
        if (input > 1) return input * fact(input - 1);
        return input;
    }

    //Дан int[]. Найти число подмассивов, где все значения всех чисел разные


    public static int numberOfAlternatingGroups(int[] colors, int k) {
        if (colors.length < k) return 0;
        List<Integer> result = new ArrayList<>();

        int currLen = 1;
        int prev = colors[0];

        for (int i = 1; i < colors.length; i++) {
            if (colors[i] != prev) {
                prev = colors[i];
                currLen++;
            } else {
                result.add(currLen);
                currLen = 1;
            }
        }

        if (currLen > 1) result.add(currLen);

        if (result.size() == 0) return 0;

        int size = result.size();

        if (colors[0] != colors[colors.length - 1]) {
            Integer sum = 0;
            if (result.size() == 1) sum = result.get(0) + 1;
            else sum = result.get(0) + result.get(size - 1);
            result.set(0, sum);
            result.remove(size - 1);
        }

        int out = 0;

        for (Integer i : result) {
            if (i == k) out++;
            else if (i > k) {
                out += i - k + 1;
            }
        }

        return out;
    }

    public static int[] plusOne(int[] digits) {

        int perenos = 1;
        int last = 0;

        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            return digits;
        }

        for (int i = digits.length - 1; i >= 0; i--) {
            last = digits[i] + perenos;
            perenos = last / 10;
            digits[i] = last % 10;

            if (perenos == 0) return digits;
        }

        int[] result = new int[digits.length];
        result[0] = perenos;

        for (int i = 1; i < digits.length; i++) {
            result[i] = digits[i - 1];
        }
        return result;
    }


    public static boolean isValid(String s) {

        StringBuilder openSign = new StringBuilder();

        String[] array = s.split("");

        for (int i = 0; i < array.length; i++) {
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

            openSign.deleteCharAt(len - 1);
        }

        if (openSign.length() == 0) return true;
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