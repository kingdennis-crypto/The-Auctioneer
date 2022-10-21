package app.repositories;

import app.models.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
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
}
