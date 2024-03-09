package com.personal.project.common.web.support;

import com.personal.project.common.mapper.EntityMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractDomainRepository<D, E, I> implements DomainRepository<D, I> {

    protected final JpaRepository<E, I> jpaRepository;
    protected final EntityMapper<D, E> entityMapper;

    protected AbstractDomainRepository(JpaRepository<E, I> jpaRepository, EntityMapper<D, E> entityMapper) {
        this.jpaRepository = jpaRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Optional<D> findById(I id) {
        return this.jpaRepository.findById(id).map(this.entityMapper::toDomain);
    }

    @Override
    public List<D> findAllByIds(List<I> ids) {
        return this.jpaRepository.findAllById(ids).stream().map(this.entityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public D save(D domain) {
        if (domain != null) {
            E entity = this.entityMapper.toEntity(domain);
            this.jpaRepository.save(entity);
        }
        return domain;
    }

    @Override
    @Transactional
    public List<D> saveAll(List<D> domains) {
        if (domains != null && !domains.isEmpty()) {
            List<E> entities = this.entityMapper.toEntity(domains);
            this.jpaRepository.saveAll(entities);
        }
        return domains;
    }

    @Override
    public List<D> getAll() {
        List<D> datas = new ArrayList<>();
        List<E> entities = jpaRepository.findAll();
        if (!entities.isEmpty()) {
            datas = entityMapper.toDomain(entities);
        }
        return datas;
    }
}
