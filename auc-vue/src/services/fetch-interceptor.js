import fetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance; // The singleton instance that has been registered
    session;
    router;
    unregister; // Callback function to unregister this instance at shutdown

    constructor(session, router) {
        this.session = session;
        this.router = router;

        // fetchIntercept does not register the object closure, only the methods as functions.
        // So we need an additional static reference
        FetchInterceptor.theInstance = this;
        this.unregister = fetchIntercept.register(this);

        console.log("FetchInterceptor has been registered; current token = ",
            FetchInterceptor.theInstance.session.getCurrentToken());
    }

    request(url, options) {}
    requestError(error) {}
    response(response) {}
    responseError(error) {}
}