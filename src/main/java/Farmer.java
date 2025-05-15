import java.util.ArrayList;

public class Farmer {

    private String name;
    private int money;
    private ArrayList<Crop> cropInventory;
    private int level;

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

}
