package neo4j.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-11-01 14:28
 */
@Data
public class WritEntity {
    private List<Lawyer> lawyerList;
    private List<LawFirm> lawFirmList;
    private List<RefLawyer2Ls> refLawyer2LsList;
    private List<RefLawyer2Ws> refLawyer2WsList;
    private List<RefLs2Ws> refLs2WsList;

    public void addLawyer(Lawyer lawyer) {
        if (lawyer == null) {
            return;
        }
        if (lawyerList == null) {
            lawyerList = new ArrayList<>();
        }
        lawyerList.add(lawyer);
    }

    public void addLawFirm(LawFirm lawFirm) {
        if (lawFirm == null) {
            return;
        }
        if (lawFirmList == null) {
            lawFirmList = new ArrayList<>();
        }
        lawFirmList.add(lawFirm);
    }

    public void addRefLawyer2Ls(RefLawyer2Ls refLawyer2Ls) {
        if (refLawyer2Ls == null) {
            return;
        }
        if (refLawyer2LsList == null) {
            refLawyer2LsList = new ArrayList<>();
        }
        refLawyer2LsList.add(refLawyer2Ls);
    }

    public void addRefLawyer2Ws(RefLawyer2Ws refLawyer2Ws) {
        if (refLawyer2Ws == null) {
            return;
        }
        if (refLawyer2WsList == null) {
            refLawyer2WsList = new ArrayList<>();
        }
        refLawyer2WsList.add(refLawyer2Ws);
    }

    public void addRefLs2Ws(RefLs2Ws refLs2Ws) {
        if (refLs2Ws == null) {
            return;
        }
        if (refLs2WsList == null) {
            refLs2WsList = new ArrayList<>();
        }
        refLs2WsList.add(refLs2Ws);
    }

    public void addLawyer(List<Lawyer> lawyerArr) {
        if (lawyerArr == null) {
            return;
        }
        if (lawyerList == null) {
            lawyerList = new ArrayList<>();
        }
        lawyerList.addAll(lawyerArr);
    }

    public void addLawFirm(List<LawFirm> lawFirmArr) {
        if (lawFirmArr == null) {
            return;
        }
        if (lawFirmList == null) {
            lawFirmList = new ArrayList<>();
        }
        lawFirmList.addAll(lawFirmArr);
    }

    public void addRefLawyer2Ls(List<RefLawyer2Ls> refLawyer2LsArr) {
        if (refLawyer2LsArr == null) {
            return;
        }
        if (refLawyer2LsList == null) {
            refLawyer2LsList = new ArrayList<>();
        }
        refLawyer2LsList.addAll(refLawyer2LsArr);
    }

    public void addRefLawyer2Ws(List<RefLawyer2Ws> refLawyer2WsArr) {
        if (refLawyer2WsArr == null) {
            return;
        }
        if (refLawyer2WsList == null) {
            refLawyer2WsList = new ArrayList<>();
        }
        refLawyer2WsList.addAll(refLawyer2WsArr);
    }

    public void addRefLs2Ws(List<RefLs2Ws> refLs2WsArr) {
        if (refLs2WsArr == null) {
            return;
        }
        if (refLs2WsList == null) {
            refLs2WsList = new ArrayList<>();
        }
        refLs2WsList.addAll(refLs2WsArr);
    }

    public void addWritEntity(WritEntity writEntity) {
        if (writEntity == null) {
            return;
        }
        if (writEntity.getLawyerList() != null) {
            this.addLawyer(writEntity.getLawyerList());
        }
        if (writEntity.getLawFirmList() != null) {
            this.addLawFirm(writEntity.getLawFirmList());
        }
        if (writEntity.getRefLawyer2LsList() != null) {
            this.addRefLawyer2Ls(writEntity.getRefLawyer2LsList());
        }
        if (writEntity.getRefLawyer2WsList() != null) {
            this.addRefLawyer2Ws(writEntity.getRefLawyer2WsList());
        }
        if (writEntity.getRefLs2WsList() != null) {
            this.addRefLs2Ws(writEntity.getRefLs2WsList());
        }
    }
}
