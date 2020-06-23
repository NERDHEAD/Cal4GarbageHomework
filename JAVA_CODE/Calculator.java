package JAVA_CODE;

import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        String infix = "1.0+2+(3+4)*5";
        // infix->postfix ->calculate!
        // 중위계산식을 후위계산식으로 치환하기
        // String infix="대충 중위 계산식";
        // String postfix=convertToPostfix(infix);
        // 후위계산식을 연산하기
        // String result=postfixCal(postfix);

        // 결과물
        // String s=getPostfixCalResult(infix);
        // "infix --> [postfix] --> result"

        

        // ex) rowInfixConverter(infix)
        String testResult = cal.rowInfixConverter(infix);
        System.out.println(testResult);

        // ex) isNumber(number)
        //String number="12.34";
        //if(cal.isNumber(number)) System.out.println("숫자 ㅇㅇ");
        //else System.out.println("숫자 ㄴㄴ");

        // ex) convertToPostfix(infix)
        testResult=cal.convertToPostfix(infix);
        System.out.println(testResult);

        // ex) postfixCal(postfix)
        System.out.println(cal.getCalResult(infix));
        System.out.println(cal.getCalResult("4!"));
    }

    public String getCalResult(String infix) {
        return postfixCal(convertToPostfix(infix));
    }

    public String getPostfixCaluResult(String infix) {
        String result_format_That_Teacher_Really_Want_But_IDK_Why = "";
        String postfix = convertToPostfix(infix);
        String result = postfixCal(postfix);

        result_format_That_Teacher_Really_Want_But_IDK_Why = infix + " --> [" + postfix + "] --> " + result;
        return result_format_That_Teacher_Really_Want_But_IDK_Why;
    }


    //stack 변환 과정 테스트용
    public String getStackC(Stack<Character> stack){
        String stackStr="";
        Stack<Character> copyStack= (Stack<Character>) stack.clone();
        while(!copyStack.isEmpty()){
            stackStr+=copyStack.pop()+" ";
        }
        return stackStr;
    }
    public String getStackS(Stack<String> stack){
        String stackStr="";
        Stack<String> copyStack= (Stack<String>) stack.clone();
        while(!copyStack.isEmpty()){
            stackStr+=copyStack.pop()+" ";
        }
        return stackStr;
    }

    public String convertToPostfix(String infix) {
        System.out.println("convertToPostfix on Run!");
        infix=rowInfixConverter(infix);
        Stack<Character> stack =new Stack<>();
        String postfix = "";
        // 대충 infix를 postfix로 치환 하는 내용
        //int i=0;
        String infixArray[]=infix.split(" ");
        for(String parts : infixArray){
            //i++;
            //String myStack=getStack(stack);
            //System.out.println(i+") postfix : " +postfix+", stack : "+myStack);
            if(isNumber(parts)) postfix+=parts+" ";
            else {
                char[] c=parts.toCharArray();
                if(c.length>1) System.out.println("Operator Array is more than 1 : lenth -> "+ c.length);
                else{
                    //여는 괄호는 스택
                    if(c[0]=='(') stack.push(c[0]);
                    //닫는 괄호는 스택 배출(?)
                    else if(c[0]==')'){
                        while(!stack.isEmpty()){
                            //여는 괄호는 방사
                            if(stack.peek()=='(') {
                                stack.pop();
                                break;
                            }postfix+=stack.pop() + " ";
                        }
                    }else{
                        //while(!stack.isEmpty&&우선순위 비교?){스택의 값이 크거나 같으면 postfix+=stack.pop()}
                        while(!stack.isEmpty()&&operatorPriority(stack.peek())>=operatorPriority(c[0])){
                            postfix+=stack.pop()+" ";
                        }
                        //그리고 아무튼 stack에 add
                        stack.add(c[0]);
                    }

                }
            }
        }                    //남는 스택을 처리
        while(!stack.isEmpty()) postfix+=stack.pop()+" ";

        postfix=postfix.trim();
        return postfix;
    }


    private String postfixCal(String postfix) {
        System.out.println("postfixCal on Run!");
        String result = "";
        Stack<String> stackNum =new Stack<>();      
        String postfixArray[]=postfix.split(" ");

        for(String str : postfixArray){
            if(isNumber(str)) stackNum.add(str);
            else calculate(stackNum, str);
        }result=stackNum.pop();
        // 대충 postfix를 받아 연산하는 내용
        return result;
    }

    private void calculate(Stack<String> stackNum, String str){
        //테스트
        String myStack=getStackS(stackNum);
        System.out.println("calculate Stack : "+myStack);        

        double numberA, numberB;
        String result="";
        //jdk7 이후 부터 switch문에 String 형도 가능하게 된 것을 확인함

        //int형으로 고정인 팩토리얼은 따로 계산 할것
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


    //팩토리얼 연산
    private int factorial(int number){
        if(number<=1) return number;
        else return factorial(number-1)*number;
    }


















    // 15+25*23 같은걸 좀더 배열화 하기 쉽게 숫자앞뒤에 공백을 추가해
    // 15 + 25 * 23 으로 치환해줌
    public String rowInfixConverter(String infix) {
        
        System.out.println("rowInfixConverter on Run!");
        String rowInfix;
        String covertedInfix = "";
        // 처리전 쓰잘데기 없는 공백 제거(오류방지)
        rowInfix = infix.replaceAll(" ", "").trim();
        // char 배열화
        char[] charInfix = rowInfix.toCharArray();
        for (char c : charInfix) {
            if(Character.isDigit(c)||c=='.') covertedInfix+=c;
            else covertedInfix+=" "+c+" ";
        }
        covertedInfix=covertedInfix.replaceAll("  ", " ").trim();
        return covertedInfix;
    }


    //숫자인지 아닌지 판별함
    public boolean isNumber(String str){
        boolean answer=false;
    
        char[] charStr=str.toCharArray();
        for(char c : charStr){
            //System.out.println(c);
            if(Character.isDigit(c)||c=='.') answer=true;
            else answer=false;
        }
        return answer;
    }
    
    //연산자 우선순위 설정
    private int operatorPriority(char c){
        if(c=='(') return 0;
        if(c=='+' || c=='-') return 1;
        else return 2;
    }
}