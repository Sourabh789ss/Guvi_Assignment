package com.example.Newsletter_And_Email_Manager_Campain.DTO;

import com.example.Newsletter_And_Email_Manager_Campain.Model.Campaign;

import java.util.List;

public record CampaignPageResponse(
        List<Campaign> campaigns,
        int currentPage,
        long totalItems,
        int totalPages,
        int pageSize
) {}