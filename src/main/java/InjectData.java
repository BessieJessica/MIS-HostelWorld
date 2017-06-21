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

        dbConnection.insertUser(1100001);

//        dbConnection.insertHostelApply(168);

//        dbConnection.selectHostelApply2Info();

//        dbConnection.selectHostelInfo2Room();

//        try{
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date start = format.parse("2017-06-10");//构造开始日期
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(start);
//            calendar.add(Calendar.DATE,10);
//            String T1 = format.format(calendar.getTime() ) ;
//            System.out.println(T1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }



    }

}
