package animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static animals.Dialogs.*;
import static animals.Grammar.indefiniteArticle;
import static animals.ImExPort.exportBinTree;
import static animals.ImExPort.importBinTree;
import static animals.Multilang.getProp;

public class Main {

    public static void main(String[] args) {

//        Locale.setDefault(Locale.forLanguageTag("eo"));

        String type;
        try {
            type = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            type = "json";
        }

        sayHi();

        BinaryTree bt = importBinTree(type);

        if (bt.root() == -1) {
            System.out.printf("%s%n%s%n", getProp("animal.wantLearn"), getProp("animal.askFavorite"));
            bt.add(indefiniteArticle(SC.nextLine().toLowerCase()));
            System.out.println();
        }
        procMenu(bt);

        exportBinTree(bt, type);

        System.out.println(sayRandomPhrase("farewell"));
    }

    public static void printMenu() {
        System.out.println(getProp("menu"));
    }

    public static void procMenu(BinaryTree bt) {
        System.out.println(getProp("welcome"));
        String command;
        do {
            printMenu();
            command = SC.nextLine();
            switch (command) {
                case "1":
                    bt = askAboutAnimal(bt);
                    break;
                case "2":
                    System.out.println(getProp("tree.list.animals"));
                    bt.printLeaves();
                    break;
                case "3":
                    System.out.println(getProp("animal.prompt"));
                    String animalForSearch = SC.nextLine();
                    List<String> facts = bt.searchByValue(indefiniteArticle(animalForSearch));
                    if (facts.isEmpty()) System.out.printf(getProp("tree.search.noFacts"), animalForSearch);
                    else {
                        System.out.printf(getProp("tree.search.facts"), animalForSearch);
                        facts.forEach(System.out::println);
                    }
                    break;
                case "4":
                    List<String> stats = new ArrayList<>();
                    stats.addAll(Arrays.asList(getProp("tree.stats").split("\f")));
                    stats.addAll(bt.getStatistics());
                    System.out.println(stats.get(0));
                    for (int i = 1; i < 8; i++) {
                        System.out.printf(stats.get(i), stats.get(i + 7));
                    }
                    break;
                case "5":
                    bt.printTree();
                    break;
                case "0":
                    break;
                default:
                    System.out.println(getProp("menu.property.error")+"\n");
            }
        } while (!command.equals("0"));
    }
}

