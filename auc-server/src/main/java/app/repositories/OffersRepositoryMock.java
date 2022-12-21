package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class OffersRepositoryMock implements OffersRepository {
    private final List<Offer> offers;

    public OffersRepositoryMock() {
        offers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Offer offer = Offer.createSampleOffer();
            offers.add(offer);
        }

    }

    /**
     * Methhod to retrieve all offers
     *
     * @return a list of offers
     */
    @Override
    public List<Offer> findAll() {
        return offers;
    }

    /**
     * Method to find an Offer by the id
     *
     * @param id the offer to find
     * @return the instance of the offer
     */
    @Override
    public Offer findById(long id) {
        return offers.stream()
                .filter(offer -> offer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to save an offer
     *
     * @param offer an instance of Offer to save
     * @return the saved offer
     */
    @Override
    public Offer save(Offer offer) {
        Offer existingOffer = findById(offer.getId());

        if (existingOffer == null) {
            offers.add(offer);
        } else {
            offers.set(offers.indexOf(existingOffer), offer);
        }

        return offer;
    }

    /**
     * Delete the offer from the repository
     *
     * @param id the offer to be deleted
     * @return an instance of the deleted offer
     */
    @Override
    public Offer deleteById(long id) {
        Offer searchedOffer = findById(id);

        offers.removeIf(offer -> offer.equals(searchedOffer));

        return searchedOffer;
    }
}
