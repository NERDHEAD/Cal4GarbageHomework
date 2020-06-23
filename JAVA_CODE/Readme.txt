Class Calculator{
    main 
        -> unit test

    public String getPostfixCaluResult(final String infix)
        -> return String type : "infix --> [postfix] --> result"

    private String convertToPostfix(final String infix)
        -> convert : infix -> postfix

    private String postfixCal(final String postfix)
        -> calculate : postfix -> result

    public String rowInfixConverter(final String infix)
        ->convert : rowInfix -> convertedInfix
        ->make space number, Operator
        ->ex :
            rowInfix = "1.0+2+(3+4)*5"
            convertedInfix = "1.0 + 2 + ( 3 + 4 ) * 5"
}