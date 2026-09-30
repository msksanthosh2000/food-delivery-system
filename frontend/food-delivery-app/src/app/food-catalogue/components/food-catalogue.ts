import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodCataloguePage, FoodItemService } from '../service/foodItemService';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'food-catalogue',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './food-catalogue.html',
  styleUrl: './food-catalogue.css',
})
export class FoodCatalogue implements OnInit {

  foodCatalogueResponse!: FoodCataloguePage;
  restaurantId: number = 1;

  foodItemCart: any[] = [];
  orderSummary: any;

  constructor(
    private foodService: FoodItemService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    // ✅ FIX 2: Load data AFTER getting param
    this.route.paramMap.subscribe(params => {
      this.restaurantId = Number(params.get('id')) || 1;
      this.loadFoodCatalogue();   // 🔥 moved here
    });
  }

  private loadFoodCatalogue() {
    this.foodService.getFoodCatalogueByRestaurantId(this.restaurantId).subscribe({
      next: (data) => {
        this.foodCatalogueResponse = data;
        console.log('Food Catalogue:', data);
      },
      error: (err) => {
        console.error('Error:', err);
      }
    });
  }

  // ===== CART LOGIC =====

  increase(food: any) {
    food.quantity = (food.quantity || 0) + 1;

    const index = this.foodItemCart.findIndex(item => item.id === food.id);

    if (index === -1) {
      this.foodItemCart.push({ ...food });
    } else {
      this.foodItemCart[index] = { ...food };
    }
  }

  decrease(food: any) {
    if (!food.quantity || food.quantity === 0) return;

    food.quantity--;

    const index = this.foodItemCart.findIndex(item => item.id === food.id);

    if (index !== -1) {
      if (food.quantity === 0) {
        this.foodItemCart.splice(index, 1);
      } else {
        this.foodItemCart[index] = { ...food };
      }
    }
  }

  getTotalItems(): number {
    return this.foodItemCart.reduce((total, item) => total + item.quantity, 0);
  }

  onCheckOut() {

    this.orderSummary = {
      foodItemList: this.foodItemCart,
      restaurantDTO: this.foodCatalogueResponse.restaurant
    };

    this.router.navigate(
      ['/order-summary'],
      {
        queryParams: {
          data: JSON.stringify(this.orderSummary)
        }
      }
    );
  }
}