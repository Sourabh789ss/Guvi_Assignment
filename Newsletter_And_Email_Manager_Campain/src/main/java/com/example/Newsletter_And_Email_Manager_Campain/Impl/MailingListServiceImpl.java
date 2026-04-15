package com.example.Newsletter_And_Email_Manager_Campain.Impl;

import com.example.Newsletter_And_Email_Manager_Campain.Model.MailingList;
import com.example.Newsletter_And_Email_Manager_Campain.Model.Subscriber;
import com.example.Newsletter_And_Email_Manager_Campain.Repository.MailingListRepository;
import com.example.Newsletter_And_Email_Manager_Campain.Service.MailingListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailingListServiceImpl implements MailingListService {

    private final MailingListRepository mailingListRepository;

    public MailingListServiceImpl(MailingListRepository mailingListRepository) {
        this.mailingListRepository = mailingListRepository;
    }

    @Override
    public MailingList createList(String name, String userId) {
        if(name == null || name.trim().isBlank() || userId == null || userId.trim().isBlank()){
            throw new IllegalArgumentException("Both the fields are required");
        }

        MailingList list = new MailingList();
        list.setName(name);
        list.setUserId(userId);
        list.setSubscribers(new ArrayList<>());

        return mailingListRepository.save(list);
    }

    @Override
    public MailingList addSubscriber(String listId, Subscriber subscriber) {

        if (listId == null || listId.trim().isBlank() || subscriber == null) {
            throw new RuntimeException("Subscriber can't be added");
        }

        MailingList list = mailingListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Mailing list not found"));

        if (list.getSubscribers() == null) {
            list.setSubscribers(new ArrayList<>());
        }

        boolean exists = list.getSubscribers()
                .stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(subscriber.getEmail()));

        if (exists) {
            throw new RuntimeException("Subscriber already exists");
        }

        list.getSubscribers().add(subscriber);

        return mailingListRepository.save(list);
    }

    @Override
    public MailingList removeSubscriber(String listId, String email) {

        if (listId == null || listId.trim().isBlank()
                || email == null || email.trim().isBlank()) {
            throw new RuntimeException("Subscriber can't be removed");
        }

        MailingList list = mailingListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Mailing list not found"));

        if (list.getSubscribers() == null) {
            throw new RuntimeException("Subscriber can't be removed");
        }

        boolean exist = list.getSubscribers().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(email));

        if (!exist) {
            throw new RuntimeException("Subscriber not found");
        }

        list.getSubscribers().removeIf(s -> s.getEmail().equalsIgnoreCase(email));

        return mailingListRepository.save(list);
    }

    @Override
    public List<MailingList> getUserLists(String userId) {
        if(userId == null || userId.trim().isBlank()){
            throw new RuntimeException("UserID can't be empty");
        }

        return mailingListRepository.findByUserId(userId);
    }
}
