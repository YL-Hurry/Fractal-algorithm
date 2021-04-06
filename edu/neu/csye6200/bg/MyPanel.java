package edu.neu.csye6200.bg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*author name:YUE LIU
NU number:001353606
*/

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	static int ctr = 0;
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, size.width, size.height);	//fill the entire panel
		
		g2d.setColor(Color.red);
		//g2d.drawLine(0, 0, size.width, size.height);
		
		//draw all the stem
		if (ctr < Stem.root.stemList.size()) {
			int i = 0;
			while( i < ctr ) {
				Stem st = Stem.root.stemList.get(i);
				g2d.drawLine((int) (st.x1), (int) (st.y1), (int) (st.x2), (int) (st.y2));
				i++;
			}
		} else {
			for (Stem st: Stem.root.stemList) {
				g2d.drawLine((int) (st.x1), (int) (st.y1), (int) (st.x2), (int) (st.y2));
			}
		}
	}

}
