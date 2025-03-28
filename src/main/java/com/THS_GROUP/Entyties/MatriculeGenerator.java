package com.THS_GROUP.Entyties;


import java.time.LocalDateTime;

public class MatriculeGenerator {

    public static String generateMatricule(String prefix) {
        // Obtenir la date actuelle
        LocalDateTime now = LocalDateTime.now();

        // Formater la date au format JJMMAA
        String day = String.format("%02d", now.getDayOfMonth());
        String month = String.format("%02d", now.getMonthValue());
        int year = now.getYear() % 100; // Derniers deux chiffres de l'année

        // Calculer le nombre de secondes écoulées depuis minuit
        long secondsSinceMidnight = now.toLocalTime().toSecondOfDay();

        // Construire le numéro matricule
        return prefix.toUpperCase() + day + month + String.format("%02d", year) + String.format("%05d", secondsSinceMidnight);
    }
}