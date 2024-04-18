package com.turkcell.crm.customer_service.out_services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MernisClient {
    private final RestTemplate restTemplate;

    public boolean checkNationality(String tcKimlikNo, String ad, String soyad, int dogumYili) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/xml; charset=utf-8");
        String requestBody = createRequestBody(tcKimlikNo, ad, soyad, dogumYili);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx", request, String.class);
        return response.getBody().contains("<TCKimlikNoDogrulaResult>true</TCKimlikNoDogrulaResult>");
    }

    public String createRequestBody(String tcKimlikNo, String ad, String soyad, int dogumYili) {
        return String.format(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                        "<soap:Body>" +
                        "<TCKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">" +
                        "<TCKimlikNo>%s</TCKimlikNo>" +
                        "<Ad>%s</Ad>" +
                        "<Soyad>%s</Soyad>" +
                        "<DogumYili>%s</DogumYili>" +
                        "</TCKimlikNoDogrula>" +
                        "</soap:Body>" +
                        "</soap:Envelope>",
                tcKimlikNo, ad, soyad, dogumYili);
    }
}
