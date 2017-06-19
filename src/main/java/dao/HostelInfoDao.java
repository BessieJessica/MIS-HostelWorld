package dao;

import common.RespInfo;
import model.HostelApply;
import model.HostelInfo;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface HostelInfoDao {

    RespInfo getPwd(int hosId);

    HostelInfo getInfo(int hosId);

    void updateInfo(HostelApply ha);

    List<HostelInfo> getHostelByLocation(String location);

    List<HostelInfo> getHostelByName(String name);

    List getAllName();
}
