export class OffersAdaptor {
    resourcesUrl;

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        console.log("Created OffersAdaptor for " + resourcesUrl);
    }

    async fetchJson(url, options = null) {
        let response = await fetch(url, options);

        if (response.ok) {
            return await response.json();
        } else {
            // The response body provides the http-error information
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null
        }
    }

    async asyncFindAll() {
        return await this.fetchJson(this.resourcesUrl);
    }

    async asyncFindById(id) {
        return await this.fetchJson(`${this.resourceUrl}/${id}`);
    }

    async asyncSave(offer) {
        console.log(offer);

        return await this.fetchJson(this.resourcesUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(offer)
        });
    }

    async asyncDeleteById(id) {
        // console.log(`${(this.resourceUrl)}/${id}`);
        console.log(this.resourcesUrl);
        return await this.fetchJson(`${this.resourcesUrl}/${id}`, {
            method: 'DELETE'
        });
    }
}