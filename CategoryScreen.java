import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Diese Klasse ist für den Category Screen
public class CategoryScreen {
        //Deklarierung aller GUI-Komponenten und der Fragen-Liste
        static JPanel choicePanel, choiceLabelPanel, categoryBackgroundPanel;
        static JLabel choiceLabel, choice1label, choice2label, choice3label, choice4label, categoryBackgroundLabel;
        static ImageIcon quizLogoIcon, gameIcon, questionImageIcon, medalImage, categoryBackgroundImage;
        static JButton choice1Button, choice2Button, choice3Button, choice4Button;
        static ArrayList<Frage> fragen = new ArrayList<Frage>();

        //Methode zum Anzeigen des Category Screen
        public static void showCategoryScreen(JFrame mainScreenWindow, JPanel quizLogoPanel) {
                //Inialisierung der Farbe
                Color cultureText = new Color(70, 159, 194);

                //Komponenten des voherigen Screens unsichtbar setzten
                quizLogoPanel.setVisible(false);
                StartScreen.startButton.setVisible(false);

                //Erstellung des Container für der Auswahlbuttons und die Buttons
                choicePanel = new JPanel();
                choice1label = new JLabel();
                choice2label = new JLabel();
                choice3label = new JLabel();
                choice4label = new JLabel();

                //Erstellung des Container für die Überschrift und das Label der Überschrift
                choiceLabelPanel = new JPanel();
                choiceLabel = new JLabel("Wähle eine Quiz Kategorie:");

                //Setzung der Buttons mit ihrem jeweiligen Bild
                ImageIcon food = new ImageIcon(".//res//foodQuestions/food-choice.jpg");
                food.setImage(food.getImage().getScaledInstance(350, 210, Image.SCALE_DEFAULT));
                choice1Button = new JButton(food);

                ImageIcon harryPotter = new ImageIcon(".//res//harryQuestions/harry-choice.jpg");
                harryPotter.setImage(harryPotter.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT));
                choice2Button = new JButton(harryPotter);

                ImageIcon geography = new ImageIcon(".//res//geoQuestions/geography-choice.jpg");
                geography.setImage(geography.getImage().getScaledInstance(350, 200,Image.SCALE_DEFAULT));
                choice3Button = new JButton(geography);

