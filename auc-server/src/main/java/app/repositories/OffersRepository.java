package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OffersRepository {
    List<Offer> findAll();
}
