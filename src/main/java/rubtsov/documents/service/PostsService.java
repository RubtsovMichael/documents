package rubtsov.documents.service;

import rubtsov.documents.data.model.dto.PostDto;
import rubtsov.documents.data.model.entity.Post;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 23.10.13
 * Time: 17:08
 */
public interface PostsService {

    List<Post> getAllPosts();

    Post load(Long id);

    Post save(Post post);

    Post saveFromDto(PostDto postDto);

    List<PostDto> getAllPostsDtos();

}
