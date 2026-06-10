import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

// the actual tiles on the minefield

public class MSButton extends JButton{
    int[] location = new int[2];
    boolean exposed = false;
    boolean flagged = false;
    int number; 
    final static int WIDTH = 50; final static int HEIGHT = 50;
    JLabel minenum = new JLabel();
    App game;
    ImageIcon mine = new ImageIcon(getClass().getResource("resources/ryan.png"));
    ImageIcon unexposed_tile = new ImageIcon(getClass().getResource("resources/tetoo.jpg"));
    ImageIcon flag = new ImageIcon(getClass().getResource("resources/china.png"));
    
    // turns it from teto to an actual number
    public void expose() {
        if (!exposed && !flagged) {
            minenum.setText(Integer.toString(number));
            exposed = true;
            this.setIcon(null);
           }
    }

    public int[] getLoc(MSButton button) {
        return button.location;
    }
    public int getNum() {
        return number;
    }
    // get status of the butto 
    public boolean getStat() {
        return exposed;
    }
    // yea this sets the location 
    public void setLoc(int row, int col) {
        location[0] = row;
        location[1] = col;
    }

    // returns a scaled version of the image
    public ImageIcon setScale(ImageIcon img) {
        Image image = img.getImage();
        Image scaledImage = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;

    }

    // sets the flag onto the mine
    public void setFlag() {
        if (!exposed) {
            if (!flagged) {
                this.setIcon(setScale(flag));
            }
            else {
                this.setIcon(setScale(unexposed_tile));
            }
        }
        if (!flagged) {
            flagged = true;
        }
        else {
            flagged = false;
        }
    }

    // sets a mine into ryan gosling
    public void setMine() {
        this.setIcon(setScale(mine));
    }

    // returns the state it is
    public boolean isExposed() {
        return exposed;
    }

    public MSButton() {
    // sets the default size when opening the program
    // this.setIcon(img);
        this.setPreferredSize(new Dimension(50,50));
        this.add(minenum);
        if (!exposed) {
            this.setIcon(setScale(unexposed_tile));
        }
    }
}
