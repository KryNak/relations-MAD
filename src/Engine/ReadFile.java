package Engine;

import java.io.*;

public class ReadFile {

    private StringBuilder cilipboard;
    private String[] lines;

    ReadFile(String filePath){

        cilipboard = new StringBuilder();

        try{

            BufferedReader stream = new BufferedReader( new FileReader( filePath ) );

            String line;
            while( (line = stream.readLine()) != null ){

                if( !line.matches("%.*|\\s*") ){

                    cilipboard.append( line ).append( "\n" );

                }

            }

        }catch (Exception e){

            System.out.println( e.getMessage() );

        }

    }

    String getCilipboard() {
        return cilipboard.toString().trim();
    }

    String[] getLineSplittedTab(){
        lines = cilipboard.toString().trim().split("\n");
        return lines;
    }
}
