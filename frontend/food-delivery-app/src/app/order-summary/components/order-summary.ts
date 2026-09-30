import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../service/order.service';
import { CommonModule } from '@angular/common';
import { Order } from '../models/Order';

@Component({
  selector: 'order-summary',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css',
})
export class OrderSummary implements OnInit {

  orderSummary!: Order;
  showDialog: boolean = false;
  userId: number = 1;

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['data']) {
        this.orderSummary = JSON.parse(params['data']);
      }
    });
  }

  getTotalAmount(): number {
    return this.orderSummary?.foodItemList?.reduce(
      (total: number, item: any) => total + (item.price * item.quantity),
      0
    ) || 0;
  }

  placeOrder() {
    this.orderSummary.userId = this.userId;
    this.orderService.saveOrder(this.orderSummary).subscribe({
      next: () => {
        this.showDialog = true;
      },
      error: () => {
        alert('Failed to place order');
      }
    });
  }

  closeDialog() {
    this.showDialog = false;
    this.router.navigate(['/restaurant-listing']);
  }
}