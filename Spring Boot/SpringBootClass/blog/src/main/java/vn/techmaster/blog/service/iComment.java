package vn.techmaster.blog.service;

import vn.techmaster.blog.model.Comment;

public interface iComment {
    Comment save(Comment comment);
    void deleteById(Long id);
}
