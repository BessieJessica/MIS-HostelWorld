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
@Table(name = "hostelregister")
public class HostelRegister implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String checkcode;
    private int applyId;
    private String state;   // 是否已注册：Reg；notReg

    public HostelRegister() {}

    public HostelRegister(String checkcode, int applyId, String state) {
        this.checkcode = checkcode;
        this.applyId = applyId;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
