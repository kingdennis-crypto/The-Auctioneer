package app.rest;

import app.exceptions.BadRequest;
import app.exceptions.NotFoundException;
import app.exceptions.PreConditionFailed;
import app.models.Bid;
import app.models.Offer;
import app.models.enums.Status;
import app.repositories.interfaces.EntityRepository;
import app.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
//    @Autowired
//    private OffersRepositoryJpa offersRepo;
    @Autowired
    private EntityRepository<Offer> offersRepo;

//    @Autowired
//    private BidsRepositoryJpa bidsRepository;

    @Autowired
    private EntityRepository<Bid> bidsRepository;

//    @GetMapping("")
//    public List<Offer> getAll() {
//        return offersRepo.findAll();
//    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Offer getOffer(@PathVariable long id) {
        Offer offer = offersRepo.findById(id);

        if (offer == null) {
            throw new NotFoundException(String.format("Offer with ID: %d was not found", id));
        }

        return offer;
    }

    @GetMapping("")
    public List<Offer> getOffers(@RequestParam Optional<String> title, @RequestParam Optional<String> status, @RequestParam Optional<Integer> minBidValue) {
        if (title.isEmpty() && status.isEmpty() && minBidValue.isEmpty()) return offersRepo.findAll();

        List<Offer> offers = new ArrayList<>();

        title.ifPresent(s -> offers.addAll(offersRepo.findByQuery("Offer_find_by_title", s)));

        if (status.isPresent()) {
            offers.clear();

            if (Stream.of(Status.values()).noneMatch(s -> s.name().equals(status.get()))) {
                throw new BadRequest(String.format("Status=%s is not a valid status", status.get()));
            }

            Status newStatus = Status.valueOf(status.get());

            if (minBidValue.isPresent()) {
                offers.addAll(offersRepo.findByQuery("Offer_find_by_status_and_minBidValue", newStatus, minBidValue.get()));

                return offers;
            }

            offers.addAll(offersRepo.findByQuery("Offer_find_by_status", newStatus));
        }

        if (title.isEmpty() && status.isEmpty()) {
            throw new BadRequest(String.format("Cannot handle you combination of request parameters of parameters title=, status= and minBidValue=%d", minBidValue.get()));
        }

        return offers;
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
    @JsonView(Views.Summary.class)
    public List<Offer> getAllScootersSummary() {
        return offersRepo.findAll();
    }

    @GetMapping("{id}/bids")
    public List<Bid> getBidsOfOffer(@PathVariable long id) {
        Offer offer = offersRepo.findById(id);

        if (offer == null) {
            throw new NotFoundException("Offer not found");
        }

        return offer.getBids();
    }

    @DeleteMapping("{id}/bids/{bidId}")
    public ResponseEntity<Bid> deleteBidOfOffer(@PathVariable long id, @PathVariable long bidId) {
        Offer offer = offersRepo.findById(id);
        Bid bid = bidsRepository.findById(bidId);

        if (offer == null) {
            throw new NotFoundException("Offer not found");
        }

        if (bid == null) {
            throw new NotFoundException("Bid not found");
        }

        bidsRepository.deleteById(bidId);
        return ResponseEntity.ok(bid);
    }

    @PostMapping(path = "{id}/bids", produces = "application/json")
    public ResponseEntity<Bid> addBidToOffer(@PathVariable long id, @RequestBody Bid bid) {
        Offer offer = offersRepo.findById(id);

        if (offer == null) {
            throw new NotFoundException("Offer not found");
        }

        boolean lowerValue = offer.getValueHighestBid() >= bid.getBidValue();
        boolean forSale = offer.getStatus() == Status.FOR_SALE;

        if (lowerValue || forSale) {
            throw new PreConditionFailed(String.format("Bid with value=%f does not beat latest bid on offerId=%d", bid.getBidValue(), offer.getId()));
        }

        bid = bidsRepository.save(bid);

        offer.associateBid(bid);
        offersRepo.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bid.getId()).toUri();

        return ResponseEntity.created(location).body(bid);
    }
}
