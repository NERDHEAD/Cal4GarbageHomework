--Test Code
--개별 테스트에서는 java jvm 설치 후 동작 확인함
--1512035 류주성 2020.06.23
--전문 직접 작성함
CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Test" AS 
public class Test {
    public String hello(String str){
        return str+ " !!!!";
    }
}
/




CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Calculator" AS 
import java.util.Stack;
public class Calculator {        
    public String getCalResult(String infix) {
        return postfixCal(convertToPostfix(infix));
    }

    public String getPostfixCalResult(String infix) {
        String result_format_That_Teacher_Really_Want_But_IDK_Why = "";
        String postfix = convertToPostfix(infix);
        String result = postfixCal(postfix);
        System.out.println("infix : "+infix);
        System.out.println("postfix : "+postfix);


        result_format_That_Teacher_Really_Want_But_IDK_Why = infix + " --> [" + postfix + "] --> " + result;
        return result_format_That_Teacher_Really_Want_But_IDK_Why;
    }

    private String convertToPostfix(String infix) {
        infix=rowInfixConverter(infix);
        Stack<Character> stack =new Stack<>();
        String postfix = "";
        String infixArray[]=infix.split(" ");
        for(String parts : infixArray){
            if(isNumber(parts)) postfix+=parts+" ";
            else {
                char[] c=parts.toCharArray();
                if(c.length>1) System.out.println("Operator Array is more than 1 : lenth -> "+ c.length);
                else{
                    if(c[0]=='(') stack.push(c[0]);
                    else if(c[0]==')'){
                        while(!stack.isEmpty()){
                            if(stack.peek()=='(') {
                                stack.pop();
                                break;
                            }postfix+=stack.pop() + " ";
                        }
                    }else{
                        while(!stack.isEmpty()&&operatorPriority(stack.peek())>=operatorPriority(c[0])){
                            postfix+=stack.pop()+" ";
                        }
                        stack.add(c[0]);
                    }

                }
            }
        }
        while(!stack.isEmpty()) postfix+=stack.pop()+" ";

        postfix=postfix.trim();
        return postfix;
    }


    private String postfixCal(String postfix) {
        String result = "";
        Stack<String> stackNum =new Stack<>();      
        String postfixArray[]=postfix.split(" ");

        for(String str : postfixArray){
            if(isNumber(str)) stackNum.add(str);
            else calculate(stackNum, str);
        }result=stackNum.pop();
        return result;
    }

    private void calculate(Stack<String> stackNum, String str){
        double numberA, numberB;
        String result="";
        if(str.equals("!")){
            int number=Integer.parseInt(stackNum.pop());
            result=Integer.toString(factorial(number));
            stackNum.add(result);
        }
        else{
            numberB=Double.parseDouble(stackNum.pop());
            numberA=Double.parseDouble(stackNum.pop());

            double resultDouble;

            switch(str){
                case "^" : 
                    resultDouble=Math.pow(numberA, numberB);
                    break;
                case "#" :
                    resultDouble=Math.pow(numberA, 1.0/numberB);
                    break;
                case "*" :
                case "X" :
                case "x" :
                    resultDouble=numberA*numberB;
                    break;
                case "/" :
                    resultDouble=numberA/numberB;
                    break;
                case "%" :
                    resultDouble=numberA%numberB;
                    break;
                case "-" :
                    resultDouble=numberA-numberB;
                    break;
                case "+" :
                    resultDouble=numberA+numberB;
                    break;
                default :
                    System.out.println("What is : "+ str);
                    resultDouble=0.0;

            }stackNum.add(Double.toString(resultDouble));
        }

    }

    private int factorial(int number){
        if(number<=1) return number;
        else return factorial(number-1)*number;
    }

    public String rowInfixConverter(String infix) {
        String rowInfix;
        String covertedInfix = "";
        rowInfix = infix.replaceAll(" ", "").trim();
        char[] charInfix = rowInfix.toCharArray();
        for (char c : charInfix) {
            if(Character.isDigit(c)||c=='.') covertedInfix+=c;
            else covertedInfix+=" "+c+" ";
        }
        covertedInfix=covertedInfix.replaceAll("  ", " ").trim();
        return covertedInfix;
    }
    public boolean isNumber(String str){
        boolean answer=false;
        char[] charStr=str.toCharArray();
        for(char c : charStr){
            if(Character.isDigit(c)||c=='.') answer=true;
            else answer=false;
        }
        return answer;
    }
    
    private int operatorPriority(char c){
        if(c=='(') return 0;
        if(c=='+' || c=='-') return 1;
        if(c=='!') return 3;
        else return 2;
    }
}
/







CREATE OR REPLACE FUNCTION CALCULATOR_ONLY_RESULT
(
    str VARCHAR2
) RETURN VARCHAR AS
begin
  LANGUAGE JAVA NAME 
    'Calculator.postfixCal(java.lang.String) return String';
END;

CREATE OR REPLACE FUNCTION CALCULATOR_FORMAT_THAT_TEACHER_WANT
(
    str VARCHAR2
) RETURN VARCHAR AS
begin
  LANGUAGE JAVA NAME 
    'Calculator.getPostfixCalResult(java.lang.String) return String';
END;

SELECT CALCULATOR_ONLY_RESULT('1.0+2+(3+4)*5!') FROM dual;
SELECT CALCULATOR_FORMAT_THAT_TEACHER_WANT('1.0+2+(3+4)*5!') FROM dual;
