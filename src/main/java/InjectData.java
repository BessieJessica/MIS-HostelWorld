import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lois on 2017/6/19.
 *
 * 造数据ing
 *
 */
public class InjectData {

    static DBConnection dbConnection = new DBConnection();

    public static void main(String[] args){

        //10w的登记用户
//        dbConnection.insertUser(1100001);

        //50家加盟店
//        dbConnection.insertHostelApply(51);

//        dbConnection.selectHostelApply2Info();

//        dbConnection.selectHostelInfo2Room();

//        dbConnection.selecthostelApply2myOrder();


        try {
            dbConnection.selectMyorderInfo2checkout();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        try{
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date start = format.parse("2017-06-30");//构造开始日期
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(start);
//            calendar.add(Calendar.DATE,1);
//            String T1 = format.format(calendar.getTime() ) ;
//            System.out.println(T1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }



    }

}