                ImageIcon culture = new ImageIcon(".//res//cultureQuestions/culture-choice.jpg");
                culture.setImage(culture.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT));
                choice4Button = new JButton(culture);

                //Schriftart anpassen für Label und Buttons
                try {
                        Font archivoQuestion = Font
                                        .createFont(Font.TRUETYPE_FONT,
                                                        new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf"))
                                        .deriveFont(27f);
                        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        graphicsEnvironment.registerFont(
                                        Font.createFont(Font.TRUETYPE_FONT,
                                                        new File(".//fonts//Archivo-VariableFont_wdth,wght.ttf")));
                        choiceLabel.setFont(archivoQuestion);
                        choiceLabel.setFont(new Font(choiceLabel.getFont().getName(), choiceLabel.getFont().getStyle(),
                                        33));
                        choice1Button.setFont(archivoQuestion);
                        choice2Button.setFont(archivoQuestion);
                        choice1label.setFont(archivoQuestion);
                        choice2label.setFont(archivoQuestion);
                        choice3label.setFont(archivoQuestion);
                        choice4label.setFont(archivoQuestion);
                        choice3Button.setFont(archivoQuestion);
                        choice4Button.setFont(archivoQuestion);
                } catch (FontFormatException | IOException ex) {
                        ex.printStackTrace();
                }

                //Anpassungen bei Contaniern und zum Fenster hinzufügen
                choiceLabelPanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (600 / 2), 80, 600, 50);
                choiceLabelPanel.setOpaque(false);
                mainScreenWindow.add(choiceLabelPanel);

                choiceLabel.setForeground(Color.WHITE);
                choiceLabelPanel.add(choiceLabel);

                choicePanel.setBounds((QuizUtils.MAINSCREEN_WIDTH  / 2) - (800 / 2), QuizUtils.MAINSCREEN_HEIGHT  / 2 - (315 / 2), 800, 315);
                choicePanel.setOpaque(false);

                //für jeweils jeden Button Größe,Breite und Rand anpassen und zum Container choicePanel hinzufügen
                //Label mit Text erstellten und Schriftfarbe auf weiß setzen und zum Button hinzufügen
                choice1Button.setPreferredSize(new Dimension(350, 150));
                choice1Button.setBorder(null);
                choice1Button.setFocusPainted(false);
                choicePanel.add(choice1Button);

                choice1label.setText("Essens Quiz");
                choice1label.setForeground(Color.WHITE);
                choice1label.setAlignmentX(0.5f);
                choice1Button.add(choice1label);

                choice2Button.setPreferredSize(new Dimension(350, 150));
                choice2Button.setBorder(null);
                choice2Button.setFocusPainted(false);
                choicePanel.add(choice2Button);

                choice2label.setText("Harry Potter Quiz");
                choice2label.setForeground(Color.WHITE);
                choice2label.setAlignmentX(0.5f);
                choice2label.setAlignmentY(0.9f);
                choice2Button.add(choice2label);

                choice3Button.setPreferredSize(new Dimension(350, 150));
                choice3Button.setBorder(null);
                choice3Button.setFocusPainted(false);
                choicePanel.add(choice3Button);

                choice3label.setText("Geografie Quiz");
                choice3label.setForeground(Color.BLACK);
                choice3label.setAlignmentX(0.5f);
                choice3Button.add(choice3label);

                choice4Button.setPreferredSize(new Dimension(350, 150));
                choice4Button.setBorder(null);
                choice4Button.setFocusPainted(false);
                choicePanel.add(choice4Button);

                choice4label.setText("Kultur Quiz");
                choice4label.setForeground(cultureText);
                choice4label.setAlignmentX(0.07f);
                choice4Button.add(choice4label);

                //fügt den Container mit der Auswahl den Fenster hinzu
                mainScreenWindow.add(choicePanel);

                //für jeden Button ActionListener hinzufügen, der beim Anklicken die Fragen inialisiert
                //und den CoundownScreen anzeigt
                choice1Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fragenInialisieren(1);
                                CountdownScreen.showCountdownScreen(mainScreenWindow, categoryBackgroundPanel, choiceLabelPanel, choicePanel,
                                                fragen);
                        }
                });

                choice2Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fragenInialisieren(2);
                                CountdownScreen.showCountdownScreen(mainScreenWindow, categoryBackgroundPanel,choiceLabelPanel, choicePanel,
                                                fragen);

                        }
                });

                choice3Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fragenInialisieren(3);
                                CountdownScreen.showCountdownScreen(mainScreenWindow,categoryBackgroundPanel, choiceLabelPanel, choicePanel,
                                                fragen);
                        }
                });

                choice4Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fragenInialisieren(4);
                                CountdownScreen.showCountdownScreen(mainScreenWindow, categoryBackgroundPanel,choiceLabelPanel, choicePanel,
                                                fragen);
                        }
                });
                //fügt jeden Button den HoverEffekt hinzu (durch Methodenaufruf)
                addHoverEffect(choice1Button);
                addHoverEffect(choice2Button);
                addHoverEffect(choice3Button);
                addHoverEffect(choice4Button);

                //passt den Hintergrund des Screens an und fügt diesen den Fenster hinzu
                categoryBackgroundImage = new ImageIcon(".//res//quiz/background.jpg");
                categoryBackgroundImage.setImage(categoryBackgroundImage.getImage().getScaledInstance(QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT , Image.SCALE_DEFAULT));
                categoryBackgroundPanel = new JPanel();
                categoryBackgroundPanel.setBackground(QuizUtils.backgroundColor);
                categoryBackgroundPanel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
                categoryBackgroundLabel = new JLabel();
                categoryBackgroundLabel.setBackground(QuizUtils.backgroundColor);
                categoryBackgroundLabel.setIcon(categoryBackgroundImage);
                categoryBackgroundLabel.setBounds(0, 0, QuizUtils.MAINSCREEN_WIDTH , QuizUtils.MAINSCREEN_HEIGHT );
                categoryBackgroundPanel.add(categoryBackgroundLabel);
                mainScreenWindow.add(categoryBackgroundPanel); 
        }

        //inialisiert die Liste mit der ausgewählten Kategorie
        public static void fragenInialisieren(int zahl) {
                // Essen Quiz
                ArrayList<Frage> essensFragen = new ArrayList<Frage>();
                essensFragen.add(new Frage("Wie heißt dieser Samen?", "Leinsamen", "Chiasamen", "Flohsamen", "Quinoa",
                                ".//res//foodQuestions/chiasamen.png", 'B'));
                essensFragen.add(new Frage("Aus welchem Land stammt dieses Gericht - Shakshuka?", "Italien",
                                "Deutschland",
                                "Türkei", "Israel", ".//res//foodQuestions/shakshuka.png", 'D'));
                essensFragen.add(new Frage("Wie heißt das cremefarbene Gemüse auf der Pizza?", "Lauch", "Artischocken",
                                "Blumenkohl", "Champignos", ".//res//foodQuestions/pizza.png", 'B'));
                essensFragen.add(new Frage("Welches Obst ist botanisch gesehen ein Gemüse?", "Rhabarber", "Kiwi",
                                "Erdberre",
                                "Hagebutte", ".//res//foodQuestions/gemüse.png", 'A'));
                essensFragen.add(new Frage("Was verbirgt sich hinter dem Gericht Kalakukko?", "Japanische Fischsuppe",
                                "Sushi mit Thunfisch", "Fisch im Teigmantel", "Fisch in Kohlmantel",
                                ".//res//foodQuestions/japan.png",
                                'C'));
                essensFragen.add(new Frage("Haggis gilt als umstrittene Delikatesse in welchem Land?", "Norwegen",
                                "Island",
                                "Schottland", "Irland", ".//res//foodQuestions/haggis.png", 'C'));
                essensFragen.add(new Frage("Das Feijoada ist die Leibspeise vieler …?", "Tunesier", "Ukrainer", "Roler",
                                "Brasilianer", ".//res//foodQuestions/brasilien.png", 'D'));
                essensFragen.add(new Frage("Eine Pho bekommt man in welchen Land serviert?", "Vietnam", "Indonesien",
                                "Thailand", "Japan", ".//res//foodQuestions/pho.png", 'A'));
                essensFragen.add(new Frage("Welches Lebensmittel wird in Deutschland am vielfältiges hergestellt?",
                                "Käse",
                                "Wurst", "Brot", "Marmelade", ".//res//foodQuestions/brot.png", 'C'));
                essensFragen.add(new Frage("Wie nennt sich dieser Salat?", "Mangold", "Rucola", "Chicoree", "Endivien",
                                ".//res//foodQuestions/mangold.png", 'A'));

                // Harry Potter Quiz
                ArrayList<Frage> harryPotterFragen = new ArrayList<Frage>();
                harryPotterFragen.add(new Frage("Wie heißt der fast kopflose Geist von Hogwarts?", "Edmund Gruber",
                                "Fast Kopflose Nick", "Sir Patrick Delaney-Podmore", "Der Blutige Baron",
                                ".//res//harryQuestions/hausgeist.png", 'B'));
                harryPotterFragen.add(
                                new Frage("Welcher Zug bringt die Schüler nach Hogwarts?", "Deutsche Bahn",
                                                "Der Gleis 9 3/4-Express",
                                                "Dumbledore Express", "Der Hogwarts-Express",
                                                ".//res//harryQuestions/express.jpg", 'D'));
                harryPotterFragen.add(new Frage("Was ist Dobbys Lieblingskleidungsstück?", "Ein Schal", "Eine Socke",
                                "Ein Hut",
                                "Ein T-Shirt", ".//res//harryQuestions/dobby.jpg", 'B'));
                harryPotterFragen.add(new Frage("Welcher Ort ist auf diesem Bild zu sehen?", "Hogsmeade", "Hogwarts",
                                "Das Haus der Weasleys", "Die Winkelgasse", ".//res//harryQuestions/hogwarts.jpg",
                                'B'));
                harryPotterFragen.add(new Frage("Welcher Charakter ist auf dem Bild zu sehen?", "Argus Filch",
                                "Cornelius Fudge", "Rufus Scrimgeour", "Mundungus Fletcher",
                                ".//res//harryQuestions/filch.jpg", 'A'));
                harryPotterFragen.add(
                                new Frage("Wie heißt die Mutter von Draco Malfoy?", "Molly", "Bellatrix", "Narcissa",
                                                "Andromeda", ".//res//harryQuestions/narcissa.jpg", 'C'));
                harryPotterFragen.add(new Frage("Welcher Charakter ist auf dem Bild zu sehen?", "Albus Dumbledore",
                                "Rubeus Hagrid", "Severus Snape", "Lord Voldemort",
                                ".//res//harryQuestions/voldemort.jpg", 'D'));
                harryPotterFragen.add(
                                new Frage("Wer ist der beste Freund von Harry Potter?", "Ron Weasley", "Cedric Diggory",
                                                "Draco Malfoy", "Hermione", ".//res//harryQuestions/weasley.jpg", 'A'));
                harryPotterFragen.add(new Frage("Welche Position spielt Harry Potter im Quidditch-Team von Gryffindor?",
                                "Treiber", "Jäger", "Hüter", "Sucher", ".//res//harryQuestions/quidditch.jpg", 'D'));
                harryPotterFragen.add(new Frage("Was ist der Patronus von Harry Potter?", "Ein Hirsch", "Ein Drache",
                                "Ein Phönix", "Ein Wolf", ".//res//harryQuestions/patronus.jpg", 'A'));

                // Geografie Quiz
                ArrayList<Frage> geografieFragen = new ArrayList<Frage>();
                geografieFragen.add(
                                new Frage("Welcher Fluss ist der längste in Europa?", "Donau", "Oka", "Wolgau", "Don",
                                                ".//res//geoQuestions/fluss.jpeg", 'C'));
                geografieFragen.add(
                                new Frage("Wie hoch ist der Mount Everest?", "8.849 m", "8.545 m", "9.243 m", "8.763 m",
                                                ".//res//geoQuestions/mountEverst.jpeg", 'A'));
                geografieFragen.add(new Frage("Wie viele Ozeane gibt es auf der Welt?", "3", "4", "5", "6",
                                ".//res//geoQuestions/ozean.jpeg", 'C'));
                geografieFragen.add(new Frage("Welches Land hat die meisten Inseln?", "Indonesien", "Schweden", "USA",
                                "Philippinen", ".//res//geoQuestions/insel.jpeg", 'B'));
                geografieFragen.add(new Frage("Welches Land hat die meisten aktiven Vulkane?", "Indonesien", "Island",
                                "Japan",
                                "USA", ".//res//geoQuestions/vulkan.jpeg", 'C'));
                geografieFragen.add(
                                new Frage("In welchem Land befindet sich das Great Barrier Reef?", "Neuseeland", "USA",
                                                "Indien", "Australien", ".//res//geoQuestions/riff.jpeg", 'D'));
                geografieFragen.add(new Frage(
                                "In welchem Land befindet sich die größte Salzwüste der Welt, Salar de Uyuni?",
                                "Peru", "Bolivien", "Argentinien", "Chile", ".//res//geoQuestions/salzwüste.jpeg",
                                'C'));
                geografieFragen.add(
                                new Frage("In welchem Land befindet sich der Mount Kilimanjaro?", "Kenia", "Tansania",
                                                "Uganda", " Äthiopien", ".//res//geoQuestions/kilimanjaro.jpeg", 'B'));
                geografieFragen.add(new Frage("Welches Land hat die größte Bevölkerung auf der Welt?", "China",
                                "Indien", "USA",
                                "Russland", ".//res//geoQuestions/bewohner.jpeg", 'A'));
                geografieFragen.add(new Frage("Welches Land hat die meisten Zeitzone auf der Welt?", "Großbritannien",
                                "Russland", "USA", "Frankreich", ".//res//geoQuestions/zeitzonen.jpeg", 'D'));

                // Kultur Quiz
                ArrayList<Frage> cultureFragen = new ArrayList<Frage>();
                cultureFragen.add(
                                new Frage("Was ist Java?", "Donau", "Oka", "Wolgau", "Don",
                                                ".//res//javaQuestions/test.png", 'C'));

                switch (zahl) {
                        case 1:
                                fragen = essensFragen;
                                break;
                        case 2:
                                fragen = harryPotterFragen;
                                break;
                        case 3:
                                fragen = geografieFragen;
                                break;
                        case 4:
                                fragen = cultureFragen;
                                break;
                        default:
                                break;
                }
        }

        //Fügt den Hover-Effekt einen Button durch einen MouseAdapter hinzu
        private static void addHoverEffect(JButton button) {
                button.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                });
        }
}
