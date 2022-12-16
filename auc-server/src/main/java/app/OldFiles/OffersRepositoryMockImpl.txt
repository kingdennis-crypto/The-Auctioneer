package app.repositories;

import app.models.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Repository
public class OffersRepositoryMockImpl implements OffersRepository {
    Offer offer1, offer2, offer3, offer4, offer5, offer6, offer7;
    private ArrayList<Offer> offers;

    public OffersRepositoryMockImpl() {
        offer1 = new Offer(30000);
        offer2 = new Offer(30003);
        offer3 = new Offer(30006);
        offer4 = new Offer(30009);
        offer5 = new Offer(30012);
        offer6 = new Offer(30015);
        offer7 = new Offer(30018);

        offers = new ArrayList<>(List.of(offer1, offer2, offer3, offer4, offer5, offer6, offer7));
    }

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
        Offer searchOffer = new Offer((int) id);

        for (Offer offer: offers) {
            if (offer.equals(searchOffer)) searchOffer = offer;
        }

        return searchOffer;
    }

    /**
     * Method to save an offer
     *
     * @param offer an instance of Offer to save
     * @return the saved offer
     */
    @Override
    public Offer save(Offer offer) {
        int index = offers.indexOf(offer);

        if (offer.getId() == 0) {
            long lastId = offers.get(offers.size() - 1).getId();

            offer.setId(lastId + 3);
        }

        if (index == -1) {
            offers.add(offer);
        } else {
            offers.set(index, offer);
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
        int index = offers.indexOf(new Offer((int) id));

        Offer offer = offers.get(index);

        offers.remove(index);

        return offer;
    }
}
