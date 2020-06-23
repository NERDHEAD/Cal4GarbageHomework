package JAVA_CODE;

import java.util.Stack;

public class Calculator {
    public static void main(final String[] args) {
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

        final Calculator cal = new Calculator();

        // ex) rowInfixConverter(infix)
        String testResult = cal.rowInfixConverter(infix);
        System.out.println(testResult);
    }

    public String getPostfixCaluResult(final String infix) {
        String result_format_That_Teacher_Really_Want_But_IDK_Why = "";
        String postfix = convertToPostfix(infix);
        String result = postfixCal(postfix);

        result_format_That_Teacher_Really_Want_But_IDK_Why = infix + " --> [" + postfix + "] --> " + result;
        return result_format_That_Teacher_Really_Want_But_IDK_Why;
    }

    //TODO : 해야함
    private String convertToPostfix(final String infix) {
        String postfix = "";
        // 대충 infix를 postfix로 치환 하는 내용



        return postfix;
    }


    //TODO : 해야함
    private String postfixCal(final String postfix) {
        String result = "";
        // 대충 postfix를 받아 연산하는 내용
        return result;
    }

    // 15+25*23 같은걸 좀더 배열화 하기 쉽게 숫자앞뒤에 공백을 추가해
    // 15 + 25 * 23 으로 치환해줌
    public String rowInfixConverter(final String infix) {
        String rowInfix;
        String covertedInfix = "";

        // 처리전 쓰잘데기 없는 공백 제거(오류방지)
        rowInfix = infix.replaceAll(" ", "").trim();
        // char 배열화
        char[] charInfix = rowInfix.toCharArray();
        for (final char c : charInfix) {
            if(Character.isDigit(c)||c=='.') covertedInfix+=c;
            else covertedInfix+=" "+c+" ";
        }
        covertedInfix=covertedInfix.replaceAll("  ", " ").trim();
        return covertedInfix;
    }
}