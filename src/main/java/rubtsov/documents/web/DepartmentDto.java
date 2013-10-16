package rubtsov.documents.web;

import rubtsov.documents.data.model.Department;

/**
 * Created by mike on 11.10.13.
 */
public class DepartmentDto {

    private int departmentId;

    private String code;

    private String shortName;

    private String fullName;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    @Override
//    public String toString() {
//        return "DepartmentDto{" +
//                "departmentId=" + departmentId +
//                ", shortName='" + shortName + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", code='" + code + '\'' +
//                '}';
//    }

    public DepartmentDto() {
    }

    public DepartmentDto(Department department) {
        this.departmentId = department.getDepartmentId();
        this.code = department.getCode();
        this.shortName = department.getShortName();
        this.fullName = department.getFullName();
    }

}
