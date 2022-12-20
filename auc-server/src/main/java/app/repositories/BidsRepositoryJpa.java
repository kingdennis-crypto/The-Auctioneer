package app.repositories;

import app.models.Bid;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BidsRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    public BidsRepositoryJpa() {
        super();
    }

    /**
     * Methhod to retrieve all offers
     *
     * @return a list of offers
     */
    public List<Bid> findAll() {
        TypedQuery<Bid> query =
                entityManager.createQuery(
                        "SELECT bid from Bid bid", Bid.class);

        return query.getResultList();
    }

    /**
     * Method to find an Offer by the id
     *
     * @param id the offer to find
     * @return the instance of the offer
     */
    public Bid findById(long id) {
        return entityManager.find(Bid.class, id);
    }

    /**
     * Method to save an offer
     *
     * @param entity an instance of Offer to save
     * @return the saved offer
     */
    public Bid save(Bid entity) {
        return entityManager.merge(entity);
    }

    /**
     * Delete the offer from the repository
     *
     * @param id the offer to be deleted
     * @return an instance of the deleted offer
     */
    public Bid deleteById(long id) {
        Bid bid = findById(id);
        entityManager.remove(bid);
        return bid;
    }
}
