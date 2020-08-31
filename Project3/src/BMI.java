import java.io.Serializable;
import java.text.DecimalFormat;
/*
This class is used to create a BMI object and calculate BMI using weight and height.
Works with metric and standard units.
 */
public class BMI implements Serializable {
    private String firstName;
    private String lastName;
    private int weight;
    private int height;
    private int option;//1 for metric 2 for standard

    public BMI(){ //default constructor
        firstName = "John";
        lastName = "Doe";
        weight = 70;
        height = 170;
        option = 1;
    }

    /**
     * BMI constructor
     * @param fn First Name
     * @param ln Last Name
     * @param w Weight
     * @param h Height
     * @param o Option
     */
    public BMI(String fn, String ln, int w, int h, int o){
        setFirstName(fn);
        setLastName(ln);
        setWeight(w);
        setHeight(h);
        setOption(o);
    }
    public void setFirstName(String fn){
        if(fn.isEmpty()){
            firstName = "John";
        }
        else{
            firstName = fn;
        }
    }
    public void setLastName(String ln){
        if(ln.isEmpty()){
            lastName = "Doe";
        }
        else{
            lastName = ln;
        }
    }
    public void setWeight(int w) {
        if (w>0){
            weight = w;
        }
        else{
            weight = 70;
        }
    }
    public void setHeight(int h) {
        if(h>0){
            height = h;
        }
        else{
            height = 170;
        }
    }

    /**
     * 1 for metric 2 for standard units, defaults to 1 with invalid input.
     * @param o Option input
     */
    public void setOption(int o) {
        if(o==1 || o==2){
            option = o;
        }
        else{
            option =1;
        }
    }
    public int getWeight(){return weight;}
    public int getHeight(){return height;}
    public int getOption(){return option;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}

    /**
     * Instance method to calculate BMI
     * @return Non-formated BMI
     */
    public double calcBMI(){
        double dblBMI;
        double dblHeight = height;
        if(option==1){
            dblBMI = weight /((dblHeight/100)*(dblHeight/100));
        }
        else{
            dblBMI = 703*(weight/((dblHeight*dblHeight)));
        }
        return dblBMI;
    }

    /**
     * Formats a string including calculated BMI, checks bmi to return appropriate output
     * @return formatted string
     */
    @Override
    public String toString(){
        DecimalFormat bmiFormat = new DecimalFormat("#0.0");
        double dblBmi = calcBMI();
        String strBmi = bmiFormat.format(dblBmi);
        String retStr ="This BMI of "+strBmi+" is considered ";
        retStr+=status()+".";
        return retStr;
    }
    public String status(){
        double dblBmi = calcBMI();
        String status;
        if(dblBmi<18.5){
            status = "underweight";
        }
        else if(dblBmi >= 18.5 && dblBmi <= 24.9){
            status = "normal weight";
        }
        else{
            status = "overweight";
        }

        return status;
    }
}
