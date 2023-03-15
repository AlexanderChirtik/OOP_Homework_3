package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Scanner;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game {
    Integer sizeWord;
    String word;

    Integer maxTry;

    static int countTry;
    GameStatus gameStatus = GameStatus.INIT;


    @Override
    public void start (Integer sizeWord, Integer maxTry) {
        this.maxTry = maxTry;
        this.sizeWord = sizeWord;
        word = generateWord();
        this.gameStatus = GameStatus.START;
    }

    @Override
    public Answer inputAnswer(String value) {
        if (value.length() == sizeWord){
            int bull = 0;
            int cow = 0;
            for (int i = 0; i < value.length(); i++) {
                if (word.contains(Character.toString(value.charAt(i)))) {
                    cow++;
                }
                if (word.charAt(i) == value.charAt(i)) {
                    bull++;
                }
            }
            countTry++;
            Answer answer = new Answer(cow, bull, value);
            gameStatus = checkState(value);
            return answer;
        }
        else if (value.length() < sizeWord || value.length() > sizeWord){
            System.out.println("Вы ввели неверное количество символов");
            return new Answer();
        }
        return null;
    }

    private GameStatus checkState(String value) {
        if (value.equals(word)) {
            System.out.printf("Вы угадали слово - %s\n", word);
            return gameStatus = GameStatus.WIN;
        } else {
            if (countTry >= maxTry) {
                System.out.printf("Попытки закончились! Слово было - %s\n", word);
                return gameStatus = GameStatus.LOSE;
            } else {
                return gameStatus = GameStatus.START;
            }
        }

    }

    /**
     * Метод направлен на получение ответа от пользователя, хочет он или не хочет выводить на экран свои предыдущие ответы
     * @param moves Список с сохраненными ответами игрока
     * @param in Объект класса Scanner, созданный с класса Application. Необходим чтобы не создавать новый.
     */
    public void movesHistory(List<String> moves, Scanner in){
        System.out.println("Вы хотите вывести историю Ваших ходов?\nНажмите 'yes' если хотите, или 'no' если нет");
        String answer = in.next();
        if (answer.equals("yes")){
            for (int i = 0; i < moves.size(); i++) {
                System.out.println(moves.get(i));
            }
        }
    }
    abstract String generateWord();

}
