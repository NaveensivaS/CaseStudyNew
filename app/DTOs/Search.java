package DTOs;

public class Search {
    public String Movie;
    public  String Multiplex;

    public Search(String movie, String multiplex) {
        Movie = movie;
        Multiplex = multiplex;
    }

    public Search() {
    }

    public String getMovie() {
        return Movie;
    }

    public void setMovie(String movie) {
        Movie = movie;
    }

    public String getMultiplex() {
        return Multiplex;
    }

    public void setMultiplex(String multiplex) {
        Multiplex = multiplex;
    }
}
