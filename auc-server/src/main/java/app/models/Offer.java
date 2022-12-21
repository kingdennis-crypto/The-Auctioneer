package app.models;

//import app.repositories.Identifiable;
import app.repositories.Identifiable;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = "Offer_find_by_status",
                query = "SELECT o FROM Offer o WHERE o.status=?1"),
        @NamedQuery(name = "Offer_find_by_title",
                query = "SELECT o FROM Offer o WHERE o.title=?1"),
        @NamedQuery(name = "Offer_find_by_status_and_minBidValue",
                query = "SELECT o FROM Offer o INNER JOIN Bid b ON b.offer = o WHERE o.status=?1 AND b.bidValue > ?2")
})

@Entity
public class Offer implements Identifiable {
    private static final String[] TITLES = {"toolset", "lamp", "lamp", "cabinet", "lamp", "clock", "bicycle", "coat"};
    private static final String[] DESCRIPTIONS = {"A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp", "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle", "A characteristic, original coat"};

    public enum STATUS {
        NEW, FOR_SALE, SOLD, PAID, DELIVERED, CLOSED, EXPIRED, WITHDRAWN
    };

    @JsonView(CustomOfferView.Summary.class)
    @Id
    private long id;
    @JsonView(CustomOfferView.Summary.class)
    private String title;
    @JsonView(CustomOfferView.Summary.class)
    @Enumerated(EnumType.ORDINAL)
    private STATUS status;
    @JsonView(CustomOfferView.Summary.class)
    private String description;
    @JsonView(CustomOfferView.Summary.class)
    private LocalDate sellDate;
    @JsonView(CustomOfferView.Summary.class)
    private int valueHighestBid;

    @OneToMany(mappedBy = "offer")
//    @JsonSerialize(using = CustomOfferView.ShallowSerializer.class)
    private List<Bid> bids;

    public Offer(int id) {
        this.id = id;
        this.bids = new ArrayList<>();

        Offer newOffer = createSampleOffer();
        this.title = newOffer.title;
        this.status = newOffer.status;
        this.description = newOffer.description;
        this.sellDate = newOffer.sellDate;
        this.valueHighestBid = newOffer.valueHighestBid;
    }

    public Offer(int id, String title, STATUS status, String description, LocalDate sellDate, int valueHighestBid) {
        this.bids = new ArrayList<>();

        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public Offer() { super(); }

    public Offer copyConstructor(Offer offer) {
        return offer;
    }

    public static Offer createSampleOffer() {
        int pId = new Random().nextInt(40000, 50000);

        LocalDate sellDate = LocalDate.now();
        int valueHighestBid = (int) Math.floor(Math.random() * 100);

        String title = TITLES[(int) Math.floor(Math.random() * TITLES.length)];
        STATUS status = STATUS.NEW;
        String description = DESCRIPTIONS[(int) Math.floor(Math.random() * DESCRIPTIONS.length)];

        return new Offer(pId, title, status, description, sellDate, valueHighestBid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    @Override
    public long getId() {
        return id;
    }

//    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public int getValueHighestBid() {
        return valueHighestBid;
    }

    public void setValueHighestBid(int valueHighestBid) {
        this.valueHighestBid = valueHighestBid;
    }

    /**
     * Associates the given bid with this offer, if not yet associated
     * and only if the value of the bid exceeds currently the highest bid on this offer
     * @param bid
     * @return Wheter a new association has been added
     */
    public boolean associateBid(Bid bid) {
        if (bid != null && bid.getOffer() == null) {
            bids.add(bid);

            setValueHighestBid((int) bid.getBidValue());

            return bid.associateOffer(this);
        }

        return false;
    }

    /**
     * Dissociates the given bid from this offer, if associated
     * @param bid
     * @return Wheter an existing association has been removed
     */
    public boolean dissocateBid(Bid bid) {
        if (bid != null && bid.getOffer() != null) {
            return this.bids.remove(bid) && bid.associateOffer(null);
        }

        return false;
    }
}


