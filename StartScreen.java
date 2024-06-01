import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

//Diese Klasse ist für den StartScreen 
public class StartScreen {
    //Deklarierung aller GUI-Komponente 
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

    //Methode zum anzeigen des Start Screens
    public static void showStartScreen() {
        //benutzerdefinierte Farben
        Color backgroundColor = new Color(116, 30, 174);
        Color btnColor = new Color(161, 36, 203);

        //benutzerdefinierte Schriftart
        try {
            Font archivoHeader = Font.createFont(Font.TRUETYPE_FONT, new File( ".//fonts//Archivo-VariableFont_wdth,wght.ttf")).deriveFont(22f);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
            //benutzerdefinierte Schriftart für die Komponenten setzen
            headerLabel.setFont(archivoHeader);
            startButton.setFont(archivoHeader);
            //für den start button wird die Schriftgröße auf 22 gesetzt
            startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 22));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        //Das Fenter wird erstellt 
        mainScreenWindow = new JFrame("Quiz");
        //Die größe des Fensters
        mainScreenWindow.setSize(MAINSCREEN_WIDTH, MAINSCREEN_HEIGHT);
        //Zum schließen, minimieren und maximieren des Fensters
        mainScreenWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //den Hintergrund des Fensters setzen
        mainScreenWindow.getContentPane().setBackground(backgroundColor);
        //zum starten des Fensters in der Mitte des Bildschirms 
        mainScreenWindow.setLocationRelativeTo(null);
        //null, damit ein benutzerdefninertes Layout zu erstellen
        mainScreenWindow.setLayout(null);
        //Das Icon der Anwendung ändern
        gameIcon = new ImageIcon(".//res//quiz/quizIcon.png");
        mainScreenWindow.setIconImage(gameIcon.getImage());

        quizLogoPanel = new JPanel();
        quizLogoPanel.setBounds((MAINSCREEN_WIDTH / 2) - (QUIZLOGO_WIDTH / 2), 100, QUIZLOGO_WIDTH, QUIZLOGO_HEIGHT);
        quizLogoPanel.setBackground(backgroundColor);
        mainScreenWindow.getContentPane().add(quizLogoPanel);


        //STARTSCREEN
        quizLogoLabel = new JLabel();
        //Das Logo des Quizzes wird angezeigt
        quizLogoIcon = new ImageIcon(".//res//quiz/quizLogo.png");
        //Die Größe wird definiert
        quizLogoIcon.setImage(quizLogoIcon.getImage().getScaledInstance(QUIZLOGO_WIDTH - 10, QUIZLOGO_HEIGHT - 10, Image.SCALE_DEFAULT));
        quizLogoLabel.setIcon(quizLogoIcon);
        //Das Bild an das Panel hinzugefügt
        quizLogoPanel.add(quizLogoLabel);

        //Das Panel wird an das Fenster hinzugefügt
        mainScreenWindow.setVisible(true);

        //Die Größe des Buttons wird definiert
        Dimension sizeStartButton = startButton.getPreferredSize();
        startButton.setBounds((MAINSCREEN_WIDTH / 2) - ((int) sizeStartButton.getWidth() / 2),
                520, (int) sizeStartButton.getWidth(), (int) sizeStartButton.getHeight());
        //Die Farbe gesetzt
        startButton.setBackground(btnColor);
        //Der Button wird sichtbar gemacht
        startButton.setOpaque(true);
        //Der Text des Buttons wird auf weiß gesetzt
        startButton.setForeground(Color.WHITE);
        //Der Rand des Buttons wird entfernt
        startButton.setBorder(null);
        //Der Rand beim klicken des Buttons wird entfernt
        startButton.setFocusPainted(false);
        //Der blaue Hintergrund beim klicken des Buttons wird entfernt 
        startButton.setContentAreaFilled(false);
        //Der Button wird an das Fenster hinzugefügt
        mainScreenWindow.add(startButton);

        //Event-Handling für den start button
        /*Wenn die Maus den Start Button betritt, so ändert sich der Cursor. 
         * Die Farbe des Textes des Buttons änder sich beim betreten, verlassen, klicken, drücken und loslassen des Buttons */
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
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        //Wenn der start button geklickt wird, soll der Category Screen angezeigt werden
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryScreen.showCategoryScreen(mainScreenWindow, quizLogoPanel);
            }
        });
    }
}
