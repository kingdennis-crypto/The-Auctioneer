package app.models;

//import app.repositories.interfaces.Identifiable;
import app.models.enums.Status;
import app.repositories.interfaces.Identifiable;
import app.views.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = "Offer_find_by_status",
                query = "SELECT o FROM Offer o WHERE o.status=?1"),
        @NamedQuery(name = "Offer_find_by_title",
                query = "SELECT o FROM Offer o WHERE o.title=?1"),
        @NamedQuery(name = "Offer_find_by_status_and_minBidValue",
                query = "SELECT o FROM Offer o WHERE o.status=?1 AND o.valueHighestBid > ?2")
})

@Entity
public class Offer implements Identifiable {
    private static final String[] TITLES = {"toolset", "lamp", "lamp", "cabinet", "lamp", "clock", "bicycle", "coat"};
    private static final String[] DESCRIPTIONS = {"A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp", "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle", "A characteristic, original coat"};

    @Id
    @SequenceGenerator(name = "Offer_Seq", initialValue = 30_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Offer_Seq")
    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    private String title;
    @JsonView(Views.Public.class)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @JsonView(Views.Summary.class)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(Views.Summary.class)
    private Date sellDate;

    @JsonView(Views.Summary.class)
    private int valueHighestBid;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
//    @JsonBackReference
    @JsonSerialize(using = Views.ShallowSerializer.class)
    private List<Bid> bids;

    protected Offer() {}

    public Offer(String title, Status status, String description, Date sellDate, int valueHighestBid) {
        this.bids = new ArrayList<>();

        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public Offer copyConstructor(Offer offer) {
        return offer;
    }

    public static Offer createSampleOffer() {
        Random random = new Random();
        int valueHighestBid = (int) Math.floor(Math.random() * 100);

        String title = TITLES[(int) Math.floor(Math.random() * TITLES.length)];
        Status status = Status.values()[random.nextInt(Status.values().length)];
        String description = DESCRIPTIONS[(int) Math.floor(Math.random() * DESCRIPTIONS.length)];

        return new Offer(title, status, description, new Date(), valueHighestBid);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public int getValueHighestBid() {
        return valueHighestBid;
    }

    public void setValueHighestBid(int valueHighestBid) {
        this.valueHighestBid = valueHighestBid;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    /**
     * Associates the given bid with this offer, if not yet associated
     * and only if the value of the bid exceeds currently the highest bid on this offer
     * @param bid
     * @return Whether a new association has been added
     */
    public boolean associateBid(Bid bid) {
        // FIXME rewrite the if statement as the bid

        if (bid != null && bid.getOffer() == null) {
            bid.associateOffer(this);
            setValueHighestBid((int) bid.getBidValue());

            bids.add(bid);

            return true;
        }

        return false;
    }

    /**
     * Dissociates the given bid from this offer, if associated
     * @param bid
     * @return Whether an existing association has been removed
     */
    public boolean dissociateBid(Bid bid) {
        if (bid != null && bid.getOffer() != null) {
            return bids.remove(bid) && bid.dissociateOffer(this);
        }

        return false;
    }

    @Transient
    @JsonView(Views.Summary.class)
    @JsonSerialize(using = Views.ShallowSerializer.class)
    public Bid getLatestBid() {
        if (this.bids == null || this.bids.size() == 0) {
            return null;
        }

        return this.bids.get(this.bids.size() - 1);
    }
}


