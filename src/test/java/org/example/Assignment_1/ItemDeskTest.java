package org.example.Assignment_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ItemDeskTest {

    @Test
    void testValidItemType() {
        Item Item1 = new Item("Steel","raw",1200,11);
        assertEquals("raw",Item1.type);
    }

    @Test
    void testRawItemCalculation() {
        Item item = new Item("Phone", "manufactured", 200.0, 1); // total cost=200
        item.calculateTotalPrice();

        double basicTax = 200.0 * 0.125; // 25
        double surcharge = (200.0 + basicTax) * 0.02; // (225)*0.02=4.5
        double expectedFinalPrice = 200.0 + basicTax + surcharge; // 229.5
        double expectedTax = basicTax + surcharge; // 29.5

        assertEquals(expectedFinalPrice, item.final_price, 0.01);
        assertEquals(expectedTax, item.sales_tax, 0.01);
    }

    @Test
    void testImportedItemUnder100() {
        Item item = new Item("Pen", "imported", 50.0, 1); // total cost=50
        item.calculateTotalPrice();

        double importDuty = 50.0 * 0.10; // 5
        double costAfterDuty = 50.0 + importDuty; // 55
        double surcharge = 5;
        double expectedFinalPrice = costAfterDuty + surcharge; // 60
        double expectedTax = importDuty + surcharge; // 10

        assertEquals(expectedFinalPrice, item.final_price, 0.01);
        assertEquals(expectedTax, item.sales_tax, 0.01);
    }

    @Test
    void testImportedItemBetween100And200() {
        Item item = new Item("Headphones", "imported", 100.0, 1); // total cost=100
        item.calculateTotalPrice();

        double importDuty = 100.0 * 0.10; // 10
        double costAfterDuty = 110.0;
        double surcharge = 10;
        double expectedFinalPrice = costAfterDuty + surcharge; //120
        double expectedTax = importDuty + surcharge; //20

        assertEquals(expectedFinalPrice, item.final_price, 0.01);
        assertEquals(expectedTax, item.sales_tax, 0.01);
    }

    @Test
    void testImportedItemAbove200() {
        Item item = new Item("Laptop", "imported", 250.0, 1); // total cost=250
        item.calculateTotalPrice();

        double importDuty = 250.0 * 0.10; //25
        double costAfterDuty = 275.0;
        double surcharge = costAfterDuty * 0.05; //13.75
        double expectedFinalPrice = costAfterDuty + surcharge; //288.75
        double expectedTax = importDuty + surcharge; //25+13.75=38.75

        assertEquals(expectedFinalPrice, item.final_price, 0.01);
        assertEquals(expectedTax, item.sales_tax, 0.01);
    }
}