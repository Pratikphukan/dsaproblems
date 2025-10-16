package com.dsaproblems.DSAProblems.stack;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyDirectoryPath {

    public static void main(String[] args) {
        //-> /home//foo/
        //-> /a/./b/../../c/
        //-> /home/user/Documents/../Pictures
        String A = "/home/user/Documents/../Pictures//";
        System.out.println(simplifyPathv1(A));
        System.out.println(simplifyPathv2(A));
    }

    //working code and optimal
    private static String simplifyPathv2(String A) {
        String[] parts = A.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue; // Skip empty or current directory
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeFirst(); // Go up one directory
                }
            } else {
                stack.addFirst(part); // Add valid directory to stack
            }
        }
        StringBuilder result = new StringBuilder("/");
        while (!stack.isEmpty()) {
            result.append(stack.removeLast()); // Build the simplified path
            if (!stack.isEmpty()) {
                result.append("/"); // Add slash between directories
            }
        }
        return result.toString();
    }

    private static String simplifyPathv1(String A) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (char c : A.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
            if (c == '/') {
                flag = false;
                if (sb.length() > 0)
                    stack.addFirst(sb.toString());
                sb = new StringBuilder();
            }
            if (c == '.') {
                if (flag) {
                    if (!stack.isEmpty()) {
                        stack.removeFirst();
                    }
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        stack.addFirst(sb.toString());
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            String x = stack.removeFirst();
            if (!x.isEmpty()) {
                ans.insert(0, x);
                ans.insert(0, "/");
            }
        }
        if (ans.toString().isEmpty()) {
            return "/";
        }

        return ans.toString();
    }
}
