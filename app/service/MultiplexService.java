package service;

import DTOs.Multiplex;
import DAOs.MultiplexDAOImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MultiplexService {
    @Inject
    MultiplexDAOImpl multiplexDAOImpl;

    public Multiplex getMultiplex(Integer id) {
        return multiplexDAOImpl.getByID(id);
    }

    public void deleteByID(Integer id){
        multiplexDAOImpl.deleteByID(id);
    }

    public List<Multiplex> getAllMultiplex() {
        return multiplexDAOImpl.list();
    }

    public Multiplex addOrUpdateMultiplex(Multiplex multiplex) {
        return multiplexDAOImpl.insertOrUpdate(multiplex);
    }
}
