public class Tool {
	
	String name;
    private int bonus;
    private int durability;

    public Tool(String name, int bonus, int durability){
    	this.name = name;
        this.bonus = bonus;
        this.durability = durability;
    }
    
    public void use() {
    	durability--;
    	if(durability <= 0) {
    		System.out.println(name + " broke!");
    	}
    }
}
