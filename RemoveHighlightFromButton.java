import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class RemoveHighlightFromButton extends JButton {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected()) {
            setBorder(BorderFactory.createEmptyBorder());
        } else {
            setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }
}