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

    async request(url, options) {
        const token = FetchInterceptor.theInstance.session.getCurrentToken();
        console.log(token);

        if (token == null) {
            console.log("TOKEN NULL");
            return [url, options];
        } else if (options == null) {
            console.log("OPTIONS NULL");
            // console.log([url, { headers: { Authorization: token } }]);
            return [url, { headers: { Authorization: token }}]
        } else {
            console.log("ELSE");
            let newOptions = { ...options };

            newOptions.headers = { ...options?.headers, Authorization: token };
            
            console.log(newOptions);
            return [url, newOptions];
        }
    }

    requestError(error) {
        return Promise.reject(error);
    }

    response(response) {
        const status = response.status;

        if (status >= 400 && status < 600) {
            // if (status === 401) { 
            //     FetchInterceptor.theInstance.router.push('/sign-in');
            //     return Promise.reject(response);
            // }
            
            // FetchInterceptor.theInstance.router.push({
            //     name: 'ERROR',
            //     params: {}
            // })
        }

        return response;
    }

    responseError(error) {
        return Promise.reject(error);
    }
}