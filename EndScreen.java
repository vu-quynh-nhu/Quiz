import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

//Diese Klasse ist für den End Screen
public class EndScreen {
    //Deklarierung aller GUI-Komponente und Variablen
    static JPanel gameOverPanel, pointsPanel, remarkPanel, medalPanel, endBackgroundPanel;
    static JLabel gameOverLabel, pointsLabel, remarkLabel, medalLabel, endBackgroundLabel;
    static ImageIcon medalImage, endBackgroundImage;
    static JButton playAgainButton, exitGameButton;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    //Methode zum Anzeigen
    public static void showEndScreen(JFrame mainScreenWindow, JPanel inGameBarPanel, JPanel questionPanel, JPanel questionImagePanel, JPanel answerPanel, JPanel nextButtonPanel, int scoreCount, Timer quizTimer) {
        InGameScreen.inGameBackgroundPanel.setVisible(false);
        inGameBarPanel.setVisible(false);
        questionPanel.setVisible(false);
        questionImagePanel.setVisible(false);
        answerPanel.setVisible(false);
        nextButtonPanel.setVisible(false);

        //Initialize der Buttons, Labels, Panels, etc.
        gameOverPanel = new JPanel();
        gameOverLabel = new JLabel();

        pointsPanel = new JPanel();
        pointsLabel = new JLabel();

        medalPanel = new JPanel();
        medalLabel = new JLabel();

        remarkPanel = new JPanel();
        remarkLabel = new JLabel();

        playAgainButton = new JButton();
        exitGameButton = new JButton();

        //Die Schriftart wird angepasst
        try {
            Font archivo = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(30f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //die Schriftart wird den Label und Buttons übergeben
            gameOverLabel.setFont(archivo);
            pointsLabel.setFont(archivo);
            pointsLabel.setFont(new Font(pointsLabel.getFont().getName(), pointsLabel.getFont().getStyle(), 23));
            remarkLabel.setFont(archivo);
            remarkLabel.setFont(new Font(remarkLabel.getFont().getName(), remarkLabel.getFont().getStyle(), 23));
            playAgainButton.setFont(archivo);
            playAgainButton.setFont(new Font(playAgainButton.getFont().getName(), playAgainButton.getFont().getStyle(), 19));
            exitGameButton.setFont(archivo);
            exitGameButton.setFont(new Font(exitGameButton.getFont().getName(), exitGameButton.getFont().getStyle(), 19));
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        //Der Endsound wird inialisiert und abgespielt
        String endGameSound = ".//sounds//end.wav";
        gameSoundEffect.setFile(endGameSound);
        gameSoundEffect.startSoundEffect();

        //Bei den Container und Label werden die Attribute angepasst und zum Fenster hinzugefügt
        gameOverPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (400 / 2), 80, 400, 40);
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setOpaque(false);
        mainScreenWindow.add(gameOverPanel);

        gameOverLabel.setText("Das Spiel ist zu Ende");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverPanel.add(gameOverLabel);

        remarkPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (200 / 2), 390, 200, 40);
        remarkPanel.setBackground(Color.BLACK);
        remarkPanel.setOpaque(false);
        mainScreenWindow.add(remarkPanel);

        remarkLabel.setForeground(Color.WHITE);
        remarkPanel.add(remarkLabel);

        pointsPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (300 / 2), 170, 300, 40);
        pointsPanel.setBackground(Color.BLACK);
        pointsPanel.setOpaque(false);
        mainScreenWindow.add(pointsPanel);

        pointsLabel.setText("Deine Punkte: " + scoreCount); 
        pointsLabel.setForeground(Color.WHITE);
        pointsPanel.add(pointsLabel);

        //Je nach Score wird der Text und das Bild des Endscreen angepasst
        String path = "";
        if (scoreCount >= 0 && scoreCount < 4) {
            path = ".//res//quiz/bronzemedal.png";
            remarkLabel.setText("You tried"); 
        } else if (scoreCount > 3 && scoreCount < 7) {
            path = ".//res.//quiz/silvermedal.png";
            remarkLabel.setText("Gut Gemacht!"); 
        } else {
            path = ".//res.//quiz/goldmedal.png";
            remarkLabel.setText("Perfekt!"); 
        }

        medalImage = new ImageIcon(path);
        
        //Die Attribute des Containers, indem die Medalie angezeigt wird, werden angepasst
        medalPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (160 / 2), 220, 160, 160);
        medalPanel.setOpaque(false);
        mainScreenWindow.add(medalPanel);
        medalPanel.add(medalLabel);
        medalImage.setImage(medalImage.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        medalLabel.setIcon(medalImage);

        //Die Attribute des Nochmal Spielen Buttons werden angepasst
        playAgainButton.setText("Nochmal spielen");
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setBorder(null);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setBounds(100, 550, 200, 30);

        //Event-Handling für den nochmal Spielen Button
        //wenn die Maus den nochmal Spielen Button betritt, so ändert sich der Cursor
        playAgainButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }   
            
            @Override
            public void mouseEntered(MouseEvent e) {
                playAgainButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        //Event-Handling für den nochmal Spielen Button
        //Wenn der Button gedrückt wird, werden die aktuellen GUI-Komponenten nicht mehr angezeigt
        //Und der Kategorie Screen wird angzeigt
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverPanel.setVisible(false);
                pointsPanel.setVisible(false);
                medalPanel.setVisible(false);
                remarkPanel.setVisible(false);
                playAgainButton.setVisible(false);
                exitGameButton.setVisible(false);
                endBackgroundPanel.setVisible(false);
                InGameScreen.setFragenIndex(1);
                CategoryScreen.showCategoryScreen(mainScreenWindow, answerPanel);
            }
        });
        //Der nochmal Spielen Button wird zum Fenster hinzugefügt
        mainScreenWindow.add(playAgainButton);

        //Die Attribute (Textfarbe, Position, Höhe, etc.) des Spiel Verlassen Button wird angepasst
        exitGameButton.setText("Spiel beenden");
        exitGameButton.setForeground(Color.WHITE);
        exitGameButton.setBorder(null);
        exitGameButton.setFocusPainted(false);
        exitGameButton.setContentAreaFilled(false);
        exitGameButton.setBounds(700, 550, 200, 30);

        //Event-Handling für den nochmal Spiel Verlassen Button
        //wenn die Maus den Spiel Verlassen Button betritt, so ändert sich der Cursor
        exitGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        //Event-Handling für den Spiel Verlassen Button
        //Wenn der Button gedrückt wird, wird das Programm beendet und das Fenster geschlossen
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenWindow.dispatchEvent(new WindowEvent(mainScreenWindow, WindowEvent.WINDOW_CLOSING));
            }
        });
        //Der Spiel Verlassen Button wird dem Fenster hinzugefügt
        mainScreenWindow.add(exitGameButton);

        //Hintergrund des Screens anpassen
        endBackgroundImage = new ImageIcon(".//res//quiz/background.jpg");
        endBackgroundImage.setImage(endBackgroundImage.getImage().getScaledInstance(QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT , Image.SCALE_DEFAULT));
        endBackgroundPanel = new JPanel();
        endBackgroundPanel.setBackground(QuizUtils.backgroundColor);
        endBackgroundPanel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        endBackgroundLabel = new JLabel();
        endBackgroundLabel.setBackground(QuizUtils.backgroundColor);
        endBackgroundLabel.setIcon(endBackgroundImage);
        endBackgroundLabel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        endBackgroundPanel.add(endBackgroundLabel);
        mainScreenWindow.add(endBackgroundPanel); 
    }
    
}
