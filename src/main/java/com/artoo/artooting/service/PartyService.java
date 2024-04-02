package com.artoo.artooting.service;


import com.artoo.artooting.entity.Comment;
import com.artoo.artooting.entity.Party;
import com.artoo.artooting.repository.CommentRepository;
import com.artoo.artooting.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyService {

    private final PartyRepository partyRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository, CommentRepository commentRepository) {
        this.partyRepository = partyRepository;
        this.commentRepository = commentRepository;
    }

    public List<Party> findAll() {
        List<Party> parties = partyRepository.findAll();
        parties.forEach(this::attachAverageStars);
        return parties;
    }

    private void attachAverageStars(Party party) {
        List<Comment> comments = commentRepository.findByPartyId(party.getId());
        double averageStars = comments.stream()
                .mapToInt(Comment::getStars)
                .average()
                .orElse(0.0);
        party.setAverageStars(averageStars); // Assuming you have a transient field in Party to hold this value
    }

    public Optional<Party> findById(Long id) {
        return partyRepository.findById(id);
    }

    public Party save(Party party) {
        return partyRepository.save(party);
    }

    public void deleteById(Long id) {
        partyRepository.deleteById(id);
    }

    public Party update(Party party) {
        return partyRepository.save(party); // JPA Repository의 save method 역시 id만 가지고 있다면 update용으로 쓸 수 있음
    }
}
