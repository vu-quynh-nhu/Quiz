import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class StartScreen {
    static JLabel headerLabel = new JLabel("Willkommen zu unserem Quiz!");
    static JButton startButton = new JButton("START");
    static JFrame mainScreenWindow;
    static JPanel quizLogoPanel;
    static JLabel quizLogoLabel;
    static ImageIcon backgroundImage, quizLogoIcon, gameIcon;
    static final int MAINSCREEN_WIDTH = 1000;
    static final int MAINSCREEN_HEIGHT = 700;
    static final int QUIZLOGO_WIDTH = 681;
    static final int QUIZLOGO_HEIGHT = 419;

    public StartScreen() {

    }

    public static void showStartScreen() {
        //Custom colors
        Color backgroundColor = new Color(116, 30, 174);
        Color btnColor = new Color(161, 36, 203);

        //custom font
        try {
            Font archivoHeader = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(22f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //set custom font for label and button
            headerLabel.setFont(archivoHeader);
            startButton.setFont(archivoHeader);
            //resize font for button
            startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 22));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // create a window (Frame)
        mainScreenWindow = new JFrame("Quiz");
        // set size
        mainScreenWindow.setSize(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        // close window
        mainScreenWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set Backgroung color
        mainScreenWindow.getContentPane().setBackground(backgroundColor);
        // start Screen in the middle
        mainScreenWindow.setLocationRelativeTo(null);
        //null to do a custom layout
        mainScreenWindow.setLayout(null);
        //Change Game Icon from Default Java to Custom
        gameIcon = new ImageIcon(".//res//quiz/quizIcon.png");
        mainScreenWindow.setIconImage(gameIcon.getImage());

        //Image Panel
        quizLogoPanel = new JPanel();
        quizLogoPanel.setBounds((MAINSCREEN_WIDTH / 2) - (QUIZLOGO_WIDTH / 2), 100, QUIZLOGO_WIDTH, QUIZLOGO_HEIGHT);
        quizLogoPanel.setBackground(backgroundColor);
        mainScreenWindow.getContentPane().add(quizLogoPanel);


        //STARTSCREEN
        //Label to put image inside Panel
        quizLogoLabel = new JLabel();
        quizLogoIcon = new ImageIcon(".//res//quiz/quizLogo.png");
        quizLogoIcon.setImage(quizLogoIcon.getImage().getScaledInstance(QUIZLOGO_WIDTH - 10, QUIZLOGO_HEIGHT - 10, Image.SCALE_DEFAULT));
        quizLogoLabel.setIcon(quizLogoIcon);
        //add label to panel
        quizLogoPanel.add(quizLogoLabel);

        //make frame visible
        mainScreenWindow.setVisible(true);
        //mainScreenWindow.setLayout(new FlowLayout());

        //Start Button
        Dimension sizeStartButton = startButton.getPreferredSize();
        startButton.setBounds((MAINSCREEN_WIDTH / 2) - ((int) sizeStartButton.getWidth() / 2),
                520, (int) sizeStartButton.getWidth(), (int) sizeStartButton.getHeight());
        startButton.setBackground(btnColor);
        startButton.setOpaque(true);
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(null);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        mainScreenWindow.getContentPane().add(startButton);

        startButton.addMouseListener(new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
            startButton.setBackground(btnColor.darker());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            startButton.setForeground(Color.WHITE);
        }

        @Override
            public void mouseExited(MouseEvent e) {
                startButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                startButton.setForeground(btnColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setForeground(btnColor);
                //Change Cursor on Hover
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        // Button Listener
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryScreen.showCategoryScreen(mainScreenWindow, quizLogoPanel, startButton);
            }
        });
    }
}
