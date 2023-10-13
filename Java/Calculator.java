package Java;

import java.util.Stack;

public class Calculator{

    private static String operators;

    static{
        operators = "+-/*()";
    }

    public double calSimple(String expression){
        String postFix = postFix(expression);
        Stack<Double> operand = new Stack<>();
        int index = 0;
        for(int i = 0; i<postFix.length(); i++){
            String current = postFix.substring(i, i+1);
            if(operators.contains(current)){
                operand.push(Double.parseDouble(postFix.substring(index,i)));
                index = i+1;
                operand.push(compute(operand.pop(),operand.pop(),current));
            }
        }
        return operand.pop();
    }
    private static String postFix(String expression){
        String postFix = "";
        Stack<String> operator = new Stack<>();
        int index = 0;
        for(int i = 0; i<expression.length(); i++){
            String current = expression.substring(i, i+1);
            String top = operator.isEmpty() ? "" : operator.peek();
            if(operators.contains(current)){
                postFix = postFix.concat(expression.substring(index, i));
                index = i+1;
                if(current.equals("("))
                    operator.push(current);
                else if(current.equals(")")){
                    while(!(top.equals("("))){
                        postFix = postFix.concat(operator.pop());
                    }
                    operator.pop();
                }
                else{
                    while(!(operator.isEmpty()) && precendence(top)>=precendence(current) && !(top.equals("(")))
                        postFix = postFix.concat(operator.pop());
                    operator.push(current);
                }
            }
        }
        while(!(operator.isEmpty()))
            postFix.concat(operator.pop());
        return postFix;
    }
    private static int precendence(String operator){
        return switch(operator){
            case "*","/" -> 2;
            case "+","-" -> 1;
            default -> 0;
        };
    }
    private static double compute(double num2, double num1, String operator){
        return switch(operator){
            case "+" -> num1+num2;
            case "-" -> num1-num2;
            case "*" -> num1*num2;
            case "/" -> num1/num2;
            default -> 0;
        };
    }
}