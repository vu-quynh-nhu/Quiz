import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Diese Klasse ist für den In Game Screen
public class InGameScreen {
    //Deklarierung aller GUI-Komponente und Variablen
    static JPanel quizLogoPanel, inGameBarPanel, questionPanel, answerPanel, questionImagePanel, nextButtonPanel, inGameBackgroundPanel;
    static JLabel quizLogoLabel, questionLabel, questionNumberLabel, scoreLabel, timerLabel, questionImageLabel, inGameBackgroundLabel;
    static ImageIcon questionImageIcon, inGameBackgroundImage;
    static JButton answerAButton, answerBButton, answerCButton, answerDButton, nextButton;
    static int fragenIndex = 1;
    static int second;
    static int scoreCount;
    static Timer quizTimer;
    static GameSoundEffect gameSoundEffect = new GameSoundEffect();

    //Methode zum Setzen des Fragenindex
    public static void setFragenIndex(int fragenIndex) {
        InGameScreen.fragenIndex = fragenIndex;
    }

    //Methode zum anzeigen des In Game Screens
    public static void showInGameScreen(JFrame mainScreenWindow, JPanel gameCountdownPanel, ArrayList<Frage> fragen) {
        //benutzerdefinierte Farben
        Color quizImageBorder = new Color(161, 36, 203);
        Color answerText = new Color(122, 31, 182);

        //Dekativierung des letzten Screens (Countdown Screen)
        CountdownScreen.countdownBackgroundPanel.setVisible(false);
        gameCountdownPanel.setVisible(false);

        //Punkte am Anfang auf null setzen
        scoreCount = 0;

        //oberer Container für momentane Frage, Punkte und Zeit wird erstellt
        inGameBarPanel = new JPanel();
        //FragenLabel wird erstellt und zentriert
        String questionNumString = "Frage " + fragenIndex + " / " + fragen.size();
        questionNumberLabel = new JLabel(questionNumString, SwingConstants.CENTER);
        //Punkte Label wird erstellt und zentriert
        scoreLabel = new JLabel("Punkte: " + scoreCount, SwingConstants.CENTER);
        //Timer Label wird erstellt und zentriert
        timerLabel = new JLabel("Zeit: 30", SwingConstants.CENTER);

        //Container und Label für die Quiz-Frage wird erstellt
        questionPanel = new JPanel();
        questionLabel = new JLabel(fragen.get(fragenIndex-1).getFrage(), SwingConstants.CENTER);

        //Container und Label für das Quiz-Bild wird erstellt
        questionImagePanel = new JPanel();
        questionImageLabel = new JLabel();
        //Quiz-Bild wird mit dem akutellem Quizbild inialisiert
        questionImageIcon = new ImageIcon(fragen.get(fragenIndex-1).getImage());

        //Container und Buttons für die Quiz-Antworten werden erstellt
        answerPanel = new JPanel();
        //Antwort Buttons mit den jeweiligen Antworten initialisieren 
        answerAButton = new JButton(fragen.get(fragenIndex-1).getAntwortA());
        answerBButton = new JButton(fragen.get(fragenIndex-1).getAntwortB());
        answerCButton = new JButton(fragen.get(fragenIndex-1).getAntwortC());
        answerDButton = new JButton(fragen.get(fragenIndex-1).getAntwortD());

        //Container und Button zum weiterklicken der nächsten Frage werden erstellt
        nextButtonPanel = new JPanel();
        nextButton = new JButton("überspringen >");

        //benutzerdefinierte Schriftart
        try {
            Font archivoQuestion = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(20f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //benutzerdefinierte Schriftart für die Komponenten setzen
            questionNumberLabel.setFont(archivoQuestion);
            scoreLabel.setFont(archivoQuestion);
            timerLabel.setFont(archivoQuestion);
            //für den Question Label wird die Schriftgröße auf 24 gesetzt
            questionLabel.setFont(new Font(questionLabel.getFont().getName(), questionLabel.getFont().getStyle(), 24));
            answerAButton.setFont(archivoQuestion);
            answerBButton.setFont(archivoQuestion);
            answerCButton.setFont(archivoQuestion);
            answerDButton.setFont(archivoQuestion);
            nextButton.setFont(archivoQuestion);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }

        //alle relevanten Komponente werden hier angepasst (Größe, Farbe, Text, etc.)
        inGameBarPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (970 / 2), 0, 970, 50);
        inGameBarPanel.setOpaque(false);
        //der Container bekommt ein Tabellenlayout mit einer Zeile und drei Spalten inialisiert
        inGameBarPanel.setLayout(new GridLayout(1, 3));
        mainScreenWindow.getContentPane().add(inGameBarPanel);

        questionNumberLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        timerLabel.setForeground(Color.WHITE);

        //Der Timer wird auf 30 Sekunden gesetzt und gestartet
        second = 30;
        quizCountdown(fragen);
        quizTimer.start();

        //Dem Container werden die drei Labels hinzugefügt
        inGameBarPanel.add(questionNumberLabel);
        inGameBarPanel.add(scoreLabel);
        inGameBarPanel.add(timerLabel);
        
        //Für jedenContainer werden die Attribute gesetzt(Unsichtbar,Position, Breite, Höhe, etc.)
        //und zum JFrame hinzugefügt
        questionPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (900 / 2), 90, 900, 40);
        questionPanel.setOpaque(false);
        mainScreenWindow.getContentPane().add(questionPanel);
        
