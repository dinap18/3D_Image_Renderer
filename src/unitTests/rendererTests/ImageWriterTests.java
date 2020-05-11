package unitTests.rendererTests;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;

import java.awt.*;

public class ImageWriterTests {
    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("first test", 1600, 1000, 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.PINK);
                } else {
                    imageWriter.writePixel(j, i, Color.WHITE);
                }
            }
        }
        imageWriter.writeToImage();
    }
}
