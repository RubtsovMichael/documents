package rubtsov.documents.web.utils;

/**
 * Created by mrubtsov on 10.01.14.
 */
public class UrlParts {

    private String base;

    private String images;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPersons() {
        return Views.PERSONS;
    }

    public String getCaseFolders() {
        return Views.CASE_FOLDERS;
    }

    public String getCorrespondents() {
        return Views.CORRESPONDENTS;
    }

    public String getDepartments() {
        return Views.DEPARTMENTS;
    }

    public String getEmployees() {
        return Views.EMPLOYEES;
    }

    public String getDocuments() {
        return Views.DOCUMENTS;
    }

    public String getPosts() {
        return Views.POSTS;
    }
}
