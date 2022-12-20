package app.models;

//import app.repositories.Identifiable;
import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Offer {
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

    public Offer(int id) {
        this.id = id;

        Offer newOffer = this.createSampleOffer(id);
        this.title = newOffer.title;
        this.status = newOffer.status;
        this.description = newOffer.description;
        this.sellDate = newOffer.sellDate;
        this.valueHighestBid = newOffer.valueHighestBid;
    }

    public Offer(int id, String title, STATUS status, String description, LocalDate sellDate, int valueHighestBid) {
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

    public static Offer createSampleOffer(int pId) {
        if (pId == 0) pId = new Random().nextInt(40000, 50000);

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
}


