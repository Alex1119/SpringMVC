package Entity;

import java.util.ArrayList;

public class HealthTraceEntity {

    private String Id;
    private String AccountId;
    private String CustomName;
    private String DeptName;
    private int Type;
    private String ReportDateFormat;
    private String CreateDate;
    private String CheckUnitCode;
    private String WorkNo;
    private String DeptImageSrc;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String AccountId) {
        this.AccountId = AccountId;
    }

    public String getCustomName() {
        return CustomName;
    }

    public void setCustomName(String CustomName) {
        this.CustomName = CustomName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getReportDateFormat() {
        return ReportDateFormat;
    }

    public void setReportDateFormat(String ReportDateFormat) {
        this.ReportDateFormat = ReportDateFormat;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getCheckUnitCode() {
        return CheckUnitCode;
    }

    public void setCheckUnitCode(String CheckUnitCode) {
        this.CheckUnitCode = CheckUnitCode;
    }

    public String getWorkNo() {
        return WorkNo;
    }

    public void setWorkNo(String WorkNo) {
        this.WorkNo = WorkNo;
    }

    public String getDeptImageSrc() {
        return DeptImageSrc;
    }

    public void setDeptImageSrc(String DeptImageSrc) {
        this.DeptImageSrc = DeptImageSrc;
    }

}
