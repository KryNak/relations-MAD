package Engine;

import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionModule {

    public FunctionModule(){

    }

    public void start() throws Exception {
        ArrayList<String> clipboard = new ArrayList<>();
        StringBuilder data = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(
                    new FileReader("D:\\Programowanie\\Programy java\\MAD_Relacje\\src\\TextFiles\\Input.txt")); //input

            String line;

            while ((line = reader.readLine()) != null){

                if(!line.matches("%.*")) clipboard.add(line + "\n");

            }

            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        String strDomain = clipboard.get(0); /* Pobieranie dziedziny */
        clipboard.remove(0);
        clipboard.forEach(data::append);

        Pattern pattern = Pattern.compile("[A-Za-z]");
        Matcher matcher = pattern.matcher(data);

        HashSet<String> domainSet = extractDomainOfFunction(strDomain);
        ArrayList<Pair<String, String>> pairArrayList = new ArrayList<>();

        while (matcher.find()){

            String var1 = matcher.group();
            matcher.find();
            String var2 = matcher.group();

            pairArrayList.add(new Pair<>(var1, var2));

        }

        if( !isFunction(pairArrayList, domainSet) ) {
            System.out.println("Podany input nie jest funkcja");
            return;
        }

        System.out.println("Funkcja jest: ");

        boolean suriection = true; //poniewaz w relacji zaweira sie cala przeciwdziedzina
        if(suriection) System.out.println("- suriekcja");

        HashMap<String, String> function = exportToHashMap( pairArrayList );

        boolean injection = isInjection(function);
        if(injection) System.out.println("- iniekcja");
        if(injection && suriection){
            System.out.println("- bijekcja\n");

            ArrayList<Pair<String, String>> reverseFunction = generateReverseFunction(pairArrayList);
            output(reverseFunction, "D:\\Programowanie\\Programy java\\MAD_Relacje\\src\\TextFiles\\Output.txt"); //output
            System.out.println("Funkcja odwrotna zostala wygenerowana. ");
        }
    }

    private static boolean isFunction(ArrayList<Pair<String, String>> pairArrayList, HashSet<String> domainOfFunction){

        Set<String> set = new HashSet<>();

        for (Pair<String, String> p : pairArrayList) {

            if( !set.add(p.getKey()) ) return false;

        }

        for(String s : set){

            if( !domainOfFunction.contains(s) ) return false;

        }

        for(String s : domainOfFunction){

            if ( !set.contains(s) ) return false;

        }

        return true;
    }

    private static boolean isInjection(HashMap<String, String> function){

        Set<String> set = new HashSet<>();

        for (String s : function.values()) {
            if (!set.add(s)) return false;
        }

        return true;
    }

    private static HashMap<String, String> exportToHashMap(ArrayList<Pair<String, String>> pairArrayList){

        HashMap<String, String> function = new HashMap<>();

        for (Pair<String, String> p : pairArrayList) {
            function.put(p.getKey(), p.getValue());
        }

        return function;
    }

    private static HashSet<String> extractDomainOfFunction(String line) throws Exception {

        HashSet<String> set= new HashSet<>();

        Pattern pattern = Pattern.compile("[A-Za-z0-9]");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()){

            if (!set.add(matcher.group())) {
                System.err.println("Elementy w dziedzinie nie powiny sie powtarzac");
                throw new Exception("Elementy dziedziny sie powtarzaja");
            }

        }

        return set;

    }

    private static ArrayList<Pair<String, String>> generateReverseFunction(ArrayList<Pair<String, String>> pairArrayList){

        ArrayList<Pair<String, String>> reverseFunction = new ArrayList<>();

        for (Pair<String, String> p : pairArrayList) {

            reverseFunction.add(new Pair<String, String>(p.getValue(), p.getKey()));

        }

        return reverseFunction;
    }

    private static void output(ArrayList<Pair<String, String>> reverseFunction, String path) throws FileNotFoundException {

        ArrayList<String> domainOfFunction = new ArrayList<>();

        for(Pair<String, String> p : reverseFunction){

            domainOfFunction.add(p.getKey());

        }

        StringBuilder builder = new StringBuilder();
        domainOfFunction.sort(String::compareTo);

        for(int i = 0; i < domainOfFunction.size(); i++){
            if( i < domainOfFunction.size() - 1) builder.append( domainOfFunction.get(i) + ",");
            else builder.append( domainOfFunction.get(i) + "\n" );
        }

        for (Pair<String, String> p : reverseFunction) {
            builder.append(p.getKey() + "," + p.getValue() + "\n");
        }

        PrintWriter writer = new PrintWriter(path); //output
        writer.println(builder);
        writer.close();

    }

}

