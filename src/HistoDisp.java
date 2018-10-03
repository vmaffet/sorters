import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class HistoDisp extends JFrame {
	
	int[] data;
	int max;
	int min;
	BufferedImage buff;
	Graphics gBuff;

	public HistoDisp () {
		super("Affichage serie");
		setSize(619, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		data= new int[1];
		
		setVisible(true);
	}
	
	public void setData (int[] input) {
		data= new int[input.length];
		max= input[0];
		min= max;
		for (int i= 0; i<data.length; i++) {
			data[i]= input[i];
			if (input[i] > max) {
				max= input[i];
			} else if (input[i] < min) {
				min= input[i];
			}
		}
		repaint();
	}
	
	public void paint (Graphics g) {
		int gauche= getInsets().left;
		int droite= getInsets().right;
		int haut= getInsets().top;
		int bas= getInsets().bottom;
		int largeur= getWidth()-gauche-droite;
		int hauteur= getHeight()-haut-bas;
		double dl= (largeur-data.length+1)/(data.length);
		buff= new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
		gBuff= buff.getGraphics();
		gBuff.setColor(new Color(202,202,202));
		gBuff.fillRect(0, 0, largeur, hauteur);
		gBuff.setColor(Color.blue);
		for (int i= 0; i<data.length; i++) {
			gBuff.fillRect((i*(int)(dl+1)), hauteur-(int)(hauteur*(data[i]-min+(int)(0.004*hauteur))/(max-min+(int)(0.004*hauteur))), (int)dl, (int)(hauteur*(data[i]-min+(int)(0.004*hauteur))/(max-min+(int)(0.004*hauteur))));
		}
		g.drawImage(buff, gauche, haut, this);
	}
	
	public static void main (String[] args) {
		HistoDisp h= new HistoDisp();
		int[] tab= {25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		h.setData(tab);
	}
}
