package app.rest;

import app.models.Offer;
import app.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    @Autowired
    private OffersRepository offersRepository;

    @GetMapping("test")
    public List<Offer> getTestOffers() {
        return offersRepository.findAll();

//        return List.of(
//                new Offer(30000),
//                new Offer(30003)
//        );
    }
}
