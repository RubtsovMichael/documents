package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.PostDto;
import rubtsov.documents.data.model.entity.Post;
import rubtsov.documents.data.repository.PostsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 23.10.13
 * Time: 17:09
 */
@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Post load(Long id) {
        return postsRepository.findOne(id);
    }

    @Override
    public Post save(Post post) {
        return postsRepository.saveAndFlush(post);
    }

    @Override
    public Post saveFromDto(PostDto postDto) {
        if (postDto == null) {
            throw new IllegalArgumentException("Post dto required!");
        }

        if (postDto.getPostId() == null) {
            throw new IllegalArgumentException("Post dto has null id!");
        }

        Post post;
        if (postDto.getPostId().equals(Long.valueOf(-1L))) {
            post = new Post();
        } else {
            post = load(postDto.getPostId());
            if (post == null)
                throw new IllegalArgumentException("Post with id [" + postDto.getPostId() + "] is not found for update");
        }

        post.setFullName(postDto.getFullName());
        post.setDisplayName(postDto.getDisplayName());

        return save(post);
    }

    @Override
    public List<PostDto> getAllPostsDtos() {
        ArrayList<PostDto> postDtos = new ArrayList<>();

        for (Post post : getAllPosts()) {
            postDtos.add(new PostDto(post));
        }

        return postDtos;
    }

    @Override
    public PostDto getAsDto(Long id) {
        Post post = load(id);

        if (post == null) {
            throw new IllegalArgumentException("Post ID [" + id + "] is not found");
        }

        return new PostDto(post);
    }

    @Override
    public Map<Long, PostDto> getPostsAsMap() {
        HashMap<Long, PostDto> posts = new HashMap<>();
        for (PostDto postDto : getAllPostsDtos()) {
            posts.put(postDto.getPostId(), postDto);
        }
        return posts;
    }
}
