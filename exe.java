public class exe {

    public static void main(String[] args) {
        double figura[][] = new double[][]{
            {320, 130}, {80, 130},
            {80, 110}, {90, 110}, {90, 90}, {100, 90},
            {100, 70}, {110, 70}, {110, 60}, {120, 60},
            {120, 50}, {130, 50}, {130, 40}, {120, 40},
            {120, 20}, {110, 20}, {110, 0}, {120, 0},
            {120, 10}, {130, 10}, {130, 20}, {140, 20},
            {140, 40}, {260, 40}, {260, 20}, {270, 20},
            {270, 10}, {280, 10}, {280, 0}, {290, 0},
            {290, 20}, {280, 20}, {280, 40}, {270, 40},
            {270, 50}, {280, 50}, {280, 60}, {290, 60},
            {290, 70}, {300, 70}, {300, 90}, {310, 90},
            {310, 110}, {320, 110}, {320, 130}, {320, 150},
            {330, 150}, {330, 140}, {340, 140}, {340, 150},
            {350, 150}, {350, 240}, {340, 240}, {340, 250},
            {330, 250}, {330, 240}, {320, 240}, {320, 150},
            {320, 130}, {320, 300}, {310, 300}, {310, 310},
            {250, 310}, {250, 370}, {240, 370}, {240, 380},
            {230, 380}, {230, 370}, {220, 370}, {220, 310},
            {180, 310}, {180, 310}, {180, 370}, {170, 370},
            {170, 380}, {160, 380}, {160, 370}, {150, 370},
            {150, 310}, {90, 310}, {90, 300}, {80, 300},
            {80, 130}, {80, 150}, {80, 240}, {70, 240},
            {70, 250}, {60, 250}, {60, 240}, {50, 240},
            {50, 150}, {60, 150}, {60, 140}, {70, 140},
            {70, 150}, {80, 150}, {80, 130}, {200, 130}};

        Principal obf = new Principal("Transformaciones", figura);

        obf.max = 800;
        obf.may = 548;
    }
}