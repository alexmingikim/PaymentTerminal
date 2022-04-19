package nz.ac.auckland.se281.a2;

import java.util.ArrayList;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;
import nz.ac.auckland.se281.a2.cli.MessagesCLI;

public class BurgerShop {

	ArrayList<Object> cart = new ArrayList<Object>();

	public BurgerShop() {

	}

	/**
	 * Add a burger in the cart
	 * 
	 * @param name
	 * @param price
	 */
	public void addBurger(String name, float price) {
		Burger burger = new Burger(name, price);
		cart.add(burger);
	}

	/**
	 * add a snack in the cart, if size is L the price should be incremented by $3
	 * if the size is XL the price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 * 
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addSnack(String name, float price, SIZE size) {
		Snack snack = new Snack(name, price, size);
		cart.add(snack);
	}

	/**
	 * 
	 * add a drink in the cart
	 * 
	 * if size is L the price should be incremented by $3 if the size is XL the
	 * price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 *
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addDrink(String name, float price, SIZE size) {
		Drink drink = new Drink(name, price, size);
		cart.add(drink);
	}

	/**
	 * print the content of the cart, or print MessagesCLI.CART_EMPTY if the cart is
	 * empty
	 *
	 *
	 */
	public void showCart() {
		int count = 0;
		float total = 0;

		for (Object object : cart) {
			// Show Snack
			if (object.getClass() == Snack.class) {
				Snack snack = (Snack) object;
				System.out.println(count + " - " + snack.getName() + " (" + snack.getSize() + ")" + ": $"
						+ String.format("%.02f", snack.getPrice()));
			}
			// Show Drink
			else if (object.getClass() == Drink.class) {
				Drink drink = (Drink) object;
				System.out.println(count + " - " + drink.getName() + " (" + drink.getSize() + ")" + ": $"
						+ String.format("%.02f", drink.getPrice()));
			}
			// Show Burger
			else if (object.getClass() == Burger.class) {
				Burger burger = (Burger) object;
				System.out
						.println(count + " - " + burger.getName() + ": $" + String.format("%.02f", burger.getPrice()));
			}
			// Show Combo
			else if (object.getClass() == Combo.class) {
				Combo combo = (Combo) object;
				System.out.println(count + " - COMBO : (" + combo.getNameBurger() + ", " + combo.getNameSnack() + " ("
						+ combo.getSize() + "), " + combo.getNameDrink() + " (" + combo.getSize() + ")): $"
						+ String.format("%.02f", combo.getComboPrice()));
			} else {
				System.out.println("Error!");
			}
			count++;

			// Calculate total price
			if (object instanceof Item) {
				Item item = (Item) object;
				total += item.getPrice();
			} else if (object.getClass() == Combo.class) {
				Combo combo = (Combo) object;
				total += combo.getComboPrice();
			}
		}

		// Empty cart
		if (count == 0) {
			MessagesCLI.CART_EMPTY.printMessage();
		}

		// Display total
		if (total >= 100) {
			// Apply discount
			MessagesCLI.DISCOUNT.printMessage();
			total = (float) (total * 0.75);
			System.out.println("Total: $" + String.format("%.02f", total));
		} else {
			System.out.println("Total: $" + String.format("%.02f", total));
		}

	}

	/**
	 * add a combo in the cart.
	 * 
	 * The price of a combo is the sum of all the items, but the drink would be half
	 * price. Note that in a combo, both snacks and drinks have the same size, and
	 * the combo price must consider the size (@see addSnack and addDrink methods).
	 * 
	 * @param nameBurger
	 * @param priceBurger
	 * @param nameSnack
	 * @param priceSnack
	 * @param nameDrink
	 * @param priceDrink
	 * @param size
	 */
	public void addCombo(String nameBurger, float priceBurger, String nameSnack, float priceSnack, String nameDrink,
			float priceDrink, SIZE size) {
		Combo combo = new Combo(nameBurger, priceBurger, nameSnack, priceSnack, nameDrink, priceDrink, size);
		cart.add(combo);
	}

	/**
	 * remove the item in the cart specified by the position posCart. Note that the
	 * position of the cart start from zero. if postCart is invalid: posCart < 0 OR
	 * posCart >= size of the cart the method prints
	 * MessagesCLI.NOT_VALID_CART_POSITION
	 * 
	 * @param posCart
	 */
	public void removeItem(int posCart) {
		// TODO TASK3
	}

	/**
	 * removes all elements in the cart
	 */
	public void clearCart() {
		// TODO TASK3
	}

	/**
	 * This method confirms the order, showing the estimated pick up time. It also
	 * give a warning if there are missing opportunities for COMBO menus
	 * 
	 */
	public void confirmOrder() {
		// TODO TASK4
	}
}
