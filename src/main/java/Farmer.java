import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Farmer {

	private String name;
	private int money;
	private ArrayList<String> inventory;
	private int level;
	private int xp;
	private FarmerSimulatorGame game;

	public Farmer(FarmerSimulatorGame game, String name) {
		this.game = game;
		this.name = name;
		this.money = 0;
		this.inventory = new ArrayList<String>();
		this.level = 1;
		this.xp = 0;

		for(int i = 0; i < 5; i++) {
			inventory.add("WheatSeed");
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void addXp(int amount) {
		this.xp += amount;
		while(this.xp >= 100) {
			this.xp -= 100;
			levelUp();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMoney(int amount) {
		money += amount;
	}

	public void spendMoney(int amount) {
		money -= amount;
	}

	public void levelUp() {
		level++;
		if(level == 4) {
			game.getFarmer().addToInventory("CarrotSeed");
			JOptionPane.showMessageDialog(null, "Unlocked Carrot!");
		}

		if(level == 5) {
			game.getCropField().expandField(4, 0, game.getGamePanel(), game);
			JOptionPane.showMessageDialog(null, "Unlocked 4 new land plots!");
		}

		if(level >= 10) {
			game.showWinScreen();
		}
	}

	public String getName() {
		return name;
	}

	public void addToInventory(String item) {
		inventory.add(item);
	}

	public boolean hasItem(String item) {
		return inventory.contains(item);
	}

	public void removeItem(String item) {
		inventory.remove(item);
	}

	public int getSeedCount(String seed) {
		int counter = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).equals(seed)) {
				counter++;
			}
		}
		return counter;
	}

	public ArrayList<String> getInventory() {
		return inventory;
	}
}
