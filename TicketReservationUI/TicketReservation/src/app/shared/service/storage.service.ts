import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class StorageService {
    storageKeys = {
        API_TOKEN: 'API_TOKEN',
        LOGGED_IN_USER: 'LOGGED_IN_USER'
    };

    constructor() { }

    setItem(key, value) {
        if (this.keyExists(key)) {
            localStorage.setItem(key, value);
        }
    }

    getItem(key) {
        if (this.keyExists(key)) {
            return localStorage.getItem(key);
        }
    }

    removeItem(key) {
        if (this.keyExists(key)) {
            return localStorage.removeItem(key);
        }
    }

    setAPIToken(tokenObj) {
        this.setItem(this.storageKeys.API_TOKEN, tokenObj);
    }

    getAPIToken() {
        return this.getItem(this.storageKeys.API_TOKEN);
    }

    removeAPIToken() {
        this.removeItem(this.storageKeys.API_TOKEN);
    }

    clear() {
        this.removeAPIToken();
    }

    keyExists(keyName) {
        return !!this.storageKeys[keyName];
    }
}
