package com.springapps.pricegenerator.service;

import com.springapps.pricegenerator.model.*;
import com.springapps.pricegenerator.repository.DiscountRepository;
import com.springapps.pricegenerator.repository.ProductRepository;
import com.springapps.pricegenerator.repository.QutationRepository;
import com.springapps.pricegenerator.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuotationService {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    private UserService userService;

    private DiscountRepository discountRepository;

    private QutationRepository quotationRepository;

    @Transactional
    public Quotation generateQuotation (Long productId, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Product product = productRepository.findById(userId).orElseThrow(() -> new RuntimeException("product not found"));

        Quotation quotation = new Quotation();
        if (userService.getUserAge(user) >= product.getAgeDiscountThreshold()){
            quotation.setAgeDiscount(product.getBasePrice()*0.2);
        }

        Country country = user.getCountry();
        CountryDiscount countryDiscount = discountRepository.findByCountry_IdAndProduct_Id(country.getId(), product.getId());
        quotation.setCountryDiscount(product.getBasePrice() * countryDiscount.getDiscountValue() / 100);

        quotation.setExpireDate(LocalDateTime.now().plusMinutes(5));
        quotation.setProduct(product);
        quotation.setUser(user);
        quotation.setPrice(product.getBasePrice() - quotation.getAgeDiscount() - quotation.getCountryDiscount());
        return quotationRepository.save(quotation);
    }

    public List<Quotation> getActiveQuotations (Long userId){
        return quotationRepository.findAllByUserAndExpireDateAfter(LocalDateTime.now());

    }
}
