package app.rest;

import app.exceptions.NotFoundException;
import app.models.Bid;
import app.models.Offer;
import app.repositories.interfaces.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidsController {
    @Autowired
    private EntityRepository<Offer> offersRepo;

    @Autowired
    private EntityRepository<Bid> bidsRepo;

    @GetMapping("")
    public List<Bid> getBids() {
        return bidsRepo.findAll();
    }

    @GetMapping("{id}")
    public Bid getBid(@PathVariable long id) {
        Bid bid = bidsRepo.findById(id);

        if (bid == null) {
            throw new NotFoundException(String.format("Bid with ID: %d was not found", id));
        }

        return bid;
    }
}
