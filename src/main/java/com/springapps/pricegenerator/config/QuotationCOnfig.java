package com.springapps.pricegenerator.config;

import com.springapps.pricegenerator.repository.QutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling


public class QuotationCOnfig {

    // Injectați repository-ul corespunzător
    private  QutationRepository quotationRepository;

    @Autowired
    public QuotationCOnfig(QutationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    // Programați metoda pentru a rula la intervale regulate (de exemplu, la fiecare oră)
    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredEntities() {
        quotationRepository.deleteAllByExpireDateBefore(LocalDateTime.now());
    }
}

