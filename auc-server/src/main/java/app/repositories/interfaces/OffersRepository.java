package app.repositories.interfaces;

import app.models.Offer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OffersRepository {
    /**
     * Methhod to retrieve all offers
     * @return a list of offers
     */
    List<Offer> findAll();

    /**
     * Method to find an Offer by the id
     * @param id the offer to find
     * @return the instance of the offer
     */
    Offer findById(long id);

    /**
     * Method to save an offer
     * @param offer an instance of Offer to save
     * @return the saved offer
     */
    Offer save(Offer offer);

    /**
     * Delete the offer from the repository
     * @param id the offer to be deleted
     * @return an instance of the deleted offer
     */
    Offer deleteById(long id);
}
