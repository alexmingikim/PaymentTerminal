package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu;

public class Snack extends Item {

	private Menu.SIZE size;

	public Snack(String name, float price, Menu.SIZE size) {
		super(name, price);
		this.size = size;
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
