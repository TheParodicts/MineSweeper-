import java.util.Random;

public class Mines {
	private Random generator = new Random();
	public int []mineArray;
	
	public Mines(int numMines){
		mineArray = new int [numMines];
		for (int i=0; i<numMines; i++){
			mineArray[i]= generator.nextInt(10);
		}
	}
	
	public int getCoord(int i){
		return this.mineArray[i];
	}
	

}
