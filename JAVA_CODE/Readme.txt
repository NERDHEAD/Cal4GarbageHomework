Class Calculator{
    main 
        -> unit test
    public String getCalResult(String infix)
        -> return String type : "postfix"

    public String getPostfixCaluResult(final String infix)
        -> return String type : "infix --> [postfix] --> result"

    private String convertToPostfix(final String infix)
        -> convert : infix -> postfix

    private String postfixCal(final String postfix)
        -> calculate : postfix -> result
            -> calculate(Stack<String> stackNum, String str)
    
    private void calculate(Stack<String> stackNum, String str)
        -> calculate : pop stackNum, add result

    private int factorial(int number)
        -> calculator for factorial!

    public String rowInfixConverter(final String infix)
        ->convert : rowInfix -> convertedInfix
        ->make space number, Operator
        ->ex :
            rowInfix = "1.0+2+(3+4)*5"
            convertedInfix = "1.0 + 2 + ( 3 + 4 ) * 5"

    public boolean isNumber(String str)
        -> return boolean : str -> char -> (if number or "." return true)

    private int operatorPriority(char c)
        -> return priority(int) : factorial Must Be First!!
}
