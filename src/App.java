// James Zheng
// Minesweeper project inside VSCode

//TODO add restart functionality

// imports
import java.awt.BorderLayout; // why cant all the j stuff be in a single importttt
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// main class that runs stuff
public class App {
    // class variables
    int rows = 10;
    int columns = 20;
    int mines = 10;
    int tilecount = rows*columns-1;
    public boolean gameState = false; // gameover or not
    JFrame frame = new JFrame("Bozo zone");
    
    // list of the buttons in a 2d shape
    MSButton[][] buttongrid = new MSButton[rows][columns]; // idk why this exists
    int[][] intgrid = new int[rows][columns];


    // checks if smths in bounds
    public boolean inBounds(int current_row, int current_col) {
        // this function sees if a certain block is in bounds
        boolean rowup = rows - current_row <= rows; 
        boolean rowdown = rows - current_row > 0;

        boolean colleft = columns - current_col <= columns; 
        boolean colright = columns - current_col > 0;
        
        // returns if the tile is out of bounds
        // I MEAN it works dont question it 
        return (rowup && rowdown) && (colleft && colright);
    }

    // actually sets grid to have numbers
    public void armMineField(int[][] grid) {
        // this sets a 2d int grid to give it mines and the numbers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 0;
            }
        }
        ArrayList<Integer> minepos = new ArrayList<>(); // arraylist of the positions of the mins
        Random rannum = new Random(); // random stream of vars
        int minesplaced = 0;

        // this loop gives the arraylist mines to place wherever
        while (mines > minesplaced) {
            int mineloc = rannum.nextInt(tilecount); // gets the next sequence from the rng
            if (!minepos.contains(mineloc)) { // ! is like not keyword in python
                minepos.add(mineloc);
                System.out.println(mineloc);
                minesplaced++;
            }} // if theres no duplicate pos add the pos
        // places the mines
        for (int mine: minepos) {
            int row = mine / columns;
            int col = mine % columns;
            grid[row][col] = -1;
        }
        // adds all the numbers near the mines
        for (int mine: minepos) {
            int row = mine / columns;
            int col = mine % columns;
            // checks one square behind and then mid and front
            for (int left = row - 1; left < row + 2; left++) {
                for (int up = col - 1; up < col + 2; up++) {
                    if (inBounds(left, up)) {
                        // if its not a mine
                        if (grid[left][up] != -1) {
                            grid[left][up]++;
                        }}}}}
        // this just loops thru everthing to print the array
        for (int[] row: grid) { 
            System.out.println(Arrays.toString(row));
        }}

    // this button exposes every number
    public void gameOver(MSButton[][] grid) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].expose();
                if (grid[i][j].number == -1) {
                    grid[i][j].setMine();
                }
            }
        }
        gameState = true;
    }

    // checks if all tiles that arent mines are clicked
    public void checkWin() {
        int tiles_exposed = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buttongrid[i][j].exposed) {
                    tiles_exposed++;
                }
            }
        }
        if (tiles_exposed == rows * columns - mines) {
            System.out.println("you wun");
            gameState = true;
      for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                    buttongrid[i][j].setFlag();
                }}}
            
        }

    // this mass exposes 0s and the numbers on the 0s edges if theres a 0 exposed
    public void massExpose(MSButton button) {
        // this is pretty inefficient because i have this in my mousehandler class
        // it does it everytime a mine is exposed, that includes a game over
        // noticable lag comes, which i wanna fix
        int sqrx = button.location[0];
        int sqry = button.location[1];
        // I dont use the inbounds func lol
        try {
        if (button.number != 0) {
            }
        else {
            // it only does numbers around it, it could never hit a mine if its 0
            // if it did hit an integer, it would not do this whole operation
            for (int row = sqrx - 1; row < sqrx + 2; row++) {
                for (int col = sqry - 1; col < sqry + 2; col++) {
                    // only if in bounds so no error is raised
                    // i should remove the try except statement atp
                    if (inBounds(row, col) && !buttongrid[row][col].getStat()) {
                        buttongrid[row][col].expose();
                        massExpose(buttongrid[row][col]);
                }
            
        }}
        }
        } catch(Exception e) {
                System.out.println("something has gone terribly wrong");
            // everythings workin like normal!!
        }

    }

    public void buildField() {
        // makes the mine pattern in here cuz if it wasnt here
        // every mine would display 0
        // and idk how to do it before creating the goddang object for the game
        gameState = false;

        this.armMineField(this.intgrid); 

        // this function makes the frame and generally runs the game

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        // Frame stuff

        GridLayout layout = new GridLayout(rows, columns);
        GridLayout topLayout = new GridLayout(1, 4);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // top panel
        auxMouseHandler amh = new auxMouseHandler(this);
        JPanel topPanel = new JPanel();
        JLabel time = new JLabel();
        JLabel mineAmt = new JLabel();
        JButton restartBtn = new JButton(); restartBtn.setPreferredSize(new Dimension(rows*50/4, 50));
        restartBtn.setText("restart");
        restartBtn.addMouseListener(amh);
        JButton helpBtn = new JButton(); helpBtn.setPreferredSize(new Dimension(rows*50/4, 50));
        helpBtn.setText("?");
        helpBtn.addMouseListener(amh);
        topPanel.add(time);
        topPanel.add(mineAmt);
        topPanel.add(restartBtn);
        topPanel.add(helpBtn);
        topPanel.setLayout(topLayout);
        frame.add(topPanel);
        // panels
        JPanel minefield = new JPanel(); // mmm excellent variable naming mmmmmmm
        JPanel label = new JPanel();
        JLabel text = new JLabel("bleh bleh bleh");
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,40);
        text.setFont(font); // for some reason we needa add the FONT TO THE TEXT 
        label.add(text); // and add the text to the panel...
        
        frame.add(label, BorderLayout.NORTH); // and add the panel to the frame...

        // minesweeper buttons

        
        // for each row...
        for (int i = 0; i < rows; i++) {
            // theres gonna be a columns that...
            for (int j = 0; j < columns; j++) {
                // makes and adds a button to the 2d list and panel field thing
                MSButton button = new MSButton();
                MouseHandler mh = new MouseHandler(this);
                buttongrid[i][j] = button;
                minefield.add(button);
                button.setLoc(i, j);
                button.number = intgrid[i][j];
                button.addMouseListener(mh);
                // and the number
                // JLabel minenum = new JLabel(Integer.toString(intgrid[i][j]));
                // button.add(minenum);
            }}
        minefield.setLayout(layout);
        frame.add(minefield, BorderLayout.SOUTH);
        frame.pack(); // and makes them squish together
    }
    // method that will run when called
    public App() {
        this.buildField();
    }

    public static void main(String[] args) throws Exception {
        // opens the frame application thingy
        App game = new App();
        // since it is public you need to have the object AND the actual thing in the object
    }}
