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
    private OffersRepositoryJpa offersRepository;

    @Autowired
    private BidsRepositoryJpa bidsRepository;

    @GetMapping("")
    public List<Offer> getOffers(@RequestParam Optional<String> title, @RequestParam Optional<String> status, @RequestParam Optional<Double> minBidValue) {
        if (title.isEmpty() && status.isEmpty() && minBidValue.isEmpty()) return offersRepository.findAll();

        List<Offer> offers = new ArrayList<>();

        title.ifPresent(s -> offers.addAll(offersRepository.findByQuery("Offer_find_by_title", s)));

        if (status.isPresent()) {
            if (Stream.of(Offer.STATUS.values()).noneMatch(s -> s.name().equals(status.get()))) {
                throw new BadRequest(String.format("Status=%s is not a valid status", status.get()));
            }

            Offer.STATUS newStatus = Offer.STATUS.valueOf(status.get());

            if (minBidValue.isPresent()) {
                offers.addAll(offersRepository.findByQuery("Offer_find_by_status_and_minBidValue", newStatus, minBidValue.get()));

                return offers;
            }

            offers.addAll(offersRepository.findByQuery("Offer_find_by_status", newStatus));
        }

        if (title.isEmpty() && status.isEmpty()) {
            throw new BadRequest(String.format("Cannot handle you combination of request parameters of parameters title=, status= and minBidValue=%.2f", minBidValue.get()));
        }

        return offers;
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Offer getOffer(@PathVariable long id) throws ClassNotFoundException {
        Offer offer = offersRepository.findById(id);

        if (offer == null) {
            throw new ClassNotFoundException("id = " + id);
        }

        return offer;
    }

    @GetMapping(path = "{id}/bids", produces = "application/json")
    public List<Bid> getBidsOfOffer(@PathVariable long id) throws ClassNotFoundException {
        Offer offer = offersRepository.findById(id);

        if (offer == null) {
            throw new ClassNotFoundException("id = " + id);
        }

        return offer.getBids();
    }

    @PostMapping(value = "{id}/bids", produces = "application/json")
    public ResponseEntity<Bid> addBidToOffer(@PathVariable long id, @RequestBody Bid bid) {
        Offer offer = offersRepository.findById(id);

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
        offersRepository.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBid.getId()).toUri();

        return ResponseEntity.created(location).body(savedBid);
    }

    @PostMapping("")
    public Offer saveOffer(@RequestBody Offer offer) {
        if (offer.getId() == 0) offer.setId(new Random().nextLong(40000, 50000));

        Offer savedOffer = offersRepository.save(offer);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedOffer.getId()).toUri();

        return ResponseEntity.created(location).body(savedOffer).getBody();
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public Offer deleteOffer(@PathVariable long id) {
        Offer deletedOffer = offersRepository.deleteById(id);

        if (deletedOffer == null) {
            throw new NotFoundException("Offer-id=" + id);
        }

        return ResponseEntity.ok(deletedOffer).getBody();
    }

    @PutMapping(path = "{id}", produces = "application/json")
    public Offer putOffer(@PathVariable int id, @RequestBody Offer offer) {
        Offer existingOffer = offersRepository.findById(id);

        if (existingOffer == null) {
            throw new NotFoundException(String.valueOf(id));
        }

        if (existingOffer.getId() != offer.getId()) {
            throw new PreConditionFailed(String.format("OfferID=%d does not match Offer=%d", id, offer.getId()));
        }

        return offersRepository.save(offer);
    }

    @JsonView(CustomOfferView.Summary.class)
    @GetMapping("summary")
    public List<Offer> getOffersSummary() {
        return offersRepository.findAll();
    }
}
