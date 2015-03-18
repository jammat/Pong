import java.util.*;
import java.io.*;

public class HSManager {
    private ArrayList<Score> scores;
    public static final String HUIPPUTULOKSET_YKSINPELI = Panel.BASEPATH + "//yksinpelitulokset.dat"; // tulokset tallennetaan tiedostoon
    public static final String HUIPPUTULOKSET_KAKSINPELI = Panel.BASEPATH + "//kaksinpelitulokset.dat";

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
    
    public ArrayList<Score> getScoresYksinpeli() {
        loadScoreFileYksinpeli();
        sort();
        return scores;
    }
    
    public ArrayList<Score> getScoresKaksinpeli() {
        loadScoreFileKaksinpeli();
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
        	if (GameStateManager.lastState == 1) {
        		inputStream = new ObjectInputStream(new FileInputStream(HUIPPUTULOKSET_KAKSINPELI));
        	} else {
        		inputStream = new ObjectInputStream(new FileInputStream(HUIPPUTULOKSET_YKSINPELI));
        	}
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
    
    public void loadScoreFileYksinpeli() {
    	try {
        	inputStream = new ObjectInputStream(new FileInputStream(HUIPPUTULOKSET_YKSINPELI));
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
    
    public void loadScoreFileKaksinpeli() {
    	try {
        	inputStream = new ObjectInputStream(new FileInputStream(HUIPPUTULOKSET_KAKSINPELI));
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
        	if (GameStateManager.lastState == 1) {
        		outputStream = new ObjectOutputStream(new FileOutputStream(HUIPPUTULOKSET_KAKSINPELI));
        	} else {
        		outputStream = new ObjectOutputStream(new FileOutputStream(HUIPPUTULOKSET_YKSINPELI));
        	}
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei lšydy, luodaan uusi tiedosto");
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
}