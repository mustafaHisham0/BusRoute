
package teamstourament;

import java.util.Scanner;

public class TeamsTourament {





    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        String[] teams = {"Team A", "Team B", "Team C", "Team D"};
        int[] home = {0, 0, 0, 1, 1, 2};
        int[] away = {1, 2, 3, 2, 3, 3}; 
        int[] s1 = new int[6], s2 = new int[6]; 

        
        int[] W = new int[4], D = new int[4], L = new int[4];
        int[] GF = new int[4], GA = new int[4], Pts = new int[4];

        
        System.out.println("WELCOME TO THE TEAM TOURNAMENT");
        for (int i = 0; i < 6; i++) {
            System.out.println("Match " + (i + 1) + ": " + teams[home[i]] + " vs " + teams[away[i]]);
        }

        
        System.out.println("\n ENTER MATCH SCORES");
        for (int i = 0; i < 6; i++) {
            int h = home[i], a = away[i];

            System.out.println("Match " + (i + 1) + ": " + teams[h] + " vs " + teams[a]);
            System.out.print(teams[h] + " goals: ");
            s1[i] = sc.nextInt();
            System.out.print(teams[a] + " goals: ");
            s2[i] = sc.nextInt();

            // Accumulate goals for and goals against
            GF[h] += s1[i]; GA[h] += s2[i];
            GF[a] += s2[i]; GA[a] += s1[i];

            // Points, Wins, Draws, Losses
            if (s1[i] > s2[i]) {
                W[h]++; L[a]++; Pts[h] += 3;
            } else if (s2[i] > s1[i]) {
                W[a]++; L[h]++; Pts[a] += 3;
            } else {
                D[h]++; D[a]++; Pts[h]++; Pts[a]++;
            }
            System.out.println();
        }

        // Print Entered Results
        System.out.println("MATCH RESULTS");
        for (int i = 0; i < 6; i++) {
            System.out.println("Match " + (i + 1) + ": " + teams[home[i]] + " " + s1[i] + " - " + s2[i] + " " + teams[away[i]]);
        }

        // Sort rankings by Points then Goal Difference (Bubble Sort)
        int[] rank = {0, 1, 2, 3};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 - i; j++) {
                int t1 = rank[j], t2 = rank[j + 1];
                int gd1 = GF[t1] - GA[t1], gd2 = GF[t2] - GA[t2];

                if (Pts[t1] < Pts[t2] || (Pts[t1] == Pts[t2] && gd1 < gd2)) {
                    int temp = rank[j];
                    rank[j] = rank[j + 1];
                    rank[j + 1] = temp;
                }
            }
        }

        // Task 4: Standings Table
        System.out.println("\n=== STANDINGS TABLE ===");
        System.out.printf("%-10s %-3s %-3s %-3s %-3s %-3s %-3s %-3s %-3s\n", "Team", "P", "W", "D", "L", "GF", "GA", "GD", "Pts");
        
        for (int r : rank) {
            int played = W[r] + D[r] + L[r];
            int gd = GF[r] - GA[r];
            System.out.printf("%-10s %-3d %-3d %-3d %-3d %-3d %-3d %-3d %-3d\n",
                    teams[r], played, W[r], D[r], L[r], GF[r], GA[r], gd, Pts[r]);
        }

        // Task 5: Champion Announcement
        System.out.println("\nTournament Champion: " + teams[rank[0]]);

        sc.close();
    }
}
