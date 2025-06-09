import javax.swing.*;
import java.awt.*;

public class FarmerStatsPanel extends JPanel {
	
	private FarmerSimulatorGame game;
	private JLabel levelLabel;
	private JLabel moneyLabel;
	private JProgressBar xpBar;

	public FarmerStatsPanel(FarmerSimulatorGame game, int x, int y, int width, int height) {

		this.game = game;

		setBounds(x, y, width, height);
		setLayout(new GridLayout(3, 1));
		

		levelLabel = new JLabel("Level: ");
		moneyLabel = new JLabel("Money: ");
		xpBar = new JProgressBar(0, 100);
		xpBar.setStringPainted(true);

		add(levelLabel);
		add(moneyLabel);
		add(xpBar);
		updateStats();
	}

	public void updateStats() {
		Farmer farmer = game.getFarmer();
		if (farmer == null) {
		} 
		else {
			setBorder(BorderFactory.createTitledBorder(game.getFarmer().getName() + "'s Stats"));
			levelLabel.setText("Level: " + farmer.getLevel());
			moneyLabel.setText("Money: " + farmer.getMoney());
			int xpPercent = (int) farmer.getXp();
			xpBar.setValue(xpPercent);
			xpBar.setString(xpPercent + "% XP");
		}
	}
}