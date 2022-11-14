package computation;

import statistics.GoalsAndStrikers;
import statistics.StatisticsHelper;

import java.util.LinkedHashMap;
import java.util.List;

public class Computations {

    private final GoalsAndStrikers goalsAndStrikers;
    private final StatisticsHelper statisticsHelper;

    public Computations() {
        goalsAndStrikers = new GoalsAndStrikers();
        statisticsHelper = new StatisticsHelper();
    }

    public void computeThings() {
        try {
            LinkedHashMap<String, List<Integer>> goalsTeam = goalsAndStrikers.computeNumberOfGoalsPerTeam();
            LinkedHashMap<String, Integer> playerGoals = goalsAndStrikers.createPlayerGoalsScoredMap(goalsTeam);
            LinkedHashMap<String, Integer> topScorers = goalsAndStrikers.getTopScorers(playerGoals);

            Thread.sleep(2000);
            System.out.println("\nFinal World Cup stats:...");
            Thread.sleep(1000);

            System.out.println("TEAM\t\t\t\tGOALS SCORED|GOALS RECEIVED\t\t\t\t\tPERFORMANCE TOP PLAYERS");
            statisticsHelper.printStatsCorrectly(goalsTeam, playerGoals);
            Thread.sleep(1000);
            System.out.println("\n Top scorers of Qatar World Cup 2022");
            statisticsHelper.printTopScorers(topScorers);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
