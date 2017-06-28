import org.hibernate.SQLQuery;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by lois on 2017/6/19.
 */
public class DBConnection {

    private static String[] sex = {"男", "女"};
    //10个城市，其中北上广3个城市为一线城市，杭州、南京、厦门为二线发达城市，苏州、武汉、西安、成都为二线中等城市
    private static String[] city = {"北京", "上海", "广州", "杭州", "南京", "厦门", "苏州", "武汉", "西安", "成都"};
    private static String[] balance = {"0", "1000"};
    private static String[] checkState = {"checked", "cancel"};
    private static String[] checkoutPayInfo = {"会员，会员卡支付","会员，现金支付"};

    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/HostelWorld?useUnicode=true&characterEncoding=UTF-8";
    private String dbUser = "root";
    private String dbPass = "123456";

    private static int room_tableId = 1;
    private static int myorderId = 1076327;//+1.8w

    private static int checkoutId = 179578;


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
     * hostelApply表中获取数据，注入hostelInfo表
     */
    public void selectHostelApply2Info() {
        String sql = "select id, hostelname, location, identity, applyer from hostelapply";
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                insertHostelInfo(resultSet.getString("id"), resultSet.getString("hostelname"),
                        resultSet.getString("location"), resultSet.getString("identity"),
                        resultSet.getString("applyer"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取hostelInfo中的数据注入hostelRoom
     */
    public void selectHostelInfo2Room() {
        String sql = "select id, roomcount from hostelinfo";
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                insertHostelRoom(resultSet.getString("id"), Integer.parseInt(resultSet.getString("roomcount")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从myorder表中抽取数据
     * 向checkout表中注入
     */
    public void selectMyorderInfo2checkout() throws ParseException {
        String sql = "select room, ordertime, userId, checkstate from myorder where hostelId=50";
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            String checkintime ;
            while (resultSet.next()) {
                if (resultSet.getString("checkstate").equals("checked")) {
                    checkintime = resultSet.getString("ordertime");
                    insertCheckout(resultSet.getString("room"), checkintime, datePlus(checkintime), resultSet.getString("userId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertCheckout(String roomId, String checkintime, String checkouttime, String userId) {

        String sql = "insert into checkout(id, hostelId, roomId, checkintime, checkouttime, totalpay, payinfo, userId) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection connection = getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, checkoutId+"");
            preparedStatement.setString(2, "50");
            preparedStatement.setString(3, roomId);
            preparedStatement.setString(4, checkintime);
            preparedStatement.setString(5, checkouttime);
            preparedStatement.setString(6, random(5000,10000)+"");
            preparedStatement.setString(7,randomPayInfo());
            preparedStatement.setString(8, userId);
            checkoutId++;
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 传入String类型的日期，返回+1天的日期
     * @param startDate
     * @return
     * @throws ParseException
     */
    public String datePlus(String startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(startDate);//构造开始日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, 1);
        String T1 = format.format(calendar.getTime());
//            System.out.println(T1);
        return T1;
    }

    /**
     * 注入hostelInfo
     *
     * @return 一线城市的酒店默认30间房间
     * 二线发达城市的酒店默认20间房间
     * 二线中等城市的酒店默认15间房间
     */
    public int insertHostelInfo(String id, String name, String location, String identity, String ownername) {
        int i = 0;
        String sql = "insert into hostelInfo(id, password, name, location, description, ownername, phone, " +
                "email, roomcount) " +
                "values (? , ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, "123");
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, "hostel加盟店诚邀您的光临");
            preparedStatement.setString(6, ownername);
            preparedStatement.setString(7, "13372511775");
            preparedStatement.setString(8, "352264191@qq.com");

            if (identity.equals("1")) {
                preparedStatement.setString(9, 30 + "");
            } else if (identity.equals("2")) {
                preparedStatement.setString(9, 20 + "");
            } else {
                preparedStatement.setString(9, 15 + "");
            }

            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public void selecthostelApply2myOrder() {
//        String sql = "select id, identity, hostelname, location, applytime from hostelapply";
        String sql = "select `identity`, hostelname, location, applytime from hostelapply where id=50";
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                for (int i = 0; i < 4000; i++) {
                    String date = resultSet.getString("applytime");
                    int roomId = (int) (100 + Math.random() * 30);
                    insertMyorder("50", resultSet.getString("hostelname"), resultSet.getString("location"),
                            roomId + "", randomDate(date, "2017-05-01"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void insertMyorder(String hostelId, String hostelname, String location, String room, String ordertime) {
        String sql = "insert into myorder(id, userId, hostelId, hostelname, location, room," +
                " ordertime, pay, phone, username, orderstate, checkstate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, myorderId + "");
            int userId = (int) (1000021 + Math.random() * 99979);
            preparedStatement.setString(2, userId + "");
            preparedStatement.setString(3, hostelId);
            preparedStatement.setString(4, hostelname);
            preparedStatement.setString(5, location);
            preparedStatement.setString(6, room);
            preparedStatement.setString(7, ordertime);
            preparedStatement.setString(8, 100 + "");
            preparedStatement.setString(9, "1");
            preparedStatement.setString(10, "liu" + (userId - 1000000));
            preparedStatement.setString(11, "history");
            preparedStatement.setString(12, randomCheckState());
            preparedStatement.executeUpdate();
            myorderId++;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据HostelInfo里的数据增加房间
     *
     * @param hostelid  酒店id
     * @param roomcount 对应的房间数
     */
    public void insertHostelRoom(String hostelid, int roomcount) {
        int i = 0;
        int roomId = 101;
        String sql = "insert into hostelroom(id, roomId, hostelId, isempty)" +
                "values (? , ?, ?, ?)";
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int x = 0; x < roomcount; x++, room_tableId++) {
                preparedStatement.setString(1, room_tableId + "");
                preparedStatement.setString(2, roomId + x + "");
                preparedStatement.setString(3, hostelid);
                preparedStatement.setString(4, "empty");
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向user表注入数据
     *
     * @param count id增加到的最大值
     * @return 最后一条数据id
     */
    public int insertUser(int count) {
        int i = 1000023;
        String sql = "insert into user(id, password, username, phone, email, birth, sex, bankcard, level, balance, point) " +
                "values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        try {
            for (; i < count; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, i + "");
                preparedStatement.setString(2, "123");
                preparedStatement.setString(3, "liu" + (i % 1000000));
                preparedStatement.setString(4, "1");
                preparedStatement.setString(5, "352264191@qq.com");
                preparedStatement.setString(6, randomDate("1970-01-01", "1999-01-01").toString());
                preparedStatement.setString(7, randomSex());
                preparedStatement.setString(8, getRandomNum(1, 1000000) + "");
                preparedStatement.setString(9, "大众会员");
                String balance = randomBalanceAndPoint();
                preparedStatement.setString(10, balance);
                preparedStatement.setString(11, balance);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 向hostelApply表中注入数据
     *
     * @param count
     * @return 最后一条数据id
     */
    public int insertHostelApply(int count) {
        int i = 3;
        String sql = "insert into hostelapply(id, applyer, phone, email, identity, hostelname, location, " +
                "description, approvalstate, approverId, applyType, applyTime) " +
                "values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        try {
            for (; i < count; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, i + "");
                preparedStatement.setString(2, "liu" + i);
                preparedStatement.setString(3, "13372511775");
                preparedStatement.setString(4, "352264191@qq.com");
                String tmpCity = getRandomCity();
                preparedStatement.setString(5, tmpCity.substring(0, 1));//用来标记城市等级，1/2/3 = 一线/二线发达/二线中等
                preparedStatement.setString(6, "hostel加盟店" + i);
                preparedStatement.setString(7, tmpCity.substring(1));
                preparedStatement.setString(8, "hostel加盟店诚邀您的光临");
                preparedStatement.setString(9, "approve");
                preparedStatement.setString(10, 1000000 + "");
                preparedStatement.setString(11, "open");
                preparedStatement.setString(12, randomDate("2010-01-01", "2016-10-01").toString());
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
     *
     * @return 城市
     */
    public static String getRandomCity() {
        double x = 10 * Math.random();
        if (x < 1.3) {
            return "1" + city[0];
        } else if (x < 2.6) {
            return "1" + city[1];
        } else if (x < 3.9) {
            return "1" + city[2];
        } else if (x < 5) {
            return "2" + city[3];
        } else if (x < 6.1) {
            return "2" + city[4];
        } else if (x < 7.2) {
            return "2" + city[5];
        } else if (x < 7.9) {
            return "3" + city[6];
        } else if (x < 8.6) {
            return "3" + city[7];
        } else if (x < 9.3) {
            return "3" + city[8];
        } else {
            return "3" + city[9];
        }

    }

    /**
     * 获得beginDate和endDate间的随机日期
     *
     * @param beginDate
     * @param endDate
     * @return 随机日期String
     */
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

    /**
     * CST时间转年月日
     *
     * @param CST
     * @return
     */
    private static String CST2Date(Date CST) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Date对象
        String sDate = sdf.format(CST);
        return sDate;
    }

    private static long random(long begin, long end) {
        long res = begin + (long) (Math.random() * (end - begin));
        if (res == begin || res == end) {
            return random(begin, end);
        }
        return res;
    }

    /**
     * 随机获得user性别
     *
     * @return 男or女
     */
    private static String randomSex() {
        double x = Math.random();
        if (x < 0.5) {
            return sex[0];
        } else {
            return sex[1];
        }
    }

    /**
     * 30%为非会员
     * 70%会员
     *
     * @return
     */
    private static String randomBalanceAndPoint() {
        double x = Math.random();
        if (x < 0.3) {
            return balance[0];
        } else {
            return balance[1];
        }
    }

    private static String randomCheckState() {
        double x = Math.random();
        if (x < 0.98) {
            return checkState[0];
        } else {
            return checkState[1];
        }
    }

    private static int getRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }


    private static String randomPayInfo(){
        double x = Math.random();
        if (x < 0.3) {
            return checkoutPayInfo[0];
        } else {
            return checkoutPayInfo[1];
        }
    }
}

