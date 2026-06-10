import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class helpTab {
    JFrame frame = new JFrame();
    private boolean state = false;
    public helpTab() {

        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel text = new JLabel("HOW TO PLAY THE GAME!!!!!");
        Font style = new Font(Font.SANS_SERIF, Font.BOLD,40);
        text.setFont(style);
        panel.add(text); frame.add(panel, BorderLayout.NORTH);
        frame.pack();
    }
    public void show() {
        if (!state) {
            frame.setVisible(true);
            state = true;
        }
        else {
            frame.setVisible(false);
            state = false;
        }
    }
}
