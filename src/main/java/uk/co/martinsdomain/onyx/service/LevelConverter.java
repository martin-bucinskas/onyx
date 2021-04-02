package uk.co.martinsdomain.onyx.service;

import org.springframework.stereotype.Component;

@Component
public class LevelConverter {

    public double convertXpToLevel(double xp) {
        double points = 0;

        for (double lvl = 1; lvl <= 99; lvl++) {
            points += Math.floor(lvl + (300 * Math.pow(2, lvl / 7)));
            if (Math.floor(points / 4) >= xp + 1) {
                return lvl;
            }
        }

        return 99;
    }

    public double convertLevelToXp(int lvl) {
        double points = 0;

        for (double i = 1; i < lvl; i++) {
            points += Math.floor(i + (300 * Math.pow(2, i / 7)));
        }

        return Math.floor(points / 4);
    }
}
