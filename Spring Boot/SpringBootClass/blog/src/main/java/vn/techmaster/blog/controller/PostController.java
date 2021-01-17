package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.security.CookieManager;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.iComment;
import vn.techmaster.blog.service.iPostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {
    @Autowired
    private CookieManager cookieManager;
    @Autowired
    private IAuthenService authenService;
    @Autowired
    private iPostService postService;
    @Autowired
    private iComment commentService;

    private String loginEmail;

    @GetMapping("/posts")
    public String getAllPosts(HttpServletRequest request, Model model) {
        loginEmail = cookieManager.getAuthenticatedEmail(request);
        if (loginEmail != null) {
            User user = authenService.findByEmail(loginEmail).get();
            model.addAttribute("user", user);
            List<Post> posts = postService.getAllPostOfUser(user);
            model.addAttribute("posts", posts);
            return Route.ALLPOSTS;
        } else {
            return Route.REDIRECT_HOME;
        }

    }

    @GetMapping("/{id}")
    public String renderPostForm(@PathVariable("id") String id, Model model) {

        Post post = new Post();
        User user = authenService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("post", post);
            return Route.POSTFORM;
        }

        return Route.REDIRECT_HOME;


    }

    //
    @PostMapping("/save/{id}")
    public String processPostForm(@PathVariable("id") String id, @ModelAttribute("post") Post post, BindingResult result) {
        if (!result.hasFieldErrors()) {
            postService.savePost(post);
            User user = authenService.findById(id);
            user.addPost(post);
            authenService.saveUser(user);
        }
        return Route.REDIRECT_HOME;
    }


    @GetMapping("/post/edit/{id}")
    public String showEditPostForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);

        model.addAttribute("post", post);
        User user = authenService.findByEmail(loginEmail).get();
        List<User> userList = authenService.findAll(user);
        model.addAttribute("users", userList);
        return Route.EDITPOSTFORM;
    }

    @PostMapping("/save")
    public String saveEditPost(@ModelAttribute("post") Post post, BindingResult result, HttpServletRequest request) throws ServletException {
        if (!result.hasFieldErrors()) {

            User user = authenService.findByEmail(loginEmail).get();
            List<User> users = authenService.findAll(user);

            String[] cms = request.getParameterValues("comment");


            int i=0;
            for(var usr:users){
                Comment c = new Comment(cms[i++]);

                usr.addComment(c);
                post.addComment(c);
                authenService.saveUser(usr);
                commentService.save(c);
            }
            postService.savePost(post);

        }

        return Route.REDIRECT_HOME;
    }

    @GetMapping("/comment/{id}")
    public String removeComment(@PathVariable("id") Long id){
        commentService.deleteById(id);
        return Route.REDIRECT_HOME;
    }
}
