public class Tool {

	private FarmerSimulatorGame game;
	private String name;
	private int price;

	public Tool(FarmerSimulatorGame game, String name, int price){
		this.game = game;
		this.name = name;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}
