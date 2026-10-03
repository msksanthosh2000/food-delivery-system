import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
// import { API_URL_ORDERS } from '../../constants/url';
import { K8ExternalIp } from '../../constants/url';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = `${K8ExternalIp}/order/saveOrder`;

  constructor(private http: HttpClient) {}

  // ===== SAVE ORDER =====
  saveOrder(orderData: any): Observable<any> {
    return this.http
      .post<any>(this.apiUrl, orderData)
      .pipe(catchError(this.handleError));
  }

  // ===== ERROR HANDLER =====
  private handleError(error: any): Observable<never> {
    console.error('ORDER API ERROR:', error);
    return throwError(() => error);
  }
}