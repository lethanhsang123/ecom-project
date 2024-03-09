package com.personal.project.productservice.application.service.impl;

import com.personal.project.common.dto.response.Response;
import com.personal.project.common.exception.ResponseException;
import com.personal.project.productservice.application.dto.request.ProductSkusCreateRequest;
import com.personal.project.productservice.application.mapper.AutoMapper;
import com.personal.project.productservice.application.mapper.AutoMapperQuery;
import com.personal.project.productservice.application.service.ProductSkusService;
import com.personal.project.productservice.domain.Product;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.productservice.domain.command.ProductSkusCreateCmd;
import com.personal.project.productservice.domain.repository.ProductSkusDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import com.personal.project.productservice.infrastructure.persistence.mapper.ProductEntityMapper;
import com.personal.project.productservice.infrastructure.persistence.mapper.ProductSkusEntityMapper;
import com.personal.project.productservice.infrastructure.persistence.repository.ProductEntityRepository;
import com.personal.project.productservice.infrastructure.persistence.repository.ProductSkusEntityRepository;
import com.personal.project.productservice.infrastructure.support.exception.BadRequestError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductSkusServiceImpl implements ProductSkusService {

    private final ProductSkusDomainRepository productSkusDomainRepository;

    private final ProductSkusEntityRepository productSkusEntityRepository;

    private final ProductEntityRepository productEntityRepository;

    private final ProductSkusEntityMapper productSkusEntityMapper;
    private final ProductEntityMapper productEntityMapper;

    private final AutoMapper autoMapper;

    private final AutoMapperQuery autoMapperQuery;

    public ProductSkusServiceImpl(ProductSkusDomainRepository productSkusDomainRepository,
                                  ProductSkusEntityRepository productSkusEntityRepository,
                                  ProductEntityRepository productEntityRepository,
                                  ProductSkusEntityMapper productSkusEntityMapper,
                                  ProductEntityMapper productEntityMapper, AutoMapper autoMapper,
                                  AutoMapperQuery autoMapperQuery) {
        this.productSkusDomainRepository = productSkusDomainRepository;
        this.productSkusEntityRepository = productSkusEntityRepository;
        this.productEntityRepository = productEntityRepository;
        this.productSkusEntityMapper = productSkusEntityMapper;
        this.productEntityMapper = productEntityMapper;
        this.autoMapper = autoMapper;
        this.autoMapperQuery = autoMapperQuery;
    }

    @Override
    @Transactional
    public Response<ProductSkus> create(ProductSkusCreateRequest request) {
        ProductSkusCreateCmd createCmd = this.autoMapper.from(request);
        this.ensureProduct(createCmd.getProductId());
        ProductSkus productSkus = new ProductSkus(createCmd);
        this.productSkusEntityRepository.save(this.productSkusEntityMapper.toEntity(productSkus));
        return Response.of(productSkus);
    }

    @Override
    public Response<ProductSkus> getById(Long id) {
        ProductSkusEntity entity = this.ensureProductSkus(id);
        ProductSkus productSkus = this.productSkusEntityMapper.toDomain(entity);
        this.enrichProductSkus(productSkus);
        return Response.of(productSkus);
    }

    private void ensureProduct(Long productId) {
        this.productEntityRepository.findById(productId).orElseThrow(
                () -> new ResponseException(BadRequestError.PRODUCT_NOT_FOUND)
        );
    }

    private ProductSkusEntity ensureProductSkus(Long productSkusId) {
        return this.productSkusEntityRepository.findById(productSkusId).orElseThrow(
                () -> new ResponseException(BadRequestError.PRODUCT_SKUS_NOT_FOUND)
        );
    }

    private void enrichProductSkus(ProductSkus productSkus) {
        Product product = this.productEntityMapper.toDomain(this.productEntityRepository.findByProductSkusId(productSkus.getId()));
        productSkus.enrichProduct(product, null);
    }
}
