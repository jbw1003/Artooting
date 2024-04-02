package com.artoo.artooting.service;

import com.artoo.artooting.entity.Comment;
import com.artoo.artooting.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findAllCommentsByPartyId(Long partyId) {
        return commentRepository.findByPartyId(partyId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
}
