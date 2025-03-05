import java.util.Scanner;
import java.util.Stack;

public class Postfixeval {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix string");
        String input = sc.nextLine();
        sc.close();

        try {
            int result = evaluatePostfix(input);
            System.out.println("Result:" + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int evaluatePostfix(String expr) {
        Stack<Integer> stack = new Stack<>();
        int operandCount = 0, operatorCount = 0;

        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(ch - '0'); // Convert char to integer
                operandCount++;
            } else if (isOperator(ch)) {
                operatorCount++;

                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid Postfix string; Operators are given more than operands\nInvalid Postfix String");
                }

                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOperation(a, b, ch));
            } else {
                throw new IllegalArgumentException("Invalid Postfix String");
            }
        }

        if (stack.size() != 1 || operandCount - operatorCount != 1) {
            throw new IllegalArgumentException("Invalid Postfix String");
        }

        return stack.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int applyOperation(int a, int b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // Assumes no division by zero
            default: throw new IllegalArgumentException("Invalid Operator");
        }
    }
} 