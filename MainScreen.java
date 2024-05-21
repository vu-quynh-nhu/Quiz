import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainScreen extends JFrame {
    private JLabel headerLabel = new JLabel("Willkommen zu unserem Quiz!");
    private JButton startButton = new JButton("START");
    private JFrame mainScreenWindow;
    private JPanel backgroundPanel, gameCountdownPanel, quizLogoPanel, inGameBarPanel, questionPanel, answerPanel, questionImagePanel, gameOverPanel, pointsPanel, remarkPanel, medalPanel;
    private JLabel backgroundLabel, gameCountdownLabel, quizLogoLabel, questionLabel, questionNumberLabel, scoreLabel, timerLabel, questionImageLabel, gameOverLabel, pointsLabel, remarkLabel, medalLabel;
    private ImageIcon backgroundImage, quizLogoIcon, gameIcon, questionImageIcon, medalImage;
    private String startTimerSound;
    private final int MAINSCREEN_WIDTH = 1000;
    private final int MAINSCREEN_HEIGHT = 700;
    private final int QUIZLOGO_WIDTH = 681;
    private final int QUIZLOGO_HEIGHT = 419;
    private JButton answerAButton, answerBButton, answerCButton, answerDButton, nextButton, playAgainButton, exitGameButton;
    Timer startTimer;
    int startSecond, second, scoreCount;
    Timer quizTimer;
    GameSoundEffect gameSoundEffect = new GameSoundEffect();

    int fragenIndex = 1;
    ArrayList<Frage> fragen = new ArrayList<Frage>();


    //Constructor 
    public MainScreen() {
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
        gameIcon = new ImageIcon(".//res//quizIcon.png");
        mainScreenWindow.setIconImage(gameIcon.getImage());

        //Image Panel
        quizLogoPanel = new JPanel();
        quizLogoPanel.setBounds((MAINSCREEN_WIDTH / 2) - (QUIZLOGO_WIDTH / 2), 100, QUIZLOGO_WIDTH, QUIZLOGO_HEIGHT);
        quizLogoPanel.setBackground(backgroundColor);
        mainScreenWindow.getContentPane().add(quizLogoPanel);

        //Label to put image inside Panel
        quizLogoLabel = new JLabel();
        quizLogoIcon = new ImageIcon(".//res//quizLogo.png");
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
                gameCountDownOnClick(e);
            }
        });

        fragen.add(new Frage("Wie heißt dieser Samen?", "Leinsamen","Chiasamen", "Flohsamen", "Quinoa", ".//res//chiasamen.png", 'B'));
        fragen.add(new Frage("Aus welchem Land stammt dieses Gericht - Shakshuka?", "Italien","Deutschland", "Türkei", "Israel", ".//res//shakshuka.png", 'D'));
        fragen.add(new Frage("Wie heißt das cremefarbene Gemüse auf der Pizza?", "Lauch","Artischocken", "Blumenkohl", "Champignos", ".//res//pizza.png", 'B'));
        fragen.add(new Frage("Welches Obst ist botanisch gesehen ein Gemüse?", "Rhabarber","Kiwi", "Erdberre", "Hagebutte", ".//res//gemüse.png", 'A'));
        fragen.add(new Frage("Was verbirgt sich hinter dem Gericht Kalakukko?", "Japanische Fischsuppe","Sushi mit Thunfisch", "Fisch im Teigmantel", "Fisch in Kohlmantel", ".//res//japan.png", 'C'));
        fragen.add(new Frage("Haggis gilt als umstrittene Delikatesse in welchem Land?", "Norwegen","Island", "Schottland", "Irland", ".//res//haggis.png", 'C'));
        fragen.add(new Frage("Das Feijoada ist die Leibspeise vieler …?", "Tunesier","Ukrainer", "Roler", "Brasilianer", ".//res//brasilien.png", 'D'));
        fragen.add(new Frage("Eine Pho bekommt man in welchen Land serviert?", "Vietnam","Indonesien", "Thailand", "Japan", ".//res//pho.png", 'A'));
        fragen.add(new Frage("Welches Lebensmittel wird in Deutschland am vielfältiges hergestellt?", "Käse","Wurst", "Brot", "Marmelade", ".//res//brot.png", 'C'));
        fragen.add(new Frage("Wie nennt sich dieser Salat?", "Mangold","Rucola", "Chicoree", "Endivien", ".//res//mangold.png", 'A'));
    }

    public static void main(String[] args) {
        new MainScreen();
    }

    //gameCountDown
    public void gameCountDownOnClick(ActionEvent e) {
        quizLogoPanel.setVisible(false);
        startButton.setVisible(false);
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
        startTimer();
        startTimer.start(); 

        startTimerSound = ".//sounds//beforequizstarts.wav";
        
        gameSoundEffect.setFile(startTimerSound);
        gameSoundEffect.startSoundEffect();


        //Background
        backgroundImage = new ImageIcon(".//res//background.jpg");
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT, Image.SCALE_DEFAULT));
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(background);
        backgroundPanel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(background);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundPanel.add(backgroundLabel);
        mainScreenWindow.add(backgroundPanel); 
    }

    public void startTimer() {
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
                    gameScreenOnClick();
                }
            }
        });
    }

    //game screen
    public void gameScreenOnClick() {
        //custom Colors
        Color quizImageBorder = new Color(161, 36, 203);
        Color answerText = new Color(122, 31, 182);
        Color background = new Color(71, 27, 158);

        //disable logo panel and button
        gameCountdownPanel.setVisible(false);
        backgroundPanel.setVisible(false);
        scoreCount = 0;

        inGameBarPanel = new JPanel();
        //increases when next/skipp Button was clicked
        String questionNumString = "Frage " + fragenIndex + " / " + fragen.size();
        questionNumberLabel = new JLabel(questionNumString, SwingConstants.CENTER);
        //increases when answer is right and next Button was clicked
        scoreLabel = new JLabel("Punkte: " + scoreCount, SwingConstants.CENTER);
        //restarts when next/skipp Button was clicked
        timerLabel = new JLabel("Zeit: 30", SwingConstants.CENTER);

        questionPanel = new JPanel();
        questionLabel = new JLabel(fragen.get(fragenIndex-1).getFrage(), SwingConstants.CENTER);

        questionImagePanel = new JPanel();
        questionImageLabel = new JLabel();
        questionImageIcon = new ImageIcon(fragen.get(fragenIndex-1).getImage());

        answerPanel = new JPanel();
        answerAButton = new JButton(fragen.get(fragenIndex-1).getAntwortA());
        answerBButton = new JButton(fragen.get(fragenIndex-1).getAntwortB());
        answerCButton = new JButton(fragen.get(fragenIndex-1).getAntwortC());
        answerDButton = new JButton(fragen.get(fragenIndex-1).getAntwortD());

        nextButton = new JButton("überspringen >");
        //custom font
        try {
            Font archivoQuestion = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(20f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //set custom font for label and button
            questionNumberLabel.setFont(archivoQuestion);
            scoreLabel.setFont(archivoQuestion);
            timerLabel.setFont(archivoQuestion);
            
            //startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 22));
            questionLabel.setFont(new Font(questionLabel.getFont().getName(), questionLabel.getFont().getStyle(), 24));
            answerAButton.setFont(archivoQuestion);
            answerBButton.setFont(archivoQuestion);
            answerCButton.setFont(archivoQuestion);
            answerDButton.setFont(archivoQuestion);
            nextButton.setFont(archivoQuestion);
            //resize font for button
            //startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 22));
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        inGameBarPanel.setBounds((MAINSCREEN_WIDTH / 2) - (970 / 2), 0, 970, 50);
        inGameBarPanel.setBackground(Color.BLACK);
        inGameBarPanel.setOpaque(false);
        inGameBarPanel.setLayout(new GridLayout(1, 3));
        mainScreenWindow.getContentPane().add(inGameBarPanel);

        questionNumberLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        timerLabel.setForeground(Color.WHITE);
        second = 30;
        quizCountdown();
        quizTimer.start();

        inGameBarPanel.add(questionNumberLabel);
        inGameBarPanel.add(scoreLabel);
        inGameBarPanel.add(timerLabel);
        
        questionPanel.setBounds((MAINSCREEN_WIDTH / 2) - (800 / 2), 90, 800, 40);
        questionPanel.setBackground(Color.BLACK);
        questionPanel.setOpaque(false);
        mainScreenWindow.getContentPane().add(questionPanel);
        
        questionLabel.setBounds((MAINSCREEN_WIDTH / 2) - (600 / 2), 90, 600, 40);
        questionLabel.setBackground(Color.BLACK);
        questionLabel.setOpaque(false);
        questionLabel.setForeground(Color.WHITE);
        //automatically creates a next line
        //questionArea.setLineWrap(true);
        questionPanel.add(questionLabel);

        questionImagePanel.setBounds((MAINSCREEN_WIDTH / 2) - (420 / 2), 140, 420, 228);
        questionImagePanel.setBackground(quizImageBorder);
        mainScreenWindow.getContentPane().add(questionImagePanel);
        questionImagePanel.add(questionImageLabel);
        questionImageIcon.setImage(questionImageIcon.getImage().getScaledInstance(410, 218, Image.SCALE_DEFAULT));
        questionImageLabel.setIcon(questionImageIcon);


        mainScreenWindow.getContentPane().add(answerPanel);
        answerPanel.setBackground(Color.BLACK);
        answerPanel.setOpaque(false);
        answerPanel.setBounds((MAINSCREEN_WIDTH / 2) - (800 / 2), 420, 800, 150);
        answerAButton.setPreferredSize(new Dimension(350, 68));
        answerAButton.setBackground(Color.WHITE);
        answerAButton.setForeground(answerText);
        answerAButton.setBorder(null);
        answerAButton.setFocusPainted(false);
        answerPanel.add(answerAButton);

        answerBButton.setPreferredSize(new Dimension(350, 68));

        answerBButton.setBackground(Color.WHITE);
        answerBButton.setForeground(answerText);
        answerBButton.setBorder(null);
        answerBButton.setFocusPainted(false);
        answerPanel.add(answerBButton);

        answerCButton.setPreferredSize(new Dimension(350, 68));
        answerCButton.setBackground(Color.WHITE);
        answerCButton.setForeground(answerText);
        answerCButton.setBorder(null);
        answerCButton.setFocusPainted(false);
        answerPanel.add(answerCButton);

        answerDButton.setPreferredSize(new Dimension(350, 68));
        answerDButton.setBackground(Color.WHITE);
        answerDButton.setForeground(answerText);
        answerDButton.setBorder(null);
        answerDButton.setFocusPainted(false);
        answerPanel.add(answerDButton);
        
        answerAButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                answerAButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
        
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        answerBButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                answerBButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
        
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        answerCButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                answerCButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
        
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        answerDButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                answerDButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
        
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        //next button
        nextButton.setBounds(760, 585, 200, 50);
        nextButton.setBorder(null);
        nextButton.setFocusPainted(false);
        nextButton.setForeground(Color.WHITE);
        nextButton.setContentAreaFilled(false);
        mainScreenWindow.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fragenIndex < fragen.size()) {
                    fragenIndex++;
                    //increases when next/skipp Button was clicked
                    String questionNumString = "Frage " + fragenIndex + " / " + fragen.size();
                    questionNumberLabel.setText(questionNumString);

                    questionLabel.setText(fragen.get(fragenIndex-1).getFrage());

                    answerAButton.setText(fragen.get(fragenIndex-1).getAntwortA());
                    answerBButton.setText(fragen.get(fragenIndex-1).getAntwortB());
                    answerCButton.setText(fragen.get(fragenIndex-1).getAntwortC());
                    answerDButton.setText(fragen.get(fragenIndex-1).getAntwortD());

                    String path = fragen.get(fragenIndex-1).getImage();
                    questionImageIcon.setImage(new ImageIcon(path).getImage());
                    questionImageLabel.repaint();

                    answerAButton.setEnabled(true);
                    answerBButton.setEnabled(true);    
                    answerCButton.setEnabled(true);
                    answerDButton.setEnabled(true);

                    answerAButton.setBackground(Color.WHITE);
                    answerBButton.setBackground(Color.WHITE);
                    answerCButton.setBackground(Color.WHITE);
                    answerDButton.setBackground(Color.WHITE);
                    second = 30;
                    timerLabel.setText("Zeit: " + second);
                    quizTimer.start();
                } else {
                    endScreenOnClick(e);
                }
                
            }
        });

        nextButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

        //Correct and Wrong answer
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton chosenAnswer = (JButton) e.getSource();
                String correctAnswerSoundEffect = ".//sounds//correct.wav";
                String wrongAnswerSoundEffect = ".//sounds//wrong.wav";
                String correctAnswer = "";

                switch (fragen.get(fragenIndex-1).getRichtigeAntwort()) {
                    case 'A': correctAnswer = fragen.get(fragenIndex-1).getAntwortA();
                        break;
                    case 'B': correctAnswer = fragen.get(fragenIndex-1).getAntwortB();
                    break;
                    case 'C': correctAnswer = fragen.get(fragenIndex-1).getAntwortC();
                    break;
                    case 'D': correctAnswer = fragen.get(fragenIndex-1).getAntwortD();
                    break;
                    default:
                        break;
                }              

                if (chosenAnswer.getText().equals(correctAnswer)) {
                    quizTimer.stop();
                    scoreCount++;
                    gameSoundEffect.setFile(correctAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    chosenAnswer.setBackground(Color.GREEN);
                    nextButton.setText("nächste Frage >");
                    
                } else {
                    quizTimer.stop();
                    gameSoundEffect.setFile(wrongAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    chosenAnswer.setBackground(Color.RED);
                    nextButton.setText("nächste Frage >");
                }

                answerAButton.setEnabled(false);
                answerBButton.setEnabled(false);    
                answerCButton.setEnabled(false);
                answerDButton.setEnabled(false);
            }
        };

        answerAButton.addActionListener(actionListener);
        answerBButton.addActionListener(actionListener);
        answerCButton.addActionListener(actionListener);
        answerDButton.addActionListener(actionListener);

        //Background
        backgroundImage = new ImageIcon(".//res//background.jpg");
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT, Image.SCALE_DEFAULT));
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(background);
        backgroundPanel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(background);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundPanel.add(backgroundLabel);
        mainScreenWindow.add(backgroundPanel);
    }   

    //quiz countdown
    public void quizCountdown() {
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;

                if (second == 0) {
                    quizTimer.stop();
                    nextButton.setText("nächste Frage >");
                    answerAButton.setEnabled(false);
                    answerBButton.setEnabled(false);    
                    answerCButton.setEnabled(false);
                    answerDButton.setEnabled(false);
                } 
                timerLabel.setText("Zeit: " + second);
            }
        });
    }

    //end screen
    public void endScreenOnClick(ActionEvent e) {
        backgroundPanel.setVisible(false);
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


        //pointsPanel
        pointsPanel.setBounds((MAINSCREEN_WIDTH / 2) - (200 / 2), 170, 200, 40);
        pointsPanel.setBackground(Color.BLACK);
        pointsPanel.setOpaque(false);
        mainScreenWindow.add(pointsPanel);

        //pointsLabel
        pointsLabel.setText("Deine Punkte: " + scoreCount); 
        pointsLabel.setForeground(Color.WHITE);
        pointsPanel.add(pointsLabel);
        
        //medal
        medalPanel.setBounds((MAINSCREEN_WIDTH / 2) - (160 / 2), 220, 160, 160);
        medalPanel.setOpaque(false);
        mainScreenWindow.add(medalPanel);
        medalPanel.add(medalLabel);
        medalImage.setImage(medalImage.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        medalLabel.setIcon(medalImage);

        //remarkPanel
        remarkPanel.setBounds((MAINSCREEN_WIDTH / 2) - (200 / 2), 390, 200, 40);
        remarkPanel.setBackground(Color.BLACK);
        remarkPanel.setOpaque(false);
        mainScreenWindow.add(remarkPanel);

        //remarkLabel
        remarkLabel.setForeground(Color.WHITE);
        remarkPanel.add(remarkLabel);

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
                backgroundPanel.setVisible(false);
                quizTimer.stop();
                //reset question index
                fragenIndex = 1;
                gameCountDownOnClick(e);
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

        //Background
        backgroundImage = new ImageIcon(".//res//background.jpg");
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT, Image.SCALE_DEFAULT));
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(background);
        backgroundPanel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(background);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundPanel.add(backgroundLabel);
        mainScreenWindow.add(backgroundPanel); 
    }
}
