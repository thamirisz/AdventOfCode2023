package advent_of_code_2015.Day21;

import java.util.Arrays;
import java.util.Comparator;

public class Day21 {

    public class Player {
        String playerName;
        int blood;
        int damage;
        int defense;

        public Player(String playerName, int blood, int damage, int defense) {
            this.playerName = playerName;
            this.blood = blood;
            this.damage = damage;
            this.defense = defense;
        }
    }

    public final static int[][] weapons = {
        {8, 4, 0},
        {10, 5, 0},
        {25, 6, 0},
        {40, 7, 0},
        {74, 8, 0}
    };

    //optional
    public final static int[][] armors = {
        {0, 0, 0},
        {13, 0, 1},
        {31, 0, 2},
        {53, 0, 3},
        {75, 0, 4},
        {102, 0, 5}
    };

    //optional
    public final static int[][] rings = {
        {0, 0, 0},
        {0, 0, 0},
        {25, 1, 0},
        {50, 2, 0},
        {100, 3, 0},
        {20, 0, 1},
        {40, 0, 2},
        {80, 0, 3}
    };

    private boolean simulateFight(Player player, Player boss) {
        int playerBlood = player.blood;
        int bossBlood = boss.blood;
        while (true) {

            if (bossBlood <= 0)
                return true;

            if (playerBlood <= 0)
                return false;

            bossBlood -= Math.max(1, player.damage - boss.defense);
            System.out.println("boss's blood goes down to " + bossBlood);

            playerBlood -= Math.max(1, boss.damage - player.defense);
            System.out.println("player's blood goes down to " + playerBlood);

           
        }
    }

    private boolean canWinWithGold (int gold) {
        boolean playerWins = false;
        for (int[] weapon : weapons) {
            for (int[] armor : armors) {
                for (int i = 0; i < rings.length; i++) {
                    for (int j = i + 1; j < rings.length; j++) {
                        int totalCost = weapon[0] + armor[0] + rings[i][0] + rings[j][0];
                        
                        //efficiency?
                        if (totalCost > gold) {
                            continue;
                        }
                        int totalDamage = weapon[1] + armor[1] + rings[i][1] + rings[j][1];
                        int totalDefense = weapon[2] + armor[2] + rings[i][2] + rings[j][2];
                        
                        Player player = new Player("Player", 100, totalDamage, totalDefense);
                        Player boss = new Player("Boss", 109, 8, 2);
                        
                        if (simulateFight(player, boss)) {
                            return true;
                        }
                    }
                }
            }          
        }
        return playerWins;
    }

    private int canLoseWithMaxGold () {
        int maxGold = 0;

        for (int[] weapon : weapons) {
            for (int[] armor : armors) {
                for (int i = 0; i < rings.length; i++) {
                    for (int j = i + 1; j < rings.length; j++) {
                        int totalCost = weapon[0] + armor[0] + rings[i][0] + rings[j][0];
                        
                        int totalDamage = weapon[1] + armor[1] + rings[i][1] + rings[j][1];
                        int totalDefense = weapon[2] + armor[2] + rings[i][2] + rings[j][2];
                        
                        Player player = new Player("Player", 100, totalDamage, totalDefense);
                        Player boss = new Player("Boss", 109, 8, 2);
                        
                        if (!simulateFight(player, boss)) {
                            maxGold = Math.max(totalCost, maxGold);
                        }
                    }
                }
            }          
        }
        return maxGold;
    }

    private int solvePart1 () {
        int gold = 0;
        while (true) {
            if (canWinWithGold(gold)) {
                return gold;
            }
            gold++;
        }
    }

    private int solvePart2() {
        return canLoseWithMaxGold();
    }
    public static void main(String[] args) {
        Arrays.sort(rings, Comparator.comparingInt(arr -> arr[0]));
        Day21 day21 = new Day21();
        int part1 = day21.solvePart1();
        int part2 = day21.solvePart2();
        System.out.println(part1);
        System.out.println(part2);
    }

}