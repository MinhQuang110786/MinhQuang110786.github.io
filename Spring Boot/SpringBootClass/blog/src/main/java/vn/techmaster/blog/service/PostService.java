package vn.techmaster.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;

@Service
public class PostService implements iPostService {
  @Autowired
  PostRepository postRepo;
  @Autowired
  UserRepository userRepo;
  @Override
  public List<Post> getAllPostOfUser(User user) {
    // TODO Auto-generated method stub
    return user.getPosts();
  }

  @Override
  public void savePost(Post post) {
    postRepo.save(post);
  }

  @Override
  public Post findById(Long id) {
    return postRepo.findById(id).orElse(null);
  }

}
