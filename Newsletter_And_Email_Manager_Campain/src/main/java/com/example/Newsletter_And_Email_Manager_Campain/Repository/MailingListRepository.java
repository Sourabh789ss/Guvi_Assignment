package com.example.Newsletter_And_Email_Manager_Campain.Repository;

import com.example.Newsletter_And_Email_Manager_Campain.Model.MailingList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MailingListRepository extends MongoRepository<MailingList, String> {
    List<MailingList> findByUserId(String userId);
}
