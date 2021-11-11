import javax.swing.JButton;
import javax.swing.JTextField;
/**
 *
 * @author Admin
 */
public class Calculate {
    private double firstNumber;
    private double secondNumber;
    private boolean checkTurn=false;
    private String operator;
    private double memory;
    private JTextField text;
    private boolean isError=false;
     boolean reset=false;

    public Calculate(JTextField text) {
        this.text = text;
        operator="";
        memory= 0;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        checkTurn=true;
    }
    
    public void pressNumber(JButton btn){
        if(!isError) {
            String value = btn.getText();
            //when firstNumber đã đc nhập
             if(checkTurn){
                 text.setText("0");
                 checkTurn=false;
             }
             String s= text.getText();
             if(s.startsWith("0") && !s.startsWith("0.")) s=s.substring(1);
             text.setText (s+value);
             assignValue();
             reset=false;
        }
    }
    public double  getValue(){
        return Double.parseDouble(text.getText());
    }
    
    public void pressDot(){
        if(!isError) {
        if(checkTurn){
            text.setText("0");
            checkTurn=false;
        }
       if(!text.getText().contains(".")) text.setText(text.getText()+".");
    }
    }  
    public void pressClear(){
        
        firstNumber= 0;
        secondNumber= 0;
        operator="";
        isError=false;
        text.setText("0");
    }
    
    public void assignValue(){
        if(operator.equals("")) {
           firstNumber=getValue();
           System.out.println("firstnumber:"+firstNumber);
        }
        else secondNumber=getValue();
    }
    
    public void calculate(){
      
        if(!checkTurn){
           switch(operator){
                case "+":
                    firstNumber = firstNumber+(secondNumber);
                    break;
                case "-":
                    firstNumber = firstNumber-(secondNumber);
                    break;
                case "*":
                    firstNumber = firstNumber *(secondNumber);
                    break;
                case "/":
                    if(secondNumber==0){
                        isError=true;
                        text.setText("ERROR");
                        break;
                    }
                    firstNumber=firstNumber/(secondNumber);
                    break;
        }
            if(!isError){
                String value=validateResult( firstNumber+"");
                text.setText(value);
                checkTurn=true; 
            }
        }
    }
   public String validateResult(String value){
       if(value.startsWith("0") && !value.startsWith("0.")) value=value.substring(1, value.length());
       if(value.endsWith(".0")) value=value.substring(0, value.length()-2);
       while(value.contains(".") && value.endsWith("0")) 
           value=value.substring(0,value.length()-1);
       return value;
      
   }
    
    public void printResult(){

        calculate();
        checkTurn =true;
        operator="";
        reset=true;
    }
   
    public void pressNegate(){
       
        try {
            double value=getValue();
            if(value!=0)  value=-(value);
            text.setText(validateResult(value+""));
            assignValue();
            checkTurn=true;
        } catch (Exception e) {
            text.setText("ERROR");
            
        }
      
    }
//    
    public void pressPercent(){
        try {
            text.setText(validateResult(getValue()/100+""));
            assignValue();
            checkTurn=true;
        } catch (Exception e) {
            text.setText("ERROR");
        }
        reset=true;
    }
    
    public void pressSqrt(){
        
        double value=Math.sqrt(getValue());
        String s=""+value;
        if(s.equals("NaN"))  {
            isError=true;
            text.setText("ERROR");
        }
        else {
            text.setText(validateResult(s));
            assignValue();
            checkTurn=true;
        }
        reset=true;
    }
//    
    public void pressInverse(){
        try {
            double valueInverse = (double) 1 / getValue();
            text.setText(valueInverse+"");
            assignValue();
            checkTurn=true;
        } catch (Exception e) {
             text.setText("ERROR");
        }
       reset=true;
    }

    public void pressMC(){
        memory= 0;
        reset=true;
    }
//    
    public void pressMS(){
        memory = getValue();
        checkTurn = true;
        reset = true;
    }
//    
    public void pressMAdd(){
        try {
            memory=memory +(getValue());
            checkTurn=true;
        } catch (Exception e) {
            text.setText("ERROR");
        }
        reset=true;
    }
//    
    public void pressMSub(){
        try {
            memory=memory-(getValue());
            checkTurn=true;
        } catch (Exception e) {
             text.setText("ERROR");
        }
          reset=true;
    }
//    
    public void pressMR()
    { 
        text.setText(validateResult(memory+""));
        assignValue();
        checkTurn=true;
        reset=true;
    }    
    

//    
    public void pressBack(){
        if(!isError){
            String value = text.getText();
            text.setText(value.substring(0,value.length()-1));
            assignValue();
        }
    }
     
    
}
