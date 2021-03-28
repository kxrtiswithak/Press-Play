package com.sparta.eng80.pressplay.util;

public class TitleCase {
    public static String toTitleCase(String string) {
        if(string == null){
            return "";
        }
        String[] words = string.split(" ");
        StringBuilder titleCase = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            titleCase.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());
            if (i < words.length - 1) {
                titleCase.append(" ");
            }
        }
        return titleCase.toString();
    }

    public static void main(String[] args) {
        System.out.println(toTitleCase("QWERTYU UIOUI dsfghfdsas dsasfsa"));
    }
}
