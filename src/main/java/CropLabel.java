import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CropLabel extends JLabel {

	private String cropType;
	private BufferedImage fieldImage;
	private BufferedImage cropImage;

	public CropLabel(String cropType) {
		setPreferredSize(new Dimension(50, 50));
		setCropType(cropType);
		try {
			fieldImage = ImageIO.read(new File("resources/field.png"));
			fieldImage = resizeImage(fieldImage, 50, 50);
		} catch(Exception e) {
			System.out.println("Error loading field image: " + e.getMessage());
		}
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;

		if(cropType.toLowerCase().equals("field")) {
			//System.out.println("hi");
			cropImage = null;
			repaint();
			return;
		}

		try {
			BufferedImage img = ImageIO.read(new File("resources/" + cropType.toLowerCase() + ".png"));
			cropImage = resizeImage(img, 50, 50);
		} catch(Exception e) {
			System.out.println("Error loading crop image: " + e.getMessage());
			cropImage = null;
		}
		repaint();
	}

	public String getCropType(){
		return cropType;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(fieldImage != null) {
			g.drawImage(fieldImage, 0, 0, null);
		}
		if(cropImage != null) {
			g.drawImage(cropImage, 0, 0, null);
		}
	}

	public BufferedImage resizeImage(BufferedImage original, int width, int height){
		Image tmp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
	
	public void setGrowthStage(double stage) {
		if(cropType == null || cropType.equals("field")) {
			cropImage = null;
			repaint();
			return;
		}
		int visualStage = Math.min((int)(stage * 4), 3);
		try {
			BufferedImage img = ImageIO.read(new File("resources/" + cropType.toLowerCase() + "_stage" + visualStage + ".png"));
			cropImage = resizeImage(img, 50, 50);
		} catch (Exception e) {
			System.out.println("Error loading image for stage: " + e.getMessage());
		}
		repaint();
	}

}