        questionLabel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (800 / 2), 90, 800, 40);
        questionLabel.setOpaque(false);
        questionLabel.setForeground(Color.WHITE);
        questionPanel.add(questionLabel);

        questionImagePanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (420 / 2), 140, 420, 228);
        questionImagePanel.setBackground(quizImageBorder);
        mainScreenWindow.getContentPane().add(questionImagePanel);
        questionImagePanel.add(questionImageLabel);
        questionImageIcon.setImage(questionImageIcon.getImage().getScaledInstance(410, 218, Image.SCALE_DEFAULT));
        questionImageLabel.setIcon(questionImageIcon);

        mainScreenWindow.getContentPane().add(answerPanel);
        answerPanel.setBackground(Color.BLACK);
        answerPanel.setOpaque(false);
        answerPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (800 / 2), 420, 800, 150);

        //Für jeden AntwortButton werden die Attribute: Größe,Textfarbe,Hintergrundfarbe,Rand gesetzt
        //und zum Container hinzugefügt
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
        
        //Event-Handling für alle Antwort Buttons
        //Wenn die Maus die Buttons betritt, so ändert sich der Cursor
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

        //Für den Container und den Button werden die Attribute gesetzt
        //wie Position, Breite, Höhe, Textfarbe etc.
        nextButtonPanel.setOpaque(false);
        nextButtonPanel.setBounds(760, 585, 200, 50);
        mainScreenWindow.add(nextButtonPanel);

        nextButton.setBounds(760, 585, 200, 50);
        nextButton.setBorder(null);
        nextButton.setFocusPainted(false);
        nextButton.setForeground(Color.WHITE);
        nextButton.setContentAreaFilled(false);
        nextButtonPanel.add(nextButton);

        //Event-Handling für den next button
        //Action Listener am Button hinzugefügt
        nextButton.addActionListener(new ActionListener() {
            //in dieser Metode wird das Verhalten des next Buttons definiert
            @Override
            public void actionPerformed(ActionEvent e) {
                //Bedingung, solange Fragenindex kleiner als 10 ist
                if (fragenIndex < fragen.size()) {
                    //index wird erhöht
                    fragenIndex++;
                    //aktuelle Anzahl der Fragen wird im Label angepasst
                    String questionNumString = "Frage " + fragenIndex + " / " + fragen.size();
                    questionNumberLabel.setText(questionNumString);

                    //Fragen und Antworten werden mit dem aktuellen index geholt und 
                    //als Text für den Frage Label und für die Antwort Buttons gesetzt 
                    questionLabel.setText(fragen.get(fragenIndex-1).getFrage());
                    answerAButton.setText(fragen.get(fragenIndex-1).getAntwortA());
                    answerBButton.setText(fragen.get(fragenIndex-1).getAntwortB());
                    answerCButton.setText(fragen.get(fragenIndex-1).getAntwortC());
                    answerDButton.setText(fragen.get(fragenIndex-1).getAntwortD());

                    //neues Bild wird aus der Liste geholt 
                    String path = fragen.get(fragenIndex-1).getImage();
                    questionImageIcon.setImage(new ImageIcon(path).getImage());
                    //vorheriges Bild wird von Label entfernt und das neue angezeigt
                    questionImageLabel.repaint();

                    //Antwort Buttons werden wieder aktivieren 
                    answerAButton.setEnabled(true);
                    answerBButton.setEnabled(true);    
                    answerCButton.setEnabled(true);
                    answerDButton.setEnabled(true);

                    //Schriftfarbe bei allen Buttons auf weiß gesetzt 
                    answerAButton.setBackground(Color.WHITE);
                    answerBButton.setBackground(Color.WHITE);
                    answerCButton.setBackground(Color.WHITE);
                    answerDButton.setBackground(Color.WHITE);
                    //Sekunden vom Spiel Countdown auf 30 Sekunden setzen 
                    second = 30;
                    //Zeit an das Label setzen
                    timerLabel.setText("Zeit: " + second);
                    //Countdwon starten
                    quizTimer.start();
                    //setze Text vom next Button auf überspringen
                    nextButton.setText("überspringen >");
                } else {
                    //wenn der Fragenindex gleich 10 ist, wird der End-Bildschirm angezeigt
                    EndScreen.showEndScreen(mainScreenWindow, inGameBarPanel, questionPanel, questionImagePanel, answerPanel, nextButtonPanel, scoreCount, quizTimer);
                }

                if (fragenIndex == fragen.size()) {
                    nextButton.setText("Quiz-Ende >");
                }
            }
        });

        //Event-Handling für den next button panel
        //wenn die Maus den Panel vom nextButton betritt, so ändert sich der Cursor
        nextButtonPanel.addMouseListener(new MouseListener() {
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

        //Richtige und falsche Antwort
        //Neuen ActionListener erstellen
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //den Button bekommen, der geklickt wurde
                JButton chosenAnswer = (JButton) e.getSource();
                //Soundeffekt-Pfade
                String correctAnswerSoundEffect = ".//sounds//correct.wav";
                String wrongAnswerSoundEffect = ".//sounds//wrong.wav";
                //String für die Richtige Antwort
                String correctAnswer = "";

                //richtige Antwort von der aktuellen Frage in den String correctAnswer setzen
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

                //Bedingung: Vergleich des Textes des angeklickten Buttons mit der richtigen Antwort der aktuellen Frage
                //wenn die richtige Antwortmöglichkeit ausgewählt wurde 
                if (chosenAnswer.getText().equals(correctAnswer)) {
                    //Timer wird gestoppt
                    quizTimer.stop();
                    //Die Punkte werden um eins erhöht und in den Label gesetzt
                    scoreCount++;
                    scoreLabel.setText("Punkte: " + scoreCount);
                    //der passende Soundeffekt wird abgespielt
                    gameSoundEffect.setFile(correctAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    //Die Farbe des Buttons wird auf grün gesetzt 
                    chosenAnswer.setBackground(Color.GREEN);
                    //Veränderung des next Button Textes, je nach index
                    if (fragenIndex == fragen.size()) {
                        nextButton.setText("Quiz-Ende >");
                    } else {
                        nextButton.setText("nächste Frage >");
                    }
                //wenn die falsche Antwortmöglichkeit ausgewählt wurde    
                } else {
                    //Timer wird gestoppt
                    quizTimer.stop();
                    //der passende Soundeffekt wird abgespielt
                    gameSoundEffect.setFile(wrongAnswerSoundEffect);
                    gameSoundEffect.startSoundEffect();
                    //Die Farbe des Buttons wird auf rot gesetzt 
                    chosenAnswer.setBackground(Color.RED);

                    //Der button mit der richtigen Antwort wird angezeigt und die Farbe auf grün gesetzt
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
                    //Veränderung des next Button textes, je nach index
                    if (fragenIndex == fragen.size()) {
                        nextButton.setText("Quiz-Ende >");
                    } else {
                        nextButton.setText("nächste Frage >");
                    }

                }
                //Die Buttons werden deaktiviert
                answerAButton.setEnabled(false);
                answerBButton.setEnabled(false);    
                answerCButton.setEnabled(false);
                answerDButton.setEnabled(false);
            }
        };

        //an allen Antwort Buttons wird ein Aktion Listener hinzugefügt und der erstelle Action Listener übergeben
        answerAButton.addActionListener(actionListener);
        answerBButton.addActionListener(actionListener);
        answerCButton.addActionListener(actionListener);
        answerDButton.addActionListener(actionListener);

        //Der Hintergrund wird gesetzt
        inGameBackgroundImage = new ImageIcon(".//res//quiz/background.jpg");
        inGameBackgroundImage.setImage(inGameBackgroundImage.getImage().getScaledInstance(QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT , Image.SCALE_DEFAULT));
        inGameBackgroundPanel = new JPanel();
        inGameBackgroundPanel.setBackground(QuizUtils.backgroundColor);
        inGameBackgroundPanel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        inGameBackgroundLabel = new JLabel();
        inGameBackgroundLabel.setBackground(QuizUtils.backgroundColor);
        inGameBackgroundLabel.setIcon(inGameBackgroundImage);
        inGameBackgroundLabel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
        inGameBackgroundPanel.add(inGameBackgroundLabel);
        mainScreenWindow.add(inGameBackgroundPanel); 
    }
    
    //quiz countdown
    public static void quizCountdown(ArrayList<Frage> fragen) {
        //Der Timer wird jede Sekunde aktualisiert 
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Die sekunden werden um eins verringert
                second--;

                //Bedingung: Wenn die nullte Sekunde erreicht wird, wird die richtige Antwort angezeigt
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
                    //Veränderung des next Button Textes, je nach index
                    if (fragenIndex == fragen.size()) {
                        nextButton.setText("Quiz-Ende >");
                    } else {
                        nextButton.setText("nächste Frage >");
                    }
                    //Die Buttons werden deaktiviert
                    answerAButton.setEnabled(false);
                    answerBButton.setEnabled(false);    
                    answerCButton.setEnabled(false);
                    answerDButton.setEnabled(false);
                } 
                //Die Sekunden werden auf dem Label gesetzt
                timerLabel.setText("Zeit: " + second);
            }
        });
    }
}
