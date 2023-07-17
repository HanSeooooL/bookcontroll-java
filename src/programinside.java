import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class programinside {
    static getDays getDays = new getDays();


}

class getDays {
    public long changethecalendertoDays(int year, int month, int day) {
        long res = 0;
        int[] monthdayys = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] yunmonthdayys = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        res += ((year - 1) / 4) * 366L;
        res += ((year - 1) - (year - 1) / 4) * 365L;

        if (year % 4 != 0) {
            for (int i = 0; i < month - 1; i++) {
                res += monthdayys[i];
            }
        } else {
            for (int i = 0; i < month - 1; i++) {
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

        year = Integer.parseInt(returnday.substring(0, 4)); //String Int로 형변환
        month = Integer.parseInt(returnday.substring(4, 6));    //String Int로 형변환
        day = Integer.parseInt(returnday.substring(6, 8));  //String Int로 형변환

        returndayslong = changethecalendertoDays(year, month, day);
        nowdayslong = changethecalendertoDays(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        reslong = nowdayslong - returndayslong;

        res = Long.valueOf(reslong).intValue(); //Long to int

        return res;
    }

    public String changenumbertochar(int year, int month, int day) {
        String res;

        res = Integer.toString(year);

        if(month < 10) {
            res = res.concat("0");
            res = res.concat(Integer.toString(month));
        }
        else res = res.concat(Integer.toString(month));

        if(day < 10) {
            res = res.concat("0");
            res = res.concat(Integer.toString(day));
        }
        else res = res.concat(Integer.toString(day));

        return res;
    }

    public String gluecalender(String year, String month, String day) {
        String res;

        System.out.println(year + month + day);

        res = year;

        if(Integer.parseInt(month) < 10) {
            res = res.concat("0");
            res = res.concat(month);
        }
        else res = res.concat(month);
        if(Integer.parseInt(day) < 10) {
            res = res.concat("0");
            res = res.concat(day);
        }
        else res = res.concat(day);

        return res;
    }
}