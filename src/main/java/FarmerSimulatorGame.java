//Made by Jason
//All textures made by Jason as well
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FarmerSimulatorGame {

	private JFrame frame;
	private JPanel namePanel;
	private JPanel gamePanel;
	private CardLayout cardLayout;
	private JPanel cardPanels;
	private JTextField textField;
	private JButton textFieldButton;
	private JLabel textLabel;
	private CropField cropField;
	private Farmer farmer;
	private Timer gameTimer;
	private ArrayList<Tool> tools;
	private String selectedItem;
	private InventoryPanel inventoryPanel;
	private FarmerStatsPanel farmerStatsPanel;
	private ShopPanel shopPanel;

	public FarmerSimulatorGame(){
		initializeGame();
		initializeFrame();
		startTimer();
	}


	public static void main(String[] args) {
		new FarmerSimulatorGame();
	}

	public void initializeGame(){
		frame = new JFrame();

		gamePanel = new JPanel(null);

		cardLayout = new CardLayout();
		cardPanels = new JPanel();
		cardPanels.setLayout(cardLayout);

		namePanel = new JPanel(null);
		textField = new JTextField(20);
		textLabel = new JLabel("Enter your name:");
		textLabel.setBounds(460, 340, 200, 100);
		textField.setBounds(560, 365, 200, 50);
		textFieldButton = new JButton("Enter");
		textFieldButton.setBounds(760, 350, 80, 80);

		namePanel.add(textField);
		namePanel.add(textFieldButton);
		namePanel.add(textLabel);

		cardPanels.add(namePanel, "namepanel");
		cardPanels.add(gamePanel, "gamepanel");

		cropField = new CropField(4, 4, gamePanel, this);

		tools = new ArrayList<Tool>();

		selectedItem = "";

		farmerStatsPanel = new FarmerStatsPanel(this, 900, 200, 200, 100);
		
		inventoryPanel = new InventoryPanel(this, 900, 300, 200, 300);

		setTools();

		shopPanel = new ShopPanel(this, 1100, 200, 400, 400);
		
		textFieldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				if(input.trim().length() != 0) {
					farmer = new Farmer(FarmerSimulatorGame.this, input);
					cardLayout.show(cardPanels, "gamepanel");
				}
				gamePanel.add(farmerStatsPanel);
				farmerStatsPanel.updateStats();
				gamePanel.add(inventoryPanel);
				inventoryPanel.updateInventory();
				gamePanel.add(shopPanel);
			}
		});
	}

	public void initializeFrame(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(cardPanels);
		frame.setVisible(true);
	}

	public void startTimer(){
		
	    ActionListener timerUpdate = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cropField.update();
	            farmerStatsPanel.updateStats();
	            inventoryPanel.updateInventory();
	            gamePanel.repaint();
	        }
	    };

	    gameTimer = new Timer(1000, timerUpdate);
	    gameTimer.start();
	}


	public CropField getCropField(){
		return cropField;
	}

	public Farmer getFarmer(){
		return farmer;
	}

	public ArrayList<Tool> getTools(){
		return tools;
	}

	public void setTools(){
		tools.add(new Tool(this, "Basic Hoe", 20));
		tools.add(new Tool(this, "Basic Sickle", 40));
		tools.add(new Tool(this, "Basic Shovel", 60));
		tools.add(new Tool(this, "Rare Hoe", 100));
		tools.add(new Tool(this, "Rare Sickle", 200));
		tools.add(new Tool(this, "Rare Shovel", 500));
	}

	public void setSelectedItem(String item) {
		this.selectedItem = item;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public FarmerStatsPanel getFarmerStatsPanel() {
		return farmerStatsPanel;
	}

	public InventoryPanel getInventoryPanel() {
		return inventoryPanel;
	}
	
	public void showWinScreen() {
	    gamePanel.removeAll();
	    gamePanel.setLayout(new BorderLayout());
	    JLabel winLabel = new JLabel("Congrats, you have beat the game", SwingConstants.CENTER);
	    gamePanel.add(winLabel, BorderLayout.CENTER);
	    gamePanel.revalidate();
	    gamePanel.repaint();
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}
}
