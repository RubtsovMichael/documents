package rubtsov.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rubtsov.documents.data.model.dto.PostDto;
import rubtsov.documents.data.model.entity.Post;
import rubtsov.documents.data.repository.PostsRepository;

import java.util.ArrayList;
import java.util.List;

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
}