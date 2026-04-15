package com.example.Newsletter_And_Email_Manager_Campain.Service;

import com.example.Newsletter_And_Email_Manager_Campain.DTO.CampaignPageResponse;
import com.example.Newsletter_And_Email_Manager_Campain.Model.Campaign;
import com.example.Newsletter_And_Email_Manager_Campain.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface CampaignService {

    Campaign createCampaign(Campaign campaign, String username);

    Campaign scheduleCampaign(String campaignId, LocalDateTime time);

    Campaign sendCampaign(String campaignId);

    Campaign getCampaignById(String campaignId);

    CampaignPageResponse getCampaigns(String status, int page, int size);
}