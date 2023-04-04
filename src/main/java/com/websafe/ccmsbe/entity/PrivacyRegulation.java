package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PrivacyRegulation")
@Table(name = "privacy_regulation")
public class PrivacyRegulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regulationId;
    private String regulationName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "privacy_regulation_geolocation_types")
    private List<String> geolocationTypes = new ArrayList<>();
}
