export class OffersAdaptor {
    resourcesUrl;

    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        console.log("Created OffersAdaptor for " + resourcesUrl);
    }

    async fetchJson(url, options = null) {
        console.log(url);
        let response = await fetch(url, options);

        // TODO: No token provided. Fix that the header gets the token

        console.log(response);

        if (response.ok) {
            return await response.json();
        } else {
            // The response body provides the http-error information
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncFindAll(queryParams = null) {
        const url = queryParams ? `${this.resourcesUrl}?${queryParams}` : this.resourcesUrl;

        return await this.fetchJson(url);
    }

    async asyncFindAllSummary() {
        const res = await this.fetchJson(this.resourcesUrl + '/summary');
        console.log(res);
        return res;
    }

    async asyncFindById(id) {
        return await this.fetchJson(`${this.resourcesUrl}/${id}`);
    }

    async asyncSave(offer) {
        return await this.fetchJson(this.resourcesUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(offer)
        });
    }

    async asyncDeleteById(id) {
        return await this.fetchJson(`${this.resourcesUrl}/${id}`, {
            method: 'DELETE'
        });
    }
}