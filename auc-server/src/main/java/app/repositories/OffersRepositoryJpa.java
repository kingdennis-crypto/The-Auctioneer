package app.repositories;

import app.models.Offer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Primary
@Repository
@Transactional
public class OffersRepositoryJpa implements EntityRepository<Offer>{

    @PersistenceContext
    private EntityManager entityManager;

    public OffersRepositoryJpa() {
        super();
    }

    /**
     * Methhod to retrieve all offers
     *
     * @return a list of offers
     */
    @Override
    public List<Offer> findAll() {
        TypedQuery<Offer> query =
                entityManager.createQuery(
                        "SELECT offer from Offer offer", Offer.class);

        return query.getResultList();
    }

    /**
     * Method to find an Offer by the id
     *
     * @param id the offer to find
     * @return the instance of the offer
     */
    @Override
    public Offer findById(long id) {
        return entityManager.find(Offer.class, id);
    }

    /**
     * Method to save an offer
     *
     * @param offer an instance of Offer to save
     * @return the saved offer
     */
    @Override
    public Offer save(Offer offer) {
        return entityManager.merge(offer);
    }

    /**
     * Delete the offer from the repository
     *
     * @param id the offer to be deleted
     * @return an instance of the deleted offer
     */
    @Override
    public Offer deleteById(long id) {
        Offer offer = findById(id);
        entityManager.remove(offer);

        return offer;
    }

    @Override
    public List<Offer> findByQuery(String jpqlName, Object... params) {
        return null;
    }
}
