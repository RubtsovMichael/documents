package rubtsov.documents.data.model.entity;

import javax.persistence.*;

/**
 * Created by mike on 18.07.13.
 */
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID_POST")
    private Long postId;

    @Column(name="DISPLAY_NAME")
    private String displayName;

    @Column(name="FULL_NAME")
    private String fullName;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
