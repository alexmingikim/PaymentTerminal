package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu;

public class Drink extends Item {

	private Menu.SIZE size;

	public Drink(String name, float price, Menu.SIZE size) {
		super(name, price);
		this.size = size;

		// Increase price for greater size
		switch (size) {
		case L:
			this.price += 3;
		case XL:
			this.price += 4;
		default:

		}
	}

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
