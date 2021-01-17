package vn.techmaster.blog.service;

import vn.techmaster.blog.controller.request.LoginRequest;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface IAuthenService {
  void login(LoginRequest loginRequest) throws AuthenException;
  Optional<User> findByEmail(String email);
  User findById(String id);


  void saveUser(User user);
  List<User> findAll(User user);
  List<User> findAll();
}
