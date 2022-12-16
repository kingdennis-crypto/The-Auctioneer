import {OffersAdaptor} from "@/services/offers-adaptor";

export class CachedOffersAdaptor extends OffersAdaptor {
    entities; // The cache of the results of all CRUD operations

    constructor(resourceUrl, copyConstructor) {
        super(resourceUrl, copyConstructor);

        this.entities = [];
    }


    async asyncFindAll() {
        this.entities = await super.asyncFindAll();

        return this.entities;
    }

    async asyncFindById(id) {
        return this.entities.find(x => x.id === id);
    }

    async asyncSave(offer) {
        if (offer.id === 0) offer.id = Math.floor(Math.random() * 40000) + 1;

        let offerId = this.entities.findIndex(x => x.id === x.id);

        if (offerId >= 0) {
            this.entities[offerId] = Object.assign(this.entities[offerId], offer);
        } else {
            this.entities.push(offer);
        }

        return await this.asyncFindById(offer.id);
    }

    async asyncDeleteById(id) {
        this.entities = this.entities.filter(x => x.id !== id);
    }
}