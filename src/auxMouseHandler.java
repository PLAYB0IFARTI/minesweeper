
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

// this is for restart and question mark buttons 

public class auxMouseHandler implements MouseListener {
    App game;
    helpTab help = new helpTab();
    
    public auxMouseHandler(App game) {
        this.game = game;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getText() == "restart") {game.buildField();}
        else {help.show();}
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
