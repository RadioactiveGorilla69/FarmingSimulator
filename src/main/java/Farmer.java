import java.util.ArrayList;

public class Farmer {

    private String name;
    private int money;
    private ArrayList<String> inventory = new ArrayList<>();
    private int level;
    private int xp;

    public Farmer(String name) {
        this.name = name;
    }

    public void addMoney(int amount){
        money += amount;
    }

    public void spendMoney(int amount){
        money -= amount;
    }

    public void levelUp(){
        level++;
    }

    public String getName(){
        return name;
    }
    
    public void sellCrop(String cropType){
    	
		int value = 0;
		
		switch(cropType.toLowerCase()){
			case "wheat":
				value = 10;
				break;
			case "carrot":
				value = 15;
				break;
			default:
				value = 5;
		};
		
		money += value;
		xp += value;

		if(xp >= level * 100){
			level++;
			xp -= 100; 
			System.out.println("Leveled up! Now level " + level);
		}
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

    
}
