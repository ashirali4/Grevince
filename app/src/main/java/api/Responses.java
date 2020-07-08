package api;

import com.google.gson.annotations.SerializedName;

public class Responses {
    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @SerializedName("ctime")
    private String ctime;
    @SerializedName("dfrom")
    private String dfrom;
    @SerializedName("dto")
    private String dto;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    private String status;

    public String getDfrom() {
        return dfrom;
    }

    public void setDfrom(String dfrom) {
        this.dfrom = dfrom;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @SerializedName("remark")
    private String remark;

    public Responses(){

    }


}
