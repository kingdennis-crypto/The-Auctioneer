package app.models;

import app.repositories.Identifiable;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Bid implements Identifiable {
    @Id
    @SequenceGenerator(name="Bid_ids", initialValue = 10_001)
    @GeneratedValue
    @JsonView(CustomOfferView.Shallow.class)
    private long id;

    @JsonView(CustomOfferView.Shallow.class)
    private double bidValue;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JsonView(CustomOfferView.ShallowSerializer.class)
    private Offer offer;

    protected Bid() {}

    public Bid(double value) {
        this.bidValue = value;
        this.offer = null;
    }

    public Bid(double value, Offer offer) {
        this.bidValue = value;
        this.offer = offer;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public double getBidValue() {
        return bidValue;
    }

    public void setBidValue(double value) {
        this.bidValue = value;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public static Bid createSampleBid() {
        double value = new Random().nextDouble(0, 50);

        Bid bid = new Bid(value);
//        bid.associateOffer(offer);

        return bid;
    }

    /**
     * Associates the given offer with this bid, if not yet associated
     * @param offer Provide null to dissociate the currently associated offer
     * @return Whether a new association has been added
     */
    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
            setOffer(offer);
            offer.associateBid(this);
            return true;
        } else if (offer == null && this.offer != null) {
            setOffer(null);
            return true;
        }

        return false;

//        if (offer != null && this.getOffer() == null) {
//            setOffer(offer);
//            offer.associateBid(this);
//            return true;
//
//        } else if (offer == null && this.offer != null) {
//            setOffer(null);
//            this.offer.dissocateBid(this);
//            return true;
//        }
//
//        return false;
    }
}

