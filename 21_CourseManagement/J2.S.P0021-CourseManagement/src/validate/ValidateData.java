/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

/**
 *
 * @author MSI
 */
public class ValidateData {
    public static boolean validString(String str) {
        return !str.trim().isEmpty();
    }
    
    public static boolean validPosInt(String intNumber) {
        int number=0;
        //if user enter non-integer character
        try {
            number = Integer.parseInt(intNumber);
        }catch(NumberFormatException e) {
            return false;
        }
        return number > 0 && number <= 33;
    }
    
}
