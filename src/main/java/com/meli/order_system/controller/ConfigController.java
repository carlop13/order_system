package com.meli.order_system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to display environment-specific configuration.
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    // Injects the value of the 'spring.profiles.active' property
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    // Inject the value of our custom property
    @Value("${app.welcome.message}")
    private String welcomeMessage;

    /**
     * Endpoint to check which profile is currently active.
     * @return A map containing the active profile and a welcome message.
     */
    @GetMapping("/profile")
    public Map<String, String> getActiveProfile() {
        Map<String, String> profileInfo = new HashMap<>();
        profileInfo.put("activeProfile", activeProfile);
        profileInfo.put("welcomeMessage", welcomeMessage);
        return profileInfo;
    }
}