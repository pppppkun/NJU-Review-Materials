package cn.seecoder;

/**
 * (\x.\y.x)(\x.x)(\y.y)
 */
public class Lexer{

    private static String DEFAULT = "LCID";
    private String source;
    private int index;
    private Token token = new Token();



    public Lexer(String s){
        source = s;
    }
    public Token nextTokern(String s){
        token.setType(s);
        return token;
    }

    public void translate(){
        boolean flag = false;
        String temp = "";
        for(index = 0;index<source.length();index++){
            if(token.getType().equals(DEFAULT)){
                flag = true;
                temp = token.getMyValue();
            }
            nextTokern(source.substring(index,index+1));
            if(flag&&!token.getType().equals(DEFAULT)){
                System.out.print(temp+token.getMyValue());
                flag = false;
            }
            else if(token.getType().equals(DEFAULT)){}
            else System.out.print(token.getMyValue());
        }
    }

    public static void main(String[] args){
        Lexer lexer = new Lexer("(\\zxc.\\y.y)(\\x.x)(\\y.y)");
        lexer.translate();
    }

}
