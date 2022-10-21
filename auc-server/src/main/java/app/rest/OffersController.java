package app.rest;

import app.exceptions.NotFoundException;
import app.exceptions.PreConditionFailed;
import app.models.Offer;
import app.repositories.OffersRepository;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    @Autowired
    private OffersRepository offersRepository;

    @GetMapping("")
    public List<Offer> getTestOffers() {
        return offersRepository.findAll();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Offer getOffer(@PathVariable long id) throws ClassNotFoundException {
        Offer offer = offersRepository.findById(id);

        if (offer == null) {
            throw new ClassNotFoundException("id = " + id);
        }

        return offer;
    }

    @PostMapping("")
    public Offer saveOffer(@RequestBody Offer offer) {
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
    public Offer putOffer(@RequestBody Offer offer) {
        Offer putOffer = offersRepository.save(offer);

        if (putOffer == null) {
            throw new NotFoundException("Offer-id=" + offer.getId());
        }

        if (offer.getId() != putOffer.getId()) {
            throw new PreConditionFailed(String.format("Offer-id=%s, putOffer-id=%s", offer.getId(), putOffer.getId()));
        }

        return ResponseEntity.ok(putOffer).getBody();
    }

    @JsonView(CustomOfferView.Summary.class)
    @GetMapping("summary")
    public List<Offer> getOffersSummary() {
        return offersRepository.findAll();
    }
}
