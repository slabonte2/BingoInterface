import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Bingo{
    //Game modes
    public static String[] modes = {"Normal", "Full Board", "X-Formation", "+-Formation","Asterisk","Lightning Round"};
    //Bingo numbers to be called
    public static int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
                                     16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                                     31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,
                                     46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
                                     61,62,63,64,65,66,67,68,69,70,71,72,73,74,75};
    //Whether a bingo number is marked or not
    public static boolean[] marked = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                                        false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                                        false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                                        false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
                                        false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
    //Interface elements
    public static JFrame frame = new JFrame("Bingo Frame for Bingo Game");
    public static JButton[] numberJButtons = new JButton[75];
    public static JLabel label = new JLabel("Welcome to the Bingo Game. Select a mode and press start to play.");;
    public static JComboBox modeSelect;
    public static JButton start = new JButton("Start");
    public static JButton next = new JButton("Next");
    public static JButton confirmBingo = new JButton("Confirm Bingo");
    public static JPanel inter = new JPanel();
    //Random Number Generator
    public static Random generator = new Random();
    //variables for individual games
    public static int numbersPicked = 0;
    public static int numPickRow1 = 0;
    public static int numPickRow2 = 0;
    public static int numPickRow3 = 0;
    public static int numPickRow4 = 0;
    public static int numPickRow5 = 0;
    public static String mode;
    public static boolean canBingo = false;
    
    public static void checkBingo(){ 
        if(mode == "Normal"){
            if(numbersPicked >= 5 && (numPickRow1 >= 5 || numPickRow2 >= 5 || numPickRow3 >= 5 || numPickRow4 >= 5 || numPickRow5 >= 5 ||
              (numPickRow1 >=1 && numPickRow2 >=1 && numPickRow3 >=1 && numPickRow4 >=1 && numPickRow5 >=1))){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
        else if(mode == "Full Board"){
            if(numbersPicked >= 25 && numPickRow1 >= 5 && numPickRow2 >= 5 && numPickRow3 >= 5 && numPickRow4 >= 5 && numPickRow5 >= 5){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
        else if(mode == "X-Formation"){
            if(numbersPicked >=9  && numPickRow1 >= 2 && numPickRow2 >= 2 && numPickRow3 >= 1 && numPickRow4 >= 2 && numPickRow5 >= 2){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
        else if(mode == "+-Formation"){
            if(numbersPicked >=9  && numPickRow1 >= 1 && numPickRow2 >= 1 && numPickRow3 >= 5 && numPickRow4 >= 1 && numPickRow5 >= 1){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
        else if(mode == "Asterisk"){
            if(numbersPicked >=17  && numPickRow1 >= 3 && numPickRow2 >= 3 && numPickRow3 >= 5 && numPickRow4 >= 3 && numPickRow5 >= 3){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
        else if(mode == "Lightning Round"){
            if(numbersPicked >=5){
                canBingo = true;
                confirmBingo.setVisible(true);
              }
        }
    }
    public static void pickNumber(){
        boolean picked = false;
        int selection = 0;
        while(!picked){
            selection = generator.nextInt(75);
            if(marked[selection] == false){
                marked[selection] = true;
                numberJButtons[selection].setEnabled(false);
                picked = true;
            }
        }
        numbersPicked++;
        label.setText("Number "+((int)selection+1)+" is picked. Hit next to pick the next number. If someone can have bingo, the Confirm Bingo button will be visible. If someone really does have bingo, hit Confirm Bingo to end the game");
        if(selection < 15){
            numPickRow1++;
        }
        else if(selection < 30){
            numPickRow2++;
        }
        else if(selection < 45){
            numPickRow3++;
        }
        else if(selection < 60){
            numPickRow4++;
        }
        else{
            numPickRow5++;
        }

    }
    public static void confirmBingoButtonPressed(){
        start.setVisible(true);
        next.setVisible(false);
        confirmBingo.setVisible(false);
        label.setText("Bingo game complete. Hit Start to play again.");
    }
    public static void nextButtonPressed(){
        pickNumber();
        if(!canBingo){
            checkBingo();
        }
    }
    public static void startButtonPressed(){
        start.setVisible(false);
        next.setVisible(true);
        numbersPicked = 0;
        numPickRow1 = 0;
        numPickRow2 = 0;
        numPickRow3 = 0;
        numPickRow4 = 0;
        numPickRow5 = 0;
        mode = (String)modeSelect.getSelectedItem();
        pickNumber();
        if(!canBingo){
            checkBingo();
        }
        System.out.println(numbersPicked +" "+numPickRow1+" "+numPickRow2+" "+ numPickRow3+" "+numPickRow4+" "+numPickRow5+" ");
    }
    public static void main(String[] args){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel numberButtons = new JPanel();
        numberButtons.setLayout(new GridLayout(5, 15));
        for(int i = 0; i < 75; i++){
            numberJButtons[i] = new JButton(Integer.toString(numbers[i]));
            numberButtons.add(numberJButtons[i]);
        }
        frame.add(BorderLayout.CENTER, numberButtons);
        modeSelect = new JComboBox(modes);
        start = new JButton("Start");
        inter.add(label);
        inter.add(modeSelect);
        inter.add(start);
        inter.add(next);
        inter.add(confirmBingo);
        next.setVisible(false);
        confirmBingo.setVisible(false);
        frame.add(BorderLayout.SOUTH, inter);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                startButtonPressed();
            }
        });
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nextButtonPressed();
            }
        });
        confirmBingo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                confirmBingoButtonPressed();
            }
        });
    }
}