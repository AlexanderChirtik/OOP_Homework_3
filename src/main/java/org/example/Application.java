package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        System.out.println("Выберете формат игры\nМеню:");
        System.out.println("1-цифры");
        System.out.println("2-английские слова");
        System.out.println("3-русские слова");
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите игру");
        int num=in.nextInt();
        List<String> moves = new ArrayList<>();
        AbstractGame game = null;
        switch (num){
            case 1:game=new NumberGame();
                break;
            case 2:game=new WordGame();
                break;
            case 3:game=new WordRussianGame();
                break;
            default:
                System.out.println("Такой игры еще не существует");
        }
        game.start(4,2);
        while(game.getGameStatus().equals(GameStatus.START)){
            System.out.println("Ваш ход");
            String answer=in.next();
            moves.add(answer);
            Answer answerGame=game.inputAnswer(answer);
            if (answerGame.getCows() != null && answerGame.getBulls() != null)
                System.out.println(String.format("Найдено %d коров и %d быков",answerGame.getCows(),answerGame.getBulls()));
            if (game.getGameStatus().equals(GameStatus.START))
                System.out.printf("Количество оставщихся попыток %d\n", game.maxTry - AbstractGame.countTry);
        }
        game.movesHistory(moves,in);
    }
}