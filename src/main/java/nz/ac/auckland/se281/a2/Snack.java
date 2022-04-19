package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu;

public class Snack extends Item {

	// Instance fields
	private Menu.SIZE size;

	// Constructor
	public Snack(String name, float price, Menu.SIZE size) {
		super(name, price);
		this.size = size;

		// Set price according to size
		switch (size) {
		case L:
			this.price += 3;
			break;
		case XL:
			this.price += 4;
			break;
		default:

		}
	}

	// Getters
	public String getSize() {
		switch (size) {
		case M:
			return "M";
		case L:
			return "L";
		default:
			return "XL";
		}
	}

}
