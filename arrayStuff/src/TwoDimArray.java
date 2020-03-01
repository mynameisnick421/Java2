import javax.swing.*;

public class TwoDimArray {
    static String[][] rankings={
            {"ivan","King"},
            {"Gantic","King"},
            {"Retin8","King"},
            {"goticat135","Queen"},
            {"BigVA", "Prince"},
            {"JustSomeGuy2","Regent"},
            {"James_Blake","Viceroy"}};
    static int[][] points={
            {1115, 39721},
            {1057, 38865},
            {1115, 36935},
            {1078, 31955},
            {1015, 30315},
            {1015, 28185},
            {940, 26145}};
    public static void main(String[] args) {
        boolean isFound= false;
        String iName;
        int r;

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
        }else{
            JOptionPane.showMessageDialog(null,"Player "+ iName+" not found.");
        }
    }
}
