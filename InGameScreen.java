import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InGameScreen {
    static JPanel quizLogoPanel, inGameBarPanel, questionPanel, answerPanel, questionImagePanel;
    static JLabel quizLogoLabel, questionLabel, questionNumberLabel, scoreLabel, timerLabel, questionImageLabel;
    static ImageIcon questionImageIcon;
    static final int MAINSCREEN_WIDTH = 1000;
    static final int MAINSCREEN_HEIGHT = 700;
    static JButton answerAButton, answerBButton, answerCButton, answerDButton, nextButton;
    static int fragenIndex = 1;
    static int second;
    static int scoreCount;
    static Timer quizTimer;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    public static void setFragenIndex(int fragenIndex) {
        InGameScreen.fragenIndex = fragenIndex;
    }

    public static void showInGameScreen(JFrame mainScreenWindow, JPanel gameCountdownPanel, ArrayList<Frage> fragen) {
        //custom Colors
        Color quizImageBorder = new Color(161, 36, 203);
        Color answerText = new Color(122, 31, 182);
        Color background = new Color(71, 27, 158);

        //disable logo panel and button
        gameCountdownPanel.setVisible(false);
        //backgroundPanel.setVisible(false);
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
        quizCountdown(fragen);
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
                    /*HIER */
                    EndScreen.showEndScreen(mainScreenWindow, inGameBarPanel, questionPanel, questionImagePanel, answerPanel, nextButton, scoreCount, quizTimer);
                }
                if (fragenIndex == 10) {
                    nextButton.setText("Quiz-Ende >");
                } else {
                    nextButton.setText("überspringen >");
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
                    scoreLabel.setText("Punkte: " + scoreCount);
                    gameSoundEffect.setFile(correctAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    chosenAnswer.setBackground(Color.GREEN);
                    
                    if (fragenIndex == 10) {
                        nextButton.setText("Quiz-Ende >");
                    } else {
                        nextButton.setText("nächste Frage >");
                    }
                    
                } else {
                    quizTimer.stop();
                    gameSoundEffect.setFile(wrongAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    chosenAnswer.setBackground(Color.RED);
                    switch (fragen.get(fragenIndex-1).getRichtigeAntwort()) {
                        case 'A':answerAButton.setBackground(Color.GREEN);
                            break;
                        case 'B':answerBButton.setBackground(Color.GREEN);
                            break;
                        case 'C':answerCButton.setBackground(Color.GREEN);
                            break;
                        case 'D':answerDButton.setBackground(Color.GREEN);
                            break;
                        default:
                            break;
                    }

                    if (fragenIndex == 10) {
                        nextButton.setText("Quiz-Ende >");
                    } else {
                        nextButton.setText("nächste Frage >");
                    }
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
       /*  backgroundImage = new ImageIcon(".//res//quiz/background.jpg");
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT, Image.SCALE_DEFAULT));
        backgroundPanel = new JPanel();
        backgroundPanel.setBackground(background);
        backgroundPanel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(background);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        backgroundPanel.add(backgroundLabel);
        mainScreenWindow.add(backgroundPanel);*/
    }
    
    //quiz countdown
    public static void quizCountdown(ArrayList<Frage> fragen) {
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;

                if (second == 0) {
                    quizTimer.stop();
                    switch (fragen.get(fragenIndex-1).getRichtigeAntwort()) {
                        case 'A':answerAButton.setBackground(Color.GREEN);
                            break;
                        case 'B':answerBButton.setBackground(Color.GREEN);
                            break;
                        case 'C':answerCButton.setBackground(Color.GREEN);
                            break;
                        case 'D':answerDButton.setBackground(Color.GREEN);
                            break;
                        default:
                            break;
                    }

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
}
