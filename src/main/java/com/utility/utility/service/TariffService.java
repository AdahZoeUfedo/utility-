package com.utility.utility.service;

import com.utility.utility.model.Tariff;
import com.utility.utility.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    public Tariff save(Tariff tariff) {
        return tariffRepository.save(tariff);
    }
}