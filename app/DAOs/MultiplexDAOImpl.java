package DAOs;

import DTOs.Multiplex;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

@Singleton
@Transactional
public class MultiplexDAOImpl {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function) {
        return this.jpaApi.withTransaction(function);
    }

    public List<Multiplex> list() {
        return this.wrap(entityManager -> entityManager.createQuery("select m from Multiplex m ", Multiplex.class).getResultList());
    }

    public Multiplex insertOrUpdate(Multiplex multiplex) {
        return this.wrap(eM -> {
            if (multiplex.getId() == null) {
                eM.persist(multiplex);
            } else {
                eM.merge(multiplex);
            }
            return multiplex;
        });
    }

    public Multiplex getByID(Integer id) {
        return this.wrap(entityManager -> entityManager.createQuery("select m from Multiplex m where  m.id=" + id, Multiplex.class).getSingleResult());
    }

    public void deleteByID(Integer id) {
        this.wrap(entityManager -> entityManager.createQuery("delete from Multiplex m where m.id=" + id).executeUpdate());
    }

    public List<Multiplex> find(String searchString) {
        return this.wrap(entityManager -> entityManager.createQuery("select s from Multiplex s where lower(s.name) like lower(:searchString) and s.isDeleted=false", Multiplex.class)
                .setParameter("searchString", "%" + searchString + "%").getResultList());
    }

}
