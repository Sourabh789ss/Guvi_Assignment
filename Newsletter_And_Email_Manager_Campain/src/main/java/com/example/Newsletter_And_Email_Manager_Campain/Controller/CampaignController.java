package com.example.Newsletter_And_Email_Manager_Campain.Controller;

import com.example.Newsletter_And_Email_Manager_Campain.Model.Campaign;
import com.example.Newsletter_And_Email_Manager_Campain.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    // Create Campaign (Auth)
    @PostMapping
    public ResponseEntity<Campaign> createCampaign(
            @RequestBody Campaign campaign,
            Authentication authentication) {

        String username = authentication.getName();

        return new ResponseEntity<>(
                campaignService.createCampaign(campaign, username),
                HttpStatus.CREATED
        );
    }

    // Schedule Campaign
    @PostMapping("/{campaignId}/schedule")
    public ResponseEntity<Campaign> scheduleCampaign(
            @PathVariable String campaignId,
            @RequestParam String time) {

        LocalDateTime dateTime = LocalDateTime.parse(time);

        return ResponseEntity.ok(
                campaignService.scheduleCampaign(campaignId, dateTime)
        );
    }

    // Send Campaign
    @PostMapping("/{campaignId}/send")
    public ResponseEntity<Campaign> sendCampaign(
            @PathVariable String campaignId) {

        return ResponseEntity.ok(
                campaignService.sendCampaign(campaignId)
        );
    }

    // Get all campaigns (optional)
    @GetMapping
    public ResponseEntity<?> getAllCampaigns(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return ResponseEntity.ok(
                campaignService.getCampaigns(status, page, size)
        );
    }

    // Get campaign by id
    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getCampaign(@PathVariable String campaignId) {
        return ResponseEntity.ok(
                campaignService.getCampaignById(campaignId)
        );
    }
}