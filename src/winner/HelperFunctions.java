package winner;

import statistics.StatisticsHelper;

import java.util.*;

public class HelperFunctions {

    private final StatisticsHelper statisticsHelper;

    public HelperFunctions() {
        statisticsHelper = new StatisticsHelper();
    }

    public void sortList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {//sorted backwards
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

    }


    public int computeResults(int result1, int result2) {
        if (result1 > result2) {
            return 1;
        } else if (result1 == result2) {
            return 2;
        } else {
            return 3;
        }
    }

    public void getPointsFromMatch(int result, int indexTeamOne, int indexTeamTwo, int pointsTeamOne, int pointsTeamTwo, List<Integer> sums) {
        if (result == 1) {
            pointsTeamOne = pointsTeamOne + 3;
            sums.set(indexTeamOne, pointsTeamOne);
        } else if (result == 2) {
            pointsTeamOne = pointsTeamOne + 1;
            pointsTeamTwo = pointsTeamTwo + 1;
            sums.set(indexTeamOne, pointsTeamOne);
            sums.set(indexTeamTwo, pointsTeamTwo);
        } else {
            pointsTeamTwo = pointsTeamTwo + 3;
            sums.set(indexTeamTwo, pointsTeamTwo);
        }
    }

    public void getQualifiedTeams(LinkedHashMap<String, List<Integer>> rankings, List<LinkedHashMap<String, List<Integer>>> qualifiedTeams) {
        int counter = 0;
        for (String key : rankings.keySet()) {
            LinkedHashMap<String, List<Integer>> temp = new LinkedHashMap<>();
            temp.put(key, Arrays.asList(rankings.get(key).get(1), rankings.get(key).get(2)));
            qualifiedTeams.add(temp);
            counter++;
            if (counter == 2) {
                break;
            }
        }
    }

    public void getNonQualifiedTeams(LinkedHashMap<String, List<Integer>> rankings, List<LinkedHashMap<String, List<Integer>>> nonQualifiedTeams) {
        int counter = 0;
        int index = 0;
        for (String key : rankings.keySet()) {
            index++;
            if (index > 2) {
                LinkedHashMap<String, List<Integer>> temp = new LinkedHashMap<>();
                temp.put(key, Arrays.asList(rankings.get(key).get(1), rankings.get(key).get(2)));
                nonQualifiedTeams.add(temp);
                counter++;
                if (counter == 2) {
                    break;
                }
            }
        }
    }

