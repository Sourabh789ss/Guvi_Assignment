package com.example.Newsletter_And_Email_Manager_Campain.Impl;

import com.example.Newsletter_And_Email_Manager_Campain.DTO.CampaignPageResponse;
import com.example.Newsletter_And_Email_Manager_Campain.Model.Campaign;
import com.example.Newsletter_And_Email_Manager_Campain.Model.CampaignStatus;
import com.example.Newsletter_And_Email_Manager_Campain.Model.MailingList;
import com.example.Newsletter_And_Email_Manager_Campain.Model.User;
import com.example.Newsletter_And_Email_Manager_Campain.Repository.CampaignRepository;
import com.example.Newsletter_And_Email_Manager_Campain.Repository.MailingListRepository;
import com.example.Newsletter_And_Email_Manager_Campain.Service.CampaignService;
import com.example.Newsletter_And_Email_Manager_Campain.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final MailingListRepository mailingListRepository;
    private final UserService userService;

    public CampaignServiceImpl(CampaignRepository campaignRepository,
                               MailingListRepository mailingListRepository, UserService userService) {
        this.campaignRepository = campaignRepository;
        this.mailingListRepository = mailingListRepository;
        this.userService = userService;
    }

    // 1. Create Campaign
    @Override
    public Campaign createCampaign(Campaign campaign, String username) {

        if (campaign == null) {
            throw new RuntimeException("Campaign cannot be null");
        }

        if (campaign.getName() == null || campaign.getName().isBlank()) {
            throw new RuntimeException("Campaign name required");
        }

        if (campaign.getMailingListId() == null || campaign.getMailingListId().isBlank()) {
            throw new RuntimeException("Mailing list required");
        }

        User user = userService.getUserByUsername(username);
        campaign.setUserId(user.getId());

        campaign.setStatus(CampaignStatus.DRAFT);

        return campaignRepository.save(campaign);
    }

    // 2. Schedule Campaign
    @Override
    public Campaign scheduleCampaign(String campaignId, LocalDateTime time) {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        if (time.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid schedule time");
        }

        campaign.setScheduledAt(time);
        campaign.setStatus(CampaignStatus.SCHEDULED);

        return campaignRepository.save(campaign);
    }

    // 3. Send Campaign
    @Override
    public Campaign sendCampaign(String campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        MailingList list = mailingListRepository.findById(campaign.getMailingListId())
                .orElseThrow(() -> new RuntimeException("Mailing list not found"));

        if (list.getSubscribers() != null) {
            list.getSubscribers().forEach(sub ->
                    System.out.println("Sending email to: " + sub.getEmail())
            );
        }

        campaign.setStatus(CampaignStatus.SENT);

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign getCampaignById(String campaignId) {
        return campaignRepository.findById(campaignId).orElse(null);
    }

    @Override
    public CampaignPageResponse getCampaigns(String status, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("scheduledAt").descending()
                        .and(Sort.by("id").descending())
        );

        Page<Campaign> campaignPage;

        if (status != null && !status.isBlank()) {

            CampaignStatus campaignStatus;

            try {
                campaignStatus = CampaignStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid status value");
            }

            campaignPage = campaignRepository
                    .findByStatus(campaignStatus, pageable);

        } else {
            campaignPage = campaignRepository
                    .findAll(pageable);
        }

        return new CampaignPageResponse(
                campaignPage.getContent(),
                campaignPage.getNumber(),
                campaignPage.getTotalElements(),
                campaignPage.getTotalPages(),
                campaignPage.getSize()
        );
    }
}