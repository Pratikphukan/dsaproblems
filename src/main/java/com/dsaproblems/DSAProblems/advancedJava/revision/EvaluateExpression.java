package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class EvaluateExpression {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("2", "1", "+", "3", "*"));
        // "4", "13", "5", "/", "+"
        // "2", "1", "+", "3", "*"
        // ["2","2","/"]
        System.out.println(evaluateExpressionv1(input));
        System.out.println(evaluateExpressionv2(input));
        System.out.println(evaluateExpressionv3(input));
    }

    private static Integer evaluateExpressionv2(List<String> input) {
        Deque<Integer> values = new LinkedList<>();
        int first;
        int second;
        for (String str : input) {
            // on encountering an operator, pop the top two elements from the stack,
            // perform the operation and push that back into the stack
            if (equal(str, "+")) {
                second = values.pop();
                first = values.pop();
                values.push(first + second);
            } else if (equal(str, "*")) {
                second = values.pop();
                first = values.pop();
                values.push(first * second);
            } else if (equal(str, "/")) {
                second = values.pop();
                first = values.pop();
                values.push(first / second);
            } else if (equal(str, "-")) {
                second = values.pop();
                first = values.pop();
                values.push(first - second);
            } else {
                first = Integer.parseInt(str);
                values.push(first);
            }
        }
        return values.peek();
    }

    private static boolean equal(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }


    private static int evaluateExpressionv3(List<String> input) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String item : input) {
            if (isOperator(item) && stack.size() >= 2) {
                int operand2 = stack.pollFirst();
                int operand1 = stack.pollFirst();
                stack.offerFirst(evaluate(operand1, operand2, item));
            } else {
                stack.offerFirst(Integer.parseInt(item));
            }
        }
        return stack.pollFirst();
    }

    private static int evaluateExpressionv1(List<String> input) {
        Deque<String> stack = new ArrayDeque<>();
        for (String item : input) {
            if (isOperator(item) && stack.size() >= 2) {
                int operand2 = Integer.parseInt(stack.pollFirst());
                int operand1 = Integer.parseInt(stack.pollFirst());
                stack.offerFirst(evaluate(operand1, operand2, item).toString());
            } else {
                stack.offerFirst(item);
            }
        }
        return Integer.parseInt(stack.pollFirst());
    }

    private static Integer evaluate(int operand1, int operand2, String currItem) {
        return switch (currItem) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            default -> operand1 / operand2;
        };
    }

    private static boolean isOperator(String c) {
        return c.equals("/") || c.equals("*") || c.equals("+") || c.equals("-"); // don't use ==
    }

}
