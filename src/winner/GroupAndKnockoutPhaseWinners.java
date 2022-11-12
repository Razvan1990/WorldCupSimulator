package winner;

import constants.Constants;
import generator.ScoreGenerator;

import java.util.*;

public class GroupAndKnockoutPhaseWinners {

    private final ScoreGenerator scoreGenerator;
    private final HelperFunctions helper;

    public GroupAndKnockoutPhaseWinners() {
        scoreGenerator = new ScoreGenerator();
        helper = new HelperFunctions();
    }

    /**
     * Methods consists in returning a list of list of maps which are following:
     * First list -> qualified teams: Every member is a map with team name and number of goals scored
     * Second list -> not qualified teams:Every member is a map with team name and number of goals scored
     * createLast16Teams method uses a method that stores also the number of points for each team -> based on this we decide who is qualified and who is not after +/
     * +/a new map is formed by sorting the first map based on number of points
     * @return list of lists of map
     * @throws InterruptedException
     */

    public List<List<LinkedHashMap<String, Integer>>> createLast16Teams() throws InterruptedException {
        try {
            /* compute things from groupA*/
            List<List<LinkedHashMap<String, Integer>>> last16ListTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> qualifiedTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> notQualifiedTeams = new ArrayList<>();
            LinkedHashMap<String, List<Integer>> mapGroupA = scoreGenerator.getTeamScores(Constants.GROUPNAMES[0], Constants.GROUPA[0],
                    Constants.GROUPA[1], Constants.GROUPA[2], Constants.GROUPA[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupA = helper.sortStandings(mapGroupA);
            helper.printGroupTable(sortedMapGroupA, Constants.GROUPNAMES[0]);
            helper.getQualifiedTeams(sortedMapGroupA, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupA, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupB*/
            LinkedHashMap<String, List<Integer>> mapGroupB = scoreGenerator.getTeamScores(Constants.GROUPNAMES[1], Constants.GROUPB[0],
                    Constants.GROUPB[1], Constants.GROUPB[2], Constants.GROUPB[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupB = helper.sortStandings(mapGroupB);
            helper.printGroupTable(sortedMapGroupB, Constants.GROUPNAMES[1]);
            helper.getQualifiedTeams(sortedMapGroupB, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupB, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupC*/
            LinkedHashMap<String, List<Integer>> mapGroupC = scoreGenerator.getTeamScores(Constants.GROUPNAMES[2], Constants.GROUPC[0],
                    Constants.GROUPC[1], Constants.GROUPC[2], Constants.GROUPC[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupC = helper.sortStandings(mapGroupC);
            helper.printGroupTable(sortedMapGroupC, Constants.GROUPNAMES[2]);
            helper.getQualifiedTeams(sortedMapGroupC, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupC, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupD*/
            LinkedHashMap<String, List<Integer>> mapGroupD = scoreGenerator.getTeamScores(Constants.GROUPNAMES[3], Constants.GROUPD[0],
                    Constants.GROUPD[1], Constants.GROUPD[2], Constants.GROUPD[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupD = helper.sortStandings(mapGroupD);
            helper.printGroupTable(sortedMapGroupD, Constants.GROUPNAMES[3]);
            helper.getQualifiedTeams(sortedMapGroupD, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupD, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupE*/
            LinkedHashMap<String, List<Integer>> mapGroupE = scoreGenerator.getTeamScores(Constants.GROUPNAMES[4], Constants.GROUPE[0],
                    Constants.GROUPE[1], Constants.GROUPE[2], Constants.GROUPE[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupE = helper.sortStandings(mapGroupE);
            helper.printGroupTable(sortedMapGroupE, Constants.GROUPNAMES[4]);
            helper.getQualifiedTeams(sortedMapGroupE, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupE, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupF*/
            LinkedHashMap<String, List<Integer>> mapGroupF = scoreGenerator.getTeamScores(Constants.GROUPNAMES[5], Constants.GROUPF[0],
                    Constants.GROUPF[1], Constants.GROUPF[2], Constants.GROUPF[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupF = helper.sortStandings(mapGroupF);
            helper.printGroupTable(sortedMapGroupF, Constants.GROUPNAMES[5]);
            helper.getQualifiedTeams(sortedMapGroupF, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupF, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupG*/
            LinkedHashMap<String, List<Integer>> mapGroupG = scoreGenerator.getTeamScores(Constants.GROUPNAMES[6], Constants.GROUPG[0],
                    Constants.GROUPG[1], Constants.GROUPG[2], Constants.GROUPG[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupG = helper.sortStandings(mapGroupG);
            helper.printGroupTable(sortedMapGroupG, Constants.GROUPNAMES[6]);
            helper.getQualifiedTeams(sortedMapGroupG, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupG, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            /*compute things from groupH*/
            LinkedHashMap<String, List<Integer>> mapGroupH = scoreGenerator.getTeamScores(Constants.GROUPNAMES[7], Constants.GROUPH[0],
                    Constants.GROUPH[1], Constants.GROUPH[2], Constants.GROUPH[3]);
            Thread.sleep(500);
            LinkedHashMap<String, List<Integer>> sortedMapGroupH = helper.sortStandings(mapGroupH);
            helper.printGroupTable(sortedMapGroupH, Constants.GROUPNAMES[7]);
            helper.getQualifiedTeams(sortedMapGroupH, qualifiedTeams);
            helper.getNonQualifiedTeams(sortedMapGroupH, notQualifiedTeams);
            Thread.sleep(1500);
            System.out.println('\n');
            //compute final list
            last16ListTeams.add(qualifiedTeams);
            last16ListTeams.add(notQualifiedTeams);
            System.out.println("Qualified teams in the last 16 are: ");
            for (LinkedHashMap<String, Integer> list : last16ListTeams.get(0)) {
                for (String team : list.keySet()) {
                    System.out.print(team + "\t");
                }
            }
            System.out.println('\n');
            return last16ListTeams;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //need here to separate from list in order to use the method from scoreGenerator
    public List<List<LinkedHashMap<String, Integer>>> getQuarterFinalTeams(List<List<LinkedHashMap<String, Integer>>> last16Teams) throws InterruptedException {
        try {
            List<List<LinkedHashMap<String, Integer>>> quarterFinalsTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> qualifiedTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> nonQualifiedTeams = new ArrayList<>();
            System.out.println("Last 16 results are: ");
            Thread.sleep(1500);
            /*Match1*/
            List<LinkedHashMap<String, Integer>> qualifier1 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(0), last16Teams.get(0).get(3));
            LinkedHashMap<String, Integer> qualifiedTeam1 = qualifier1.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam1 = qualifier1.get(1);
            qualifiedTeams.add(qualifiedTeam1);
            nonQualifiedTeams.add(nonQualifiedTeam1);
            Thread.sleep(1500);
            /*Match2*/
            List<LinkedHashMap<String, Integer>> qualifier2 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(1), last16Teams.get(0).get(2));
            LinkedHashMap<String, Integer> qualifiedTeam2 = qualifier2.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam2 = qualifier2.get(1);
            qualifiedTeams.add(qualifiedTeam2);
            nonQualifiedTeams.add(nonQualifiedTeam2);
            Thread.sleep(1500);
            /*Match3*/
            List<LinkedHashMap<String, Integer>> qualifier3 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(4), last16Teams.get(0).get(7));
            Thread.sleep(1500);
            LinkedHashMap<String, Integer> qualifiedTeam3 = qualifier3.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam3 = qualifier3.get(1);
            qualifiedTeams.add(qualifiedTeam3);
            nonQualifiedTeams.add(nonQualifiedTeam3);
            /*Match4*/
            List<LinkedHashMap<String, Integer>> qualifier4 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(5), last16Teams.get(0).get(6));
            LinkedHashMap<String, Integer> qualifiedTeam4 = qualifier4.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam4 = qualifier4.get(1);
            qualifiedTeams.add(qualifiedTeam4);
            nonQualifiedTeams.add(nonQualifiedTeam4);
            Thread.sleep(1500);
            /*Match5*/
            List<LinkedHashMap<String, Integer>> qualifier5 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(8), last16Teams.get(0).get(11));
            LinkedHashMap<String, Integer> qualifiedTeam5 = qualifier5.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam5 = qualifier5.get(1);
            qualifiedTeams.add(qualifiedTeam5);
            nonQualifiedTeams.add(nonQualifiedTeam5);
            Thread.sleep(1500);
            /*Match6*/
            List<LinkedHashMap<String, Integer>> qualifier6 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(9), last16Teams.get(0).get(10));
            LinkedHashMap<String, Integer> qualifiedTeam6 = qualifier6.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam6 = qualifier6.get(1);
            qualifiedTeams.add(qualifiedTeam6);
            nonQualifiedTeams.add(nonQualifiedTeam6);
            Thread.sleep(1500);
            /*Match7*/
            List<LinkedHashMap<String, Integer>> qualifier7 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(12), last16Teams.get(0).get(15));
            LinkedHashMap<String, Integer> qualifiedTeam7 = qualifier7.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam7 = qualifier7.get(1);
            qualifiedTeams.add(qualifiedTeam7);
            nonQualifiedTeams.add(nonQualifiedTeam7);
            Thread.sleep(1500);
            /*Match8*/
            List<LinkedHashMap<String, Integer>> qualifier8 = scoreGenerator.getQualifiedTeamsFromKnockouts(last16Teams.get(0).get(13), last16Teams.get(0).get(14));
            LinkedHashMap<String, Integer> qualifiedTeam8 = qualifier8.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam8 = qualifier8.get(1);
            qualifiedTeams.add(qualifiedTeam8);
            nonQualifiedTeams.add(nonQualifiedTeam8);
            Thread.sleep(1500);
            quarterFinalsTeams.add(qualifiedTeams);
            quarterFinalsTeams.add(nonQualifiedTeams);
            System.out.println("Qualified teams in the quarter finals are:");
            for (LinkedHashMap<String, Integer> list : quarterFinalsTeams.get(0)) {
                for (String team : list.keySet()) {
                    System.out.print(team + "\t");
                }
            }
            System.out.println('\n');
            return quarterFinalsTeams;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<LinkedHashMap<String, Integer>>> getSemifinalTeams(List<List<LinkedHashMap<String, Integer>>> quarterFinalTeams) throws InterruptedException {
        try {

            List<List<LinkedHashMap<String, Integer>>> semifinalTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> qualifiedTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> nonQualifiedTeams = new ArrayList<>();
            System.out.println("Quarter final results are: ");
            Thread.sleep(1500);
            /*Match1*/
            List<LinkedHashMap<String, Integer>> qualifier1 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(0),
                    quarterFinalTeams.get(0).get(4));
            LinkedHashMap<String, Integer> qualifiedTeam1 = qualifier1.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam1 = qualifier1.get(1);
            qualifiedTeams.add(qualifiedTeam1);
            nonQualifiedTeams.add(nonQualifiedTeam1);
            Thread.sleep(1500);
            /*Match2*/
            List<LinkedHashMap<String, Integer>> qualifier2 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(1),
                    quarterFinalTeams.get(0).get(3));
            LinkedHashMap<String, Integer> qualifiedTeam2 = qualifier2.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam2 = qualifier2.get(1);
            qualifiedTeams.add(qualifiedTeam2);
            nonQualifiedTeams.add(nonQualifiedTeam2);
            Thread.sleep(1500);
            /*Match3*/
            List<LinkedHashMap<String, Integer>> qualifier3 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(2),
                    quarterFinalTeams.get(0).get(6));
            LinkedHashMap<String, Integer> qualifiedTeam3 = qualifier3.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam3 = qualifier3.get(1);
            qualifiedTeams.add(qualifiedTeam3);
            nonQualifiedTeams.add(nonQualifiedTeam3);
            Thread.sleep(1500);
            /*Match4*/
            List<LinkedHashMap<String, Integer>> qualifier4 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(5),
                    quarterFinalTeams.get(0).get(7));
            LinkedHashMap<String, Integer> qualifiedTeam4 = qualifier4.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam4 = qualifier4.get(1);
            qualifiedTeams.add(qualifiedTeam4);
            nonQualifiedTeams.add(nonQualifiedTeam4);
            Thread.sleep(1500);
            semifinalTeams.add(qualifiedTeams);
            semifinalTeams.add(nonQualifiedTeams);
            System.out.println("Qualified teams in the semifinals are:");
            for (LinkedHashMap<String, Integer> list : semifinalTeams.get(0)) {
                for (String team : list.keySet()) {
                    System.out.print(team + "\t");
                }
            }
            System.out.println('\n');
            return semifinalTeams;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<List<LinkedHashMap<String, Integer>>> concludeFinals(List<List<LinkedHashMap<String, Integer>>> quarterFinalTeams) throws InterruptedException {
        try {
            List<List<LinkedHashMap<String, Integer>>> finalTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> qualifiedTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> nonQualifiedTeams = new ArrayList<>();
            System.out.println("Semifinal results are: ");
            Thread.sleep(1500);
            List<LinkedHashMap<String, Integer>> qualifier1 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(0),
                    quarterFinalTeams.get(0).get(2));
            LinkedHashMap<String, Integer> qualifiedTeam1 = qualifier1.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam1 = qualifier1.get(1);
            qualifiedTeams.add(qualifiedTeam1);
            nonQualifiedTeams.add(nonQualifiedTeam1);
            Thread.sleep(1500);
            /*Match2*/
            List<LinkedHashMap<String, Integer>> qualifier2 = scoreGenerator.getQualifiedTeamsFromKnockouts(quarterFinalTeams.get(0).get(1),
                    quarterFinalTeams.get(0).get(3));
            LinkedHashMap<String, Integer> qualifiedTeam2 = qualifier2.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam2 = qualifier2.get(1);
            qualifiedTeams.add(qualifiedTeam2);
            nonQualifiedTeams.add(nonQualifiedTeam2);
            Thread.sleep(1500);
            finalTeams.add(qualifiedTeams);
            finalTeams.add(nonQualifiedTeams);

            System.out.println("Qatar World Cup 2022 finals:");
            Thread.sleep(1000);
            System.out.print("Small final \t");
            int index = 0;
            for (LinkedHashMap<String, Integer> l : nonQualifiedTeams) {
                for (String team : l.keySet()) {
                    if (index < 1) {
                        System.out.print(team + "-");
                        index++;
                    } else {
                        System.out.print(team);
                    }
                }
            }
            System.out.print("\nBig final \t");
            index = 0;
            for (LinkedHashMap<String, Integer> l : qualifiedTeams) {
                for (String team : l.keySet()) {
                    if (index < 1) {
                        System.out.print(team + "-");
                        index++;
                    } else {
                        System.out.print(team);
                    }
                }


            }
            return finalTeams;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<List<LinkedHashMap<String, Integer>>> getWinners(List<List<LinkedHashMap<String, Integer>>> finalTeams) throws InterruptedException {
        try {
            List<List<LinkedHashMap<String, Integer>>> winners = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> qualifiedTeams = new ArrayList<>();
            List<LinkedHashMap<String, Integer>> nonQualifiedTeams = new ArrayList<>();
            System.out.println("Qatar World Cup 2022 small final result:");
            Thread.sleep(1500);
            List<LinkedHashMap<String, Integer>> smallFinal = scoreGenerator.getQualifiedTeamsFromKnockouts(finalTeams.get(1).get(0),
                    finalTeams.get(1).get(1));
            LinkedHashMap<String, Integer> qualifiedTeam1 = smallFinal.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam1 = smallFinal.get(1);
            qualifiedTeams.add(qualifiedTeam1);
            nonQualifiedTeams.add(nonQualifiedTeam1);
            Thread.sleep(1500);
            System.out.println("Qatar World Cup 2022 big final result:");
            Thread.sleep(1500);
            List<LinkedHashMap<String, Integer>> bigFinal = scoreGenerator.getQualifiedTeamsFromKnockouts(finalTeams.get(0).get(0),
                    finalTeams.get(0).get(1));
            LinkedHashMap<String, Integer> qualifiedTeam2 = bigFinal.get(0);
            LinkedHashMap<String, Integer> nonQualifiedTeam2 = bigFinal.get(1);
            qualifiedTeams.add(qualifiedTeam2);
            nonQualifiedTeams.add(nonQualifiedTeam2);
            winners.add(qualifiedTeams);
            winners.add(nonQualifiedTeams);
            System.out.println("THE NEW FOOTBALL WORLD TEAM CHAMPION IS...");
            Thread.sleep(1500);
            for (String key : qualifiedTeam2.keySet()) {
                System.out.println(key.toUpperCase());
            }
            return winners;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}