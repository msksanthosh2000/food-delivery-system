import { FoodItem } from "../../food-catalogue/model/FoodItem";
import { Restaurant } from "../../food-catalogue/service/foodItemService";

export interface Order {
    orderId: string;
    foodItemList: FoodItem[];
    restaurantDTO: Restaurant;
    userId: number;
}