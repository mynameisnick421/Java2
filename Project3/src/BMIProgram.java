import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
This program prompts for patient information and calculates their BMI. This program gives the option to store the BMI
to a file or to discard it after viewing.
 */

public class BMIProgram {
    public static Scanner scan = new Scanner(System.in);
    static List<BMI> bmiList = new ArrayList<BMI>();
    public static void main(String[] args) {
        BMI bmi;
        init();
        String option= menu();//priming read
        while(!option.equals("4")){
            switch (option){
                case "1":
                case "2":
                    bmi = input(Integer.parseInt(option));
                    System.out.println();
                    System.out.println(bmi.toString());
                    storeBMI(bmi);
                    break;
                case "3":
                    dispList();
            }

            System.out.println();
            option = menu();
        }
        System.out.print("You have chosen to exit the program. Press enter to exit.");
        scan.nextLine();
        closeProgram();
    }

    public static void init() {
        List<Object> objectList = new ArrayList<Object>();
        String filename = "BMIList.ser";
        FileInputStream inFile;
        ObjectInputStream inStream;
        File f = new File(filename);
        if (f.exists()) {
            try {
                inFile = new FileInputStream(filename);
                inStream = new ObjectInputStream(inFile);
                //Read objects into array
                while (inFile.available() > 0) {
                    BMI b = ((BMI) inStream.readObject());
                    bmiList.add(b);
                }
                inStream.close();
                inFile.close();
            } catch (Exception e) {
                System.out.println("There was an issue reading the file. " + e.getMessage());
            }
        }
    }

    public static String menu() {
        String input;
        boolean valid = false;
        do {
            System.out.println("BMI Calculator");
            System.out.println("Enter 1 for metric units.");
            System.out.println("Enter 2 for standard units.");
            System.out.println("Enter 3 to display bmi list.");
            System.out.println("Enter 4 to exit program.");
            System.out.print("Enter Option: ");
            input = scan.nextLine().trim().toUpperCase();
            switch (input) {
                case "1":
                case "2":
                case "3":
                case "4":
                    valid = true;
                    break;
                default:
                    System.out.print("Invalid entry. Press enter to try again.");
                    scan.nextLine();
                    System.out.println();
            }
        }
        while(!valid);
        return input;
    }

    public static BMI input(int opt) {
        int height, weight;
        String fName, lName;
        fName = getFirstName();
        lName = getLastName();
        height = getHeight(opt);
        weight = getWeight(opt);
        return new BMI(fName, lName, weight, height, opt);
    }

    public static String getLastName() {
        String lName;
        do{
            System.out.print("Enter patients last name: ");
            lName = scan.nextLine().trim();
            if(lName.isEmpty()){
                System.out.print("Please enter a name. Press enter to try again.");
                scan.nextLine();
            }
        }
        while (lName.isEmpty());
        return lName;
    }

    public static String getFirstName() {
        String fName;
        do{
            System.out.print("Enter patients first name: ");
            fName = scan.nextLine().trim();
            if(fName.isEmpty()){
                System.out.print("Please enter a name. Press enter to try again.");
                scan.nextLine();
            }
        }
        while (fName.isEmpty());
        return fName;
    }

    public static int getHeight(int opt){
        String heightUnit = "inches: ";
        int height=0;
        boolean valid = false;
        if(opt ==1){
            heightUnit="centimeters: ";
        }
        do{
            try{
                System.out.print("Enter height in "+heightUnit);
                height = Integer.parseInt(scan.nextLine().trim());
                if(height>0){
                    valid=true;
                }
                else{
                    System.out.print("Value must be greater than 0. Press enter to try again.");
                    scan.nextLine();
                }
            }
            catch (Exception e) {
                System.out.print("Valid data is whole number only. Press enter to try again.");
                scan.nextLine();
            }
        }
        while(!valid);
        return height;
    }
    public static int getWeight(int opt){
        String weightUnit = "pounds: ";
        int weight=0;
        boolean valid = false;
        if(opt ==1){
            weightUnit="kilograms: ";
        }
        do{
            try{
                System.out.print("Enter weight in "+ weightUnit);
                weight = Integer.parseInt(scan.nextLine().trim());
                if(weight>0){
                    valid=true;
                }
                else{
                    System.out.print("Value must be greater than 0. Press enter to try again.");
                    scan.nextLine();
                }
            }
            catch (Exception e){
                System.out.print("Valid data is whole number only. Press enter to try again.");
                scan.nextLine();
            }
        }
        while(!valid);
        return weight;
    }
    public static void storeBMI(BMI bmi) {
        String input;
        boolean valid = false;
        do{
            System.out.print("Do you want to store this BMI? (Y/N):");
            input = scan.nextLine().trim().toUpperCase();
            switch(input){
                case "Y":
                    bmiList.add(bmi);
                    System.out.print("Bmi stored. Press enter to continue.");
                    scan.nextLine();
                    valid = true;
                    break;
                case "N":
                    valid = true;
                    break;
                default:
                    System.out.print("Invalid entry. Press enter to try again.");
            }
        }
        while (!valid);
    }
    public static void dispList() {
        String type;
        DecimalFormat bmiFormat = new DecimalFormat("#0.0");
        String heading = String.format("\n%-20s%-10s%-10s%-12s%-10s%-13s", "Name", "Height", "Weight","Type", "BMI", "Status");
        System.out.println(heading);
        if(bmiList.isEmpty()){
            System.out.println();
            System.out.println("No BMIs have been stored...");
            System.out.println();
        }
        for(BMI b:bmiList){
            if(b.getOption() ==1){
                type = "Metric";
            }
            else{
                type= "Standard";
            }
            String strBmi = bmiFormat.format(b.calcBMI());
            String status = b.status().substring(0,1).toUpperCase() + b.status().substring(1);
            String record = String.format("%-20s%-10s%-10s%-12s%-10s%-13s", b.getLastName()+", "+b.getFirstName(), b.getHeight(), b.getWeight(), type, strBmi, status);
            System.out.println(record);
        }
        System.out.println();
        System.out.print("Press enter to return to menu.");
        scan.nextLine();
    }
    public static void closeProgram() {
        String filename = "BMIList.ser";
        FileOutputStream outFile;
        ObjectOutputStream outStream;
        try{
            outFile = new FileOutputStream(filename);
            outStream = new ObjectOutputStream(outFile);
            for(BMI b : bmiList){
                outStream.writeObject(b);
            }
            outStream.close();
            outFile.close();
        }
        catch (Exception e){
            System.out.println("There was a problem writing to the file."+ e.getMessage());
            scan.nextLine();
        }
    }
}

