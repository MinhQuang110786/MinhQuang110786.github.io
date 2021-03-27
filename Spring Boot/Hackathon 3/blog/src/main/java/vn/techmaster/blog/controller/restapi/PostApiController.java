package vn.techmaster.blog.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.DTO.*;
import vn.techmaster.blog.controller.Route;
import vn.techmaster.blog.controller.request.CommentRequest;
import vn.techmaster.blog.controller.request.IdRequest;
import vn.techmaster.blog.controller.request.PostRequest;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.Tag;
import vn.techmaster.blog.repository.TagRepository;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;
import vn.techmaster.blog.service.PostException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api")
public class PostApiController {
  @Autowired private IAuthenService authenService;
  @Autowired private IPostService postService;



  //1. Tra ve danh sach Post duoc phan trang
  @GetMapping("/post")
  public ResponseEntity<List<PostPOJO>> getPostPage(@RequestParam(name="size") int size, @RequestParam(name="page") int page ){
    Page<Post> pages = postService.findAllPaging(page,size);
    List<Post> posts = pages.getContent();
    List<PostPOJO> listPosts = new ArrayList<>();
    posts.forEach(p->{
      listPosts.add(new PostPOJO(p.getId(),p.getTitle(),p.getContent(),p.getUser().getId(),p.getUser().getFullname(),p.getLastUpdate()));
    });
    Comparator<PostPOJO> comparelastUpdate = (PostPOJO p1,PostPOJO p2)->p1.getLastUpdate().compareTo(p2.getLastUpdate());
    Collections.sort(listPosts,comparelastUpdate);
    return ResponseEntity.ok(listPosts);
  }
//  @GetMapping("/posts")  //Liệt kê các post của một blogger cụ thể
//  public String getAllPosts(Model model, HttpServletRequest request) {
//    UserInfo user = authenService.getLoginedUser(request);
//    if (user != null) {
//      model.addAttribute("user", user);
//      List<Post> posts = postService.getAllPostsByUserID(user.getId());
//      model.addAttribute("posts", posts);
//      return Route.ALLPOSTS;
//    } else {
//      return Route.REDIRECT_HOME;
//    }
//  }

