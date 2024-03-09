package com.personal.project.productservice.application.service.impl;

import com.personal.project.common.exception.ResponseException;
import com.personal.project.productservice.application.dto.request.ProductCreateRequest;
import com.personal.project.productservice.application.dto.request.ProductSearchRequest;
import com.personal.project.productservice.application.dto.request.ProductUpdateRequest;
import com.personal.project.common.dto.PageDTO;
import com.personal.project.common.dto.response.PagingResponse;
import com.personal.project.common.dto.response.Response;
import com.personal.project.productservice.application.mapper.AutoMapper;
import com.personal.project.productservice.application.mapper.AutoMapperQuery;
import com.personal.project.productservice.application.service.ProductService;
import com.personal.project.productservice.domain.Product;
import com.personal.project.productservice.domain.ProductSkus;
import com.personal.project.productservice.domain.command.ProductCreateCmd;
import com.personal.project.productservice.domain.command.ProductUpdateCmd;
import com.personal.project.productservice.domain.query.ProductSearchQuery;
import com.personal.project.productservice.domain.repository.ProductDomainRepository;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductEntity;
import com.personal.project.productservice.infrastructure.persistence.entity.ProductSkusEntity;
import com.personal.project.productservice.infrastructure.persistence.mapper.ProductEntityMapper;
import com.personal.project.productservice.infrastructure.persistence.mapper.ProductSkusEntityMapper;
import com.personal.project.productservice.infrastructure.persistence.repository.ProductEntityRepository;
import com.personal.project.productservice.infrastructure.persistence.repository.ProductSkusEntityRepository;
import com.personal.project.productservice.infrastructure.support.exception.BadRequestError;
import com.personal.project.common.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDomainRepository productDomainRepository;

    private final AutoMapper autoMapper;
    private final AutoMapperQuery autoMapperQuery;
    private final ProductEntityMapper productEntityMapper;
    private final ProductSkusEntityMapper productSkusEntityMapper;
    private final ProductEntityRepository productEntityRepository;

    private final ProductSkusEntityRepository productSkusEntityRepository;

    public ProductServiceImpl(ProductDomainRepository productDomainRepository, AutoMapper autoMapper,
                              AutoMapperQuery autoMapperQuery, ProductEntityMapper productEntityMapper,
                              ProductSkusEntityMapper productSkusEntityMapper, ProductEntityRepository productEntityRepository,
                              ProductSkusEntityRepository productSkusEntityRepository) {
        this.productDomainRepository = productDomainRepository;
        this.autoMapper = autoMapper;
        this.autoMapperQuery = autoMapperQuery;
        this.productEntityMapper = productEntityMapper;
        this.productSkusEntityMapper = productSkusEntityMapper;
        this.productEntityRepository = productEntityRepository;
        this.productSkusEntityRepository = productSkusEntityRepository;
    }

    @Override
    @Transactional
    public Response<Product> create(ProductCreateRequest request) {
        Product product = null;
        if (request != null) {
            // map to ProductCmd
            ProductCreateCmd productCreateCmd = autoMapper.from(request);
            // get Product domain
            product = new Product(productCreateCmd);
            productDomainRepository.save(product);
        }
        return Response.of(product);
    }

    @Override
    public PagingResponse<Product> search(ProductSearchRequest request) {
        List<Product> products = new ArrayList<>();
        if (request != null) {
            ProductSearchQuery query = autoMapperQuery.toQuery(request);
            products = productEntityMapper.toDomain(
                    productEntityRepository.search(query)
            );
        }
        PageDTO<Product> data = new PageDTO<>(products, request.getPageIndex(), request.getPageSize(), products.size());
        return PagingResponse.of(data);
    }

    @Override
    @Transactional
    public Response<Product> update(Long id, ProductUpdateRequest request) {
        Product product = null;
        if (DataUtils.isBlank(request) || DataUtils.isBlank(id)) {
            product = ensureExist(id);  // get Product
            ProductUpdateCmd updateCmd = this.autoMapper.from(request); // map product update cmd
            product.update(updateCmd);
        }
        return Response.of(product);
    }

    @Override
    public Response<Product> active(Long id) {
        Product product = ensureExist(id);
        product.active();
        this.productEntityRepository.save(this.productEntityMapper.toEntity(product));
        return Response.of(product);
    }

    @Override
    public Response<Product> inActive(Long id) {
        Product product = ensureExist(id);
        product.inActive();
        this.productEntityRepository.save(this.productEntityMapper.toEntity(product));
        return Response.of(product);
    }

    @Override
//    @Cacheable(value = "product", key = "#id", condition = "#id != null ", unless = "#result == null ")
    public Response<Product> getById(Long id) {
        log.info("Calling WebService");
        Product product = this.ensureExist(id);
        this.enrichProduct(product);
        return Response.of(product);
    }

    private Product ensureExist(Long id) {
        ProductEntity entity = this.productEntityRepository.findById(id).orElseThrow(
                () -> new ResponseException(BadRequestError.PRODUCT_NOT_FOUND));
        return this.productEntityMapper.toDomain(entity);
    }

    private void enrichProduct(Product product) {
        // enrich product skus
        List<ProductSkus> productSkuses = new ArrayList<>();
        List<ProductSkusEntity> productSkusEntities = this.productSkusEntityRepository.getByProductId(product.getId());
        if (!productSkusEntities.isEmpty()) {
            productSkuses.addAll(this.productSkusEntityMapper.toDomain(productSkusEntities));
        }
        if (!productSkuses.isEmpty()) product.assignProductSkus(productSkuses);
    }
}
