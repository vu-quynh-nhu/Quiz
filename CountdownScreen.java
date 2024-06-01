import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Diese Klasse ist für den Countdown Screen
public class CountdownScreen{
    //Deklaration der GUI-Komponenten
    static JPanel gameCountdownPanel, countdownBackgroundPanel;
    static JLabel gameCountdownLabel, countdownBackgroundLabel;
    static ImageIcon countdownBackgroundImage;
    static String startTimerSound;
    static Timer startTimer;
    static int startSecond;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    //Methode zum anzeigen des Countdownscreen
    public static void showCountdownScreen(JFrame mainScreenWindow,JPanel categoryBackgroundPanel,JPanel choiceLabelPanel, JPanel choicePanel, ArrayList<Frage> fragen) {
        //Unsichtbar setzen der vorherigen GUI-Komponenten
        categoryBackgroundPanel.setVisible(false);
        choiceLabelPanel.setVisible(false);
        choicePanel.setVisible(false);

        //Erstellung des Container und des Labels für den Countdown
        gameCountdownPanel = new JPanel();
        gameCountdownLabel = new JLabel("3");

        //Anpassung der Schriftart
        try {
            Font archivo = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(120f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //set custom font for label and button
            gameCountdownLabel.setFont(archivo);

        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        //Attribute des Containers für den Countdown anpassen und zum Fenster hinzufügen
        gameCountdownPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (700 / 2), (QuizUtils.MAINSCREEN_HEIGHT  / 2) - (140 / 2), 700, 140);
        gameCountdownPanel.setBackground(Color.BLACK);
        gameCountdownPanel.setOpaque(false);
        gameCountdownLabel.setForeground(Color.WHITE);
        gameCountdownPanel.add(gameCountdownLabel);
        mainScreenWindow.add(gameCountdownPanel);

        //den Timer auf drei Sekunden setzen und starten
        startSecond = 3;
        startTimer(mainScreenWindow, fragen);
        startTimer.start(); 

        //Startsound inialisieren und abspielen
        startTimerSound = ".//sounds//beforequizstarts.wav";
        gameSoundEffect.setFile(startTimerSound);
        gameSoundEffect.startSoundEffect();

        //Hinterground des Screens anpassen
        countdownBackgroundImage = new ImageIcon(".//res//quiz/background.jpg");
        countdownBackgroundImage.setImage(countdownBackgroundImage.getImage().getScaledInstance(QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT , Image.SCALE_DEFAULT));
        countdownBackgroundPanel = new JPanel();
        countdownBackgroundPanel.setBackground(QuizUtils.backgroundColor);
        countdownBackgroundPanel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        countdownBackgroundLabel = new JLabel();
        countdownBackgroundLabel.setBackground(QuizUtils.backgroundColor);
        countdownBackgroundLabel.setIcon(countdownBackgroundImage);
        countdownBackgroundLabel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        countdownBackgroundPanel.add(countdownBackgroundLabel);
        mainScreenWindow.add(countdownBackgroundPanel); 
    }

    //Methode zum Starten des Timers
    public static void startTimer(JFrame mainScreenWindow, ArrayList<Frage> fragen) {
        //Hinzufügen eines ActionListerns die Aktion nach Ablauf einer Sekunde auslöst
        startTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sekunden werden abgezogen
                startSecond--;
                //Label wird angepasst
                gameCountdownLabel.setText("" + startSecond);

                //wenn die Sekunden gleich null sind wird der Text des Labels angepasst
                if (startSecond == 0) {
                    gameCountdownLabel.setText("LOS!");
                }

                //wenn die Sekunden gleich minus eins sind wird der Timer gestoppt
                //und der In Game Screen angezeigt
                if (startSecond == -1) {
                    startTimer.stop();
                    InGameScreen.showInGameScreen(mainScreenWindow, gameCountdownPanel, fragen);
                }
            }
        });
    }
}
