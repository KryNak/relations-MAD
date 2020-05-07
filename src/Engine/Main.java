package Engine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        String input = "D:\\Programowanie\\Programy java\\MAD_Relacje\\src\\TextFiles\\Input.txt";
        Mechanism mechanism = new Mechanism(input);
        FunctionModule function = new FunctionModule();

        Scanner scanner = new Scanner(System.in);
        int typeIn = 0;
        do{
            System.out.println("Ktory modul chcesz wybrac: " +
                    "\n [1] Badanie Relacji" +
                    "\n [2] Badanie Funckji " +
                    "\n [3] Exit");

            try {
                typeIn = scanner.nextInt();
            }
            catch (Exception e){
                System.err.println("Powtorz wybor. ");
            }

        }while (! (typeIn == 1 || typeIn ==2 || typeIn ==3));


        switch (typeIn){
            case 1:
                mechanism.start();
                break;
            case 2:
                function.start();
                break;
            case 3:
                break;
            default:
                throw new Exception("Nieznana operacja");
        }

    }
}
