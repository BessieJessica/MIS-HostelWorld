package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "hostelapply")
public class HostelApply implements Serializable {

    private int id;
    private String applyer;
    private String phone;
    private String email;
    private String identity;  // 身份证
    private String hostelname;
    private String location;
    private String description;
    private String imgurl;
    private String approvalstate;  // approve; inapprove；默认：unchecked
    private int approverId;  // 经理Id
    private String applytype;  // 申请类型：open；modify
    private String applytime;

    private String notice;  //公告


    public HostelApply() {};

    public HostelApply(String applyer, String phone, String email, String identity, String hostelname, String location, String description, String imgurl, String approvalstate, String applytype, String applytime) {
        this.applyer = applyer;
        this.phone = phone;
        this.email = email;
        this.identity = identity;
        this.hostelname = hostelname;
        this.location = location;
        this.description = description;
        this.imgurl = imgurl;
        this.approvalstate = approvalstate;
        this.applytype = applytype;
        this.applytime = applytime;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getHostelname() {
        return hostelname;
    }

    public void setHostelname(String hostelname) {
        this.hostelname = hostelname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getApprovalstate() {
        return approvalstate;
    }

    public void setApprovalstate(String approvalstate) {
        this.approvalstate = approvalstate;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public String getApplytype() {
        return applytype;
    }

    public void setApplytype(String applytype) {
        this.applytype = applytype;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
