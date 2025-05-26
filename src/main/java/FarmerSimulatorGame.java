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
	private Time gameTime;
	private ArrayList<Tool> tools;
	private String selectedItem;
	private InventoryPanel inventoryPanel;
	
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
		
		gameTime = new Time();
		
		tools = new ArrayList<Tool>();
		
		selectedItem = "";
		
		inventoryPanel = new InventoryPanel(this, 600, 300, 200, 300);
	    gamePanel.add(inventoryPanel);

	    textFieldButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String input = textField.getText();
	            if(input.trim().length() != 0) {
	                farmer = new Farmer(input);
	                cardLayout.show(cardPanels, "gamepanel");
	            }
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
		gameTimer = new Timer(1000, e -> {
			gameTime.addTime(0.01);
			cropField.update();
			gamePanel.repaint();
		});
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
		tools.add(new Tool("Basic Hoe", 0, 10));
		tools.add(new Tool("Basic Sickle", 0, 10));
		tools.add(new Tool("Basic Shovel", 0, 10));
		tools.add(new Tool("Rare Hoe", 1, 25));
		tools.add(new Tool("Rare Sickle", 1, 25));
		tools.add(new Tool("Rare Shovel", 1, 25));
	}
	
	public void setSelectedItem(String item) {
		this.selectedItem = item;
	}

	public String getSelectedItem() {
		return selectedItem;
	}
	
	//ADD FUNCTIONALITY FOR FARMER SELECTING TOOL/CROP SEED
	//ADD SELLING AND LEVELING FUNCTIONALITY
	//ADD DIFF PIXEL TEXTURES FOR GROWTH STAGES
	//ADD MORE CROPS
}
