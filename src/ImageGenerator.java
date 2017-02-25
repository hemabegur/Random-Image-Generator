
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageGenerator {

	public void imageGenerator(ArrayList<Integer> randomNumbers) {
		BufferedImage img = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
		// Generate using random numbers
		for (int i : randomNumbers ) {
			int x = i & 127;
			int y = i / 128;
			int r = i << 3 & 0xF8;
			int g = i >> 2 & 0xF8;
			int b = i >> 7 & 0xF8;
		img.setRGB(x, y, (r << 8 | g) << 8 | b);
		}
		// Save the image to disk
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream("RGB.bmp"))) {
			ImageIO.write(img, "bmp", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
