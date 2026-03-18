package com.demo.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculatrice calc = new Calculatrice();
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   CALCULATRICE JENKINS - DEMO");
        System.out.println("========================================");
        System.out.println("Choisissez une operation :");
        System.out.println("1. Addition");
        System.out.println("2. Soustraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();

        System.out.print("Entrez le premier nombre : ");
        int a = scanner.nextInt();
        System.out.print("Entrez le deuxième nombre : ");
        int b = scanner.nextInt();

        switch (choix) {
            case 1:
                System.out.println("Résultat : " + a + " + " + b + " = " + calc.additionner(a, b));
                break;
            case 2:
                System.out.println("Résultat : " + a + " - " + b + " = " + calc.soustraire(a, b));
                break;
            case 3:
                System.out.println("Résultat : " + a + " × " + b + " = " + calc.multiplier(a, b));
                break;
            case 4:
                System.out.println("Résultat : " + a + " ÷ " + b + " = " + calc.diviser(a, b));
                break;
            default:
                System.out.println("Choix invalide !");
        }
        scanner.close();
    }
}