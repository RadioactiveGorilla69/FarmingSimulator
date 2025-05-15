import javax.swing.*;

public class Game {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);

		

		CropField cropField = new CropField(4, 4);
		Farmer farmer = new Farmer("get input here");
		Time gameTime = new Time();


	}

}
