package generator;

import winner.HelperFunctions;

import java.util.*;

public class ScoreGenerator {

    private final HelperFunctions helper;

    public ScoreGenerator() {
        helper = new HelperFunctions();
    }

    public int getGeneratedScore() {
        return (int) Math.floor(Math.random() * 6);
    }

    public List<Integer> defineGroupResults() {
        /*
         * There will be a total of 6 games per group -> 12 generations needed
         */
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int x = getGeneratedScore();
            results.add(x);
        }
        return results;
    }

    public LinkedHashMap<String, List<Integer>> getTeamScores(String groupName, String t1, String t2, String t3, String t4) {
        try {
            List<Integer> results = defineGroupResults();
            List<Integer> myList = new ArrayList<>();
            List<Integer> partialList = new ArrayList<>();
            List<List<Integer>> matchesList = new ArrayList<>();
            LinkedHashMap<String, List<Integer>> teamResults = new LinkedHashMap<>();
            int sumTeamA = 0;
            int sumTeamB = 0;
            int sumTeamC = 0;
            int sumTeamD = 0;
            int goalsTeamA = 0;
            int goalsTeamB = 0;
            int goalsTeamC = 0;
            int goalsTeamD = 0;
            //Create a list of 6 lists of scores of size 2 -> every list is a result basically
            for (int i = 0; i < results.size(); i++) {
                int random = (int) Math.floor(Math.random() * results.size());
                myList.add(random);
                while (myList.contains(random)) {
                    random = (int) Math.floor(Math.random() * results.size());
                }
                int resultChosen = results.get(random);
                partialList.add(resultChosen);
                if (partialList.size() == 2) {
                    //here we are going to compare the result
                    List<Integer> clonedPartialList = new ArrayList<>(partialList);
                    matchesList.add(clonedPartialList);
                    partialList.clear();
                }

            }
            System.out.println(groupName + " results are");
            Thread.sleep(1000);
            //Match1
            System.out.println(t1 + " " + matchesList.get(0).get(0) + " - " + matchesList.get(0).get(1) + " " + t2);
            int compare = helper.computeResults(matchesList.get(0).get(0), matchesList.get(0).get(1));
            goalsTeamA += matchesList.get(0).get(0);
            goalsTeamB += matchesList.get(0).get(1);
            if (compare == 1) {
                sumTeamA += 3;
            } else if (compare == 2) {
                sumTeamA += 1;
                sumTeamB += 1;
            } else {
                sumTeamB += 3;
            }
            //Match2
            Thread.sleep(1000);
            System.out.println(t3 + " " + matchesList.get(1).get(0) + " - " + matchesList.get(1).get(1) + " " + t4);
            compare = helper.computeResults(matchesList.get(1).get(0), matchesList.get(1).get(1));
            goalsTeamC += matchesList.get(1).get(0);
            goalsTeamD += matchesList.get(1).get(1);
            if (compare == 1) {
                sumTeamC += 3;
            } else if (compare == 2) {
                sumTeamC += 1;
                sumTeamD += 1;
            } else {
                sumTeamD += 3;
            }
            //Match3
            Thread.sleep(1000);
            System.out.println(t1 + " " + matchesList.get(2).get(0) + " - " + matchesList.get(2).get(1) + " " + t3);
            compare = helper.computeResults(matchesList.get(2).get(0), matchesList.get(2).get(1));
            goalsTeamA += matchesList.get(2).get(0);
            goalsTeamC += matchesList.get(2).get(1);
            if (compare == 1) {
                sumTeamA += 3;
            } else if (compare == 2) {
                sumTeamC += 1;
                sumTeamA += 1;
            } else {
                sumTeamC += 3;
            }
            //Match4
            Thread.sleep(1000);
            System.out.println(t2 + " " + matchesList.get(3).get(0) + " - " + matchesList.get(3).get(1) + " " + t4);
            compare = helper.computeResults(matchesList.get(3).get(0), matchesList.get(3).get(1));
            goalsTeamB += matchesList.get(3).get(0);
            goalsTeamD += matchesList.get(3).get(1);
            if (compare == 1) {
                sumTeamB += 3;
            } else if (compare == 2) {
                sumTeamB += 1;
                sumTeamD += 1;
            } else {
                sumTeamD += 3;
            }
            //Match5
            Thread.sleep(1000);
            System.out.println(t1 + " " + matchesList.get(4).get(0) + " - " + matchesList.get(4).get(1) + " " + t4);
            compare = helper.computeResults(matchesList.get(4).get(0), matchesList.get(4).get(1));
            goalsTeamA += matchesList.get(4).get(0);
            goalsTeamD += matchesList.get(4).get(1);
            if (compare == 1) {
                sumTeamA += 3;
            } else if (compare == 2) {
                sumTeamD += 1;
                sumTeamA += 1;
            } else {
                sumTeamD += 3;
            }
            //Match6
            Thread.sleep(1000);
            System.out.println(t2 + " " + matchesList.get(5).get(0) + " - " + matchesList.get(5).get(1) + " " + t3);
            compare = helper.computeResults(matchesList.get(5).get(0), matchesList.get(5).get(1));
            goalsTeamB += matchesList.get(5).get(0);
            goalsTeamC += matchesList.get(5).get(1);
            if (compare == 1) {
                sumTeamB += 3;
            } else if (compare == 2) {
                sumTeamB += 1;
                sumTeamC += 1;
            } else {
                sumTeamC += 3;
            }


            teamResults.put(t1, Arrays.asList(sumTeamA, goalsTeamA));
            teamResults.put(t2, Arrays.asList(sumTeamB, goalsTeamB));
            teamResults.put(t3, Arrays.asList(sumTeamC, goalsTeamC));
            teamResults.put(t4, Arrays.asList(sumTeamD, goalsTeamD));
            return teamResults;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LinkedHashMap<String, Integer>> getQualifiedTeamsFromKnockouts(LinkedHashMap<String, Integer> map1, LinkedHashMap<String, Integer> map2) {
        List<String> keyA = new ArrayList<>(map1.keySet());
        String a = keyA.get(0);
        List<String> keyB = new ArrayList<>(map2.keySet());
        String b = keyB.get(0);
        int resultTeamA = getGeneratedScore();
        int resultTeamB = getGeneratedScore();
        LinkedHashMap<String, Integer> qualified = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> notQualifed = new LinkedHashMap<>();
        List<LinkedHashMap<String, Integer>> knockoutTeamsGoals = new ArrayList<>();
        int finalResultA;
        int finalResultB;
        if (resultTeamA > resultTeamB) {
            System.out.println(a + " " + resultTeamA + " - " + resultTeamB + " " + b);
            qualified.put(a, resultTeamA);
            notQualifed.put(b, resultTeamB);
            knockoutTeamsGoals.add(qualified);
            knockoutTeamsGoals.add(notQualifed);
            return knockoutTeamsGoals;
        } else if (resultTeamB > resultTeamA) {
            System.out.println(a + " " + resultTeamA + " - " + resultTeamB + " " + b);
            qualified.put(b, resultTeamB);
            notQualifed.put(a, resultTeamA);
            knockoutTeamsGoals.add(qualified);
            knockoutTeamsGoals.add(notQualifed);
            return knockoutTeamsGoals;
        } else {
            int penalty1 = 0;
            int penalty2 = 0;
            while (penalty1 == penalty2) {
                penalty1 = getGeneratedScore();
                penalty2 = getGeneratedScore();

            }
            //conditions for correct penalty
            if (penalty1 > 3 && penalty2 == 0) {
                penalty1 = 3;
            } else if (penalty2 > 3 && penalty1 == 0) {
                penalty2 = 3;
            } else if (penalty1 == 1 && penalty2 > 4) {
                penalty2 = 4;
            } else if (penalty1 > 4 && penalty2 == 1) {
                penalty1 = 4;
            }
            finalResultA = resultTeamA + penalty1;
            finalResultB = resultTeamB + penalty2;
            if (finalResultA > finalResultB) {
                System.out.println(a + " " + resultTeamA + " - " + resultTeamB + " " + b + "-> " + a + " won on penalty with " + finalResultA + " - " + finalResultB);
                qualified.put(a, resultTeamA);
                notQualifed.put(b, resultTeamB);
                knockoutTeamsGoals.add(qualified);
                knockoutTeamsGoals.add(notQualifed);
                return knockoutTeamsGoals;

            } else {
                System.out.println(a + " " + resultTeamA + " - " + resultTeamB + " " + b + "-> " + b + " won on penalty with " + finalResultB + " - " + finalResultA);
                qualified.put(b, resultTeamB);
                notQualifed.put(a, resultTeamA);
                knockoutTeamsGoals.add(qualified);
                knockoutTeamsGoals.add(notQualifed);
                return knockoutTeamsGoals;
            }
        }

    }


}





