package service;

import DTOs.Screen;
import DAOs.MovieDAOImpl;
import DAOs.MultiplexDAOImpl;
import DAOs.ScreenDAOImpl;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ScreenService {

    @Inject
    ScreenDAOImpl screenDAOImpl;
    @Inject
    MovieDAOImpl movieDAOImpl;
    @Inject
    MultiplexDAOImpl multiplexDAOImpl;

    public Screen getScreenByMultiplexAndScreenNumber(Integer multiplexID, Integer screenNumber) {
        return screenDAOImpl.getScreenByMultiplexAndScreenNumber(multiplexID, screenNumber);
    }

    public void addScreen(Screen screen) {
        screenDAOImpl.add(screen);
    }

    public void delete(Integer movieID, Integer multiplexID, Integer screenNumber) {
        screenDAOImpl.delete(movieID, multiplexID, screenNumber);
    }

}
