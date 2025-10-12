package com.dsaproblems.DSAProblems.intermediate;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Find for how many elements, there is a strictly smaller element
 * and a strictly greater element.
 */
public class SmallerAndGreater {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(241, 710, 751, 904, 264, 955, 413, 163, 914, 173, 940, 164, 494, 545, 750,
                685, 665, 619, 99, 953, 759, 783, 534, 706, 771, 570, 471, 543, 658, 674, 634, 766, 931, 40, 244, 209,
                360, 517, 962, 420, 155, 711, 845, 646, 117, 641, 458, 790, 620, 46, 837, 560, 374, 510, 133, 214, 322,
                275, 342, 460, 642, 153, 751, 317, 71, 8, 256, 195, 256, 469, 16, 986, 339, 753, 773, 921, 638, 529,
                333, 73, 393, 985, 846, 175, 548, 694, 560, 174, 710, 478, 316);
        // 1, 2, 3
        // 913,440,865,72,612,445,101,994,356,91,461,930,583,448,543,170,333,107,425,73,172,416,899,826,659,561,314,25,110
        // [241,710,751,904,264,955,413,163,914,173,940,164,494,545,750,685,665,619,99,953,759,783,534,706,771,570,471,543,658,674,634,766,931,40,244,209,360,517,962,420,155,711,845,646,117,641,458,790,620,46,837,560,374,510,133,214,322,275,342,460,642,153,751,317,71,8,256,195,256,469,16,986,339,753,773,921,638,529,333,73,393,985,846,175,548,694,560,174,710,478,316]
        System.out.println(findNoOfElementsv1(input));
        System.out.println(findNoOfElementsv2(input));
        System.out.println(findNoOfElementsv3(input));
        System.out.println(findNoOfElementsv4(input));
        System.out.println(findNoOfElementsv5(input));
    }

    private static long findNoOfElementsv5(List<Integer> input) {
        Comparator<Integer> integerComparator = Integer::compare;
        Integer largest = input.stream().max(integerComparator).orElse(1000000);
        Integer smallest = input.stream().min(integerComparator).orElse(1);
        return Long.valueOf(input.stream().filter(e -> e > smallest && e < largest).count()).intValue();
    }

    // working code, around 1200ms
    private static int findNoOfElementsv4(List<Integer> input) {
        int smallest = input.parallelStream().reduce(Integer.MAX_VALUE, (acc, x) -> Math.min(acc, x));
        int greatest = input.parallelStream().reduce(Integer.MIN_VALUE, (acc, x) -> Math.max(acc, x));
        return input.parallelStream().filter(element -> smallest < element && element < greatest)
                .collect(Collectors.counting()).intValue();
    }

    // not working
    private static int findNoOfElementsv2(List<Integer> input) {
        Set<Integer> uniqueElements = new HashSet<>();
        for (int item : input) {
            uniqueElements.add(item);
        }
        if (uniqueElements.size() == 1) {
            return 0;
        }
        if (uniqueElements.size() <= input.size()) {
            return uniqueElements.size() - 2;
        }
        return 0;
    }

    // working code, around 1000ms
    private static int findNoOfElementsv1(List<Integer> input) {
        int smallest = Integer.MAX_VALUE;
        int greatest = Integer.MIN_VALUE;
        for (int element : input) {
            smallest = Math.min(smallest, element);
            greatest = Math.max(greatest, element);
        }
        int countOfElements = 0;
        for (int element : input) {
            if (smallest < element && element < greatest) {
                countOfElements++;
            }
        }
        return countOfElements;
    }

    // working code, around 1100ms
    private static int findNoOfElementsv3(List<Integer> input) {
        int smallest = input.stream().reduce(Integer.MAX_VALUE, (acc, x) -> Math.min(acc, x));
        int greatest = input.stream().reduce(Integer.MIN_VALUE, (acc, x) -> Math.max(acc, x));
        return input.stream().filter(element -> smallest < element && element < greatest).collect(Collectors.counting())
                .intValue();
    }
}
