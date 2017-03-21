import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
public int numMines=20;
	
	public Mines minesX = new Mines(numMines);
	public Mines minesY = new Mines(numMines);
	
	
	
	
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
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
			//Do nothing
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
							//On the grid other than on the left column and on the top row:
						if(myPanel.colorArray[gridX][gridY].equals(Color.RED))	{
							//If it is flagged (Red), do nothing.
						}
						else if (myPanel.colorArray[gridX][gridY].equals(Color.WHITE)){//Clicked on a non-flagged box.
							int xCoord;
							int yCoord;
							int i=0;
							boolean mineHere=false;
							do {
								 xCoord = minesX.getCoord(i);
								 yCoord = minesY.getCoord(i);
								 if(gridX==xCoord && gridY == yCoord){
									
										mineHere = true;// If there is a mine here. Write missing code.
								 }
								 i++;
								 System.out.println("checked for mine");
							}while (i<numMines);
							
							if (mineHere == true){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();
							}
							
							else{
							Color newColor = Color.LIGHT_GRAY;
							
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
							}
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
			if ((gridXRightClick == -1) || (gridYRightClick == -1)) {//Pressed outside of grid.
					
					//Do nothing
					System.out.println("Right Clicked outside.");
			}
			else if(!(myPanelRightClick.mouseDownGridX == -1) && !(myPanelRightClick.mouseDownGridY == -1)){
				System.out.println("Right Clicked inside grid.");
				if(myPanelRightClick.colorArray[gridXRightClick][gridYRightClick].equals(Color.WHITE)){
					myPanelRightClick.colorArray[gridXRightClick][gridYRightClick] = Color.RED;
					myPanelRightClick.repaint();
					System.out.println("Turned Red");//yeah
				}
				else if(myPanelRightClick.colorArray[gridXRightClick][gridYRightClick].equals(Color.RED)){
					myPanelRightClick.colorArray[gridXRightClick][gridYRightClick] = Color.WHITE;
					myPanelRightClick.repaint();
					System.out.println("Unflagged.");
				}
			}
			break; 
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}