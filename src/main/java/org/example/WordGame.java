package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.apache.commons.io.FileUtils;
import java.nio.charset.*;


public class WordGame extends AbstractGame{


    /**
     * Проход по всем элементам массива строк. Если длина слова равна sizeWord, слово добавляется в созданный заранее список.
     * Затем с помощью random выбирается число, не превышающее размер списка.
     * @return Возвращается элемент списка с индексом random.
     */
    @Override
    String generateWord() {
        List <String> charList = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == sizeWord)
                charList.add(words[i]);
        }
        int choice = random.nextInt(charList.size());
        return charList.get(choice);
    }


    private String [] words = splitText();

    public String[] getWords(){
        return words;
    }

    /**
     * @return Возвращение массива строк (слова из файла) с использованием try catch, путем разделения строки из метода readFile.
     * Разделение идет через пробел, так как в словаре все слова записаны поочередно через пробел
     */
    private String []  splitText (){
        String tempText;
        try {
            tempText = readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tempText.split(" ");
    }

    /**
     *
     * @return Возвращение строки, созданной после прочтения указанного файла в строку, используя указанную кодировку
     * @throws IOException
     */
    private String readFile() throws IOException {
        File file = new File("C:\\Users\\александр\\Desktop\\OOP_Homework_3\\src\\main\\java\\org\\example\\Dictionaries\\WordGame.txt");
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

}
