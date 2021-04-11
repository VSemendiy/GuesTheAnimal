package animals;

import java.time.LocalTime;
import java.util.*;

import static animals.Multilang.getProp;
import static animals.Grammar.*;

class Dialogs {
    static final Scanner SC = new Scanner(System.in);

    static BinaryTree askAboutAnimal(BinaryTree bt) {
        int key = bt.root();

        while (true) {

            System.out.println(getProp("game.think"));
            System.out.println(getProp("game.enter"));
            SC.nextLine();
            int lastQuestionKey = 0;
            Boolean lastQuestionAnswer = null;
            while (!bt.isLeaf(key)) {
                lastQuestionKey = key;
                String fact = bt.getValue(key);
                System.out.println(factToQuestion(fact));
                lastQuestionAnswer = checkNegOrPosUserAnswer();
                if (lastQuestionAnswer) key = bt.getRight(key);
                else key = bt.getLeft(key);
            }

            String firstAnimal = bt.getValue(key);
            System.out.printf(getProp("game.try"), firstAnimal);
            if (checkNegOrPosUserAnswer()) {
                System.out.println(sayRandomPhrase("game.win"));
            } else {
                System.out.println(getProp("game.giveUp"));
                String secondAnimal = indefiniteArticle(SC.nextLine().toLowerCase());
                bt.add(secondAnimal);

                String fact = specifyFact(firstAnimal, secondAnimal);

                System.out.printf(getProp("game.isCorrect"), secondAnimal);
                boolean turn = checkNegOrPosUserAnswer();
//                System.out.println(sayRandomPhrase("animal.nice") + " " + getProp("animal.learnedMuch"));
                if (turn) {
                    System.out.println(negativeFact(fact, firstAnimal));
                    System.out.println(positiveFact(fact, secondAnimal));
                } else {
                    System.out.println(positiveFact(fact, firstAnimal));
                    System.out.println(negativeFact(fact, secondAnimal));
                }
                bt.insert(fact, key, lastQuestionKey, lastQuestionAnswer, turn);
            }
            System.out.println(sayRandomPhrase("game.again"));
            if (checkNegOrPosUserAnswer()) key = 2;
            else break;

        }
        return bt;
    }

    static String specifyFact(String firstAnimal, String secondAnimal) {
        String fact;
        do {
            System.out.printf(getProp("statement.prompt"), firstAnimal, secondAnimal);

            fact = SC.nextLine().toLowerCase();
        } while (!fact.matches(getProp("statement.isCorrect")));
        return fact;
    }

    static boolean checkNegOrPosUserAnswer() {
        while (true) {
            String userAnswer = SC.nextLine().trim().toLowerCase();



            if (userAnswer.matches(getProp("positiveAnswer"))) return true;
            if (userAnswer.matches(getProp("negativeAnswer"))) return false;
            System.out.println(sayRandomPhrase("ask.again"));
        }
    }

    static void sayHi() {
        List<String> hi = new ArrayList<>();
        hi.addAll(List.of(getProp("greeting").split("\f")));

        LocalTime mornStart = LocalTime.parse(getProp("time.morning.start"));
        LocalTime aftStart = LocalTime.parse(getProp("time.afternoon.start"));
        LocalTime evnStart = LocalTime.parse(getProp("time.evening.start"));
        LocalTime nightStart = LocalTime.parse(getProp("time.night.start"));
        LocalTime earlyStart = LocalTime.parse(getProp("time.early.start"));

        LocalTime now = LocalTime.now();

        if (now.isAfter(mornStart.minusSeconds(1)) && now.isBefore(aftStart)) hi.add(getProp("greeting.morning"));
        else if (now.isAfter(aftStart.minusSeconds(1)) && now.isBefore(evnStart)) hi.add(getProp("greeting.afternoon"));
        else if (now.isAfter(evnStart.minusSeconds(1)) && now.isBefore(nightStart)) hi.add(getProp("greeting.evening"));
        else if (now.isAfter(nightStart.minusSeconds(1)) && now.isBefore(earlyStart)) hi.add(getProp("greeting.night"));
        else hi.add(getProp("greeting.early"));

        System.out.println(hi.stream().skip(new Random().nextInt(hi.size())).findFirst().get());
    }

    static String sayRandomPhrase(String propertyName) {
        String[] property = getProp(propertyName).split("\f");
        return Arrays.stream(property).skip(new Random().nextInt(property.length)).findFirst().get();
    }
}
