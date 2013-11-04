package rubtsov.documents.web.dto;

import rubtsov.documents.data.model.Department;

import static rubtsov.documents.web.Utils.Conversions.stringToViewString;
/**
 * Created by mike on 11.10.13.
 */
public class DepartmentDto implements EntityDto<Department>{

    private String departmentId;

    private String code;

    private String shortName;

    private String fullName;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
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

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentId=" + departmentId +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public DepartmentDto() {
    }

    public DepartmentDto(Department department) {
        loadFromEntity(department);
    }

    @Override
    public void loadFromEntity(Department department) {
        this.departmentId = department.getDepartmentId().toString();
        this.code = stringToViewString(department.getCode());
        this.shortName = stringToViewString(department.getShortName());
        this.fullName = stringToViewString(department.getFullName());
    }

    @Override
    public void saveToEntity(Department dep) {
        dep.setFullName(getFullName());
        dep.setShortName(getShortName());
        dep.setCode(getCode());
    }
}
