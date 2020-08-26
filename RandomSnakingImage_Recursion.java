import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

/*This code produces an image with colorful squiggly lines cascading from top to bottom. 
It is an excercise in recursive graphics completed as part of Choate's AP CS (Java) curriculum in 2015-2016.*/ 

public class RandomSnakingImage_Recursion {
	private static Random rand = new Random();
	private static final int WIDTH = 256;
	private static final int HEIGHT = 256;
	private static BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public static void main(String[] args) {
		new RandomSnakingImage_Recursion();
	}

	//Drawing and saving the image.
	//Using random numbers as input parameters for drawSnake() to achieve our desired aesthetic. 
	public RandomSnakingImage_Recursion() {
		drawBackground();
		for (int i=0; i<100; i++) {
			drawSnake(rand.nextInt(WIDTH), 0, rand.nextInt(255),rand.nextInt(255),rand.nextInt(255), rand.nextInt(HEIGHT*2)+HEIGHT*2, 0);
		}

		saveFile("SnakingImage_recursion.png");
	}

	/*Direction variables: x,y
	Color variables: r,g,b
	Iteration variables: max_iter, iterations
	Recursively call function until base condition is met. That's when the drawing is done. */
	public void drawSnake(int x, int y, int r, int g, int b, int max_iter, int iterations) {
		//System.out.println("Location: "+ x+", "+y);
		if (x>=0 && x<WIDTH-1 && y>=0 && y<HEIGHT-1 && max_iter>iterations) {
			colorPixel(r,g,b,x,y);
			int direction = rand.nextInt(4);
			if (direction==0) { // left
				x--;
			} else if (direction == 1) {
				x++;
			} else if (direction == 2) {
				y++;
			} 
			iterations++;
			drawSnake(x, y, r, g, b, max_iter, iterations);
		}
	}

	//Draw a black background. 
	public void drawBackground() {
		for (int row=0; row<HEIGHT; row++) {
			for (int column = 0; column<WIDTH; column++) {
				colorPixel(0,0,0,column,row);
			}
		}
	}

	//Determine pixel's color. 
	public void colorPixel(int r, int g, int b, int columns, int rows) {
		int color = (r << 16) + (g << 8) + b;
		img.setRGB(columns, rows, color);
	}

	//Save the image
	public void saveFile(String imagename) {
		File f = new File(imagename);

		try{
			ImageIO.write(img, "PNG", f);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}