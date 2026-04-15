package com.example.Newsletter_And_Email_Manager_Campain.Controller;

import com.example.Newsletter_And_Email_Manager_Campain.Model.MailingList;
import com.example.Newsletter_And_Email_Manager_Campain.Model.Subscriber;
import com.example.Newsletter_And_Email_Manager_Campain.Service.MailingListService;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mailing-lists")
public class MailingListController {

    private final MailingListService mailingListService;

    public MailingListController(MailingListService mailingListService) {
        this.mailingListService = mailingListService;
    }

    // Create Mailing List (Auth user)
    @PostMapping
    public ResponseEntity<?> createMailingList(
            @RequestParam String name,
            Authentication authentication) {

        String username = authentication.getName();

        MailingList list = mailingListService.createList(name, username);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    // Get all lists of logged-in user
    @GetMapping
    public ResponseEntity<?> getUserLists(Authentication authentication) {
        String username = authentication.getName();

        return ResponseEntity.ok(
                mailingListService.getUserLists(username)
        );
    }

    // Add subscriber
    @PostMapping("/{listId}/subscribers")
    public ResponseEntity<?> addSubscriber(
            @PathVariable String listId,
            @RequestBody Subscriber subscriber) {

        return ResponseEntity.ok(
                mailingListService.addSubscriber(listId, subscriber)
        );
    }

    // Remove subscriber
    @DeleteMapping("/{listId}/subscribers")
    public ResponseEntity<?> removeSubscriber(
            @PathVariable String listId,
            @RequestParam String email) {

        return ResponseEntity.ok(
                mailingListService.removeSubscriber(listId, email)
        );
    }
}
