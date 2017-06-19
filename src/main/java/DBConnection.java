import org.hibernate.SQLQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lois on 2017/6/19.
 */
public class DBConnection {

    private static String[] sex = {"男","女"};
    //10个城市，其中北上广3个城市为一线城市，杭州、南京、厦门为二线发达城市，苏州、武汉、西安、成都为二线中等城市
    private static String[] city = {"北京","上海","广州","杭州","南京","厦门","苏州","武汉","西安","成都"};

    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/HostelWorld?useUnicode=true&characterEncoding=UTF-8";
    private String dbUser = "root";
    private String dbPass = "123456";


    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 向user表注入数据
     * @param count id增加到的最大值
     * @return 最后一条数据id
     */
    public int insertUser(int count) {
        int i = 1001040;
        String sql = "insert into user(id, password, username, phone, email, birth, sex, bankcard, level, balance, point) " +
                "values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        try {
            for (;i<count;i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, i + "");
                preparedStatement.setString(2, "123");
                preparedStatement.setString(3, "liu" + (i % 1000000));
                preparedStatement.setString(4, "1");
                preparedStatement.setString(5, "352264191@qq.com");
                preparedStatement.setString(6, randomDate("1970-01-01", "2017-05-31").toString());
                preparedStatement.setString(7, randomSex());
                preparedStatement.setString(8, getRandomNum(1, 1000000) + "");
                preparedStatement.setString(9, "大众会员");
                preparedStatement.setString(10, 1000 + "");
                preparedStatement.setString(11, 1000 + "");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     *
     * @param count
     * @return
     */
    public int insertHostelApply(int count) {
        int i = 13;
        String sql = "insert into hostelapply(id, applyer, phone, email, identity, hostelname, location, " +
                "description, approvalstate, approverId, applyType, applyTime) " +
                "values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        try {
            for (;i<count;i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, i + "");
                preparedStatement.setString(2, "liu"+i);
                preparedStatement.setString(3, "13372511775");
                preparedStatement.setString(4, "352264191@qq.com");
                String tmpCity = getRandomCity();
                preparedStatement.setString(5, tmpCity.substring(0,1));//用来标记城市等级，1/2/3 = 一线/二线发达/二线中等
                preparedStatement.setString(6, "hostel加盟店");
                preparedStatement.setString(7, tmpCity.substring(1));
                preparedStatement.setString(8, "hostel加盟店诚邀您的光临");
                preparedStatement.setString(9, "approve");
                preparedStatement.setString(10, 1000000 + "");
                preparedStatement.setString(11, "open");
                preparedStatement.setString(12,randomDate("2010-01-01", "2016-10-01").toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 随机获取10所城市中的一个
     * 一线城市的产生概率为39%，三所城市各13%
     * 二线发达城市的产生概率为33%，三所城市各11%
     * 二线中等城市的产生概率为28%，四所城市各7%
     * @return 城市
     */
    public static String getRandomCity(){
        double x = 10 * Math.random();
        if (x<1.3){
            return "1"+city[0];
        }else if(x<2.6){
            return "1"+city[1];
        }else if(x<3.9){
            return "1"+city[2];
        }else if(x<5){
            return "2"+city[3];
        }else if(x<6.1){
            return "2"+city[4];
        }else if(x<7.2){
            return "2"+city[5];
        }else if(x<7.9){
            return "3"+city[6];
        }else if(x<8.6){
            return "3"+city[7];
        }else if(x<9.3){
            return "3"+city[8];
        }else{
            return "3"+city[9];
        }

    }

    private static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);//构造开始日期
            Date end = format.parse(endDate);//构造结束日期
            //getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            Date rtnDate = new Date(date);
            return CST2Date(rtnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static String CST2Date(Date CST){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Date对象
        String sDate=sdf.format(CST);
        return  sDate;
    }

    private static long random(long begin, long end) {
        long res = begin + (long) (Math.random() * (end - begin));
        if (res == begin || res == end) {
            return random(begin, end);
        }
        return res;
    }

    private static String randomSex(){
        double x = Math.random();
        if(x<0.5){
            return sex[0];
        }else{
            return sex[1];
        }
    }

    private static int getRandomNum(int min, int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }


}

