public class Frage {

    private String frage;
    private String[] antwort = new String[4];
    private String image;
    private char richtigeAntwort;

    public Frage(String frage, String antwortA, String antwortB, String antwortC, String antwortD, String image, char richtigeAntwort) {
        this.frage = frage;
        this.antwort[0] = antwortA;
        this.antwort[1] = antwortB;
        this.antwort[2] = antwortC;
        this.antwort[3] = antwortD;
        this.image = image;
        this.richtigeAntwort = richtigeAntwort;
    }

    public String getFrage() {
        return frage;
    }

    public String getAntwortA() {
        return antwort[0];
    }

    public String getAntwortB() {
        return antwort[1];
    }

    public String getAntwortC() {
        return antwort[2];
    }

    public String getAntwortD() {
        return antwort[3];
    }

    public String getImage() {
        return image;
    }

    public char getRichtigeAntwort() {
        return richtigeAntwort;
    }
}
