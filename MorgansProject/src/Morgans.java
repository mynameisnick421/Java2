import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/*
This program uses the console to prompt for input. It stores employee information and will format the input into a
report displayed to the console. This program uses bonus information read from a file to calculate employee bonus
based on status and pay amount.
 */
public class Morgans {
    static Scanner scan = new Scanner(System.in);
    static double[] bonusArr = new double[10];//array to store bonus values
    static String[][] employeeArr= new String[10][3];//array to store employee info
    static double[] payCheckArr = new double[10];//array to store pay check values
    static double[][] bonusPercentArr = new double[4][3];
    static int empIndex=0;//stores number of employees

    public static void main(String[] args) {
        init();
        String input= displayMenu();//priming read
        while(!input.equals("4")) {
            optionSelector(input);
            input = displayMenu();
        }
        System.out.print("You have chosen to leave the program. Press enter to exit.");
        scan.nextLine();
    }

    public static void init() {
        fillArrays();
    }

    public static void fillArrays() {
        //initialize bonus array
        Arrays.fill(bonusArr, 0);
        //initialize percentage array with data from file
        String record;
        String[] fields;
        Scanner percentScanner;
        boolean eof = false;
        File file = new File("percentage_break.dat");
        try{
            percentScanner= new Scanner(file);
            int row = 0;
            if(percentScanner.hasNext()){
                while(!eof){
                    record = percentScanner.nextLine();
                    fields = record.split(",");
                    bonusPercentArr[row][0]= Double.parseDouble(fields[0]);
                    bonusPercentArr[row][1]= Double.parseDouble(fields[1]);
                    bonusPercentArr[row][2]= Double.parseDouble(fields[2]);
                    row++;
                    if(!percentScanner.hasNext()){
                        eof=true;
                    }
                }
            }
            else{
                System.out.println("File empty. Program closing.");
                System.exit(1);
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found. Program closing.");
            System.exit(1);
        }
    }

    /**
     * Prints menu to console
     * @return validated input
     */
    public static String displayMenu(){
        String input;
        do {
            System.out.println("Morgan's Department Store Terminal\n");
            System.out.println("Option 1: Enter employee data.");
            System.out.println("Option 2: Display all employee data.");
            System.out.println("Option 3: Select data of single employee.");
            System.out.println("Option 4: Exit.");
            System.out.print("Enter option to continue: ");
            input = scan.nextLine();
            System.out.println();
        }
        while(!validInput(input));
        return input;
    }

    /**
     * retuns boolean indicating a valid menu option selection
     * @param input user input string
     * @return true if valid input
     */
    public static boolean validInput(String input) {
        boolean valid = true;
        if (!input.matches("[1234]")) {//checks against valid input
            System.out.print("Invalid Input. Press enter to try again.");
            scan.nextLine();
            System.out.println();
            valid = false;
        }
        return valid;
    }

    /**
     * calls proper option module based on input
     * @param input user input
     */
    public static void optionSelector(String input){
        switch(input){
            case "1":
                optionOne();
                break;
            case "2":
                optionTwo();
                break;
            case "3":
                optionThree();
                break;
        }
    }

    public static void optionOne() {
        boolean addEmp = true;
        String prompt1= "Enter employee first name: ";
        String prompt2= "Enter employee last name: ";
        while(empIndex<10 && addEmp){
            //fill arrays with validated input from input method
            employeeArr[empIndex][0] = nameInput(prompt1).trim();
            employeeArr[empIndex][1] = nameInput(prompt2).trim();
            payCheckArr[empIndex]= Double.parseDouble(checkInput());
            employeeArr[empIndex][2] = statusInput();
            System.out.print("Enter another employee? (y/n): ");
            String input = scan.nextLine().toUpperCase();
            switch(input){
                case "Y":
                    break;
                case "N":
                    addEmp = false;
                    break;
                default:
                    System.out.println("Invalid entry. Press enter to return to menu.");
                    scan.nextLine();
                    addEmp = false;
            }
            empIndex++;
        }
        if (empIndex == 10){
            System.out.println("The limit of employees has been met. Press enter to continue");
            scan.nextLine();
        }
    }

    public static void optionTwo() {
        if(empIndex>0) {
            System.out.println("\n"+printHeading());
            for (int x = 0; x < empIndex; x++) {
                System.out.println(printData(x, true));
            }
            System.out.println(grandTotals());
            System.out.println("Press enter to return to menu");
        }
        else {
            System.out.println("No data entered. Press enter to return to menu");
        }
        scan.nextLine();
    }

    /**
     * Totals all employee bonuses
     * @return Formatted string to use as totals line of report
     */
    private static String grandTotals() {
        String pattern = "$###,###.00";
        DecimalFormat moneyFormat = new DecimalFormat(pattern);
        double total = 0;
        for(int i=0;i<empIndex;i++){
            total += bonusArr[i];
        }
        return String.format("%53s%11s","Total Bonus:",moneyFormat.format(total));
    }

    public static void optionThree() {
        String prompt1= "Enter employee first name: ";
        String prompt2= "Enter employee last name: ";
        int x; //hold index of employee if found
        if(empIndex>0) {
            String fName=nameInput(prompt1).toUpperCase();
            String lName=nameInput(prompt2).toUpperCase();
            for (x = 0; x < empIndex; x++) {
                if (fName.trim().equals(employeeArr[x][0].toUpperCase()) && lName.trim().equals(employeeArr[x][1].toUpperCase())) {
                    System.out.println(printData(x, false));//passes employee index to printData method
                    break;
                }
            }
            if(x==empIndex){
                System.out.print("Employee not found. ");
            }
        }
        else{
            System.out.print("No data entered. ");
        }
        System.out.println("Press enter to return to menu");
        scan.nextLine();
    }

    /**
     * validates name input
     * @param prompt prompt for input from user
     * @return validated input string
     */
    public static String nameInput(String prompt){
        String input;
        boolean invalid= true;
        do {
            System.out.print(prompt);
            input = scan.nextLine();
            if (input.isBlank()) {
                System.out.print("Please enter data. Press enter to try again.");
                scan.nextLine();
            }
            else{
                if (input.length() > 10) {//limits size of name for report formatting reasons
                    System.out.print("Please limit name input to 10 characters or less. Press enter to try again.");
                    scan.nextLine();
                }
                else{
                    invalid = false;
                }
            }
        }
        while(invalid);
        return input;
    }

    /**
     * prompts for user input
     * @return validated check amount input
     */
    public static String checkInput(){
        String input;
        boolean invalid = true;
        do {
            System.out.print("Enter paycheck amount: ");
            input = scan.nextLine();
            if (input.isBlank()) {
                System.out.print("Please enter data. Press enter to try again.");
                scan.nextLine();
            } else {
                try {
                    if (Double.parseDouble(input) < .01 || (Double.parseDouble(input) > 9999.99)) {
                        System.out.print("Check amount must be .01-9999.99. Press enter to try again.");
                        scan.nextLine();
                    }
                    else{
                        invalid = false;
                    }
                } catch (Exception e) {
                    System.out.print("Invalid number entered. Press enter to try again.");
                    scan.nextLine();
                }
            }
        }
        while (invalid);
        return input;
    }

    public static String statusInput(){
        String input;
        boolean invalid = true;
        do{
            System.out.print("Enter employee status (FT/PT): ");
            input = scan.nextLine();
            input=input.toUpperCase();
            if (!input.matches("FT|PT")) {//checks against valid input
                System.out.print("Invalid Input. Enter (FT or PT). Press enter to try again.");
                scan.nextLine();
            }
            else{
                invalid = false;
            }
        }while(invalid);
        return input;
    }

    public static String printHeading(){
        return String.format("%-23s%-9s%16s%16s", "Employee Name","Status","Paid Amount","Bonus Amount");
    }

    /**
     * uses employee index to access employee data in arrays
     * @param employeeIndex employee index in array
     * @param column supply true for column display, false for vertical display
     * @return a string of formatted emoloyee data
     */
    public static String printData(int employeeIndex, boolean column){
        String status;
        String pattern = "$###,###.00";
        DecimalFormat moneyFormat = new DecimalFormat(pattern);
        String name= employeeArr[employeeIndex][1]+", "+employeeArr[employeeIndex][0];
        int statusIndex;
        if(employeeArr[employeeIndex][2].equals("FT")){
            status = "Full Time";
            statusIndex = 2;
        }
        else {
            status = "Part Time";
            statusIndex = 1;
        }
        for(int x=0;x<bonusPercentArr.length;x++){
            if(payCheckArr[employeeIndex]<=bonusPercentArr[x][0]){
                bonusArr[employeeIndex] = payCheckArr[employeeIndex] * bonusPercentArr[x][statusIndex];
                break;
            }
        }
        if (column){
            return String.format("%-23s%-9s%16s%16s", name,status,moneyFormat.format(payCheckArr[employeeIndex]),moneyFormat.format(bonusArr[employeeIndex]));
        }
        else {
            return "\nName:\t\t"+ name +
                    "\nStatus:\t\t"+ status +
                    "\nPay Amount:\t"+ moneyFormat.format(payCheckArr[employeeIndex]) +
                    "\nBonus Pay:\t"+ moneyFormat.format(bonusArr[employeeIndex]) ;
        }
    }
}