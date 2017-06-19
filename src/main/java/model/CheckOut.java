package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */
@Entity
@Table(name = "checkout")
public class CheckOut implements Serializable {

    @Id
    private int id;
    private int hostelId;
    private int roomId;
    private String checkintime;
    private String checkouttime;
    private String checkinstaff;  // 联系人1-手机号；联系人2-手机号
    private double totalpay;
    private String payinfo;
    private int userId;

    public CheckOut() {
    }

    public CheckOut(int hostelId, int roomId, String checkintime, String checkouttime, String checkinstaff, double totalpay, String payinfo, int userId) {
        this.hostelId = hostelId;
        this.roomId = roomId;
        this.checkintime = checkintime;
        this.checkouttime = checkouttime;
        this.checkinstaff = checkinstaff;
        this.totalpay = totalpay;
        this.payinfo = payinfo;
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public String getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(String checkouttime) {
        this.checkouttime = checkouttime;
    }

    public String getCheckinstaff() {
        return checkinstaff;
    }

    public void setCheckinstaff(String checkinstaff) {
        this.checkinstaff = checkinstaff;
    }

    public double getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(double totalpay) {
        this.totalpay = totalpay;
    }

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String paytype) {
        this.payinfo = paytype;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }
}
