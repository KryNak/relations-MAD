package Engine;

import javafx.util.Pair;

import java.util.ArrayList;

class Matrix {

    private ArrayList<Element> elements;
    private ArrayList<Pair<Element, Element>> elementsInRelations;
    private boolean[][] matrix;

    Matrix(ArrayList<Element> elements, ArrayList<Pair<Element, Element>> elementsInRelations) {
        this.elements = elements;
        this.elementsInRelations = elementsInRelations;

        fillInMatrix();
    }

    Pair<Element, Element>[][] createMatrixPattern(){

        if(elements.size() == 0){

            try {
                throw new Exception("Zbior pusty");
            } catch (Exception e) {
                return null;
            }

        }

        Pair<Element, Element>[][] matrixPattern = new Pair[elements.size()][elements.size()];
        
        for(int i = 0; i < matrixPattern.length; i++){

            for(int j = 0; j < matrixPattern[i].length; j++){

                matrixPattern[i][j] = new Pair<>(elements.get(i), elements.get(j));

            }

        }

        return matrixPattern;

    }

    private void fillInMatrix(){

        if(createMatrixPattern() == null){
            matrix = null;
            return;
        }

        Pair<Element, Element>[][] matrixPattern = createMatrixPattern();
        matrix = new boolean[matrixPattern.length][matrixPattern[0].length];

        for(int i = 0; i < matrix.length; i++){

            for(int j = 0; j < matrix[i].length; j++){

                matrix[i][j] = false;

            }

        }

        for(int i = 0; i < matrix.length; i++){

            for(int j = 0; j < matrix[i].length; j++){

                for (Pair<Element, Element> pair : elementsInRelations) {

                    if(matrixPattern[i][j].getKey().equals(pair.getKey())
                    && matrixPattern[i][j].getValue().equals(pair.getValue())){
                        matrix[i][j] = true;
                    }

                }

            }

        }
    }

    boolean[][] getMatrix() {
        return matrix;
    }
}
