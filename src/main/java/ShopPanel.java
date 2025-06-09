import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel {

	private FarmerSimulatorGame game;
	private JLabel moneyLabel;

	public ShopPanel(FarmerSimulatorGame game, int x, int y, int width, int height) {
		this.game = game;
		setBounds(x, y, width, height);
		setLayout(new GridLayout(0, 1, 5, 5));
		setBorder(BorderFactory.createTitledBorder("Shop"));

		if(game.getFarmer() == null) {
		}
		else {
			moneyLabel = new JLabel("Money: " + game.getFarmer().getMoney());
			add(moneyLabel);
		}

		for(int i = 0; i < game.getTools().size(); i++) {
			addToShop(game.getTools().get(i));
		}
	}


	public void addToShop(Tool tool) {
		JPanel itemPanel = new JPanel(new BorderLayout());
		itemPanel.setBorder(BorderFactory.createEtchedBorder());

		JLabel infoLabel = new JLabel(tool.getName() + " - $" + tool.getPrice());
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Farmer farmer = game.getFarmer();

				if(farmer.getMoney() >= tool.getPrice()) {
					if(!farmer.hasItem(tool.getName())) {
						farmer.spendMoney(tool.getPrice());
						farmer.addToInventory(tool.getName());
						showMoney();
						//System.out.println("success");
						JOptionPane.showMessageDialog(ShopPanel.this, 
								"You bought " + tool.getName() + "!", 
								"Purchase Successful", 
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(ShopPanel.this, 
								"You already own " + tool.getName() + "!", 
								"Already Owned", 
								JOptionPane.WARNING_MESSAGE);
					}
				}

				else {
					JOptionPane.showMessageDialog(ShopPanel.this, 
							"Not enough money! You need $" + tool.getPrice() + 
							" but only have $" + farmer.getMoney(), 
							"Insufficient Funds", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		itemPanel.add(infoLabel, BorderLayout.CENTER);
		itemPanel.add(buyButton, BorderLayout.EAST);

		add(itemPanel);
	}

	public void showMoney() {
		Farmer farmer = game.getFarmer();
		if(farmer != null && moneyLabel != null)
			moneyLabel.setText("Money: " + farmer.getMoney());
	}
}