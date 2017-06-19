package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "checkin")
public class CheckIn implements Serializable {

    @Id
    private int id;
    private int hostelId;
    private int roomId;
    private String checktime;
    private String leavetime;
    private String checkinstaff;
    private double pay;
    private String ismember;
    private String paytype;
    private int userId;
    private String state;  // valid, invalid; checkout后设置对应房间号为invalid
    private int orderId;

    public CheckIn() {
    }

    public CheckIn(int hosId, int roomId, String checktime, String leavetime, String checkinstaff, double pay, String ismember, String paytype, int userId, int orderId) {
        this.hostelId = hosId;
        this.roomId = roomId;
        this.checktime = checktime;
        this.leavetime = leavetime;
        this.checkinstaff = checkinstaff;
        this.pay = pay;
        this.ismember = ismember;
        this.paytype = paytype;
        this.userId = userId;
        this.orderId = orderId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public String getCheckinstaff() {
        return checkinstaff;
    }

    public void setCheckinstaff(String checkinstaff) {
        this.checkinstaff = checkinstaff;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getIsmember() {
        return ismember;
    }

    public void setIsmember(String ismember) {
        this.ismember = ismember;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
