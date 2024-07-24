package br.com.igrejaparaiso.Igrejaparaiso.model;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class CropImageToSquare {
    public static void crop(Path path) {
        try {
            File sourceFile = path.toFile();
            BufferedImage originalImage = ImageIO.read(sourceFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            int x, y, cropWidth, cropHeight;
            if(width > height) {
                x = (width - height) / 2;
                y = 0;
                cropWidth = cropHeight = height;
            } else {
                x = 0;
                y = (height - width) / 2;
                cropWidth = cropHeight = width;
            }

            BufferedImage resizedImage = Scalr.crop(originalImage, x, y, cropWidth, cropHeight);

            ImageIO.write(resizedImage, "jpg", sourceFile);

            originalImage.flush();
            resizedImage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
