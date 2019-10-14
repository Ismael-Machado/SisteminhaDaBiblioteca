package biblioteca.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Templates{
	private static BufferedImage bi;
	public static void FundoBg (File f) throws IOException {
		bi = ImageIO.read(f);
	}
	public static class TemplateHome extends JPanel{
		private int x=0,y=0;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g.create();
			Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		    int lar = (int) tela.getWidth();
		    int alt = (int) tela.getHeight();
	        g2.drawImage(bi, x, y, lar, alt-50, null);
	        g2.dispose();
		}
	}
	public static class TemplateForm extends JPanel{
		private int x=-330,y=-60;
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g.create();
			Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		    int lar = (int) tela.getWidth();
		    int alt = (int) tela.getHeight();
	        g2.drawImage(bi, x, y, lar, alt-50, null);
	        g2.dispose();
	    }
	}
}
