
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vietddse62677
 */
public class Courses {
    public static final int MAX_CREDIT=33;
    public static final int MIN_CREDIT=0;
    private String code;
    private String name;
    private int credit;
    public Courses(String code,String name,int credit)
    {
        this.code=code.toUpperCase();      
        this.name=convertToUpper(name);
        this.credit=credit;
    }
    public String getCode()
    {
        return code;
    }
    public String getName()
    {
        return name;
    }
    public int getCredit()
    {
        return credit;
    }
    public String toString()
    {
        return code+" | "+name+" | "+credit;
    }
    private String sconvert(String str) {
        String tmp = "";
        tmp += String.valueOf(str.charAt(0)).toUpperCase();
        tmp += str.substring(1); //tmp+=str.substring(1, str.length());
        return tmp;
    }

    private String convertToUpper(String name) {
        String name1 = "";
        StringTokenizer tok = new StringTokenizer(name, " ");
        while (tok.hasMoreTokens()) {
            name1 += (sconvert(tok.nextToken()) + " ");
        }
        return name1;
    }
}
