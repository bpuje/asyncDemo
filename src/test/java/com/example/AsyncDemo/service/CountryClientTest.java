package com.example.AsyncDemo.service;

import com.example.AsyncDemo.entity.Country;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class CountryClientTest {

    private CountryClient countryClient;

    @Before
    public void setUp() {
        CountryClient countryClient = Mockito.spy(new CountryClient());
    }

    @Test
    public void getCountriesByLanguage() throws ExecutionException, InterruptedException {
        List<Country> countriesByLanguage = countryClient.getCountriesByLanguage("fr").get();
        assertNotNull(countriesByLanguage);
        assertEquals("Belgium", countriesByLanguage.get(0).getName());
    }

    @Test
    public void getCountryByRegion() throws ExecutionException, InterruptedException {
        List<Country> countriesByRegion = countryClient.getCountriesByRegion("europe").get();
        assertNotNull(countriesByRegion);
        assertEquals("Åland Islands", countriesByRegion.get(0).getName());
        assertEquals("Albania", countriesByRegion.get(1).getName());
    }
}