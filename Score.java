import java.io.Serializable;
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	private int score;
	private String naam;

	public int getScore() {
		return score;
	}

	public String getNaam() {
	    return naam;
	}

	public Score(String naam, int score) {
	    this.score = score;
	    this.naam = naam;
	}
}