package Engine;

import javafx.util.Pair;

import java.util.ArrayList;

class Relations {

    private boolean[][] matrix;
    private Pair<Element, Element>[][] matrixPattern;

    Relations(boolean[][] matrix, Pair<Element, Element>[][] matrixPattern) {
        this.matrix = matrix;
        this.matrixPattern = matrixPattern;
    }

    void findRelations() {

        if(matrix == null){

            System.out.println("Relacja jest relacją pustą zatem posiada następujące cechy:\n" +
                    "- Przeciwzwrotnosc\n" +
                    "- Symetrycznosc\n" +
                    "- Antysymetrycznosc\n" +
                    "- Asymetrycznosc\n" +
                    "- Przechodniosc");

            return;

        }

        int printCounter = 0;
        System.out.println("Relacja posiada następujące cechy: ");

        if (isReflexive()) {
            System.out.println("- Zwrotnosc");
            ++printCounter;
        }
        if (isIrreflexive()) {
            System.out.println("- Przeciwzwrotnosc");
            ++printCounter;
        }
        if (isSymmetric()) {
            System.out.println("- Symetrycznosc");
            ++printCounter;
        }
        if (isAntisymmetric()) {
            System.out.println("- Antysymetrycznosc");
            ++printCounter;
        }
        if (isAsymmetric()) {
            System.out.println("- Asymetrycznosc");
            ++printCounter;
        }
        if (isTransitive()) {
            System.out.println("- Przechodniosc");
            ++printCounter;
        }
        if (isIdentity()) {
            System.out.println("- Spojnosc");
            ++printCounter;
        }
        if (printCounter == 0) System.out.println("- Relacja nie posiada żadnej z sprawdzanych cech.");


        printCounter = 0;
        System.out.println("Relacja jest relacja: ");

        if (isReflexive() && isAntisymmetric() && isTransitive()) {
            System.out.println("- Porzadku czesciowego");
            ++printCounter;
        }
        if (isReflexive() && isSymmetric()) {
            System.out.println("- Podobienstwa");
            ++printCounter;
        }
        if (isReflexive() && isSymmetric() && isTransitive()) {
            System.out.println("- Rownowaznosci");
            ++printCounter;
            System.out.println("Klasy abstrakcji: ");
            equivalenceRelationAbstractClasses();
        }
        if (printCounter == 0)
            System.out.println("- Brak");
    }

    private boolean isReflexive() {

        for (int i = 0; i < matrix.length; i++) {

            if (!matrix[i][i]) return false;

        }

        return true;

    }

    private boolean isIrreflexive() {

        for (int i = 0; i < matrix.length; i++) {

            if (matrix[i][i]) return false;

        }

        return true;

    }

    private boolean isSymmetric() {

        for (int i = 0; i < matrix.length - 1; i++) {

            for (int j = i + 1; j < matrix.length; j++) {

                if (matrix[i][j] != matrix[j][i]) return false;

            }

        }

        return true;

    }

    private boolean isAntisymmetric() {

        for (int i = 0; i < matrix.length - 1; i++) {

            for (int j = i + 1; j < matrix[i].length; j++) {

                if (matrix[i][j] && matrix[j][i]) return false;

            }

        }

        return true;

    }

    private boolean isAsymmetric() {

        return isAntisymmetric() && isIrreflexive();

    }

    private boolean isTransitive() {

        boolean[][] booleanMultiplicationMatrix = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                boolean value = false;
                for (int k = 0; k < matrix.length; k++) {
                    value = matrix[i][k] && matrix[k][j];
                    if (value)
                        break;
                }
                booleanMultiplicationMatrix[i][j] = value;
            }
        }

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                if (matrix[i][j] != booleanMultiplicationMatrix[i][j]) return false;

            }

        }

        return true;
    }

    private boolean isIdentity() {

        for (int i = 0; i < matrix.length - 1; i++) {

            for (int j = i + 1; j < matrix.length; j++) {

                if (!(matrix[i][j] || matrix[j][i])) return false;

            }

        }

        return true;

    }

    private void equivalenceRelationAbstractClasses() {

        ArrayList<ArrayList<Pair<Element, Element>>> abstractClassesPairHolder = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {

            ArrayList<Pair<Element, Element>> rows = new ArrayList<>();

            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j]) rows.add( matrixPattern[i][j] );

            }

            abstractClassesPairHolder.add(rows);

        }

        ArrayList<ArrayList<Element>> abstractClasses = new ArrayList<>();

        for( ArrayList<Pair<Element, Element>> arr : abstractClassesPairHolder){

            ArrayList<Element> rows = new ArrayList<>();

            for(Pair<Element, Element> pair : arr){

                rows.add(pair.getValue());

            }

            abstractClasses.add( rows );

        }

        ArrayList<ArrayList<Element>> abstractClassesWithoutDuplicates = new ArrayList<>();

        for(ArrayList<Element> elArr : abstractClasses){

            if(!abstractClassesWithoutDuplicates.contains(elArr)){

                abstractClassesWithoutDuplicates.add(elArr);

            }

        }

        for(int i = 0; i < abstractClassesWithoutDuplicates.size(); i++){

            for(int j = 0; j < abstractClassesWithoutDuplicates.get(i).size(); j++){

                if(j == 0) System.out.print("[" + (i + 1) + ".] : " + abstractClassesWithoutDuplicates.get(i).get(j) + ", ");
                else if(j < abstractClassesWithoutDuplicates.get(i).size() - 1)
                    System.out.print(abstractClassesWithoutDuplicates.get(i).get(j)  + ", ");
                else System.out.print(abstractClassesWithoutDuplicates.get(i).get(j));

            }

            System.out.println();

        }
    }

}
