import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayFromFile {
    static String[][] rankings= new String[5][2];
    static int[][] points= new int[5][2];
    public static void main(String[] args) {
        boolean isFound= false;
        String iName;
        int r;
        init();
        iName = JOptionPane.showInputDialog("Enter Player Name: ");
        for(r=0;r<rankings.length;r++){
            if(iName.trim().toUpperCase().equals((rankings[r][0].toUpperCase()))){
                isFound = true;
                break;
            }
        }
        if(isFound){
            JOptionPane.showMessageDialog(null,"Player "+ rankings[r][0]+ " has reached "+
                    rankings[r][1]+" status.\n"+ "Quests: "+points[r][0]+"\n AG Points: "+points[r][1]);
        }
        else{
            JOptionPane.showMessageDialog(null,"Player "+ iName+" not found.");
        }
    }

    public static void init() {

        loadArrays();
    }

    public static void loadArrays() {
        String record;
        String[] fields;
        Scanner playerScanner;
        boolean eof = false;
        File file = new File("array.dat");
        try{
            playerScanner= new Scanner(file);
            int row = 0;
            if(playerScanner.hasNext()){
                while(!eof){
                    record = playerScanner.nextLine();
                    fields = record.split(",");
                    rankings[row][0]=fields[0];
                    rankings[row][1]=fields[1];
                    points[row][0]= Integer.parseInt(fields[2]);
                    points[row][1]= Integer.parseInt(fields[3]);
                    row++;
                    if(!playerScanner.hasNext()){
                        eof=true;
                    }
                }
                JOptionPane.showMessageDialog(null,"Player data loaded ok.");
            }
            else{
                JOptionPane.showMessageDialog(null,"File empty, shut'er down");
                System.exit(1);
            }
        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Player stat file not found, shut'er down");
            System.exit(1);
        }

    }
}
