package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.repository.CommentRepository;

@Service
public class CommentService implements iComment {
    @Autowired
    CommentRepository commentRepo;

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
