package animals;

import static animals.Multilang.getProp;

class Grammar {
    static String indefiniteArticle(String animal) {
        animal = animal.replaceFirst(getProp("articleDel"), "");

        try {
            String art = getProp("articleIndAn");
            if (null != art && animal.matches(art)) return "an " + animal;
        } catch (NullPointerException e){}

        try {
            String art = getProp("articleIndA");
            if (art != null && animal.matches(art)) return "a " + animal;
        } catch (NullPointerException e){}
        return animal;
    }

    static String removeArticle(String word) {
        return word.replaceAll("(an)|a\\s", "");
    }

    static String positiveFact(String fact, String animal) {
        animal = getProp("articleDefAdd") + " " + animal.replaceFirst(getProp("articleDel"), "");
        fact = fact.replaceFirst(getProp("pronounPatt"), animal).replaceAll("[!?.]", "") + ".";
        return fact;
    }

    static String negativeFact(String fact, String animal) {
        fact = positiveFact(fact, animal);
        int count = 0;
        while (true) {
            count++;
            try {
                fact = fact.replaceAll(getProp("negative." + count + ".pattern"), getProp("negative." + count + ".replace"));
            } catch (NullPointerException e) {
                break;
            }
        }
        return fact;
    }

    static String factToQuestion(String fact) {
        int count = 0;
        while (true) {
            count++;
            try {
                fact = fact.replace(getProp("question." + count + ".pattern"), getProp("question." + count + ".replace"));
            } catch (NullPointerException e) {
                break;
            }
        }
        fact = " - " + fact.replaceAll("[!?.]", "") + "?";
        return fact;
    }
}
