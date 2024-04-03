package com.artoo.artooting.controller;

import com.artoo.artooting.entity.Comment;
import com.artoo.artooting.entity.Party;
import com.artoo.artooting.entity.User;
import com.artoo.artooting.service.CommentService;
import com.artoo.artooting.service.PartyService;
import com.artoo.artooting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parties")
public class PartyController {

    private final PartyService partyService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public PartyController(PartyService partyService, UserService userService,CommentService commentService) {
        this.partyService = partyService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping
    public String listParties(Model model) {
        model.addAttribute("parties", partyService.findAll());
        return "parties/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("party", new Party());
        return "parties/create";
    }

    @PostMapping
    public String saveParty(@ModelAttribute Party party) {
        partyService.save(party);
        return "redirect:/parties";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Party party = partyService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid party Id:" + id));
        model.addAttribute("party", party);
        return "parties/edit";
    }

    @PostMapping("/update/{id}")
    public String updateParty(@PathVariable("id") long id, @ModelAttribute Party party) {
        party.setId(id);
        partyService.update(party);
        return "redirect:/parties";
    }

    @GetMapping("/delete/{id}")
    public String deleteParty(@PathVariable("id") long id) {
        partyService.deleteById(id);
        return "redirect:/parties";
    }

    // 파티 세부정보랑 댓글
    @GetMapping("/parties/{id}")
    public String viewParty(@PathVariable Long id, Model model) {
        Party party = partyService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid party Id:" + id));
        List<Comment> comments = commentService.findAllCommentsByPartyId(id);
        model.addAttribute("party", party);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment()); // Object for the comment form
        return "parties/view";
    }

    // 댓글 저장
    @PostMapping("/parties/{partyId}/comments")
    public String addComment(@PathVariable Long partyId, @ModelAttribute Comment newComment,
                             @AuthenticationPrincipal User principal) {
        // User와 Party를 ID를 통해서 불러옴, 그리고 newComment에 기입
        // userService와 partyService가 둘 다 이용 가능하다는 가정
        long userId = principal.getId(); // Get username from logged-in user
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Party party = partyService.findById(partyId)
                .orElseThrow(() -> new IllegalArgumentException("Party not found: " + partyId));

        newComment.setUser(user);
        newComment.setParty(party);
        commentService.saveComment(newComment);

        return "redirect:/parties/" + partyId;
    }
}
