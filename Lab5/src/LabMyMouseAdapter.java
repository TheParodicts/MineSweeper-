import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class LabMyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {// Returns which mouse button was clicked.
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component c1RightClick = e.getComponent();
			while (!(c1RightClick instanceof JFrame)) {
				c1RightClick = c1RightClick.getParent();
				if (c1RightClick == null) {
					return;
				}
			}
			JFrame myFrameRightClick = (JFrame) c1RightClick;
			MyPanel myPanelRightClick = (MyPanel) myFrameRightClick.getContentPane().getComponent(0);
			Insets myInsetsRightClick = myFrameRightClick.getInsets();
			int x1RightClick = myInsetsRightClick.left;
			int y1RightClick = myInsetsRightClick.top;
			e.translatePoint(-x1RightClick, -y1RightClick);
			int xRightClick = e.getX();
			int yRightClick = e.getY();
			myPanelRightClick.x = xRightClick;
			myPanelRightClick.y = yRightClick;
			myPanelRightClick.mouseDownGridX = myPanelRightClick.getGridX(xRightClick, yRightClick);
			myPanelRightClick.mouseDownGridY = myPanelRightClick.getGridY(xRightClick, yRightClick);
			myPanelRightClick.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						
						int colorCaseToPaint;
						int colorComparisionPointer =9;
						Color currentColor;
						Color []colorComparisonArray = new Color[5] ;
						colorComparisonArray[0] = Color.YELLOW;
						colorComparisonArray[1] = Color.MAGENTA;
						colorComparisonArray[2] = Color.BLACK;
						colorComparisonArray[3] = new Color (0x964B00);
						colorComparisonArray[4] = new Color (0xB57EDC);
						Color newColor = null;
						
						
						
						if (gridY == 0){// on the top row
							System.out.println("top row click");
							if(gridX == 0 ){//On the top row of the left column.
								System.out.println("Top left click");
								
								for(int i =1; i<10; i++){	
									currentColor = myPanel.colorArray[i][i];
									for (int h=0; h<5; h++){
										if(colorComparisonArray[h].equals(currentColor)){
											colorComparisionPointer = h;
											break;
										}
										else colorComparisionPointer = 6;
									}
									do{//Changes the color to be painted if it's the same color as the current square.
										colorCaseToPaint=generator.nextInt(5); 
									}while (colorCaseToPaint==colorComparisionPointer);
								
									switch (colorCaseToPaint) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								myPanel.colorArray[i][i] = newColor;
								myPanel.repaint();
								}
							}
								
							else{//On the top row, not on the left-most square.
								
								for(int i =1; i<10; i++){	
									currentColor = myPanel.colorArray[myPanel.mouseDownGridX][i];
									for (int h=0; h<5; h++){
										if(colorComparisonArray[h].equals(currentColor)){
											colorComparisionPointer = h;
											break;
										}
										else colorComparisionPointer = 6;
									}
									
									do{//Changes the color to be painted if it's the same color as the current square.
										colorCaseToPaint=generator.nextInt(5); 
									}while (colorCaseToPaint==colorComparisionPointer);
								
									switch (colorCaseToPaint) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor;
								myPanel.repaint();
								}
							}
						}
						
						else if (gridX == 0){//On the left column.
							
							if(gridY ==10){//bottom row of the left column.
								System.out.println("Bottom left click");
								for(int i =4; i<7; i++){	
									for(int k =4; k<7; k++){
										currentColor = myPanel.colorArray[i][k];
										for (int h=0; h<5; h++){
									
											if(colorComparisonArray[h].equals(currentColor)){
												colorComparisionPointer = h;
												break;
											}
											else colorComparisionPointer = 6;
										}
										do{//Changes the color to be painted if it's the same color as the current square.
											colorCaseToPaint=generator.nextInt(5); 
										}while (colorCaseToPaint==colorComparisionPointer);
									
										switch (colorCaseToPaint) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA;
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}
									myPanel.colorArray[i][k] = newColor;
									myPanel.repaint();
									}
								}
							}
							
							else{// Between the top and bottom rows of the left column.	
								System.out.println("Left row click");
								for(int i =1; i<10; i++){	
									currentColor = myPanel.colorArray[i][myPanel.mouseDownGridY];
									for (int h=0; h<5; h++){
										if(colorComparisonArray[h].equals(currentColor)){
											colorComparisionPointer = h;
											break;
										}
										else colorComparisionPointer = 6;
									}
									
									do{//Changes the color to be painted if it's the same color as the current square.
										colorCaseToPaint=generator.nextInt(5); 
									}while (colorCaseToPaint==colorComparisionPointer);
								
									switch (colorCaseToPaint) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
								}
							}
						}
					
						 else {
							//On the grid other than on the left column and on the top row:
							 System.out.println("Individual square click");
							currentColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
							
							for (int i=0; i<5; i++){
								if(colorComparisonArray[i].equals(currentColor)){
									colorComparisionPointer = i;
									break;
								}
								else colorComparisionPointer = 6;
							}
							do{
								colorCaseToPaint=generator.nextInt(5);
							}while (colorCaseToPaint==colorComparisionPointer);
						
							switch (colorCaseToPaint) {
							case 0:
								newColor = Color.YELLOW;
								break;
							case 1:
								newColor = Color.MAGENTA;
								break;
							case 2:
								newColor = Color.BLACK;
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							}
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
							
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component cRightClick = e.getComponent();
			while (!(cRightClick instanceof JFrame)) {
				cRightClick = cRightClick.getParent();
				if (cRightClick == null) {
					return;
				}
			}
			JFrame myFrameRightClick = (JFrame)cRightClick;
			MyPanel myPanelRightClick = (MyPanel) myFrameRightClick.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsRightClick = myFrameRightClick.getInsets();
			int x1RightClick = myInsetsRightClick.left;
			int y1RightClick = myInsetsRightClick.top;
			e.translatePoint(-x1RightClick, -y1RightClick);
			int xRightClick = e.getX();
			int yRightClick = e.getY();
			myPanelRightClick.x = xRightClick;
			myPanelRightClick.y = yRightClick;
			int gridXRightClick = myPanelRightClick.getGridX(xRightClick, yRightClick);
			int gridYRightClick = myPanelRightClick.getGridY(xRightClick, yRightClick);
			if ((myPanelRightClick.mouseDownGridX == -1) || (myPanelRightClick.mouseDownGridY == -1)) {//Pressed outside of grid.
				if (!(gridXRightClick == -1) || !(gridYRightClick == -1)) {
					//Is releasing inside grid
					//Do nothing
					System.out.println("Right Clicked outside, but released inside");
				} 
				else{
					System.out.print("Right Clicked outside.");
					int colorCaseToPaint;
					int colorComparisionPointer =9;
					Color currentColor;
					Color []colorComparisonArray = new Color[3] ;
					colorComparisonArray[0] = Color.RED;
					colorComparisonArray[1] = Color.GREEN;
					colorComparisonArray[2] = Color.BLUE;
					Color newColor = null;
					
					for(int i =1; i<10; i++){	
						for(int k =1; k<10; k++){
							currentColor = myPanelRightClick.colorArray[i][k];
							for (int h=0; h<3; h++){
						
								if(colorComparisonArray[h].equals(currentColor)){
									colorComparisionPointer = h;
									break;
								}
								else colorComparisionPointer = 6;
							}
							do{//Changes the color to be painted if it's the same color as the current square.
								colorCaseToPaint=generator.nextInt(3); 
							}while (colorCaseToPaint==colorComparisionPointer);
						
							switch (colorCaseToPaint) {
							case 0:
								newColor = Color.RED;
								break;
							case 1:
								newColor = Color.GREEN;
								break;
							case 2:
								newColor = Color.BLUE;
								break;
							}
						myPanelRightClick.colorArray[i][k] = newColor;
						myPanelRightClick.repaint();
						}
					}
				}
			}
			else System.out.print("Right Clicked inside grid.");
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}