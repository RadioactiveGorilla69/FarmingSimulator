public class Field {
	
	private boolean isPlanted;
	private String cropType;
	private double growthStage;
	private boolean isTilled;

	public Field() {
		isPlanted = false;
		cropType = "";
		growthStage = 0;
		isTilled = true;
	}
	
	public void till() {
        isTilled = true;
        System.out.println("Field has been tilled");
    }
	
	public void plant(String crop) {
		if(isTilled) {
			isPlanted = true;
			cropType = crop;
			growthStage = 0;
		}
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
	
	public boolean isTilled() {
        return isTilled;
    }

	public String harvest() {
        if(isMature()) {
            String harvestedCrop = cropType;
            isPlanted = false;
            cropType = "";
            growthStage = 0;
            return harvestedCrop;
        }
        return null;
    }
}
