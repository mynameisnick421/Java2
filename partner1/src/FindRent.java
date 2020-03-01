import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FindRent
{
    public static void main(String[] args)
    {
        int[][] rents= loadArray();
        int maxFloors = rents.length -1;
        int maxBedrooms = rents[0].length -1;
        int floor = floorInput(maxFloors);
        int bedrooms = bedroomInput(maxBedrooms);

        String entry;
        entry = JOptionPane.showInputDialog(null,
                "Enter a floor number ");
        floor = Integer.parseInt(entry);
        entry = JOptionPane.showInputDialog(null,
                "Enter number of bedrooms ");
        bedrooms = Integer.parseInt(entry);
        JOptionPane.showMessageDialog(null,
                "The rent for a " + bedrooms +
                        " bedroom apartment on floor " + floor +
                        " is $" + rents[floor][bedrooms]);
    }

    private static int bedroomInput(int maxBedrooms) {
        String entry;
        entry = JOptionPane.showInputDialog(null,
                "Enter number of bedrooms ");
        return Integer.parseInt(entry);
    }

    public static int floorInput(int max) {
        String entry;
        entry = JOptionPane.showInputDialog(null,
                "Enter a floor number ");


        return Integer.parseInt(entry);
    }

    public static int[][] loadArray() {
        int[][] rents= new int[4][3];
        String record;
        String[] fields;
        Scanner rentScanner;
        boolean eof = false;
        File file = new File("rent.dat");
        try{
            rentScanner= new Scanner(file);
            int row = 0;
            if(rentScanner.hasNext()){
                while(!eof){
                    record = rentScanner.nextLine();
                    fields = record.split(",");
                    rents[row][0]=Integer.parseInt(fields[0]);
                    rents[row][1]=Integer.parseInt(fields[1]);
                    rents[row][2]=Integer.parseInt(fields[2]);
                    row++;
                    if(!rentScanner.hasNext()){
                        eof=true;
                    }
                }
                JOptionPane.showMessageDialog(null,"Rent data loaded ok.");
            }
            else{
                JOptionPane.showMessageDialog(null,"File empty, shut'er down");
                System.exit(1);
            }
        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Rent file not found, shut'er down");
            System.exit(1);
        }
        return rents;
    }

}
   