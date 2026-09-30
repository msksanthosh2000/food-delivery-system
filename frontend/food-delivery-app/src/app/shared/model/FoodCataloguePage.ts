import { Restaurant } from "../../food-catalogue/service/foodItemService";
import { FoodItem } from "../../food-catalogue/model/FoodItem";

export interface FoodCataloguePage {
    restaurant: Restaurant;
    foodItems: FoodItem[];
}