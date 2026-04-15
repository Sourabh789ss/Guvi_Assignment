package com.example.Newsletter_And_Email_Manager_Campain.Repository;

import com.example.Newsletter_And_Email_Manager_Campain.Model.Campaign;
import com.example.Newsletter_And_Email_Manager_Campain.Model.CampaignStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {

    Page<Campaign> findAll(Pageable pageable);

    Page<Campaign> findByStatus(CampaignStatus status, Pageable pageable);
}
