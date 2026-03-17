package com.demo.calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Calculatrice
 *
 * Ces tests sont automatiquement exécutés par Jenkins à chaque commit.
 * Jenkins collecte les résultats via le plugin JUnit et affiche les
 * statistiques dans son tableau de bord.
 */
@DisplayName("Tests Unitaires - Calculatrice")
class CalculatriceTest {

    private Calculatrice calc;

    @BeforeEach
    void initialiser() {
        // Exécuté avant chaque test
        calc = new Calculatrice();
    }

    // ============================================================
    //  TESTS : ADDITION
    // ============================================================
    @Nested
    @DisplayName("Addition")
    class TestsAddition {

        @Test
        @DisplayName("Addition de deux positifs")
        void additionnerDeuxPositifs() {
            assertEquals(8, calc.additionner(5, 3));
        }

        @Test
        @DisplayName("Addition avec un négatif")
        void additionnerAvecNegatif() {
            assertEquals(2, calc.additionner(5, -3));
        }

        @Test
        @DisplayName("Addition avec zéro")
        void additionnerAvecZero() {
            assertEquals(7, calc.additionner(7, 0));
        }

        @Test
        @DisplayName("Addition de deux négatifs")
        void additionnerDeuxNegatifs() {
            assertEquals(-8, calc.additionner(-5, -3));
        }
    }

    // ============================================================
    //  TESTS : SOUSTRACTION
    // ============================================================
    @Nested
    @DisplayName("Soustraction")
    class TestsSoustraction {

        @Test
        @DisplayName("Soustraction normale")
        void soustraire() {
            assertEquals(2, calc.soustraire(5, 3));
        }

        @Test
        @DisplayName("Résultat négatif")
        void soustraireResultatNegatif() {
            assertEquals(-2, calc.soustraire(3, 5));
        }
    }

    // ============================================================
    //  TESTS : MULTIPLICATION
    // ============================================================
    @Nested
    @DisplayName("Multiplication")
    class TestsMultiplication {

        @Test
        @DisplayName("Multiplication normale")
        void multiplierNormal() {
            assertEquals(15, calc.multiplier(3, 5));
        }

        @Test
        @DisplayName("Multiplier par zéro")
        void multiplierParZero() {
            assertEquals(0, calc.multiplier(9, 0));
        }

        @Test
        @DisplayName("Multiplier deux négatifs → positif")
        void multiplierDeuxNegatifs() {
            assertEquals(6, calc.multiplier(-2, -3));
        }
    }

    // ============================================================
    //  TESTS : DIVISION
    // ============================================================
    @Nested
    @DisplayName("Division")
    class TestsDivision {

        @Test
        @DisplayName("Division entière")
        void diviserNormal() {
            assertEquals(2.0, calc.diviser(10, 5));
        }

        @Test
        @DisplayName("Division décimale")
        void diviserDecimal() {
            assertEquals(2.5, calc.diviser(5, 2));
        }

        @Test
        @DisplayName("Division par zéro → exception")
        void diviserParZeroLanceException() {
            ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calc.diviser(10, 0)
            );
            assertTrue(ex.getMessage().contains("zéro"));
        }
    }

    // ============================================================
    //  TESTS : PUISSANCE
    // ============================================================
    @Nested
    @DisplayName("Puissance")
    class TestsPuissance {

        @Test
        @DisplayName("2 puissance 10")
        void puissanceDeux() {
            assertEquals(1024.0, calc.puissance(2, 10));
        }

        @Test
        @DisplayName("Exposant zéro → 1")
        void puissanceExposantZero() {
            assertEquals(1.0, calc.puissance(5, 0));
        }
    }

    // ============================================================
    //  TESTS : NOMBRE PREMIER
    // ============================================================
    @Nested
    @DisplayName("Nombre Premier")
    class TestsNombrePremier {

        @Test
        @DisplayName("7 est premier")
        void septEstPremier() {
            assertTrue(calc.estPremier(7));
        }

        @Test
        @DisplayName("97 est premier")
        void quatreVingtDixSeptEstPremier() {
            assertTrue(calc.estPremier(97));
        }

        @Test
        @DisplayName("4 n'est pas premier")
        void quatreNestPasPremier() {
            assertFalse(calc.estPremier(4));
        }

        @Test
        @DisplayName("1 n'est pas premier")
        void unNestPasPremier() {
            assertFalse(calc.estPremier(1));
        }
    }

    // ============================================================
    //  TESTS : FACTORIELLE
    // ============================================================
    @Nested
    @DisplayName("Factorielle")
    class TestsFactorielle {

        @Test
        @DisplayName("0! = 1")
        void factorielleZero() {
            assertEquals(1, calc.factorielle(0));
        }

        @Test
        @DisplayName("5! = 120")
        void factorielleCinq() {
            assertEquals(120, calc.factorielle(5));
        }

        @Test
        @DisplayName("Négatif → exception")
        void factorielleNegatifLanceException() {
            assertThrows(IllegalArgumentException.class,
                () -> calc.factorielle(-1));
        }
    }

    // ============================================================
    //  TESTS : MOYENNE
    // ============================================================
    @Nested
    @DisplayName("Moyenne")
    class TestsMoyenne {

        @Test
        @DisplayName("Moyenne de [2, 4, 6] = 4.0")
        void moyenneNormale() {
            assertEquals(4.0, calc.moyenne(new int[]{2, 4, 6}));
        }

        @Test
        @DisplayName("Tableau vide → exception")
        void moyenneTableauVideLanceException() {
            assertThrows(IllegalArgumentException.class,
                () -> calc.moyenne(new int[]{}));
        }
    }
}
