/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author vietddse62677
 */
public class ExpTree {

    private TreeNode root;

    public ExpTree(String infix) {
        root = ExpTree.this.infix2Tree(infix);
    }

    private String LRV(TreeNode t) {
        if (t == null) {
            return "";
        }
        return LRV(t.left) + LRV(t.right) + t.data + " ";
    }

    private void grow(String op, Stack<TreeNode> stack2) {
        TreeNode newNode = new TreeNode(op);
        newNode.right = stack2.pop();
        newNode.left = stack2.pop();
        stack2.push(newNode);
    }

    public TreeNode infix2Tree(String infix) {
        Stack<String> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        Scanner sc = new Scanner(infix);
        sc.useDelimiter(" ");
        while (sc.hasNext()) {
            if (sc.hasNextDouble()) {
                stack2.push(new TreeNode(String.valueOf(sc.nextDouble())));
            } else {
                String s = sc.next();
                if ("+-*/^".contains(s)) {
                    while (!stack1.isEmpty() && getPriority(s) <= getPriority(stack1.peek())) {
                        grow(stack1.pop(), stack2);
                    }
                    stack1.push(s);
                } else if (s.equals("(")) {
                    stack1.push(s);
                } else if (s.equals(")")) {
                    for (String s1 = stack1.pop(); !s1.equals("("); s1 = stack1.pop()) {
                        grow(s1, stack2);
                    }
                }
            }
        }
        while (!stack1.empty()) {
            grow(stack1.pop(), stack2);
        }
        return stack2.pop();
    }

    public double postfixEval(String postfix) {
        Stack<Double> stack = new Stack<>();
        Scanner scanner = new Scanner(postfix);
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                stack.push(scanner.nextDouble());
            } else {
                char op = scanner.next().charAt(0);
                double y = stack.pop();
                double x = stack.pop();
                double z = 0;
                switch (op) {
                    case '+':
                        z = x + y;
                        break;
                    case '-':
                        z = x - y;
                        break;
                    case '*':
                        z = x * y;
                        break;
                    case '/':
                        z = x / y;
                        break;
                    case '^':
                        z = Math.pow(x, y);
                        break;
                }
                stack.push(z);
            }
        }
        return stack.pop();
    }

    private int getPriority(String s) {
        if ("+-".contains(s)) {
            return 0;
        }
        if ("*/".contains(s)) {
            return 1;
        }
        if ("^".contains(s)) {
            return 2;
        }
        return -1;
    }

    public String tree2Postfix() {
        return LRV(root);
    }
}

class TreeNode {

    protected String data;
    protected TreeNode left = null, right = null;

    public TreeNode(String data) {
        this.data = data;
    }

}
