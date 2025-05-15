import javax.swing.*;

public class Game {

	private CropField cropField = new CropField(4,4);
	Farmer farmer = new Farmer(TextIO.getlnString());
	Time gameTime = new Time();

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(null);
		frame.add(panel);
		frame.setVisible(true);


	}

}
