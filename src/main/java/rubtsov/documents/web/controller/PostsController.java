package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.Post;
import rubtsov.documents.service.PostsService;
import rubtsov.documents.web.Utils.Views;
import rubtsov.documents.web.dto.PostDto;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 23.10.13
 * Time: 17:05
 */
@Controller
@RequestMapping(Views.POSTS + "/**")
public class PostsController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(PostsController.class);

    @Autowired
    private PostsService postsService;

    @RequestMapping(method = RequestMethod.GET, value = Views.POSTS)
    public String getPosts(ModelMap model) {

        model.put("posts", postsService.getAllPosts());

        return Views.POSTS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.POSTS + "/{postId}")
    public String getPostForm(@PathVariable Long postId, ModelMap model) {

        PostDto postDto;
        if (postId == -1) {
            postDto = new PostDto();
            postDto.setPostId(postId);
        } else {
            Post post = postsService.load(postId);
            if (post == null) {
                throw new IllegalArgumentException("Department ID is not found");
            }
            postDto = new PostDto(post);
        }

        model.put("postCommand", postDto);

        return Views.POST_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.POSTS + "/*")
    public String save(@ModelAttribute("postCommand") PostDto postDto) {

        if (postDto == null) {
            throw new IllegalArgumentException("A postDto is required");
        }

        LOG.debug("Submitted postId " + postDto.getPostId());
        Post post = postsService.load(Long.valueOf(postDto.getPostId()));
//        updateDepartmentFromDto(dep, departmentCommand);
        postsService.save(post);

        return "redirect:" + Views.POSTS;
    }

}
