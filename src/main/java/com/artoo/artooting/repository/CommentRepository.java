package com.artoo.artooting.repository;

import com.artoo.artooting.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Method to find comments by partyId
    List<Comment> findByPartyId(Long partyId);
}
