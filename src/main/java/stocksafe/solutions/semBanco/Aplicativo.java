package stocksafe.solutions.semBanco;

import java.io.*;

public class Aplicativo {
    public static void main(String[] args) {

        //Criando um arquivo TXT
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        Menu gerente = new Menu();



         gerente.cadastro();
         System.out.println(gerente.getMensagem());
        gerente.login();
        System.out.println(gerente.getMensagem());
        gerente.menu();












    }
}

