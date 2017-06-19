package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String password;
    private String username;
    private String phone;
    private String email;
    private String birth;
    private String sex;
    private String bankcard;
    private String level = "大众会员";
    private double balance;
    private int point;
    private String jointime;

    public User() {

    }

    public User(String password, String username, String phone, String email, String birth, String sex, String bankcard) {
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.sex = sex;
        this.bankcard = bankcard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getJointime() { return jointime; }

    public void setJointime(String entertime) { this.jointime = entertime; }
}
