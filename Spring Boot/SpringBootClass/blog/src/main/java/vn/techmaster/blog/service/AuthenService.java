package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.repository.PostRepository;
import vn.techmaster.blog.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenService implements IAuthenService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  @Override
  public void login(LoginRequest loginRequest) throws AuthenException {
    var user = userRepository.findByEmail(loginRequest.getEmail());
    if (user.isPresent()) {
      if (!user.get().getPassword().equals(loginRequest.getPassword())){
        throw new AuthenException("Wrong password");
      }
    } else {
      throw new AuthenException("User with email " + loginRequest.getEmail() + " does not exist");
    }

  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User findById(String id) {

    return userRepository.findById(id).orElse(null);
  }



  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }

  @Override
  public List<User> findAll(User user) {
    List<User> userList = userRepository.findAll();
    userList.remove(user);
    return userList;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }


}
