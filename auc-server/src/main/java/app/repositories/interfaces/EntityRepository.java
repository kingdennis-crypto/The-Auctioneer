package app.repositories.interfaces;

import app.repositories.interfaces.Identifiable;

import java.util.List;

public interface EntityRepository<E extends Identifiable> {

    /**
     * Methhod to retrieve all offers
     * @return a list of offers
     */
    List<E> findAll();

    /**
     * Method to find an Offer by the id
     * @param id the offer to find
     * @return the instance of the offer
     */
    E findById(long id);

    /**
     * Method to save an offer
     * @param entity an instance of Offer to save
     * @return the saved offer
     */
    E save(E entity);

    /**
     * Delete the offer from the repository
     * @param id the offer to be deleted
     * @return an instance of the deleted offer
     */
    E deleteById(long id);

    /**
     *
     * @param jpqlName
     * @param params
     * @return
     */
    List<E> findByQuery(String jpqlName, Object ... params);
}
