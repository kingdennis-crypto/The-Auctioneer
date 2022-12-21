package app.rest;

import app.exceptions.BadRequest;
import app.exceptions.NotFoundException;
import app.exceptions.PreConditionFailed;
import app.models.Bid;
import app.models.Offer;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepository;
import app.repositories.OffersRepositoryJpa;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequestMapping("/offers")
public class OffersController {
    @Autowired
    private OffersRepositoryJpa offersRepo;

    @Autowired
    private BidsRepositoryJpa bidsRepository;

    @GetMapping("")
    public List<Offer> getAll() {
        return offersRepo.findAll();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Offer getOffer(@PathVariable long id) {
        Offer offer = offersRepo.findById(id);

        if (offer == null) {
            throw new NotFoundException(String.format("Offer with ID: %d was not found", id));
        }

        return offer;
    }

    @PostMapping("")
    public Offer create(@RequestBody Offer offer) {
        if (offer.getId() == 0) offer.setId(new Random().nextInt(40000, 50000));
        Offer savedOffer = offersRepo.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOffer.getId()).toUri();

        return ResponseEntity.created(location).body(savedOffer).getBody();
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Offer> delete(@PathVariable long id) {
        Offer deletedOffer = offersRepo.deleteById(id);

        if (deletedOffer == null) {
            throw new NotFoundException(String.format("Offer with ID: %d was not found", id));
        }

        return ResponseEntity.ok(deletedOffer);
    }

    @PutMapping(path = "{id}", produces = "application/json")
    public Offer update(@PathVariable long id, @RequestBody Offer toMergeOffer) {
        Offer existingOffer = offersRepo.findById(id);

        if (existingOffer == null) {
            throw new NotFoundException(String.format("Offer with ID: %d was not found", id));
        }

        if (existingOffer.getId() != toMergeOffer.getId()) {
            throw new PreConditionFailed(String.format("Offer with ID: %d does not match with parameter ID: %d", existingOffer.getId(), toMergeOffer.getId()));
        }

        return offersRepo.save(toMergeOffer);
    }

    @GetMapping("/summary")
    @JsonView(CustomOfferView.Summary.class)
    public List<Offer> getAllScootersSummary() {
        return offersRepo.findAll();
    }

    @PostMapping(value = "{id}/bids", produces = "application/json")
    public ResponseEntity<Bid> addBidToOffer(@PathVariable long id, @RequestBody Bid bid) {
        Offer offer = offersRepo.findById(id);

        if (offer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found");
        }

        System.out.println(offer.getValueHighestBid());

        boolean higherValue = offer.getValueHighestBid() > bid.getBidValue();
        boolean notForSale = offer.getStatus() == Offer.STATUS.FOR_SALE;

        if (higherValue || notForSale) {
            throw new PreConditionFailed(String.format("Bid with value=%f does not beat latest bid on offerId=%d", bid.getBidValue(), offer.getId()));
        }

        bid.associateOffer(offer);

        Bid savedBid = bidsRepository.save(bid);
        offersRepo.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBid.getId()).toUri();

        return ResponseEntity.created(location).body(savedBid);
    }
}
