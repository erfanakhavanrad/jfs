package com.jahanfoolad.jfs.service.impl;

import com.jahanfoolad.jfs.JfsApplication;
import com.jahanfoolad.jfs.domain.*;
import com.jahanfoolad.jfs.domain.dto.ProductAttributeDto;
import com.jahanfoolad.jfs.domain.dto.ProductDto;
import com.jahanfoolad.jfs.jpaRepository.PriceRepository;
import com.jahanfoolad.jfs.jpaRepository.ProductAttributeRepository;
import com.jahanfoolad.jfs.security.SecurityService;
import com.jahanfoolad.jfs.service.ProductAttributeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    ProductAttributeRepository productAttributeRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    ResponseModel responseModel;

    @Autowired
    SecurityService securityService;

    @Resource(name = "faMessageSource")
    private MessageSource faMessageSource;


    @Override
    public Page<ProductAttribute> getProductAttributes(Integer pageNo, Integer perPage) throws Exception {
        return productAttributeRepository.findAll(JfsApplication.createPagination(pageNo, perPage));
    }

    @Override
    public ProductAttribute getProductAttributeById(Long id) throws Exception {
        return productAttributeRepository.findById(id).orElseThrow(() -> new Exception(faMessageSource.getMessage("PRODUCT_ATTRIBUTE_NOT_FOUND", null, Locale.ENGLISH)));
    }

    @Override
    public ProductAttribute createProductAttribute(ProductAttributeDto productAttributeDto, HttpServletRequest httpServletRequest) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        ProductAttribute productAttribute = modelMapper.map(productAttributeDto, ProductAttribute.class);
        productAttribute.setCreatedBy((((Person) securityService.getUserByToken(httpServletRequest).getContent()).getId()));
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public List<ProductAttribute> createProductAttributes(List<ProductAttribute> productAttributeList) throws Exception {
        for (ProductAttribute productAttribute : productAttributeList) {
            savePrice(productAttribute.getPrices());
        }
        return productAttributeRepository.saveAll(productAttributeList);
    }

    private List<Price> savePrice(List<Price> priceList) {
        return priceRepository.saveAll(priceList);
    }

    @Override
    public void deleteProductAttribute(Long id) {
        productAttributeRepository.deleteById(id);
    }
}
