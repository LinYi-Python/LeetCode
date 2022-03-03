//https://leetcode-cn.com/problems/day-of-the-year/
import java.util.*;
//1. using hashmap.
class DayoftheYear1154 {
    public int dayOfYear(String date) {
        Map<Integer, Integer> monthNumber = new HashMap<Integer, Integer>();
        monthNumber.put(1, 31);
        monthNumber.put(2, 28);
        monthNumber.put(3, 31);
        monthNumber.put(4, 30);
        monthNumber.put(5, 31);
        monthNumber.put(6, 30);
        monthNumber.put(7, 31);
        monthNumber.put(8, 31);
        monthNumber.put(9, 30);
        monthNumber.put(10, 31);
        monthNumber.put(11, 30);
        monthNumber.put(12, 31);

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        int ans = 0;


        for(int i = 1; i < month; i++){
            ans = ans + monthNumber.get(i);
        }
        ans = ans + day;
        if((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3){
            ans += 1;
        }

        return ans;
    }
}


//2. using arrays
class DayoftheYear1154A {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        int ans = 0;
        for (int i = 0; i < month - 1; ++i) {
            ans += amount[i];
        }
        return ans + day;
    }
}
