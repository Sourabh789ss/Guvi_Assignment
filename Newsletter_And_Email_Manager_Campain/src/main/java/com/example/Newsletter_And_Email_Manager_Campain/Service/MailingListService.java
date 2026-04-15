package com.example.Newsletter_And_Email_Manager_Campain.Service;

import com.example.Newsletter_And_Email_Manager_Campain.Model.MailingList;
import com.example.Newsletter_And_Email_Manager_Campain.Model.Subscriber;

import java.util.List;

public interface MailingListService {
    MailingList createList(String name, String userId);
    MailingList addSubscriber(String listId, Subscriber subscriber);
    MailingList removeSubscriber(String listId, String email);
    List<MailingList> getUserLists(String userId);
}