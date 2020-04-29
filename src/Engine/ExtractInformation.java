package Engine;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExtractInformation {

    private ArrayList<Element> elements = new ArrayList<>();
    private ArrayList<Pair<Element, Element>> elementsInRelations = new ArrayList<>();

    ExtractInformation(ArrayList<String> textTab){

        ArrayList<String> textTabHolder = textTab;
        extractPossibleElements(textTabHolder);
        textTabHolder.remove(0);
        extractElementsInRelations(textTabHolder);
    }

    private void extractPossibleElements(ArrayList<String> textTab){

        String line = textTab.get(0);

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()){

            elements.add(new Element(matcher.group()));

        }

    }

    private void extractElementsInRelations(ArrayList<String> textTab){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");

        for (String l : textTab){

            Matcher matcher = pattern.matcher(l);

            Element[] singlePair = new Element[2];

            int counter = 0;
            while (matcher.find()){

                singlePair[counter++] = new Element(matcher.group());

            }

            elementsInRelations.add(new Pair<>(singlePair[0], singlePair[1]));

        }

    }

    ArrayList<Element> getElements() {
        return elements;
    }

    ArrayList<Pair<Element, Element>> getElementsInRelations() {
        return elementsInRelations;
    }
}