    public HashMap<Integer, Integer> frequencyMapPoints(List<Integer> points) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            if (frequencyMap.containsKey(points.get(i))) {
                frequencyMap.put(points.get(i), frequencyMap.get(points.get(i)) + 1);
            } else {
                frequencyMap.put(points.get(i), 1);
            }

        }
        return frequencyMap;
    }

    public LinkedHashMap<String, List<Integer>> sortStandings(LinkedHashMap<String, List<Integer>> standingsMap) {
        List<Integer> pointsList = new ArrayList<>();
        List<Integer> duplicatePoints = new ArrayList<>();
        for (String team : standingsMap.keySet()) {
            pointsList.add(standingsMap.get(team).get(0));
        }
        sortList(pointsList);
        //we basically count every appearance of points. If there are same points in the for 2 different team, we add in a list the point which is duplicate
        HashMap<Integer, Integer> pointsFrequnecyMap = frequencyMapPoints(pointsList);
        for (int i : pointsFrequnecyMap.keySet()) {
            if (pointsFrequnecyMap.get(i) > 1) {
                duplicatePoints.add(i);
            }
        }
        //here we make a new linked map sorted based on the number of points
        LinkedHashMap<String, List<Integer>> standingsMapFinal = new LinkedHashMap<>();
        for (Integer integer : pointsList) {
            for (String key : standingsMap.keySet()) {
                //check if the number of points of that team is equal to the value from that index of the list and also to not contain already the key
                if (Objects.equals(standingsMap.get(key).get(0), integer) && !standingsMapFinal.containsKey(key)) {
                    List<Integer> list = Arrays.asList(standingsMap.get(key).get(0), standingsMap.get(key).get(1), standingsMap.get(key).get(2));
                    standingsMapFinal.put(key, list);
                }
            }

        }
        //if there are no teams with duplicate points, we just return the map sorted by points
        if (duplicatePoints.size() == 0) {
            return standingsMapFinal;
        }
        /**
         * We create here a list of maps which is containing only duplicates
         * We use a list so that we can then sort it by the goal difference
         */
        List<LinkedHashMap<String, List<Integer>>> myDuplicateValuesMap = new ArrayList<>();
        for (int point : duplicatePoints) {
            for (String key : standingsMap.keySet()) {
                if (Objects.equals(standingsMap.get(key).get(0), point)) {
                    List<Integer> temp = Arrays.asList(standingsMap.get(key).get(0), standingsMap.get(key).get(1), standingsMap.get(key).get(2));
                    LinkedHashMap<String, List<Integer>> tempMap = new LinkedHashMap<>();
                    tempMap.put(key, temp);
                    myDuplicateValuesMap.add(tempMap);
                }
            }
        }
        LinkedHashMap<String, List<Integer>> standingsMapFinal2 = new LinkedHashMap<>();
        LinkedHashMap<String, List<Integer>> sortedDupsPointMap = sortMapByGoalDifference(myDuplicateValuesMap);

        List<String> keys = new ArrayList<>(standingsMapFinal.keySet());
        List<String> duplicateKeys = new ArrayList<>(sortedDupsPointMap.keySet());
        List<LinkedHashMap<String, List<Integer>>> twoDuplicatesListFirst = new ArrayList<>();
        List<LinkedHashMap<String, List<Integer>>> twoDuplicatesListSecond = new ArrayList<>();
        //we need to check 2 cases: either all have same number of points or there are 2 teams with same nr points and two teams sharing a different nr of points
        if (sortedDupsPointMap.size() == 4) {
            //with this part basically we are seeing if we are in the second case mentioned above-> we see if the duplicate map contains 2 values (2 different number points)
            boolean needsComputing = false;
            String keyZero = (String) sortedDupsPointMap.keySet().toArray()[0];
            int firstValue = sortedDupsPointMap.get(keyZero).get(0);
            for (String key : sortedDupsPointMap.keySet()) {
                int pointValue = sortedDupsPointMap.get(key).get(0);
                if (firstValue != pointValue) {
                    needsComputing = true;
                    break;
                }
            }
            /**
             * We are in the second case now
             * For this we will create 2 list of maps. The goal here is to traverse the originalSortedDupsPointMap and check the following:
             *      1. if the nr of points of the first element of the map is greater than the nr of points of the secondElement
             *          2. We then check to see if the value is equal to the firstValue (nr of points first team) is equal to the nr of points of the first team in the map
             *          3. If first condition is true then we add in our first list the map with the points number greater or equal than the second team's points number
             *          4. In the second map we add then the map with the team than has less numb of points
             *      5. If the points of the first element of the map is less than then nr of points of the secondElement then:
             *          6. We just make the same alghoritm, but in the first list we will add the map which has number of points different than the first element
             *          and in the second list we'll add the value equal to the number of points of the first element
             *      7. Goal is to have 2 lists in which one has only elements with a certain number of points and the other elements with the different number of points. It is certain
             *      that the lists will be same size(2) because of the conditions above
             *
             */
            if (needsComputing) {
                String firstKey = (String) sortedDupsPointMap.keySet().toArray()[0];
                String secondKey = (String) sortedDupsPointMap.keySet().toArray()[1];
                if (sortedDupsPointMap.get(firstKey).get(0) >= sortedDupsPointMap.get(secondKey).get(0)) {
                    for (String key : sortedDupsPointMap.keySet()) {
                        if (sortedDupsPointMap.get(key).get(0) == firstValue) {
                            LinkedHashMap<String, List<Integer>> map1 = new LinkedHashMap<>();
                            List<Integer> list = Arrays.asList(sortedDupsPointMap.get(key).get(0), sortedDupsPointMap.get(key).get(1), sortedDupsPointMap.get(key).get(2));
                            map1.put(key, list);
                            twoDuplicatesListFirst.add(map1);
                        }
                        if (sortedDupsPointMap.get(key).get(0) != firstValue) {
                            LinkedHashMap<String, List<Integer>> map1 = new LinkedHashMap<>();
                            List<Integer> list = Arrays.asList(sortedDupsPointMap.get(key).get(0), sortedDupsPointMap.get(key).get(1), sortedDupsPointMap.get(key).get(2));
                            map1.put(key, list);
                            twoDuplicatesListSecond.add(map1);
                        }
                    }
                } else {
                    for (String key : sortedDupsPointMap.keySet()) {
                        if (sortedDupsPointMap.get(key).get(0) != firstValue) {
                            LinkedHashMap<String, List<Integer>> map1 = new LinkedHashMap<>();
                            List<Integer> list = Arrays.asList(sortedDupsPointMap.get(key).get(0), sortedDupsPointMap.get(key).get(1), sortedDupsPointMap.get(key).get(2));
                            map1.put(key, list);
                            twoDuplicatesListSecond.add(map1);
                        }
                        if (sortedDupsPointMap.get(key).get(0) == firstValue) {
                            LinkedHashMap<String, List<Integer>> map1 = new LinkedHashMap<>();
                            List<Integer> list = Arrays.asList(sortedDupsPointMap.get(key).get(0), sortedDupsPointMap.get(key).get(1), sortedDupsPointMap.get(key).get(2));
                            map1.put(key, list);
                            twoDuplicatesListFirst.add(map1);
                        }
                    }
                }
                //We will now sort the lists by goal Difference
                List<LinkedHashMap<String, List<Integer>>> firstSortedLst = sortListByGoalDifference(twoDuplicatesListFirst);
                List<LinkedHashMap<String, List<Integer>>> secondSortedLst = sortListByGoalDifference(twoDuplicatesListSecond);
                /**make comparison of points to see order of insertion
                 * We use a function to compare the points of the first element of the first list with the nr of points of the first element of the second list
                 * 1. If (nrPoints[list1]>=nrPoints[list2])
                 *     a. We will put in our finalMap the keys and values(List of points and goals) from the elements of list1 and then elements for list2
                 * 2. else:
                 *      b. We will put in our finalMap the keys and values(List of points and goals) from the elements of list2 and then elements for list1
                 */
                boolean checkOrder = checkFirstElementByNumberPoints(firstSortedLst, secondSortedLst);
                if (checkOrder) {
                    for (String key : firstSortedLst.get(0).keySet()) {
                        List<Integer> tempList = Arrays.asList(firstSortedLst.get(0).get(key).get(0), firstSortedLst.get(0).get(key).get(1), firstSortedLst.get(0).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : firstSortedLst.get(1).keySet()) {
                        List<Integer> tempList = Arrays.asList(firstSortedLst.get(1).get(key).get(0), firstSortedLst.get(1).get(key).get(1), firstSortedLst.get(1).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : secondSortedLst.get(0).keySet()) {
                        List<Integer> tempList = Arrays.asList(secondSortedLst.get(0).get(key).get(0), secondSortedLst.get(0).get(key).get(1), secondSortedLst.get(0).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : secondSortedLst.get(1).keySet()) {
                        List<Integer> tempList = Arrays.asList(secondSortedLst.get(1).get(key).get(0), secondSortedLst.get(1).get(key).get(1), secondSortedLst.get(1).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    return standingsMapFinal2;
                } else {
                    for (String key : secondSortedLst.get(0).keySet()) {
                        List<Integer> tempList = Arrays.asList(secondSortedLst.get(0).get(key).get(0), secondSortedLst.get(0).get(key).get(1), secondSortedLst.get(0).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : secondSortedLst.get(1).keySet()) {
                        List<Integer> tempList = Arrays.asList(secondSortedLst.get(1).get(key).get(0), secondSortedLst.get(1).get(key).get(1), secondSortedLst.get(1).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : firstSortedLst.get(0).keySet()) {
                        List<Integer> tempList = Arrays.asList(firstSortedLst.get(0).get(key).get(0), firstSortedLst.get(0).get(key).get(1), firstSortedLst.get(0).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    for (String key : firstSortedLst.get(1).keySet()) {
                        List<Integer> tempList = Arrays.asList(firstSortedLst.get(1).get(key).get(0), firstSortedLst.get(1).get(key).get(1), firstSortedLst.get(1).get(key).get(2));
                        standingsMapFinal2.put(key, tempList);
                    }
                    return standingsMapFinal2;
                }


            }
        }
        //now there is the option of two or three teams having the same number of points
        String comparingKey = duplicateKeys.get(0);
        for (String key : keys) {
            /**
             * //[7 3 3 2] [7 7 3 2]
             * [7 5 2 2]
             * here it is certain that we have just 2 or three keys and have the same number of points
             * 1.If the value of nr points of the sorted map by points is bigger than the number of points of the first key form the duplicate list, then we add the
             * key and points-goal list to the map, as it is certain to be bigger -> [7] []
             * 2. If the value is less, then we will need now to add the elements from the sortedDupsMap first then add the last element/elements which is/are less,
             * to maintain the correct order [7 3 3]; [7 7]
             * 3. We need to just add the elements which have less numb of points than the duplicate [7 3 3 2]; [7 7 3 2]
             * 4. If it is equal to duplicate we just continue
             * 5. If the duplicates are the least number of points, then we will add them after traversing the whole standingsMap
             *      a. [7 5]
             *      b. [7 5 2 2]
             */
            if (standingsMapFinal.get(key).get(0) > sortedDupsPointMap.get(comparingKey).get(0)) {
                List<Integer> tempList = Arrays.asList(standingsMapFinal.get(key).get(0), standingsMapFinal.get(key).get(1), standingsMapFinal.get(key).get(2));
                standingsMapFinal2.put(key, tempList);
            } else if (standingsMapFinal.get(key).get(0) < sortedDupsPointMap.get(comparingKey).get(0)) {
                for (String key2 : sortedDupsPointMap.keySet()) {
                    List<Integer> tempList2 = Arrays.asList(sortedDupsPointMap.get(key2).get(0), standingsMapFinal.get(key2).get(1), standingsMapFinal.get(key2).get(2));
                    standingsMapFinal2.put(key2, tempList2);
                }
                List<Integer> tempList = Arrays.asList(standingsMapFinal.get(key).get(0), standingsMapFinal.get(key).get(1), standingsMapFinal.get(key).get(2));
                standingsMapFinal2.put(key, tempList);

            } else {

            }
        }

        for (String key2 : sortedDupsPointMap.keySet()) {
            List<Integer> tempList2 = Arrays.asList(sortedDupsPointMap.get(key2).get(0), standingsMapFinal.get(key2).get(1), standingsMapFinal.get(key2).get(2));
            standingsMapFinal2.put(key2, tempList2);
        }


        return standingsMapFinal2;


    }

    public LinkedHashMap<String, List<Integer>> sortMapByGoalDifference(List<LinkedHashMap<String, List<Integer>>> standingsMap) {
        List<Integer> differenceGoals = new ArrayList<>();
        for (LinkedHashMap<String, List<Integer>> map : standingsMap) {
            for (String key : map.keySet()) {
                int goalsScored = map.get(key).get(1);
                int goalsReceived = map.get(key).get(2);
                int difference = goalsScored - goalsReceived;
                differenceGoals.add(difference);
            }
        }
        sortList(differenceGoals);
        LinkedHashMap<String, List<Integer>> finalMap = new LinkedHashMap<>();
        for (Integer integer : differenceGoals) {
            for (LinkedHashMap<String, List<Integer>> map : standingsMap) {
                for (String key : map.keySet()) {
                    //check if the number of points of that team is equal to the value from that index of the list and also to not already contain the key
                    if (Objects.equals(map.get(key).get(1) - map.get(key).get(2), integer) && !finalMap.containsKey(key)) {
                        List<Integer> list = Arrays.asList(map.get(key).get(0), map.get(key).get(1), map.get(key).get(2));
                        LinkedHashMap<String, List<Integer>> tempMap = new LinkedHashMap<>();
                        finalMap.put(key, list);
                    }
                }

            }
        }
        return finalMap;


    }

    public List<LinkedHashMap<String, List<Integer>>> sortListByGoalDifference(List<LinkedHashMap<String, List<Integer>>> list) {
        List<LinkedHashMap<String, List<Integer>>> resultList = new ArrayList<>();
        LinkedHashMap<String, List<Integer>> elementMap1 = list.get(0);
        LinkedHashMap<String, List<Integer>> elementMap2 = list.get(1);
        String keyElementA = (String) elementMap1.keySet().toArray()[0];
        String keyElementB = (String) elementMap2.keySet().toArray()[0];
        //start comparing
        if (elementMap1.get(keyElementA).get(1) - elementMap1.get(keyElementA).get(2) >= elementMap2.get(keyElementB).get(1) - elementMap2.get(keyElementB).get(2)) {
            resultList.add(elementMap1);
            resultList.add(elementMap2);
        } else {
            resultList.add(elementMap2);
            resultList.add(elementMap1);
        }
        return resultList;
    }

    public boolean checkFirstElementByNumberPoints(List<LinkedHashMap<String, List<Integer>>> list, List<LinkedHashMap<String, List<Integer>>> list2) {
        LinkedHashMap<String, List<Integer>> listElement1 = list.get(0);
        LinkedHashMap<String, List<Integer>> listElement2 = list2.get(0);
        String keyElementA = (String) listElement1.keySet().toArray()[0];
        String keyElementB = (String) listElement2.keySet().toArray()[0];
        return listElement1.get(keyElementA).get(0) > listElement2.get(keyElementB).get(0);
    }

    public void printGroupTable(LinkedHashMap<String, List<Integer>> standings, String groupname) {
        System.out.print("Final standings in " + groupname + ":\t");
        for (String key : standings.keySet()) {
            System.out.print(key + " - " + standings.get(key).get(0) + "p\t");
        }
    }


    public <K, V> Set<String> getKeys(LinkedHashMap<String, List<Integer>> map, V value) {
        Set<String> keys = new HashSet<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().get(0).equals(value)) {
                keys.add((entry.getKey()));
            }
        }
        return keys;
    }


}
