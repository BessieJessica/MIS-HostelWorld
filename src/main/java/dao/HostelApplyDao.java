package dao;

import model.HostelApply;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface HostelApplyDao {

     void insert(HostelApply apply);

     HostelApply updateState(int applyId, String state, int approverId);

     List<HostelApply> getApply();

     List<HostelApply> getHistory();

     HostelApply getTheApply(int applyId);
}
