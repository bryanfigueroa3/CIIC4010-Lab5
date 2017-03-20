import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
    private Random generator = new Random();
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
        case 1:        //Left mouse button
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
        case 3:        //Right mouse button
        	Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			myPanelR.mouseDownGridX = myPanelR.getGridX(xR, yR);
			myPanelR.mouseDownGridY = myPanelR.getGridY(xR, yR);
			myPanelR.repaint();
			break;
        default:    //Some other button (2 = Middle mouse button, etc.)
            //Do nothing
            break;
        }
    }
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
        case 1:        //Left mouse button
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
                        if ((gridX == 0) || (gridY == 0)) {
                            //On the left column and on the top row... do nothing
                        } else {
                            //On the grid other than on the left column and on the top row:
                            
                                Color newColor = null;
                            do{
                                switch (generator.nextInt(5)) {
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
                            } while(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor));                        
                            myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
                            myPanel.repaint();
                        }
                    }
                }
            }
            
            if (myPanel.mouseDownGridX == 0 && myPanel.mouseDownGridY != 0) {
                Color newColor = null;
                for(int i=1; i<10; i++){
                    switch (generator.nextInt(5)) {
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
            
            if (myPanel.mouseDownGridX != 0 && myPanel.mouseDownGridY == 0) {
                Color newColor = null;
                for(int i=1; i<10; i++){
                    switch (generator.nextInt(5)) {
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
            
            if (myPanel.mouseDownGridX == 0 && myPanel.mouseDownGridY == 0) {
                Color newColor = null;
                for(int i=1; i<10; i++){
                	for(int j=1; j<10; j++){
                		if(i==j){
		                    switch (generator.nextInt(5)) {
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
                		}
                	}
                myPanel.colorArray[i][i] = newColor;
                myPanel.repaint();
                }
            } 
            
            if (myPanel.mouseDownGridX == 0 && myPanel.mouseDownGridY == 10) {
                Color newColor = null;
                for(int i=4; i<7; i++){
                	for(int j=4; j<7; j++){
	                    switch (generator.nextInt(5)) {
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
	                    myPanel.colorArray[i][j] = newColor;
	                    myPanel.repaint();
                	}	
                }
            } 
            myPanel.repaint();
            break;
        case 3:		//Right mouse button
			Component cR = e.getComponent();
			while (!(cR instanceof JFrame)) {
				cR = cR.getParent();
				if (cR == null) {
					return;
				}
			}
			JFrame myFrameR = (JFrame) cR;
			MyPanel myPanelR = (MyPanel) myFrameR.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsR = myFrameR.getInsets();
			int x1R = myInsetsR.left;
			int y1R = myInsetsR.top;
			e.translatePoint(-x1R, -y1R);
			int xR = e.getX();
			int yR = e.getY();
			myPanelR.x = xR;
			myPanelR.y = yR;
			int gridXR = myPanelR.getGridX(xR, yR);
			int gridYR = myPanelR.getGridY(xR, yR);
			
			if(gridXR < 0 && gridYR < 0){
				
				int randomColorIntR = generator.nextInt(3);
				Color newColor = null;
				
				Color[] newColorArray = new Color[3];
				newColorArray[0] = Color.BLUE;
				newColorArray[1] = Color.RED;
				newColorArray[2] = Color.GREEN;
				
				Color originalColor = myPanelR.colorArray[0][0];
				
				while(newColorArray[randomColorIntR].equals(originalColor)){
					randomColorIntR = generator.nextInt(3);
				}	
				
				switch(randomColorIntR){
				case 0:
					newColor = Color.BLUE;
					break;
				case 1:
					newColor = Color.RED;
					break;
				case 2:
					newColor = Color.GREEN;
					break;
					
				}
				
				for(int i = 0; i< 10; i++){
					myPanelR.colorArray[i][0] = newColor;
				}
				for(int j = 0; j< 11; j++){
					myPanelR.colorArray[0][j] = newColor;
				}
				myPanelR.repaint();
				
			}	
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}