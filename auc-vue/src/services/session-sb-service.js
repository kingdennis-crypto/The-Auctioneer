/**
 * provide an adapter (interface) to the backend for sign-in and sign-up
 * @author Simon and Dennis
 */
export class SessionSbService {
    BROWSER_STORAGE_ITEM_NAME;
    RESOURCES_URL;

    _loggedIn = false;

    constructor(resourceUrl, browserStorageItemName) {
        this.RESOURCES_URL = resourceUrl;
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        
        this.signInFromBrowserStorage();

        console.log("Created SessionSbService for " + resourceUrl);
    }

    async asyncSignIn(email, password) {
        const body = JSON.stringify({ email: email, password: password });

        let response = await fetch(this.RESOURCES_URL + "/login",
            {
                method : 'POST',
                headers : { 'Content-Type' : 'application/json' },
                body : body,
                credentials: 'include',
            })
        
        console.log(response);
        if (response.ok) {
            const token = response.headers.get("Authorization");
            const user = await response.json();

            this._loggedIn = true;
            this.saveTokenIntoBrowserStorage(token, user);

            return user;
        } 

        this.signOut();
        return null;
    }

    signInFromBrowserStorage() {
        const token = this.getCurrentToken();
        const user = this.getCurrentUser();

        if (!token || !user) {
            this.signOut();
            return;
        }

        this._loggedIn = true
        return true;
    }

    signOut() {
        // Remove the copy of the token from the service and browser storage
        this._loggedIn = false;
        this.removeTokenFromBrowserStorage();
    }

    saveTokenIntoBrowserStorage(token, user) {
        localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, JSON.stringify({ jwt: token, user }));
    }

    removeTokenFromBrowserStorage() {
        window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
    }

    isLoggedIn() {
        return this._loggedIn;
    }
    
    getCurrentToken() {
        return JSON.parse(localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME))?.jwt;
    }

    getCurrentUser() {
        const user = JSON.parse(localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME))?.user;
        
        console.log("USER", user);
        // Sign out if the user is not valid
        if (!user) {
            this.signOut();
            return;
        }

        return user;
    }
}
