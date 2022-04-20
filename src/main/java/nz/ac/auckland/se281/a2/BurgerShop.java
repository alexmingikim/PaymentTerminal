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

		// Remove ith element
		if (posCart >= 0 && posCart < cart.size()) {
			cart.remove(posCart);
		}
		// Provided position is not valid
		else {
			MessagesCLI.NOT_VALID_CART_POSITION.printMessage();
		}

	}

	/**
	 * removes all elements in the cart
	 */
	public void clearCart() {
		cart.removeAll(cart);
	}

	/**
	 * This method confirms the order, showing the estimated pick up time. It also
	 * give a warning if there are missing opportunities for COMBO menus
	 * 
	 */
	public void confirmOrder() {

		// Empty cart
		if (cart.size() == 0) {
			MessagesCLI.ORDER_INVALID_CART_EMPTY.printMessage();
		}

		// Change to combo to save money

		for (Object item1 : cart) {
			for (Object item2 : cart) {
				for (Object item3 : cart) {
					// Determine if cart contains at least one burger, one snack and one drink
					if ((item1.getClass() == Snack.class) && (item2.getClass() == Drink.class)
							&& (item3.getClass() == Burger.class)) {
						Snack snack = (Snack) item1;
						Drink drink = (Drink) item2;
						// Determine if size of snack and drink are same
						if (snack.getSize() == drink.getSize()) {
							MessagesCLI.MISSED_COMBO.printMessage();
							return;
						}
					}
				}
			}
		}

		// Confirm order, empty cart and print estimated waiting time
		int waitingTimeHours = 0;
		int waitingTimeMins = 0;
		int waitingTimeSecs = 0;
		int numOfBurger = 0;
		int numOfSnack = 0;
		int numOfDrink = 0;

		// Calculate waiting time
		for (Object item : cart) {
			// Waiting time for burger
			if (item.getClass() == Burger.class) {
				numOfBurger++;
				if (numOfBurger == 1) {
					waitingTimeSecs += 300;
				} else {
					waitingTimeSecs += 60;
				}
			}
			// Waiting time for snack
			else if (item.getClass() == Snack.class) {
				numOfSnack++;
				if (numOfSnack == 1) {
					waitingTimeSecs += 180;
				} else {
					waitingTimeSecs += 30;
				}
			}
			// Waiting time for drink
			else if (item.getClass() == Drink.class) {
				numOfDrink++;
				if (numOfDrink == 1) {
					waitingTimeSecs += 45;
				} else {
					waitingTimeSecs += 15;
				}
			}
			// Waiting time for combo
			else {
				numOfBurger++;
				numOfSnack++;
				numOfDrink++;
				if (numOfBurger == 1) {
					waitingTimeSecs += 300;
				} else {
					waitingTimeSecs += 60;
				}
				if (numOfSnack == 1) {
					waitingTimeSecs += 180;
				} else {
					waitingTimeSecs += 30;
				}
				if (numOfDrink == 1) {
					waitingTimeSecs += 45;
				} else {
					waitingTimeSecs += 15;
				}
			}
		}
		// Total waiting time in minutes and seconds
		// code adapted from https://stackoverflow.com/a/6118983
		waitingTimeHours = waitingTimeSecs / 3600;
		waitingTimeMins = (waitingTimeSecs % 3600) / 60;
		waitingTimeSecs = waitingTimeSecs % 60;

		// Show cart
		showCart();

		// Print waiting time
		System.out.println(MessagesCLI.ESTIMATE_WAITING_TIME.getMessage() + waitingTimeHours + " hours "
				+ waitingTimeMins + " minutes " + waitingTimeSecs + " seconds");

		// Clear cart
		clearCart();

	}

}
