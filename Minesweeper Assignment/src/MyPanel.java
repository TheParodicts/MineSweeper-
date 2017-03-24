import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;


public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 29;
	private static final int GRID_Y = 30;
	private static final int INNER_CELL_SIZE = 28;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 10;   //Last row has only one cell
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];



	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}

		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;

			}
		}
	}
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;



		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS -1; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS - 1)));
		}
		//Paint the background
		g.setColor(Color.GRAY);
		g.fillRect(x1, y1, width - 1, height -1);


		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS-1; y++) {

				Color c = colorArray[x][y];
				g.setColor(c);
				g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				
				
			}
		}
		
		

		
	}
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return x;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 2) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 2) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}

	public static boolean[][] setMines(int numMines){
		boolean [][] mineArray = new boolean[9][9];
		Random generator = new Random();
		int x; int y;
		for (int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				mineArray[i][j]=false;
			}
		}

		for(int i = 0; i<numMines; i++){
			do{
				x = generator.nextInt(9);
				y = generator.nextInt(9);
				if(mineArray[x][y]== false){
					mineArray[x][y]=true;
					break;
				}
			}while(mineArray[x][y]==true);
		}
		return mineArray;
	}

	public int mineChecker(int x, int y, boolean mines[][], MyPanel myPanel){
		int mineContact=0;
		
		if(mines[x][y]|| myPanel.colorArray[x][y].equals(Color.LIGHT_GRAY)) return mineContact;
		
		for(int i= x-1; i<x+2; i++){
			if(i<0){
				i=0;}
			else if(i>8)
				break;
			for( int j= y-1; j<y+2; j++){
				if(j<0){
					j=0;}
				else if(j>8)
					break;
				if(myPanel.colorArray[i][j].equals(Color.WHITE)||myPanel.colorArray[i][j].equals(Color.RED)){
					if (mines[i][j]==true){
						mineContact++;
					}
				}
			}
		}
		
		
							
		
		
		if (mineContact ==0 ){
			myPanel.colorArray[x][y]=Color.LIGHT_GRAY;

			for(int i=-1; i<2; i++){
				if(x+i<0){
					i=0;
					}
				else if(x+i>8)
					break;
				for(int j =-1; j<2; j++){
					if(y+j<0){
						j=0;
						}
					else if(y+j>8){
						break;
						}
					
					mineChecker(x+i, y+j, mines, myPanel);
					
					System.out.println("Subcount is 0");
				}myPanel.repaint();
			}
		}
		else{
			switch (mineContact) {
			
			case 1:
				colorArray[x][y]=Color.YELLOW;
				
				break;
			case 2:
				colorArray[x][y]=Color.BLUE;
				
				break;
			case 3:
				colorArray[x][y]=Color.CYAN;
				
				break;
			case 4:
				colorArray[x][y]=Color.GREEN;
				
				break;
			case 5:
				colorArray[x][y]=Color.ORANGE;
				
				break;
			case 6:
				colorArray[x][y]=Color.MAGENTA;
				
				break;
			case 7:
				colorArray[x][y]=Color.PINK;
				
				break;
			case 8:
				colorArray[x][y]=new Color(10,100,220);
				
				break;
			

			}

			
			System.out.println("Main count is " +mineContact);
			myPanel.repaint();
		}
		return mineContact;
	}
}