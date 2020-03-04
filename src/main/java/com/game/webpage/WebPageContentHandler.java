package com.game.webpage;

import com.game.question.WordForQuestion;

import java.util.ArrayList;
import java.util.List;

public class WebPageContentHandler {

    private List<String> wordsAndDefinitions;


    public WordForQuestion getWordObject() {
        wordsAndDefinitions = WebPageReader.getValuesArray();
        String word = wordsAndDefinitions.get(0);
        String definition = wordsAndDefinitions.get(1);
        return new WordForQuestion(word, definition);
    }

    public List<String> getWrongAnswerOptions() {
        List<String> array = removeWordAndDefinitionForQuestion();
        return leaveOnlyThreeDefinitions(array);
    }

    private List<String> removeWordAndDefinitionForQuestion() {
        List<String> list = new ArrayList<>();
        for (String s : wordsAndDefinitions) {
            if (isNotWordOrRightAnswerForQuestion(s))
                list.add(s);
        }
        return list;
    }

    private boolean isNotWordOrRightAnswerForQuestion(String s) {
        return wordsAndDefinitions.indexOf(s) != 0 &&
                wordsAndDefinitions.indexOf(s) != 1;
    }

    private static List<String> leaveOnlyThreeDefinitions(List<String> list) {
        List<String> definitions = new ArrayList<>();
        int index = 0;
        for (String s : list) {
            if (isDefinition(index))
                definitions.add(s);
            index++;
        }
        return removeForthDefinition(definitions);
    }

    private static boolean isDefinition(int index) {
        return index%2 != 0;
    }

    private static List<String> removeForthDefinition(List<String> definitions) {
        int forthDefinitionIndex = definitions.size()-1;
        definitions.remove(forthDefinitionIndex);
        return definitions;
    }

}