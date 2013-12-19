package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Department;

import java.util.HashSet;
import java.util.Set;

import static rubtsov.documents.data.model.utils.Conversions.stringToViewString;
/**
 * Created by mike on 11.10.13.
 */
public class DepartmentDto {

    private Long departmentId;

    private String code;

    private String shortName;

    private String fullName;

    private Set<EmployeeDto> employees = new HashSet<>();

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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

    public Set<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return shortName + " [" + fullName + "]";
    }

    public DepartmentDto() {
    }

    public DepartmentDto(Department department) {
        departmentId = department.getDepartmentId();
        code = stringToViewString(department.getCode());
        shortName = stringToViewString(department.getShortName());
        fullName = stringToViewString(department.getFullName());
    }

}
