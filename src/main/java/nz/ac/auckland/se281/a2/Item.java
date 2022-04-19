package nz.ac.auckland.se281.a2;

public abstract class Item {

	// Instance fields
	private String name;
	protected float price;

	// Constructor
	public Item(String name, float price) {
		this.name = name;
		this.price = price;
	}

	// Getters
	public String getName() {
		return this.name;
	}

	public float getPrice() {
		return this.price;
	}

}
