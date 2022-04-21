package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu;

public class Combo {

	// Instance fields
	private String nameBurger;
	private float priceBurger;
	private String nameSnack;
	private float priceSnack;
	private String nameDrink;
	private float priceDrink;
	private Menu.SIZE size;
	private float comboPrice;

	// Constructor
	public Combo(String nameBurger, float priceBurger, String nameSnack, float priceSnack, String nameDrink,
			float priceDrink, Menu.SIZE size) {
		this.nameBurger = nameBurger;
		this.priceBurger = priceBurger;
		this.nameSnack = nameSnack;
		this.priceSnack = priceSnack;
		this.nameDrink = nameDrink;
		this.priceDrink = priceDrink;
		this.size = size;

		// Set combo price according to size
		switch (size) {
		case M:
			comboPrice = priceBurger + priceSnack + priceDrink / 2;
			break;
		case L:
			comboPrice = priceBurger + priceSnack + 3 + (priceDrink + 3) / 2;
			break;
		case XL:
			comboPrice = priceBurger + priceSnack + 4 + (priceDrink + 4) / 2;
			break;
		default:

		}

	}

	// Getters
	public String getNameBurger() {
		return nameBurger;
	}

	public float getPriceBurger() {
		return priceBurger;
	}

	public String getNameSnack() {
		return nameSnack;
	}

	public float getPriceSnack() {
		return priceSnack;
	}

	public String getNameDrink() {
		return nameDrink;
	}

	public float getPriceDrink() {
		return priceDrink;
	}

	public String getSize() {
		// Return size of combo
		switch (size) {
		case M:
			return "M";
		case L:
			return "L";
		default:
			return "XL";
		}
	}

	public float getComboPrice() {
		return comboPrice;
	}

}
