package cn.fmachine.vo;

import java.io.Serializable;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 5/2/16
 */
public class Question implements Serializable {
    private String id;
    private String qDesc;
    private int qType;
    private int qaType;

    public Question() {
    }

    public Question(String id, String qDesc, int qType, int qaType) {
        this.id = id;
        this.qDesc = qDesc;
        this.qType = qType;
        this.qaType = qaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getqDesc() {
        return qDesc;
    }

    public void setqDesc(String qDesc) {
        this.qDesc = qDesc;
    }

    public int getqType() {
        return qType;
    }

    public void setqType(int qType) {
        this.qType = qType;
    }

    public int getQaType() {
        return qaType;
    }

    public void setQaType(int qaType) {
        this.qaType = qaType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", qDesc='" + qDesc + '\'' +
                ", qType=" + qType +
                ", qaType=" + qaType +
                '}';
    }
}
