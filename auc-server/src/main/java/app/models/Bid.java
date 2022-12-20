package app.models;

import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    @JsonView(CustomOfferView.Shallow.class)
    private long id;

    @JsonView(CustomOfferView.Shallow.class)
    private double bidValue;

    @JsonIgnore // Added this because I got errors with the GET request
    @ManyToOne()
//    @JsonView(CustomOfferView.ShallowSerializer.class)
    private Offer offer;

    protected Bid() {}

    public Bid(double value) {
        this.bidValue = value;
    }

//    @Override
    public long getId() {
        return id;
    }

//    @Override
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
        double value = new Random().nextDouble(0, 9999);

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
            return true;

        } else if (offer == null && this.offer != null) {
            setOffer(null);
            return true;
        }

        return false;
    }
}

