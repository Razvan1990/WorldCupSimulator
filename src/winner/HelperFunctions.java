package winner;

import java.util.*;

public class HelperFunctions {

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
                temp.put(key,Arrays.asList(rankings.get(key).get(1), rankings.get(key).get(2)));
                nonQualifiedTeams.add(temp);
                counter++;
                if (counter == 2) {
                    break;
                }
            }
        }
    }

    public LinkedHashMap<String, List<Integer>> sortStandings(LinkedHashMap<String, List<Integer>> standings) {
        List<Integer> pointsList = new ArrayList<>();
        for (String key : standings.keySet()) {
            pointsList.add(standings.get(key).get(0));
        }
        sortList(pointsList);
        LinkedHashMap<String, List<Integer>> standingsMap = new LinkedHashMap<>();
        for (Integer integer : pointsList) {
            for (String key : standings.keySet()) {
                //check if the number of points of that team is equal to the value from that index of the list and also to not contain already the key
                if (Objects.equals(standings.get(key).get(0), integer) && !standingsMap.containsKey(key)) {
                    List<Integer> list = Arrays.asList(standings.get(key).get(0), standings.get(key).get(1), standings.get(key).get(2));
                    standingsMap.put(key, list);
                }
            }

        }
        return standingsMap;
    }

    public void printGroupTable(LinkedHashMap<String, List<Integer>> standings, String groupname) {
        System.out.print("Final standings in " + groupname + ":\t");
        for (String key : standings.keySet()) {
            System.out.print(key + " - " + standings.get(key).get(0) + "p\t");
        }
    }


}
