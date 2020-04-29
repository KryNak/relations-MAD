package Engine;

import java.util.ArrayList;
import java.util.Arrays;

public class Mechanism {

    private String file;

    public Mechanism(String file){

       this.file = file;

    }

    public void start(){

        ReadFile readFile = new ReadFile( file );

        ArrayList<String> lineSplittedText = new ArrayList<>(
                Arrays.asList( readFile.getLineSplittedTab() ) );

        System.out.println("Input: \n");
        lineSplittedText.forEach(System.out::println);
        separate();

        ExtractInformation extract = new ExtractInformation(lineSplittedText);

        System.out.println("Matrix: \n");
        Matrix matrix = new Matrix(extract.getElements(), extract.getElementsInRelations());
        if(matrix.getMatrix() != null)printTab(matrix.getMatrix());
        separate();

        System.out.println("Output: \n");
        Relations relations = new Relations(matrix.getMatrix(), matrix.createMatrixPattern());
        relations.findRelations();

    }

    void printTab(boolean[][] tab){

        for(int i = 0; i < tab.length; i++){

            for(int j = 0; j < tab[i].length; j++){

                if(j == 0) System.out.print("| " + (tab[i][j]?1:0) + ", ");
                else if(j < tab.length - 1) System.out.print((tab[i][j]?1:0) + ", ");
                else System.out.print((tab[i][j]?1:0) + " |");

            }
            System.out.println();

        }

    }

     private void separate(){

         System.out.println("\n------------------------------------------------------------------------------------\n");

     }

}
