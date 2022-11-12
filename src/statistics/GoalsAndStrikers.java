package statistics;

import constants.Constants;
import winner.GroupAndKnockoutPhaseWinners;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GoalsAndStrikers {

    private final GroupAndKnockoutPhaseWinners groupAndKnockoutPhaseWinners;
    private final StatisticsHelper statisticsHelper;

    public GoalsAndStrikers() {
        groupAndKnockoutPhaseWinners = new GroupAndKnockoutPhaseWinners();
        statisticsHelper = new StatisticsHelper();
    }

    public LinkedHashMap<String, Integer> computeNumberOfGoalsPerTeam() throws InterruptedException {
        /*
         * Method involves looking in every list created form Groupknockout and check to see if that team has scored goals
         * Based on this, we create a list of all goals from teams based on a list of teams from alphabetical order
         */
        try {
            List<List<LinkedHashMap<String, Integer>>> last16Teams = groupAndKnockoutPhaseWinners.createLast16Teams();
            List<List<LinkedHashMap<String, Integer>>> last8Teams = groupAndKnockoutPhaseWinners.getQuarterFinalTeams(last16Teams);
            List<List<LinkedHashMap<String, Integer>>> last4Teams = groupAndKnockoutPhaseWinners.getSemifinalTeams(last8Teams);
            List<List<LinkedHashMap<String, Integer>>> finals = groupAndKnockoutPhaseWinners.concludeFinals(last4Teams);
            List<List<LinkedHashMap<String, Integer>>> winners = groupAndKnockoutPhaseWinners.getWinners(finals);
            List<String> worldCupTeams = statisticsHelper.createListOfTeams();
            List<Integer> goals = statisticsHelper.createListOfGoals(last16Teams, last8Teams, last4Teams, finals, winners, worldCupTeams);
            LinkedHashMap<String, Integer> nationGoals = new LinkedHashMap<>();
            for (int i = 0; i < goals.size(); i++) {
                nationGoals.put(worldCupTeams.get(i), goals.get(i));
            }

            return nationGoals;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedHashMap<String, Integer> createPlayerGoalsScoredMap(LinkedHashMap<String, Integer> nationsGoalMap) {
        /*
         * Method involves taking the list of players and generating a random number of goals for every player based on the amount of goals scored by his country
         */

        LinkedHashMap<String, Integer> goalsPerPlayer = new LinkedHashMap<>();
        List<Integer> goals = new ArrayList<>(nationsGoalMap.values());
        List<Integer> randomPlayerGoals = statisticsHelper.getPlayerGoalNumber(goals);
        for (int i = 0; i < Constants.PLAYERS.size(); i++) {
            goalsPerPlayer.put(Constants.PLAYERS.get(i), randomPlayerGoals.get(i));
        }

        return goalsPerPlayer;

    }


    public LinkedHashMap<String, Integer> getTopScorers (LinkedHashMap<String, Integer> playerGoalMap){
        /*
         * Method involves taking the map of player-goals and sorting it by value in another map
         * We finally create a map with the top 3 scorers(name, number of goals)
         */

        LinkedHashMap<String, Integer> top3Scorers = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> arrangedPlayerMapByGoals = statisticsHelper.sortMapByValue(playerGoalMap);
        int index =0;
        for (String player:arrangedPlayerMapByGoals.keySet()){
            top3Scorers.put(player, arrangedPlayerMapByGoals.get(player));
            index++;
            if (index>2){
                break;
            }

        }
        return top3Scorers;

    }


}