import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// views click input for the tiles on the playing field

public class MouseHandler implements MouseListener{
    App game; // Makes it not execute the program (no i did NOT accidentally make a new app)

    public MouseHandler(App game) {
        this.game = game;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // basically the right click = meta key
        
    }

    @Override
    // if the mouse is released on the mine, to prevent misclick
    public void mouseReleased(MouseEvent e) {
        MSButton button = (MSButton)e.getSource();
        // TODO Auto-generated method stub
        // if the game isnt finished
        if (!game.gameState) {
            // isMetaDown is right mouse button
            if (!e.isMetaDown()) {
                if (button.exposed) {
                    System.out.println("ble");
                    game.checkAround(button);
                }
                else if (!button.exposed) {
                    System.out.println("ble");
                    button.expose();
                    game.massExpose(button);
                    game.checkWin();
                    
                    // booms it all if the thing clicked is -1
                    if (button.minenum.getText().equals(Integer.toString(-1))) {
                        game.gameOver(game.buttongrid);
                    }
                    }
            }
            // if right click is down yea do that function 
            else if (e.isMetaDown()) {
                button.setFlag();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
