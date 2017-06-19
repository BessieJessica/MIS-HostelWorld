package dao;

import common.RespInfo;
import model.User;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface UserDao {

    int insert(User user);

    void setPwd(int userId, String pwd);

    void setBalance(int userId, double balance);

    RespInfo getPwd(int userId);

    User getUserInfo(int userId);

    void updateUserInfo(User user);

    void updateBankcard(int userId, String bankcard);

    RespInfo getManagerPwd(int managerId);

    void charge(int userId, double charge);
}
