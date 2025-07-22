package org.example.Assignment_1;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

/*

Tax rules for the 3 types are as follows:
raw: 12.5% of the item cost
manufactured: 12.5% of the item cost + 2% of (item cost + 12.5% of the item cost)
imported: 10% import duty on item cost + a surcharge (surcharge is: Rs. 5 if the final cost after applying tax & import duty is up to Rs. 100, Rs. 10 if the cost exceeds 100 and up to 200 and 5% of the final cost if it exceeds 200).

 */

class Item {
    String name, type;
    double price,sales_tax,final_price;
    int quantity;


    Item(String name, String type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    Item() {
        this.name = null;
        this.type = null;
        this.price = 0.0;
        this.quantity = 0;
    }

    public static void fill_Item(Item currentItem) {
        Scanner sc = new Scanner(in);
            System.out.println("Enter Item Name:");
            currentItem.name = sc.next();

            System.out.println("Enter Item Type ( raw / manufactured / imported ):");
            String type = sc.next();
            while(!type.equals("raw") && !type.equals("manufactured") && !type.equals("imported")) {
                System.out.println("Invalid Type!");
                System.out.println("Please Enter from ( raw / manufactured / imported )");
                type = sc.next();
            }
            currentItem.type = type;

            System.out.println("Enter Item price per unit:");
            double price = sc.nextDouble();
            while(price < 0) {
                System.out.println("Price cannot be negative!!");
                System.out.println("Enter Item price per unit:");
                price = sc.nextDouble();
            }
            currentItem.price = price;

            System.out.println("Enter Item Quantity:");
            int quantity = sc.nextInt();
            while(quantity <= 0) {
                System.out.println("Please Enter valid Quantity");
                quantity = sc.nextInt();
            }
            currentItem.quantity = quantity;
    }

    public void calculateTotalPrice() {
        double totalCost = this.price * this.quantity;

        if(this.type.equals("raw")) {
            double tax = totalCost * 0.125;
            this.sales_tax = tax;
            this.final_price = totalCost + tax;
        }
        else if(this.type.equals("manufactured")) {
            double basicTax = totalCost * 0.125;
            double surcharge = (totalCost + basicTax) * 0.02;
            this.sales_tax = basicTax + surcharge;
            this.final_price = totalCost + basicTax + surcharge;
        }
        else if(this.type.equals("imported")) {
            double importDuty = totalCost * 0.10;
            double costAfterDuty = totalCost + importDuty;
            double surcharge;
            if(costAfterDuty <= 100){
                surcharge = 5;
            }
            else if(costAfterDuty <= 200){
                surcharge = 10;
            }
            else{
                surcharge = costAfterDuty * 0.05;
            }
            this.sales_tax = importDuty + surcharge;
            this.final_price = costAfterDuty + surcharge;
        }
    }

    public void displayDetails() {
        System.out.println("Item Name: " + this.name);
        System.out.println("Item Type: " + this.type);
        System.out.println("Item Price/Unit: " + this.price);
        System.out.println("Item Quantity: " + this.quantity);
        System.out.println("Total Price(incl tax): " + this.final_price);
    }


}

public class ItemDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to MEMO");
        ArrayList<Item> itemList = new ArrayList<>();
        char check = 'y';
        int number = 1;
        while (check == 'y') {
            Item i1 = new Item();
            Item.fill_Item(i1);
            i1.calculateTotalPrice();
            itemList.add(i1);
            System.out.println("Do you want to enter more item(s)? (y/n) ");
            check = sc.next().charAt(0);
        }

        for(Item item : itemList) {
            System.out.println("Here is the List of the Items");
            item.displayDetails();
            System.out.println("\n --------------- \n");
        }
    }
}
