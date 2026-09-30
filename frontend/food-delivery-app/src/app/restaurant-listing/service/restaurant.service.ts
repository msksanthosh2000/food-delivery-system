import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { API_URL_RESTAURANTS } from '../../constants/url';

@Injectable({
    providedIn: 'root'
})
export class RestaurantService {

    // Base URL for fetching all restaurants from the backend API
    private apiFetchAllRestaurantsUrl = API_URL_RESTAURANTS + '/restaurant/fetchAllRestaurants';

    constructor(private httpClient: HttpClient) {}

    /*
        * Fetches all restaurants from the backend API.
        * Returns an Observable of an array of restaurants.
        * Handles errors gracefully by logging and rethrowing a user-friendly message.
    */
    getAllRestaurants(): Observable<any[]> {
        return this.httpClient
        .get<any[]>(this.apiFetchAllRestaurantsUrl)
        .pipe(catchError(this.handleError));
    }


    // Private method to handle errors from HTTP requests
    private handleError(error: any): Observable<never> {
    console.error('FULL ERROR:', error); // 🔥 important
    return throwError(() => error);
}
}