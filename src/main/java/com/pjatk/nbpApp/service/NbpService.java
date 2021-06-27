package com.pjatk.nbpApp.service;

import com.pjatk.nbpApp.model.NbpResponse;
import com.pjatk.nbpApp.model.Rate;
import com.pjatk.nbpApp.model.Root;
import com.pjatk.nbpApp.repository.NbiApiResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NbpService {
    private final RestTemplate restTemplate;
    private final NbiApiResponseRepository nbiApiResponseRepository;

    public NbpService( RestTemplate restTemplate,NbiApiResponseRepository nbiApiResponseRepository){
        this.restTemplate = restTemplate;
        this.nbiApiResponseRepository = nbiApiResponseRepository;
    }
    public NbpResponse calculateRootForCurrency(String currency, int numberOfDays){
        String url = "http://api.nbp.pl/api/exchangerates/rates/a" + currency + "/last/" + numberOfDays;
        Root root = restTemplate.getForObject(url, Root.class);
        double average = calculate(root.getRates());
        NbpResponse nbpResponse = getNbpResponse(currency, numberOfDays, average);
        return nbiApiResponseRepository.save(nbpResponse);
    }

    private NbpResponse getNbpResponse(String currency, int numberOfDays, double calculate){
        NbpResponse nbpResponse = new NbpResponse();
        nbpResponse.setCurrency(currency);
        nbpResponse.setDays(numberOfDays);
        nbpResponse.setAverage(calculate);
        nbpResponse.setCreatedAt(LocalDateTime.now());
        return nbpResponse;
    }

    public double calculate(List<Rate> rateList){
        return rateList.stream()
                .mapToDouble(Rate::getMid)
                .average()
                .orElse(0.0d);
    }

}
