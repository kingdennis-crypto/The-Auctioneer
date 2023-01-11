/**
 * provide an adapter (interface) to the backend for sign-in and sign-up
 * @author Simon and Dennis
 */
export class SessionSbService {
    BROWSER_STORAGE_ITEM_NAME;
    RESOURCES_URL;

    _currentToken = null;
    _currentUser = null;

    constructor(resourceUrl, browserStorageItemName) {
        this.RESOURCES_URL = resourceUrl;
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.getTokenFromBrowserStorage();
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
        if (response.ok) {
            let user = await response.json();
            this.saveTokenIntoBrowserStorage(
                response.headers.get('Authorization'),
                user);
            return user;
        } else {
            console.log(response)
            return null;
        }
    }

    signOut() {
        // Remove the copy of the token from the service and browser storage
        localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
    }

    saveTokenIntoBrowserStorage(token, user) {
        this._currentToken = token;
        this._currentUser = user;
        // Bearer [TOKEN]
        console.log("USER", user);

        if (token == null) {
            window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
        } else {
            const array = token.split(" ");
            token = array[1];



            window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
            window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
        }
    }

    getTokenFromBrowserStorage() {
        let token = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);

        if (token === null) {
            token = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        }

        this._currentToken = token;

        return token;
    }

    isLoggedIn() {
        return this._currentUser !== null;
    }

    getUserEmail() {
        return this.isLoggedIn() ? this._currentUser.name : "Visitor";
    }
}
