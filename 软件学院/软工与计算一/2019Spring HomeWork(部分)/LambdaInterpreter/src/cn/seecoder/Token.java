package cn.seecoder;

import java.util.HashMap;
import java.util.regex.*;

/**
 * LPAREN: '('
 * RPAREN: ')'
 * LAMBDA: '\' // 为了方便使用 “\”
 * DOT: '.'
 * LCID: /[a-z][a-zA-Z]* /
 *
 * 这个类将解析Lexer传进来的字符串/字符，并且解析成一个Token对象返回回去，这个Token对象应该包含type和value
 *
 * type 是上面五个名字，value 是后面的符号
 * @author P君
 */


public class Token {
    private static final HashMap<String,String> types = new HashMap<>();
    private String type = "";
    private String myValue = "";
    private static final String patternString = "[a-z][a-zA-Z]*";
    private static final String patternStringTwo = "[a-zA-Z]*";

    //通过键值对查找

    public Token(){
        types.put(".","DOT");
        types.put("(","LPAREN");
        types.put(")","RPAREN");
        types.put("\\","LAMBDA");
    }

    //设置Type

    public void setType(String s){
        for(String p : types.keySet()){
            if(p.equals(s)){
                myValue = s;
                type = types.get(s);
                return;
            }
        }
        setLCID(s);
    }

    //判断LCID很长的情况

    private void setLCID(String s){

        if(type.equals("LCID")){
            Pattern pattern = Pattern.compile(patternStringTwo);
            Matcher matcher = pattern.matcher(myValue+s);
            if(matcher.matches()){
                myValue +=s;
            }
            else{
                System.out.println("ERROR");
            }
        }
        else{
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(s);
            if(matcher.matches()){
                type = "LCID";
                myValue = s;
            }
            else{
                System.out.println("ERROR");
            }
        }

    }


    public String getType(){
        return type;
    }

    public String getMyValue(){
        return myValue;
    }
}
