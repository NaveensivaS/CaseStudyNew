package service;

import DAOs.SearchDAOImpl;
import DTOs.Screen;

import javax.inject.Inject;
import java.util.List;

public class SearchService {

    @Inject
    SearchDAOImpl searchDAOImpl;

    public List<Screen> getScreen(String movie, String Multiplex){
        return searchDAOImpl.getScreen(movie,Multiplex);
    }
}
