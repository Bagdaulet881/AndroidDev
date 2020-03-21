package com.example.calcbybakosta;

import java.util.Stack;

public class Calculator {


    public enum Operator{ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK, DOT, DBLE, SQRT, FACT, PERCENT, SIN, COS,TAN,LN,LOG,E,XN,SQRTN}

    public static void main(String[] args){
        String expression = "2-6-7*8/2+5";
        Calculator calc = new Calculator();
        System.out.println(calc.compute(expression));
    }

    public double compute(String sequence){
        Stack<Double> numberStack = new Stack<Double>();
        Stack<Operator> operatorStack = new Stack<Operator>();
        for(int i = 0; i < sequence.length(); i++){
            try{
                int number = parseNumber(sequence, i);
                numberStack.push((double)number);

                i += Integer.toString(number).length();
                if(i >= sequence.length()){
                    break;
                }

                Operator op = parseOperator(sequence, i);
                collapseTop(numberStack, operatorStack, op);
                operatorStack.push(op);
            } catch(NumberFormatException ex){
                return Integer.MIN_VALUE;
            }
        }
        collapseTop(numberStack, operatorStack, Operator.BLANK);
        if(numberStack.size() == 1 && operatorStack.size() == 0){
            return numberStack.pop();
        }
        return 0;
    }
//operatorsStack peek futureTop
    private void collapseTop(Stack<Double> numberStack, Stack<Operator> operatorStack, Operator futureTop){
        while(numberStack.size() >= 2 && operatorStack.size() >= 1){
            if(priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())){

                double second = numberStack.pop();
                double first = numberStack.pop();

                Operator op = operatorStack.pop();

                double result = applyOp(first, op, second);

                numberStack.push(result);
            } else{
                break;
            }
        }
    }
    private double setMaxSize(double answer){
        double divSum = Double.parseDouble(String.format("%.3f",answer));
        return divSum;

    }
    private double applyOp(double left, Operator op, double right){
        int left1 = (int)left;
        int right1 = (int)right;//secret 2.06!

        String dotcase = left1 + "." + right1;
        Double res = Double.parseDouble(dotcase);
        switch (op){
//            case ADD: return left + right;
//            case SUBTRACT: return left - right;
//            case MULTIPLY: return left * right;
//            case DIVIDE: return left / right;
//            case DBLE: return left * left;
//            case DOT: return res;
//            case SQRT: return Math.sqrt(left);
//            case FACT: return fact(left);
            case ADD: return setMaxSize(left + right);
            case SUBTRACT: return setMaxSize(left - right);
            case MULTIPLY: return setMaxSize(left * right);
            case DIVIDE: return setMaxSize(left / right);
            case DBLE: return setMaxSize(left * left);
            case DOT: return res;
            case SQRT: return setMaxSize(Math.sqrt(left));
            case FACT: return setMaxSize(fact(left));


            case PERCENT: return right*left/100;
            case SIN:return Math.sin(right);
            case COS:return Math.cos(right);
            case TAN:return Math.tan(right);
            case LN:return Math.log(right);
            case LOG:return Math.log10(right);
            case E:return 2.7182;
            case XN:return Math.pow(left,right);
            case SQRTN:return sqrtN(left,right);
            default: return right;
        }

    }

    private int priorityOfOperator(Operator op){
        switch (op){
            case ADD: return 1;
            case SUBTRACT: return 1;
            case MULTIPLY: return 2;
            case DIVIDE: return 2;
            case DBLE: return 3;
            case FACT: return 3;
            case SQRT: return 3;
            case DOT: return 4;
            case BLANK: return 0;

            case PERCENT:return 3;
            case SIN:return 3;
            case COS:return 3;
            case TAN:return 3;
            case LN:return 3;
            case LOG:return 3;
            case E:return 5;
            case XN:return 2;
            case SQRTN:return 3;

        }
        return 0;
    }

    private int parseNumber(String sequence, int offset){
        StringBuilder sb = new StringBuilder();
        while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset))){
            sb.append(sequence.charAt(offset));
            offset++;
        }
        return Integer.parseInt(sb.toString());
    }

    private Operator parseOperator(String sequence, int offset){
        if(offset < sequence.length()){
            char op = sequence.charAt(offset);
            switch (op){
                case '+': return Operator.ADD;
                case '-': return Operator.SUBTRACT;
                case '*': return Operator.MULTIPLY;
                case '/': return Operator.DIVIDE;
                case '.': return Operator.DOT;
                case 'x': return Operator.DBLE;
                case '√': return Operator.SQRT;
                case '!': return Operator.FACT;



                case '%': return Operator.PERCENT;
                case 's': return Operator.SIN;
                case 'c': return Operator.COS;
                case 't': return Operator.TAN;
                case 'l': return Operator.LN;
                case 'g': return Operator.LOG;
                case 'e': return Operator.E;
                case '^': return Operator.XN;
                case 'n': return Operator.SQRTN;

            }
        }
        return Operator.BLANK;
    }
    private double sqrtN(double left, double right){
        if(right==0){
            return 1;
        }else
            return Math.pow(left, 1.0/right);
    }
    private long fact(double left){
        int n = (int)left;
        long fact=1;
        if(n==0)return 1;
        else{
            for(int i=1;i<=n;i++)
            {
                fact=fact*i;
            }
            return fact;
        }

    }
}
