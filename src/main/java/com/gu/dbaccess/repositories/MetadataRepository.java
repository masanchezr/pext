package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.MetadataEntity;

public interface MetadataRepository extends CrudRepository<MetadataEntity, String> {

}
