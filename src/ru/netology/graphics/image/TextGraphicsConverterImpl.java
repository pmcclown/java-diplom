package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter{
    private int width;
    private int height;
    private double maxRatio;

    private TextColorSchema schema = new TextColorSchemaImpl();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        int imageRatio = img.getWidth() / img.getHeight();
        if (imageRatio > maxRatio) {
            throw new BadImageSizeException(imageRatio, maxRatio);
        }

        int newWidth = width;
        int newHeight = height;
        if (img.getWidth() > width || img.getHeight() > height) {
            int nW = img.getWidth() / width;
            int nH = img.getHeight() / height;
            int max = Math.max(nW, nH);
            newWidth = img.getWidth() / max;
            newHeight = img.getHeight() / max;
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();

        char[][] chars = new char[newHeight][newWidth];
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                chars[h][w] = c;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char[] row : chars) {
            for (char cell : row) {
                result.append(cell).append(cell);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}