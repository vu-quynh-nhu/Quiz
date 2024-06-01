import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CountdownScreen{
    static JPanel gameCountdownPanel, countdownBackgroundPanel;
    static JLabel gameCountdownLabel, countdownBackgroundLabel;
    static ImageIcon countdownBackgroundImage;
    static String startTimerSound;
    static final int MAINSCREEN_WIDTH = 1000;
    static final int MAINSCREEN_HEIGHT = 700;
    static Timer startTimer;
    static int startSecond;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    public static void showCountdownScreen(JFrame mainScreenWindow, JPanel choiceLabelPanel, JPanel choicePanel, ArrayList<Frage> fragen) {
        CategoryScreen.categoryBackgroundPanel.setVisible(false);
        choiceLabelPanel.setVisible(false);
        choicePanel.setVisible(false);

        //Custom Colors
        Color background = new Color(71, 27, 158);

        gameCountdownPanel = new JPanel();
        gameCountdownLabel = new JLabel("3");

        try {
            Font archivo = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(120f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //set custom font for label and button
            gameCountdownLabel.setFont(archivo);

        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        gameCountdownPanel.setBounds((MAINSCREEN_WIDTH / 2) - (700 / 2), (MAINSCREEN_HEIGHT / 2) - (140 / 2), 700, 140);
        gameCountdownPanel.setBackground(Color.BLACK);
        gameCountdownPanel.setOpaque(false);
        gameCountdownLabel.setForeground(Color.WHITE);
        gameCountdownPanel.add(gameCountdownLabel);
        mainScreenWindow.add(gameCountdownPanel);
        startSecond = 3;
        startTimer(mainScreenWindow, fragen);
        startTimer.start(); 

        startTimerSound = ".//sounds//beforequizstarts.wav";
        
        gameSoundEffect.setFile(startTimerSound);
        gameSoundEffect.startSoundEffect();

        countdownBackgroundImage = new ImageIcon(".//res//quiz/background.jpg");
        countdownBackgroundImage.setImage(countdownBackgroundImage.getImage().getScaledInstance(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT, Image.SCALE_DEFAULT));
        countdownBackgroundPanel = new JPanel();
        countdownBackgroundPanel.setBackground(background);
        countdownBackgroundPanel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        countdownBackgroundLabel = new JLabel();
        countdownBackgroundLabel.setBackground(background);
        countdownBackgroundLabel.setIcon(countdownBackgroundImage);
        countdownBackgroundLabel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        countdownBackgroundPanel.add(countdownBackgroundLabel);
        mainScreenWindow.add(countdownBackgroundPanel); 
    }

    public static void startTimer(JFrame mainScreenWindow, ArrayList<Frage> fragen) {
        startTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSecond--;
                gameCountdownLabel.setText("" + startSecond);

                if (startSecond == 0) {
                    gameCountdownLabel.setText("LOS!");
                }

                if (startSecond == -1) {
                    startTimer.stop();
                    InGameScreen.showInGameScreen(mainScreenWindow, gameCountdownPanel, fragen);
                }
            }
        });
    }
}
