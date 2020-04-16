package DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer ScreenNumber;
    @ManyToOne
    private Multiplex multiplex;
    @ManyToOne
   private Movie movie;


    public Screen() {
    }

    public Screen(Integer id, Integer screenNumber, Multiplex multiplex, Movie movie) {
        this.id = id;
        ScreenNumber = screenNumber;
        this.multiplex = multiplex;
        this.movie = movie;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScreenNumber() {
        return ScreenNumber;
    }

    public void setScreenNumber(Integer screenNumber) {
        ScreenNumber = screenNumber;
    }

    public Multiplex getMultiplex() {
        return multiplex;
    }

    public void setMultiplex(Multiplex multiplex) {
        this.multiplex = multiplex;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

  }
