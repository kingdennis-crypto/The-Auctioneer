package app.rest;

import app.exceptions.NotFoundException;
import app.exceptions.PreConditionFailed;
import app.models.Offer;
import app.repositories.OffersRepositoryMock;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/offers")
public class OffersController {
    @Autowired
    private OffersRepositoryMock offersRepo;

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
}
