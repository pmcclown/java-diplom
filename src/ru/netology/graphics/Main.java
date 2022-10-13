package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextColorSchemaImpl;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImpl;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new TextGraphicsConverterImpl(); // Создайте тут объект вашего класса конвертера

//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        converter.setMaxHeight(300);
//        converter.setMaxWidth(300);
//        converter.setMaxRatio(2);
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
    }
}
