import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {
	private FarmerSimulatorGame game;

	public InventoryPanel(FarmerSimulatorGame game, int x, int y, int width, int height) {
		this.game = game;

		setBounds(x, y, width, height);
		setLayout(new GridLayout(0, 1, 5, 5));
		setBorder(BorderFactory.createTitledBorder("Inventory"));

		updateInventory();
	}

	public void updateInventory() {
		removeAll();

		if(game.getFarmer() != null) {
			Farmer farmer = game.getFarmer();

			//trying out enhanced for loop lol
			for(Tool tool : game.getTools()) {
				if(farmer.hasItem(tool.getName())) {
					JButton toolButton = new JButton(tool.getName());
					toolButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							game.setSelectedItem(tool.getName());
							highlightSelectedButton(toolButton);
						}
					});
					add(toolButton);
				}
			}

			String[] seeds = {"Wheat", "Carrot"};
			for(String seed : seeds) {
				int count = farmer.getSeedCount(seed + "Seed");
				if(count > 0) {
					JButton seedButton = new JButton(seed + " Seeds: " + count);
					seedButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							game.setSelectedItem(seed);
							highlightSelectedButton(seedButton);
						}
					});
					add(seedButton);
				}
			}
		}

		JButton emptyButton = new JButton("Empty");
		emptyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setSelectedItem("");
				highlightSelectedButton(emptyButton);
				//System.out.println("Selected: Empty");
			}
		});
		add(emptyButton);

		revalidate();
		repaint();
	}

	private JButton lastSelectedButton = null;

	public void highlightSelectedButton(JButton button) {
		if(lastSelectedButton != null) {
			lastSelectedButton.setBackground(null);
		}
		lastSelectedButton = button;
	}
}