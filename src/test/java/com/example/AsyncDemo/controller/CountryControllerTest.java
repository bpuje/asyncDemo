package com.example.AsyncDemo.controller;

import com.example.AsyncDemo.entity.Country;
import com.example.AsyncDemo.service.CountryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CountryControllerTest {

    @InjectMocks
    private CountryController countryController;

    private CountryClient countryClient;

    @BeforeEach
    void setUp() {
        this.countryClient = mock(CountryClient.class);
        this.countryController = new CountryController(countryClient);
    }

    @Test
    void getAllEuropeanFrenchSpeakingCountries() throws Throwable {

        // given
        Country country = new Country();
        country.setName("France");
        Country country2 = new Country();
        country2.setName("Belgium");
        Country country3 = new Country();
        country3.setName("Germany");

        List<Country> countriesByLanguage = new ArrayList<>();
        countriesByLanguage.add(country);
        countriesByLanguage.add(country2);
        when(countryClient.getCountriesByLanguage(anyString())).thenReturn(CompletableFuture.completedFuture(countriesByLanguage));
        List<Country> countriesByRegion = new ArrayList<>();
        countriesByRegion.add(country);
        countriesByRegion.add(country3);
        when(countryClient.getCountriesByRegion(anyString())).thenReturn(CompletableFuture.completedFuture(countriesByRegion));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("France");

        // when
        List<String> result = countryController.getAllEuropeanFrenchSpeakingCountries();

        // then
        assertEquals(expectedResult, result);
    }
}