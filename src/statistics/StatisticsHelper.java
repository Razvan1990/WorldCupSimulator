package statistics;

import constants.Constants;

import java.util.*;

public class StatisticsHelper {

    public List<Integer> getPlayerGoalNumber(List<Integer> numberTeamGoals) {
        List<Integer> goals = new ArrayList<>();
        for (Integer numberTeamGoal : numberTeamGoals) {
            int x = (int) Math.floor(Math.random() * numberTeamGoal);
            goals.add(x);
        }
        return goals;
    }


    public List<String> createListOfTeams() {
        List<String> teams = new ArrayList<>();
        Collections.addAll(teams, Constants.GROUPA);
        Collections.addAll(teams, Constants.GROUPB);
        Collections.addAll(teams, Constants.GROUPC);
        Collections.addAll(teams, Constants.GROUPD);
        Collections.addAll(teams, Constants.GROUPE);
        Collections.addAll(teams, Constants.GROUPF);
        Collections.addAll(teams, Constants.GROUPG);
        Collections.addAll(teams, Constants.GROUPH);
        Collections.sort(teams);
        return teams;

    }

    public int getNumberOfTeamGoals(List<List<LinkedHashMap<String, Integer>>> matchPhaseList, String teamName) {
        int nrTotalGoals = 0;
        //search in the big list till we find the key which has the same name as the team and get the number of points
        for (List<LinkedHashMap<String, Integer>> l : matchPhaseList) {
            for (LinkedHashMap<String, Integer> teamGoal : l) {
                for (String team : teamGoal.keySet()) {
                    if (Objects.equals(team, teamName)) {
                        nrTotalGoals = teamGoal.get(team);
                        break;
                    }
                }
            }

        }
        return nrTotalGoals;
    }

    public List<Integer> createListOfGoals(List<List<LinkedHashMap<String, Integer>>> list1, List<List<LinkedHashMap<String, Integer>>> list2,
                                           List<List<LinkedHashMap<String, Integer>>> list3, List<List<LinkedHashMap<String, Integer>>> list4,
                                           List<List<LinkedHashMap<String, Integer>>> list5, List<String> orderedTeams) {
        List<Integer> goals = new ArrayList<>();
        for (String orderedTeam : orderedTeams) {
            int totalGoals;
            int phase1 = getNumberOfTeamGoals(list1, orderedTeam);
            int phase2 = getNumberOfTeamGoals(list2, orderedTeam);
            int phase3 = getNumberOfTeamGoals(list3, orderedTeam);
            int phase4 = getNumberOfTeamGoals(list4, orderedTeam);
            int phase5 = getNumberOfTeamGoals(list5, orderedTeam);
            totalGoals = phase1 + phase2 + phase3 + phase4 + phase5;
            goals.add(totalGoals);

        }
        return goals;
    }

    public LinkedHashMap<String, Integer> sortMapByValue(LinkedHashMap<String, Integer> map) {
        List<Map.Entry> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> Integer.compare((int) o2.getValue(), (int) o1.getValue()));
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry entry : list) {
            sortedMap.put((String) entry.getKey(), (int) entry.getValue());
        }
        return sortedMap;
    }

    public void printStatsCorrectly(LinkedHashMap<String, Integer> map, LinkedHashMap<String, Integer> map2) {
        List<String> computedWritting = makePlayerNameGoalPrintCorrectly(map, map2);
        int index = 0;
        for (String key : map.keySet()) {
            if (key.length() <= 6) {
                System.out.println(key + ":\t\t\t\t" + map.get(key) + " goals" + computedWritting.get(index));
            } else if (key.length() <= 10) {
                System.out.println(key + "\t\t\t" + map.get(key) + " goals" + computedWritting.get(index));
            } else {
                System.out.println(key + "\t\t" + map.get(key) + " goals" + computedWritting.get(index));
            }
            index++;

        }
    }

    public List<String> makePlayerNameGoalPrintCorrectly(LinkedHashMap<String, Integer> map, LinkedHashMap<String, Integer> map2) {
        //get the keys in a list and values in another list to easily iterate
        String player;
        List<String> results = new ArrayList<>();
        List<String> keysList = new ArrayList<>(map2.keySet());
        List<Integer> valuesList = new ArrayList<>(map2.values());
        List<Integer> goalsNation = new ArrayList<>(map.values());
        int index = 0;
        while (index < valuesList.size()) {
            if (goalsNation.get(index) < 10) {
                player = "\t\t\t\t\t\t\t" + keysList.get(index) + " - " + valuesList.get(index) + " goals";
            } else {
                player = "\t\t\t\t\t\t" + keysList.get(index) + " - " + valuesList.get(index) + " goals";
            }
            results.add(player);
            index++;
        }
        return results;
    }

    public void printTopScorers(LinkedHashMap<String, Integer> map) {
        int index = 1;
        for (String player : map.keySet()) {
            System.out.print(index + "." + player + " - " + map.get(player) + " goals" + "\t\t");
            index++;
        }
    }

}
