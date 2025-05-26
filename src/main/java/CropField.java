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
			ArrayList<Field> fieldRow = new ArrayList<>();
			ArrayList<CropLabel> labelRow = new ArrayList<>();
			
			for(int c = 0; c < cols; c++){
				fieldRow.add(new Field());

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

				        if(isToolName(selectedItem) && !field.isPlanted()) {
				            field.till();
				            System.out.println("Tilled soil at (" + row + ", " + col + ")");
				            cropLabels.get(row).get(col).setCropType("tilled");
				        } 
				        else if((selectedItem.equals("Wheat") || selectedItem.equals("Carrot")) 
				                && field.isTilled() && !field.isPlanted()) {
				            field.plant(selectedItem);
				            cropLabels.get(row).get(col).setCropType(selectedItem);
				            System.out.println("Planted " + selectedItem + " at (" + row + ", " + col + ")");
				        }
				        else if(field.isPlanted() && field.isMature()) {
				            String harvestedCrop = field.getCropType();
				            field.harvest();
				            game.getFarmer().addToInventory(harvestedCrop);
				            cropLabels.get(row).get(col).setCropType("field");
				            System.out.println("Harvested " + harvestedCrop + " at (" + row + ", " + col + ")");
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
				f.grow(0.2);
				cropLabels.get(r).get(c).setGrowthStage(f.getGrowthStage());
			}
		}
	}
	
	public boolean isToolName(String item) {
	    return item.contains("Hoe") || item.contains("Sickle") || item.contains("Shovel");
	}
}
