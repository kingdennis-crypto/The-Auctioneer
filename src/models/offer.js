/**
 * Offer class
 * Made by Simon Vriesema
 */

// eslint-disable-next-line no-unused-vars
export class Offer {
    static titles = ["toolset", "lamp", "lamp", "cabinet", "lamp", "clock", "bicycle", "coat"];
    static statusses = ["NEW", "FOR_SALE", "SOLD", "PAID", "DELIVERED", "CLOSED", "EXPIRED", "WITHDRAWN"];
    static descriptions = ["A characteristic, original toolset", "A modern comfort lamp", "A small comfortable lamp", "An antique cozy cabinet", "An antique cozy lamp", "A characteristic, cozy clock", "A small, robust bicycle", "A characteristic, original coat"];

    constructor(id, title, status, description, sellDate, valueHighestBid) {
        this.id = id;
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    static getStatusses() {
        return this.statusses;
    }

    static copyConstructor(offer) {
        if (offer == null) return null;
        let copy = Object.assign(new Object(), offer);
        copy.sellDate = new Date(offer.sellDate);
        return copy;
    }

    static createSampleOffer(pId = 0) {
        function randomDate(start, end) {
            return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
        }

        function randomArrayObject(array){
            return array[Math.floor(Math.random() * array.length)]
        }

        
        const sellDate = randomDate(new Date(2012, 0, 1), new Date());
        const valueHighestBid = Math.floor(Math.random() * 100);

        const title = randomArrayObject(this.titles);
        const status = randomArrayObject(this.statusses);
        const description = randomArrayObject(this.descriptions);

        return new Offer(pId, title, status, description, sellDate, valueHighestBid);
    }

    static isFilledIn(givenOffer) {
        const id = givenOffer.id !== "";
        const title = givenOffer.title !== "";
        const status = givenOffer.status !== "";
        const description = givenOffer.description !== "";
        const sellDate = givenOffer.sellDate !== "";
        const valueHighestBid = givenOffer.valueHighestBid !== "";

        console.log(id && title && status && description && sellDate && valueHighestBid);
        return id && title && status && description && sellDate && valueHighestBid;
    }

    static equals(other) {
        return JSON.stringify(this) === JSON.stringify(other);
    }
}