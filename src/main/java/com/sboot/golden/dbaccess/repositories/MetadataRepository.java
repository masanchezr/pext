package com.sboot.golden.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.MetadataEntity;

public interface MetadataRepository extends CrudRepository<MetadataEntity, String> {

}
