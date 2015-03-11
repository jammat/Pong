import java.util.*;
import java.io.*;

public class HSManager {
    private ArrayList<Score> scores;
    public static final String HIGHSCORE_FILE = Panel.BASEPATH + "//tulokset.dat"; // tulokset tallennetaan tiedostoon

    // tarvitaan, jotta voidaan ladata ja tallentaa tuloksia tulokset.dat -tiedostoon
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HSManager() {
        scores = new ArrayList<Score>();
    }
    
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    
    private void sort() {
        HSComparator comparator = new HSComparator();
        Collections.sort(scores, comparator);
    }
    
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei lšydy: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Luokkaa ei lšydy: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }
    
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei lšydy: " + e.getMessage() + ", ohjelma tekee uuden tiedoston");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public String getHSString() {
        String hsString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            hsString += (i + 1) + ".\t" + scores.get(i).getNaam() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return hsString;
    }
}