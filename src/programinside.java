import java.time.ZonedDateTime;
import java.time.ZoneId;

public class programinside {


    public class getDays {
        public long changethecalendertoDays(int year, int month, int day) {
            long res = 0;
            int[] monthdayys = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int[] yunmonthdayys = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            res +=((year - 1) / 4) * 366L;
            res += ((year - 1) - (year - 1) / 4) * 365L;

            if(year % 4 != 0) {
                for(int i = 0; i < month - 1; i++) {
                    res += monthdayys[i];
                }
            }

            else {
                for(int i = 0; i < month - 1; i++) {
                    res += yunmonthdayys[i];
                }
            }

            res += day;




            return res;
        }
        public int checkHowyouDidntReturn(String returnday) {
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
            int res, year, month, day;
            long reslong, returndayslong, nowdayslong;

            year = Integer.parseInt(returnday.substring(0, 4));
            month = Integer.parseInt(returnday.substring(4, 6));
            day = Integer.parseInt(returnday.substring(6, 8));

            returndayslong = changethecalendertoDays(year, month, day);
            nowdayslong = changethecalendertoDays(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

            reslong = nowdayslong - returndayslong;

            res = Long.valueOf(reslong).intValue();

            return res;
        }
    }

}
