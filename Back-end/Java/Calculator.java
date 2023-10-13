package Java;

import java.util.Stack;
import java.util.ArrayList;

public class Calculator{
    

    private static String operators;

    static{
        operators = "+-/*()";
    }

    // public static void main(String[]  args){
    //     String str = "01234";
    //     System.out.println(str.substring(0, 2));
    //     System.out.println(calSimple("1+2"));
    // }
    public static double calSimple(String expression){
        ArrayList<String> postFix = postFix(expression);
        Stack<Double> operand = new Stack<>();
        for(String symbol: postFix){
            if(operators.contains(symbol))
                operand.push(compute(operand.pop(),operand.pop(),symbol));
            else
                operand.push(Double.parseDouble(symbol));
        }
        return operand.pop();
    }
    private static ArrayList<String> postFix(String expression){
        ArrayList<String> postFix = new ArrayList<>();
        Stack<String> operator = new Stack<>();
        int index = 0;
        for(int i = 0; i<expression.length(); i++){
            String current = expression.substring(i, i+1);
            String top = operator.isEmpty() ? "" : operator.peek();
            if(operators.contains(current) || i+1==expression.length()){
                if(i+1==expression.length()){
                    postFix.add(expression.substring(index));
                    break;
                }
                postFix.add(expression.substring(index, i));
                index = i+1;
                if(current.equals("("))
                    operator.push(current);
                else if(current.equals(")")){
                    while(!(top.equals("("))){
                        postFix.add(operator.pop());
                    }
                    operator.pop();
                }
                else{
                    while(!(operator.isEmpty()) && precendence(top)>=precendence(current) && !(top.equals("(")))
                        postFix.add(operator.pop());
                    operator.push(current);
                }
            }
        }
        while(!(operator.isEmpty()))
            postFix.add(operator.pop());
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