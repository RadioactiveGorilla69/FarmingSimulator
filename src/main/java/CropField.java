import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CropField {

	private ArrayList<ArrayList<Field>> cropField;
	private ArrayList<ArrayList<CropLabel>> cropLabels;
	private int rows;
	private int cols;

	public CropField(int rows, int cols, JPanel panel, FarmerSimulatorGame game) {
		this.rows = rows;
		this.cols = cols;
		cropField = new ArrayList<>();
		cropLabels = new ArrayList<>();

		for(int r = 0; r < rows; r++) {
			ArrayList<Field> fieldRow = new ArrayList<Field>();
			ArrayList<CropLabel> labelRow = new ArrayList<CropLabel>();

			for(int c = 0; c < cols; c++){
				fieldRow.add(new Field(game));

				CropLabel cropLabel = new CropLabel("field");
				cropLabel.setBounds(r * 50 + 300, c * 50 + 300, 50, 50);
				labelRow.add(cropLabel);
				panel.add(cropLabel);

				JButton fieldButton = new JButton();
				fieldButton.setBounds(r * 50 + 300, c * 50 + 300, 50, 50);
				fieldButton.setBorderPainted(false);
				fieldButton.setContentAreaFilled(false);
				fieldButton.setFocusPainted(false);

				final int row = r, col = c;

				fieldButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selectedItem = game.getSelectedItem();
						Field field = cropField.get(row).get(col);

						if((selectedItem.equals("Wheat") || selectedItem.equals("Carrot")) && !field.isPlanted() && field.plant(selectedItem)) {
							cropLabels.get(row).get(col).setCropType(selectedItem);
							//System.out.println("Planted " + selectedItem + " at (" + row + ", " + col + ")");
						}
						else if(isToolName(selectedItem) && field.isMature()) {
							String harvestedCrop = field.getCropType();
							if(harvestedCrop == "Carrot") {
								field.harvest(harvestedCrop, selectedItem, 25, 20);
							}
							else {
								field.harvest(harvestedCrop, selectedItem, 10, 10);
							}
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							game.getFarmer().addToInventory(harvestedCrop + "Seed");							
							cropLabels.get(row).get(col).setCropType("field");
							//System.out.println("Harvested " + harvestedCrop + " at (" + row + ", " + col + ")");
						} 
						else if(field.isPlanted() && field.isMature()) {
							String harvestedCrop = field.getCropType();
							if(harvestedCrop == "Carrot") {
								field.harvest(harvestedCrop, selectedItem, 25, 20);
							}
							else {
								field.harvest(harvestedCrop, selectedItem, 10, 10);
							}
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							cropLabels.get(row).get(col).setCropType("field");
							//System.out.println("Harvested " + harvestedCrop + " at (" + row + ", " + col + ")");
						}
						panel.repaint();
					}
				});
				panel.add(fieldButton);
			}
			cropField.add(fieldRow);
			cropLabels.add(labelRow);
		}
	}

	public Field getField(int row, int col){
		return cropField.get(row).get(col);
	}

	public void update(){
		for(int r = 0; r < cropField.size(); r++) {
			for(int c = 0; c < cropField.get(0).size(); c++) {
				Field f = cropField.get(r).get(c);
				f.grow(0.1);
				cropLabels.get(r).get(c).setGrowthStage(f.getGrowthStage());
			}
		}
	}

	public boolean isToolName(String item) {
		return item.contains("Hoe") || item.contains("Sickle") || item.contains("Shovel");
	}

	public void expandField(int additionalRows, int additionalCols, JPanel panel, FarmerSimulatorGame game) {
		int newRows = this.rows + additionalRows;
		int newCols = this.cols + additionalCols;

		ArrayList<ArrayList<Field>> newCropField = new ArrayList<>();
		ArrayList<ArrayList<CropLabel>> newCropLabels = new ArrayList<>();

		for(int r = 0; r < this.rows; r++) {
			newCropField.add(new ArrayList<>(this.cropField.get(r)));
			newCropLabels.add(new ArrayList<>(this.cropLabels.get(r)));
		}

		for(int r = this.rows; r < newRows; r++) {
			ArrayList<Field> fieldRow = new ArrayList<>();
			ArrayList<CropLabel> labelRow = new ArrayList<>();

			for(int c = 0; c < cols; c++){
				fieldRow.add(new Field(game));

				CropLabel cropLabel = new CropLabel("field");
				cropLabel.setBounds(r * 50 + 300, c * 50 + 300, 50, 50);
				labelRow.add(cropLabel);
				panel.add(cropLabel);

				JButton fieldButton = new JButton();
				fieldButton.setBounds(r * 50 + 300, c * 50 + 300, 50, 50);
				fieldButton.setBorderPainted(false);
				fieldButton.setContentAreaFilled(false);
				fieldButton.setFocusPainted(false);

				final int row = r, col = c;

				fieldButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selectedItem = game.getSelectedItem();
						Field field = cropField.get(row).get(col);

						if((selectedItem.equals("Wheat") || selectedItem.equals("Carrot")) && !field.isPlanted() && field.plant(selectedItem)) {
							cropLabels.get(row).get(col).setCropType(selectedItem);
						//	System.out.println("Planted " + selectedItem + " at (" + row + ", " + col + ")");
						}
						else if(isToolName(selectedItem) && field.isMature()) {
							String harvestedCrop = field.getCropType();
							if(harvestedCrop == "Carrot") {
								field.harvest(harvestedCrop, selectedItem, 25, 20);
							}
							else {
								field.harvest(harvestedCrop, selectedItem, 10, 10);
							}
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							game.getFarmer().addToInventory(harvestedCrop + "Seed");							
							cropLabels.get(row).get(col).setCropType("field");
						//	System.out.println("Harvested " + harvestedCrop + " at (" + row + ", " + col + ")");
						} 
						else if(field.isPlanted() && field.isMature()) {
							String harvestedCrop = field.getCropType();
							if(harvestedCrop == "Carrot") {
								field.harvest(harvestedCrop, selectedItem, 25, 20);
							}
							else {
								field.harvest(harvestedCrop, selectedItem, 10, 10);
							}
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							game.getFarmer().addToInventory(harvestedCrop + "Seed");
							cropLabels.get(row).get(col).setCropType("field");
							//System.out.println("Harvested " + harvestedCrop + " at (" + row + ", " + col + ")");
						}
						panel.repaint();
					}
				});
				panel.add(fieldButton);
			}
			newCropField.add(fieldRow);
			newCropLabels.add(labelRow);
		}

		this.rows = newRows;
		this.cols = newCols;
		this.cropField = newCropField;
		this.cropLabels = newCropLabels;
	}
}
