package ru.netology.graphics.image;

public class TextColorSchemaImpl implements TextColorSchema {
    @Override
    public char convert(int color) {
        char[] chars = {
                '#', '$', '@', '%', '*', '+', '-', '\''
        };
        return color == 0 ? chars[0] : chars[color / 32];
    }
}