import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RestaurantService } from '../service/restaurant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-listing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './restaurant-listing.html',
  styleUrl: './restaurant-listing.css',
})
export class RestaurantListing implements OnInit {

  restaurantList: any[] = [];

  constructor(private restaurantService: RestaurantService, private router: Router) {}

  ngOnInit(): void {
    this.loadRestaurants();
  }

  loadRestaurants() {
    this.restaurantService.getAllRestaurants().subscribe({
      next: (data) => {
        this.restaurantList = data.map((res, index) => ({
          ...res,
          image: this.getRandomImage(index)
        }));
      },
      error: (err) => {
        console.error('Error fetching restaurants', err);
      }
    });
  }

  // 🔥 Random image logic
  getRandomImage(index: number): string {
    const images = [
      'https://images.unsplash.com/photo-1600891964599-f61ba0e24092?w=800',
      'https://images.unsplash.com/photo-1550547660-d9450f859349?w=800',
      'https://images.unsplash.com/photo-1631515243349-e0cb75fb8d3a?w=800',
      'https://images.unsplash.com/photo-1551183053-bf91a1d81141?w=800',
      'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800'
    ];
    return images[index % images.length];
  }

  onButtonClick(id: number) {
    this.router.navigate(['/food-catalogue', id]);
    console.log('Order clicked for restaurant ID:', id);
  }
}