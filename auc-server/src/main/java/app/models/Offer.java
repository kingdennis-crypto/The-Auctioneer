package app.models;

import java.time.LocalDate;

public class Offer {
    private static final String[] TITLES = {"toolset", "lamp", "lamp", "cabinet", "lamp", "clock", "bicycle", "coat"};
    private static final String[] STATUSSES = {"NEW", "FOR_SALE", "SOLD", "PAID", "DELIVERED", "CLOSED", "EXPIRED", "WITHDRAWN"};
    private static final String[] DESCRIPTIONS = {"A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp", "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle", "A characteristic, original coat"};

    private int id;
    private String title;
    private String status;
    private String description;
    private LocalDate sellDate;
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

    public Offer(int id, String title, String status, String description, LocalDate sellDate, int valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public int getValueHighestBid() {
        return valueHighestBid;
    }

    public Offer copyConstructor(Offer offer) {
        return offer;
    }

    public Offer createSampleOffer() {
        return createSampleOffer(0);
    }

    public Offer createSampleOffer(int pId) {
        LocalDate sellDate = LocalDate.now();
        int valueHighestBid = (int) Math.floor(Math.random() * 100);

        String title = TITLES[(int) Math.floor(Math.random() * TITLES.length)];
        String status = STATUSSES[(int) Math.floor(Math.random() * STATUSSES.length)];
        String description = DESCRIPTIONS[(int) Math.floor(Math.random() * DESCRIPTIONS.length)];

        return new Offer(pId, title, status, description, sellDate, valueHighestBid);
    }
}


