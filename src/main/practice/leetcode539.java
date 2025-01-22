import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class leetcode539  {
    public int findMinDifference(List<String> timePoints) {
        //sort using Collections.sort()
        //using iteration to compare minDiff between adjacent node(timepoints[i], timepoints[i + 1])
        //during time timepoints all trasfre to mintues for compare.

        Collections.sort(timePoints);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < timePoints.size(); i++){
            minDiff = Math.min(minDiff, getMinutes(timePoints.get(i)) - getMinutes(timePoints.get(i - 1)));
        }

        // f[0] - f[timePoints.length - 1]
        int temp = Math.abs(getMinutes(timePoints.get(0)) - getMinutes(timePoints.get(timePoints.size() - 1))+ 1440) ;
        minDiff = Math.min(minDiff, temp);

        return minDiff;

    }

    private int getMinutes(String time){
//        int temp1 = (int)(time.charAt(0) - '0');
//        System.out.println(temp1);
//        int temp2 = (int)(time.charAt(1) - '0');
//        System.out.println(temp2);
//        int temp3 = (int)(time.charAt(2) - '0');
//        System.out.println(temp3);
//        int temp4 = (int)(time.charAt(3) - '0');
//        System.out.println(temp4);
        int result = ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(2) - '0') * 10 + (time.charAt(3) - '0');
        return result;
    }

    public static void main(String[] args) {
        leetcode539 test = new leetcode539();
        List<String> timePoints = new ArrayList<>();
        timePoints.add("23:59");
        timePoints.add("00:00");
        System.out.println(timePoints.toString());
        int diff = test.findMinDifference(timePoints);
        System.out.println(diff);
    }
}