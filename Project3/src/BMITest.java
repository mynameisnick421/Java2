import java.text.DecimalFormat;
/*
This class is used to test and validate information from BMI.java class
 */
public class BMITest {
    public static void main(String[] args) {
        BMI bmi = new BMI();
        DecimalFormat bmiFormat = new DecimalFormat("#0.0");

        System.out.println("Default Values");
        System.out.println("First Name: "+bmi.getFirstName());
        System.out.println("Last Name: "+bmi.getLastName());
        System.out.println("Height: "+bmi.getHeight());
        System.out.println("Weight: "+bmi.getWeight());
        System.out.println("Option: "+bmi.getOption());
        System.out.println("BMI from toString method: "+bmi.toString());
        System.out.println("_______________________________________________________________________");
        System.out.println("Using setters to set weight to 200, height to 66, option to 2.");
        bmi.setWeight(200);
        bmi.setHeight(66);
        bmi.setOption(2);
        System.out.println("First Name: "+bmi.getFirstName());
        System.out.println("Last Name: "+bmi.getLastName());
        System.out.println("Height: "+bmi.getHeight());
        System.out.println("Weight: "+bmi.getWeight());
        System.out.println("Option: "+bmi.getOption());
        System.out.println("BMI from toString method: "+bmi.toString());
        System.out.println("_______________________________________________________________________");
        System.out.println("Initializing new bmi with weight to 60, height to 190, option to 1.");
        bmi = new BMI("Bob","Lawbla",60,190,1);
        System.out.println("First Name: "+bmi.getFirstName());
        System.out.println("Last Name: "+bmi.getLastName());
        System.out.println("Height: "+bmi.getHeight());
        System.out.println("Weight: "+bmi.getWeight());
        System.out.println("Option: "+bmi.getOption());
        System.out.println("BMI from toString method: "+bmi.toString());
        System.out.println("_______________________________________________________________________");
        System.out.println("Testing setter validation by setting all params to 0. Should display default values.");
        bmi = new BMI("","",0,0,0);
        System.out.println("First Name: "+bmi.getFirstName());
        System.out.println("Last Name: "+bmi.getLastName());
        System.out.println("Height: "+bmi.getHeight());
        System.out.println("Weight: "+bmi.getWeight());
        System.out.println("Option: "+bmi.getOption());
        System.out.println("BMI from toString method: "+bmi.toString());
        System.out.println("_______________________________________________________________________");
        System.out.println("Testing setter validation by calling setters with invalid data.");
        bmi.setWeight(0);
        bmi.setHeight(0);
        bmi.setOption(0);
        bmi.setFirstName("Frank");
        bmi.setLastName("Smith");
        System.out.println("First Name: "+bmi.getFirstName());
        System.out.println("Last Name: "+bmi.getLastName());
        System.out.println("Height: "+bmi.getHeight());
        System.out.println("Weight: "+bmi.getWeight());
        System.out.println("Option: "+bmi.getOption());
        System.out.println("BMI from toString method: "+bmi.toString());

    }
}