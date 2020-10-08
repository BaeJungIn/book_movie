package movie;

public class MovieStore {
	private String moviename;
	private String day;
	private String time;
	private int moviepoint;
	
	public MovieStore(String moviename, String day, String time, int moviepoint) {
		this.moviename = moviename;
		this.day = day;
		this.time = time;
		this.moviepoint = moviepoint;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMoviepoint() {
		return moviepoint;
	}

	public void setMoviepoint(int moviepoint) {
		this.moviepoint = moviepoint;
	}
}
