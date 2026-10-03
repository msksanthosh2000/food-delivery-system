import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
// import { API_URL_FOOD_CATALOG } from '../../constants/url';
import { FoodItem } from '../model/FoodItem';
import { K8ExternalIp } from '../../constants/url';

export interface Restaurant {
    id: number;
    name: string;
    address: string;
    city: string;
    restaurantDescription: string;
}

export interface FoodCataloguePage {
    restaurant: Restaurant;
    foodItems: FoodItem[];
}

/* ===== Service ===== */

@Injectable({
    providedIn: 'root'
})
export class FoodItemService {

    private baseUrl = K8ExternalIp + '/foodCatalogue';

    constructor(private http: HttpClient) { }

    /* ===== ADD FOOD ITEM ===== */
    addFoodItem(foodItem: FoodItem): Observable<FoodItem> {
        return this.http
            .post<FoodItem>(`${this.baseUrl}/addFoodItem`, foodItem)
            .pipe(catchError(this.handleError));
    }

    /* ===== GET FOOD + RESTAURANT ===== */
    getFoodCatalogueByRestaurantId(id: number): Observable<FoodCataloguePage> {
        return this.http
            .get<FoodCataloguePage>(`${this.baseUrl}/fetchRestaurantAndFoodItemById/${id}`)
            .pipe(catchError(this.handleError));
    }


    /* ===== ERROR HANDLER ===== */
    private handleError(error: any): Observable<never> {
        console.error('API ERROR:', error);
        return throwError(() => error);
    }
}