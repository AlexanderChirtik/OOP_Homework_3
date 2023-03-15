package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class NumberGame extends AbstractGame{
    @Override
    String generateWord() {
        List<String> charList = generateCharList();
        SecureRandom random = new SecureRandom();
        String res = "";
        for (int i = 0; i < sizeWord; i++) {
            int randomeIndex = random.nextInt(charList.size());
            res += charList.get(randomeIndex);
            charList.remove(randomeIndex);
        }
        return res;
    }


    List<String> generateCharList() {
        List <String> charList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            charList.add(String.valueOf(i));
        }
        return charList;
    }
}
