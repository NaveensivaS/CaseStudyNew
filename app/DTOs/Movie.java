package DTOs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    List<Screen> screens;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String movieName;
    private String url;
    private String category;
    private String producer;
    private String director;
    private String releaseDate;

    public Movie(List<Screen> screens, Integer id, String movieName, String url, String category, String producer, String director, String releaseDate) {
        this.screens = screens;
        this.id = id;
        this.movieName = movieName;
        this.url = url;
        this.category = category;
        this.producer = producer;
        this.director = director;
        this.releaseDate = releaseDate;

    }

    public Movie() {
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
