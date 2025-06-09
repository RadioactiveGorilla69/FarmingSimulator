public class Field {

	private boolean isPlanted;
	private String cropType;
	private double growthStage;
	private FarmerSimulatorGame game;
	
	public Field(FarmerSimulatorGame game) {
		this.game = game;
		isPlanted = false;
		cropType = "";
		growthStage = 0;
	}

	public boolean plant(String crop) {
		if(game.getFarmer().getSeedCount(crop + "Seed") > 0) {
			//System.out.println(game.getFarmer().getSeedCount(crop + "Seed"));
			isPlanted = true;
			cropType = crop;
			growthStage = 0;
			game.getFarmer().removeItem(crop + "Seed");
			return true;
		}
		return false;
	}

	public boolean isPlanted() {
		return isPlanted;
	}

	public String getCropType() {
		return cropType;
	}

	public double getGrowthStage() {
		return growthStage;
	}

	public void grow(double amount) {
		if(isPlanted) {
			growthStage += amount;
		}
	}

	public boolean isMature() {
		return growthStage >= 1.0;
	}

	public void harvest(String cropType, String selectedItem, int money, int xp) {
		if(isMature()) {
			isPlanted = false;
			cropType = "";
			growthStage = 0;
			Farmer farmer = game.getFarmer();
			farmer.addMoney(money);
			farmer.addXp(xp);
			if(selectedItem.contains("Rare")) {
				for(int i = 0; i < (int) (Math.random() * 2); i++) {
					farmer.addXp(xp);
					farmer.addMoney(money);
				}
			}
			if(selectedItem.contains("Hoe")) {
				for(int i = 0; i < (int) (Math.random() * 2); i++) {
					farmer.addXp(xp);
					farmer.addMoney(money);
				}
			}
			if(selectedItem.contains("Sickle")) {
				for(int i = 0; i < (int) (Math.random() * 2); i++) {
					farmer.addXp(xp);
					farmer.addXp(xp);
				}
			}
			if(selectedItem.contains("Shovel")) {
				for(int i = 0; i < (int) (Math.random() * 2); i++) {
					farmer.addMoney(money);
				}
			}
			//System.out.println("added " + cropType + "Seed");
		}
	}
}
