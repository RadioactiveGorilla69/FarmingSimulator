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
        setBackground(new Color(220, 230, 240)); 
        
        initializeInventory();
    }

    public void initializeInventory() {
    	
        ArrayList<Tool> tools = game.getTools();
        
        for(int i = 0; i < tools.size(); i++){
            String toolName = tools.get(i).name;
            JButton toolButton = new JButton(toolName);
            toolButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    game.setSelectedItem(toolName);
                    highlightSelectedButton(toolButton);
                    System.out.println("Selected: " + toolName);
                }
            });
            add(toolButton);
        }

        String[] seeds = {"Wheat", "Carrot"};
        
        for(int i = 0; i < seeds.length; i++){
        	String seed = seeds[i];
            JButton seedButton = new JButton(seed + " Seed");
            seedButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    game.setSelectedItem(seed);
                    highlightSelectedButton(seedButton);
                    System.out.println("Selected: " + seed);
                }
            });
            add(seedButton);
        }

        JButton noneButton = new JButton("None");
        noneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setSelectedItem("");
                highlightSelectedButton(noneButton);
                System.out.println("Selected: None");
            }
        });
        add(noneButton);
    }

    private JButton lastSelectedButton = null;
    
    public void highlightSelectedButton(JButton button) {
        if(lastSelectedButton != null) {
            lastSelectedButton.setBackground(null);
        }
        button.setBackground(new Color(173, 216, 230));
        lastSelectedButton = button;
    }
}