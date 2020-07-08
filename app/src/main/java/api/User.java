package api;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String response;
    @SerializedName("compid")
    private String compid;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("userid")
    private String userid;

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("subject")
    private String subject;
    @SerializedName("status")
    private String status;
    @SerializedName("category")
    private String category;
    @SerializedName("department")
    private String department;
    @SerializedName("identity")
    private String identity;
    @SerializedName("reportid")
    private String reportid;
    @SerializedName("ctime")
    private String ctime;
    @SerializedName("message")
    private String message;
    public String getResponse() {
        return response;
    }
    public User(String compid,String subject, String ctime){
      this.compid=compid;
      this.subject=subject;
      this.ctime=ctime;
    }
}
