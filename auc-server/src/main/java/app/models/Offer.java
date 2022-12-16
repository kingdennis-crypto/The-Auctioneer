package app.models;

import app.views.CustomOfferView;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.util.Random;

public class Offer {
    private static final String[] TITLES = {"toolset", "lamp", "cabinet", "clock", "bicycle", "coat"};
    private static final String[] STATUSSES = {"NEW", "FOR_SALE", "SOLD", "PAID", "DELVIERED", "CLOSED", "EXPIRED", "WITHDRAWN"};
    private static final String[] DESCRIPTIONS = {"A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp", "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle", "A characteristic, original coat"};

    @JsonView(CustomOfferView.Summary.class)
    private int id;

    @JsonView(CustomOfferView.Summary.class)
    private String title;

    @JsonView(CustomOfferView.Summary.class)
    private String status;

    private String description;
    private LocalDate sellDate;
    private int valueHighestBid;

    public Offer(int id, String title, String status, String description, LocalDate sellDate, int valueHighestBid) {
        this.id = id;

        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public static Offer createSampleOffer(int id) {
        Random random = new Random();

        LocalDate sellDate = LocalDate.now();
        int valueHighestBid = random.nextInt(1000);

        String title = TITLES[random.nextInt(TITLES.length)];
        String status = STATUSSES[random.nextInt(STATUSSES.length)];
        String description = DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)];

        return new Offer(id, title, status, description, sellDate, valueHighestBid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Offer copyConstructor(Offer offer) {
        return offer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
