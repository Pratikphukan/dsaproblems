package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Check2BracketExpressions {

    public static void main(String[] args) {

        System.out.println(simplifyExpressionv1("-(-(-(-(-a))))"));
        String A = "-(a+b+c)";
        String B = "-a-b-c";
        System.out.println(check2BracketExpressionsv1(A, B));

    }

    private static int check2BracketExpressionsv1(String A, String B) {
        int[] a = simplifyExpressionv2(A);
        int[] b = simplifyExpressionv2(B);
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return 0;
            }
        }
        return 1;
    }

    //working code
    private static int[] simplifyExpressionv2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int[] alphabets = new int[26];
        char localOperator = '+';
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-') {
                if (!stack.isEmpty()) {
                    char o = stack.peekFirst();
                    if (c == '+' && o == '-' ||
                            (c == '-' && o == '+')) {
                        c = '-';
                    } else {
                        c = '+';
                    }
                }
                localOperator = c;
            } else if (c == '(') {
                stack.addFirst(localOperator);
            } else if (c == ')') {
                stack.pollFirst();
            } else {
                alphabets[c - 'a'] = localOperator;
            }
        }
        return alphabets;
    }

    //WORKING CODE
    private static int[] simplifyExpressionv1(String s) {
        int[] coeff = new int[26];      // net coefficient for a..z
        Deque<Integer> signStack = new ArrayDeque<>();
        signStack.push(1);               // global sign multiplier
        int nextOp = 1;                  // + for '+', - for '-'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;

            if (ch == '+') {
                nextOp = 1;
            } else if (ch == '-') {
                nextOp = -1;
            } else if (ch == '(') {
                // push the current cumulative sign for the group
                signStack.push(signStack.peek() * nextOp);
                nextOp = 1; // reset immediate op after '('
            } else if (ch == ')') {
                signStack.pop();
            } else if (ch >= 'a' && ch <= 'z') {
                coeff[ch - 'a'] += signStack.peek() * nextOp;
                nextOp = 1; // consume the op
            }
            // (Optional: handle uppercase by lowercasing, or digits if needed)
        }
        return coeff;
    }
}
