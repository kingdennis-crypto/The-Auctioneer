/**
 * Offer class
 * Made by Simon Vriesema
 */

class Offer {

    constructor(id, title, status, description, sellDate, valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.decription = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public static createSampleOffer(pId = 0) {
        function randomDate(start, end) {
            return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
        }

        function randomArrayObject(array){
            return array[Math.floor(Math.random() * array.length)]
        }

        const titles = ["toolset", "lamp", "lamp", "cabinet", "lamp", "clock", "bicycle", "coat"];
        const statusses = ["NEW", "FOR_SALE", "SOLD", "PAID", "DELIVERED", "CLOSED", "EXPIRED", "WITHDRAWN"];
        const descriptions = ["A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp",
            "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle",
            "A characteristic, original coat"];

        const sellDate = randomDate(new Date(2012, 0, 1), new Date());
        const valueHighestBid = Math.floor(Math.random() * 100);

        const title = randomArrayObject(titles);
        const status = randomArrayObject(statusses);
        const description = randomArrayObject(descriptions);

        const offer = new Offer(0, title, status, description, sellDate, valueHighestBid);

        return offer;
    }
}