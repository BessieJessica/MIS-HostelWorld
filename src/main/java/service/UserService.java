package service;

import common.RespInfo;

/**
 * Created by L.H.S on 2017/3/10.
 */
public interface UserService {

    int userRegister(String password, String username, String phone, String email, String birth, String sex, String bankcard);

    void setPwd(int userId, String pwd);

    void setBalance(int userId, double balance);

    RespInfo userLogin(int userId, String pwd);

    RespInfo getUserInfo(int userId);

    void userUpdate(int userId, String username, String sex, String birth, String phone, String email);

    void updateBankcard(int userId, String bankcard);

    RespInfo managerLogin(int managerId, String pwd);

    void recharge(int userId, double charge);
}
