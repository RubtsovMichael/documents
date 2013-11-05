package rubtsov.documents.data.model.dto;

import rubtsov.documents.data.model.entity.Post;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 23.10.13
 * Time: 16:52
 */
public class PostDto implements Serializable {

    private Long postId;

    private String displayName;

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

    public PostDto() {
    }

    public PostDto(Post post) {
        this.postId = post.getPostId();
        this.displayName = post.getDisplayName();
        this.fullName = post.getFullName();
    }
}