  //2. Tra ve post theo id
  @GetMapping("/post/{id}")
  public ResponseEntity<PostInfo> getPostById(@PathVariable long id){
    Optional<Post> optionalPost = postService.findById(id);
    if (optionalPost.isPresent()) {
        Post post = optionalPost.get();
        PostPOJO postPOJO = new PostPOJO(post.getId(),post.getTitle(),post.getContent()
                ,post.getUser().getId(),post.getUser().getFullname(),post.getLastUpdate());
        Set<TagPOJO> tagList = new HashSet<>();
        post.getTags().forEach(tag->{
          tagList.add(new TagPOJO(tag.getId(),tag.getName()));
        });
        PostInfo postInfo = new PostInfo(postPOJO.getId(),postPOJO.getTitle(),postPOJO.getContent(),
                postPOJO.getUser_id(),postPOJO.getUserFullName(),postPOJO.getLastUpdate(),
                tagList,post.getComments().size());
        return ResponseEntity.ok(postInfo);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
    }
  }
//
//  @GetMapping("/post")  //Show form để tạo mới Post
//  public String createEditPostForm(Model model, HttpServletRequest request) {
//    UserInfo user = authenService.getLoginedUser(request);
//    if (user != null) {
//      PostRequest postReqest = new PostRequest();
//      postReqest.setUser_id(user.getId());
//      model.addAttribute("post", postReqest);
//      model.addAttribute("user", user);
//      List<Tag> tags = postService.getAllTags();
//      model.addAttribute("allTags", tags);
//      return Route.POST;
//    } else {
//      return Route.REDIRECT_HOME;
//    }
//  }
//
//  @PostMapping("/post")
//  public String createEditPostSubmit(@Valid @ModelAttribute("post") PostRequest postRequest, BindingResult bindingResult, Model model, HttpServletRequest request) {
//    UserInfo user = authenService.getLoginedUser(request);
//    if (bindingResult.hasErrors()) {
//      List<Tag> tags = postService.getAllTags();
//      model.addAttribute("tags", tags);
//			return Route.POST;
//    }
//
    //3. Tao mot Post moi
    @PostMapping("/post")
    public String makeANewPost(@RequestBody PostAPI postAPI) throws PostException {
       Set<Integer> tagSet = postAPI.getTag_id();
       List<Tag> tag = postService.getAllTags();
       Set<Tag> tags = new HashSet<>();
       tagSet.forEach(t->{
           tags.add(tag.get(t));
       });
       PostRequest request = new PostRequest(null,postAPI.getTitle()
       ,postAPI.getContent(),postAPI.getUser_id(),tags);
       postService.createNewPost(request);
       return "Succesfully";

    }

//    if (user != null && user.getId() == postRequest.getUser_id()) {
//      try {
//        if (postRequest.getId() == null) {
//          postService.createNewPost(postRequest); //Create
//        } else {
//          postService.updatePost(postRequest);  //Edit
//        }
//      } catch (PostException pe) {
//        return Route.REDIRECT_HOME;
//      }
//
//      return Route.REDIRECT_POSTS;
//    } else {
//      return Route.REDIRECT_HOME;
//    }
//  }
//
//  //Lấy ra một post cụ thể cùng comment
//  @GetMapping("/post/{id}")
//  public String showPostAndComment(@PathVariable("id") long id, Model model, HttpServletRequest request) {
//    Optional<Post> optionalPost = postService.findById(id);
//    if (optionalPost.isPresent()) {
//      Post post = optionalPost.get();
//      PostPOJO postPOJO = PostMapper.INSTANCE.postToPostPOJO(post);
//      model.addAttribute("post", postPOJO);
//
//      Set<Tag> tags = post.getTags();
//      model.addAttribute("tags", tags);
//
//      List<Comment> comments = post.getComments();
//      Collections.reverse(comments);
//      model.addAttribute("comments", comments); //Trả  về danh sách comment
//
//      UserInfo user = authenService.getLoginedUser(request); //Login user
//
//      if (user != null) { //Nếu user login và xem post, cần bổ xung chức năng comment
//        model.addAttribute("user", user); //Người dùng đang login
//        model.addAttribute("commentRequest", new CommentRequest(postPOJO.getId()));
//      } else {
//        model.addAttribute("commentRequest", new CommentRequest());
//      }
//
//      return Route.POST_COMMENT;
//    } else {
//      return Route.REDIRECT_HOME;
//    }
//  }
//
//  //Xoá một post
//  @PostMapping("/post/delete")
//  public String deletePost(@ModelAttribute IdRequest idRequest, HttpServletRequest request) {
//    UserInfo user = authenService.getLoginedUser(request);
//    if (user != null) {
//      postService.deletePostById(idRequest.getId());
//    }
//    return Route.REDIRECT_POSTS;
//  }
//
//  //Mở form để edit một post
//  @PostMapping("/post/edit")
//  public String editPost(@ModelAttribute IdRequest idRequest, Model model, HttpServletRequest request) {
//    UserInfo user = authenService.getLoginedUser(request);
//
//    Optional<Post> optionalPost = postService.findById(idRequest.getId());
//
//    if (user != null && optionalPost.isPresent()) {
//      Post post = optionalPost.get();
//      PostRequest postReqest =  PostMapper.INSTANCE.postToPostRequest(post);
//
//      model.addAttribute("post", postReqest);
//      List<Tag> tags = postService.getAllTags();
//      model.addAttribute("allTags", tags);
//      UserInfo userInfo = UserMapper.INSTANCE.userToUserInfo(post.getUser());
//      model.addAttribute("user", userInfo);
//      return Route.POST;
//    } else {
//      return Route.REDIRECT_POSTS;
//    }
//  }

    //4. Cap nhat noi dung post theo id
    @PutMapping("/post/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody PostUpdate update, @PathVariable long id) throws PostException {
        Set<Integer> tagSet = update.getTag_id();
        List<Tag> tag = postService.getAllTags();
        Set<Tag> tags = new HashSet<>();
        tagSet.forEach(t->{
            tags.add(tag.get(t));
        });

        PostRequest request = new PostRequest(update.getTitle(),update.getContent(),tags);
        request.setId(id);
        postService.updatePost(request);
        return ResponseEntity.ok().build();
    }

    //5. Liet ke cac comment thuoc post
    @GetMapping("/comments/{id}")
    public ResponseEntity<PostComment> getCommentByPostId(@PathVariable long id){
      Optional<Post> optionalPost = postService.findById(id);
      PostComment postComment = new PostComment();
      if(optionalPost.isPresent()){
          Post post = optionalPost.get();
          List<Comment> comments = post.getComments();
          List<CommentPOJO> list = new ArrayList<>();
          comments.forEach(c->{
              CommentPOJO pc = new CommentPOJO(c.getContent(),c.getUser().getFullname());
              list.add(pc);
          });
          postComment.setTitle(post.getTitle());
          postComment.setComments(list);
          return ResponseEntity.ok(postComment);
      }
      else
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}