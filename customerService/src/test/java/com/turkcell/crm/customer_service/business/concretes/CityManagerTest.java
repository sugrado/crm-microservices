package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetAllCitiesResponse;
import com.turkcell.crm.customer_service.business.dtos.responses.cities.GetByIdCityResponse;
import com.turkcell.crm.customer_service.business.mappers.CityMapper;
import com.turkcell.crm.customer_service.business.mappers.CityMapperImpl;
import com.turkcell.crm.customer_service.business.rules.CityBusinessRules;
import com.turkcell.crm.customer_service.core.business.abstracts.MessageService;
import com.turkcell.crm.customer_service.data_access.abstracts.CityRepository;
import com.turkcell.crm.customer_service.entities.concretes.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CityManagerTest {
    private CityRepository cityRepository;
    private CityMapper cityMapper;
    private CityManager cityManager;
    private MessageService messageService;
    private CityBusinessRules cityBusinessRules;

    @BeforeEach
    void setUp() {
        messageService = mock(MessageService.class);
        cityRepository = mock(CityRepository.class);
        cityMapper = new CityMapperImpl();
        cityBusinessRules = new CityBusinessRules(messageService);
        cityManager = new CityManager(cityRepository, cityBusinessRules, cityMapper);
    }

    @Test
    void getAll_shouldReturnAllCities() {
        // Arrange
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1);
        city1.setName("City1");

        City city2 = new City();
        city2.setId(2);
        city2.setName("City2");

        cities.add(city1);
        cities.add(city2);

        when(cityRepository.findAll()).thenReturn(cities);

        List<GetAllCitiesResponse> expectedResponses = new ArrayList<>();
        GetAllCitiesResponse response1 = new GetAllCitiesResponse(1, "City1");
        GetAllCitiesResponse response2 = new GetAllCitiesResponse(2, "City2");

        expectedResponses.add(response1);
        expectedResponses.add(response2);

        // Act
        List<GetAllCitiesResponse> actualResponses = cityManager.getAll();

        // Assert
        assertEquals(expectedResponses.size(), actualResponses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            assertEquals(expectedResponses.get(i).id(), actualResponses.get(i).id());
            assertEquals(expectedResponses.get(i).name(), actualResponses.get(i).name());
        }
    }

    @Test
    void getAllById_shouldReturnCitiesById() {
        // Arrange
        List<Integer> ids = List.of(1, 2);
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1);
        city1.setName("City1");

        City city2 = new City();
        city2.setId(2);
        city2.setName("City2");

        cities.add(city1);
        cities.add(city2);

        when(cityRepository.findAllByIdIsIn(ids)).thenReturn(cities);

        // Act
        List<City> actualCities = cityManager.getAllById(ids);

        // Assert
        assertEquals(cities.size(), actualCities.size());
        for (int i = 0; i < cities.size(); i++) {
            assertEquals(cities.get(i).getId(), actualCities.get(i).getId());
            assertEquals(cities.get(i).getName(), actualCities.get(i).getName());
        }
    }

    @Test
    void getById_shouldReturnCityById() {
        // Arrange
        int cityId = 1;
        City city = new City();
        city.setId(cityId);
        city.setName("City1");

        GetByIdCityResponse expectedResponse = new GetByIdCityResponse(cityId, "City1");

        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        // Act
        GetByIdCityResponse actualResponse = cityManager.getById(cityId);

        // Assert
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.name(), actualResponse.name());
    }

    @Test
    void getById_shouldThrowExceptionWhenCityNotFound() {
        // Arrange
        int cityId = 1;
        Optional<City> cityOptional = Optional.empty();

        when(cityRepository.findById(cityId)).thenReturn(cityOptional);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> cityManager.getById(cityId));
    }
}
