package com.techolution.algorithm.tests.solutions;

/**
 * @author Gnanesh Arva
 * @since 20 Sep 2017 at 16:58
 */
public class FindTheWinner {

    static String winner(int[] andrea, int[] maria, String s) {
        int len = andrea.length;
        int totPointsMaria = 0;
        int totPointsAndrea = 0;
        for (int i = 0; i < len; i++) {
            if (EVENODD.Even.name().equalsIgnoreCase(s) && i % 2 == 0) {
                if (andrea[i] > maria[i])
                    totPointsAndrea = totPointsAndrea + andrea[i] - maria[i];
                else if (maria[i] > andrea[i])
                    totPointsMaria = totPointsMaria + maria[i] - andrea[i];
            } else if (EVENODD.Odd.name().equalsIgnoreCase(s) && i % 2 != 0) {
                if (andrea[i] > maria[i])
                    totPointsAndrea = totPointsAndrea + andrea[i] - maria[i];
                else if (maria[i] > andrea[i])
                    totPointsMaria = totPointsMaria + maria[i] - andrea[i];
            }
        }
        if (totPointsAndrea > totPointsMaria)
            return RESULT.Andrea.name();
        else if (totPointsMaria > totPointsAndrea)
            return RESULT.Maria.name();
        else
            return RESULT.Tie.name();
    }



    public enum RESULT {Maria, Andrea, Tie}

    public enum EVENODD {Even, Odd}

}
