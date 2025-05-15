import java.util.ArrayList;

public class CropField {
	
	private ArrayList<ArrayList<Field>> cropField;
	private int rows;
	private int cols;
	
	public CropField(int rows, int cols){
		cropField = new ArrayList<ArrayList<Field>>();
		for(int r = 0; r < rows; r++){
			ArrayList<Field> fieldRow = new ArrayList<>();
			for(int c = 0; c < cols; c++){
				fieldRow.add(c, new Field());
			}
			cropField.add(fieldRow);
		}
	}

	public Field getField(int row, int col){
		return cropField.get(row).get(col);
	}
	
}
