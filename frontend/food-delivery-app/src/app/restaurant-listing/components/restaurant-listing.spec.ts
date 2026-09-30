import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantListing } from './restaurant-listing';

describe('RestaurantListing', () => {
  let component: RestaurantListing;
  let fixture: ComponentFixture<RestaurantListing>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantListing]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantListing);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
