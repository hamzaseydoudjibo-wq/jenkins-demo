package com.demo.calculator;

/**
 * Classe Calculatrice - Application de démonstration Jenkins
 * Module : Tests de Vérification et Validation Logiciel
 *
 * Cette classe représente le code source à tester via le pipeline CI Jenkins.
 */
public class Calculatrice {

    /**
     * Additionne deux nombres entiers.
     */
    public int additionner(int a, int b) {
        return a + b;
    }

    /**
     * Soustrait deux nombres entiers.
     */
    public int soustraire(int a, int b) {
        return a - b;
    }

    /**
     * Multiplie deux nombres entiers.
     */
    public int multiplier(int a, int b) {
        return a * b;
    }

    /**
     * Divise deux nombres.
     * @throws ArithmeticException si le diviseur est zéro
     */
    public double diviser(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Erreur : division par zéro impossible !");
        }
        return (double) a / b;
    }

    /**
     * Calcule la puissance d'un nombre (a^n).
     */
    public double puissance(double base, int exposant) {
        return Math.pow(base, exposant);
    }

    /**
     * Vérifie si un nombre est premier.
     */
    public boolean estPremier(int nombre) {
        if (nombre <= 1) return false;
        if (nombre <= 3) return true;
        if (nombre % 2 == 0 || nombre % 3 == 0) return false;
        for (int i = 5; i * i <= nombre; i += 6) {
            if (nombre % i == 0 || nombre % (i + 2) == 0) return false;
        }
        return true;
    }

    /**
     * Calcule la factorielle d'un entier positif.
     * @throws IllegalArgumentException si n est négatif
     */
    public long factorielle(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("La factorielle n'est pas définie pour les entiers négatifs.");
        }
        if (n == 0 || n == 1) return 1;
        return n * factorielle(n - 1);
    }

    /**
     * Calcule la moyenne d'un tableau de nombres.
     * @throws IllegalArgumentException si le tableau est vide
     */
    public double moyenne(int[] nombres) {
        if (nombres == null || nombres.length == 0) {
            throw new IllegalArgumentException("Le tableau ne peut pas être vide.");
        }
        int somme = 0;
        for (int n : nombres) {
            somme += n;
        }
        return (double) somme / nombres.length;
    }
}
