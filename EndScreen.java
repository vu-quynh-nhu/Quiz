import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EndScreen {
    static JPanel gameOverPanel, pointsPanel, remarkPanel, medalPanel;
    static JLabel gameOverLabel, pointsLabel, remarkLabel, medalLabel;
    static ImageIcon medalImage;
    static final int MAINSCREEN_WIDTH = 1000;
    static final int MAINSCREEN_HEIGHT = 700;
    static JButton playAgainButton, exitGameButton;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    public static void showEndScreen(JFrame mainScreenWindow, JPanel inGameBarPanel, JPanel questionPanel, JPanel questionImagePanel, JPanel answerPanel, JButton nextButton, int scoreCount, Timer quizTimer) {
        inGameBarPanel.setVisible(false);
        questionPanel.setVisible(false);
        questionImagePanel.setVisible(false);
        answerPanel.setVisible(false);
        nextButton.setVisible(false);

        //Custom Colors
        Color background = new Color(71, 27, 158);

        //initialize Buttons, Labels, Panels, etc
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

        //Custom Font
        try {
            Font archivo = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(30f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //set custom font for label and button
            gameOverLabel.setFont(archivo);
            pointsLabel.setFont(archivo);
            pointsLabel.setFont(new Font(pointsLabel.getFont().getName(), pointsLabel.getFont().getStyle(), 23));
            remarkLabel.setFont(archivo);
            remarkLabel.setFont(new Font(remarkLabel.getFont().getName(), remarkLabel.getFont().getStyle(), 23));
            playAgainButton.setFont(archivo);
            playAgainButton.setFont(new Font(playAgainButton.getFont().getName(), playAgainButton.getFont().getStyle(), 19));
            exitGameButton.setFont(archivo);
            exitGameButton.setFont(new Font(exitGameButton.getFont().getName(), exitGameButton.getFont().getStyle(), 19));
            //resize font for button
            //startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 22));
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        String endGameSound = ".//sounds//end.wav";
        gameSoundEffect.setFile(endGameSound);
        gameSoundEffect.startSoundEffect();

        //gameoverPanel
        gameOverPanel.setBounds((MAINSCREEN_WIDTH / 2) - (400 / 2), 80, 400, 40);
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setOpaque(false);
        mainScreenWindow.add(gameOverPanel);

        //gameoverlabel
        gameOverLabel.setText("Das Spiel ist zu Ende");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverPanel.add(gameOverLabel);

         //remarkPanel
        remarkPanel.setBounds((MAINSCREEN_WIDTH / 2) - (200 / 2), 390, 200, 40);
        remarkPanel.setBackground(Color.BLACK);
        remarkPanel.setOpaque(false);
        mainScreenWindow.add(remarkPanel);

         //remarkLabel
        remarkLabel.setForeground(Color.WHITE);
        remarkPanel.add(remarkLabel);

        //pointsPanel
        pointsPanel.setBounds((MAINSCREEN_WIDTH / 2) - (300 / 2), 170, 300, 40);
        pointsPanel.setBackground(Color.BLACK);
        pointsPanel.setOpaque(false);
        mainScreenWindow.add(pointsPanel);

        //pointsLabel
        pointsLabel.setText("Deine Punkte: " + scoreCount); 
        pointsLabel.setForeground(Color.WHITE);
        pointsPanel.add(pointsLabel);

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
        
        //medal
        medalPanel.setBounds((MAINSCREEN_WIDTH / 2) - (160 / 2), 220, 160, 160);
        medalPanel.setOpaque(false);
        mainScreenWindow.add(medalPanel);
        medalPanel.add(medalLabel);
        medalImage.setImage(medalImage.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        medalLabel.setIcon(medalImage);

        //playAgainButton
        playAgainButton.setText("Nochmal spielen");
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setBorder(null);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setBounds(100, 550, 200, 30);

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

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverPanel.setVisible(false);
                pointsPanel.setVisible(false);
                medalPanel.setVisible(false);
                remarkPanel.setVisible(false);
                playAgainButton.setVisible(false);
                exitGameButton.setVisible(false);
                //backgroundPanel.setVisible(false);
                quizTimer.stop();
                //reset question index
                //fragenIndex = 1;
                //selectQuestionSet();
                InGameScreen.setFragenIndex(1);
                CategoryScreen.showCategoryScreen(mainScreenWindow, answerPanel, nextButton);
            }
        });
        mainScreenWindow.add(playAgainButton);

        //exitButton
        exitGameButton.setText("Spiel beenden");
        exitGameButton.setForeground(Color.WHITE);
        exitGameButton.setBorder(null);
        exitGameButton.setFocusPainted(false);
        exitGameButton.setContentAreaFilled(false);
        exitGameButton.setBounds(700, 550, 200, 30);

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
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreenWindow.dispatchEvent(new WindowEvent(mainScreenWindow, WindowEvent.WINDOW_CLOSING));
            }
        });
        mainScreenWindow.add(exitGameButton);
    }
    
}
