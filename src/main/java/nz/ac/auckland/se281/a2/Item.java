package nz.ac.auckland.se281.a2;

public abstract class Item {

	private String name;
	protected float price;

	public Item(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public float getPrice() {
		return this.price;
	}

}
