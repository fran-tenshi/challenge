package com.conductor.challenge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.conductor.challenge.entitys.Dados;

public interface DadosRepository extends MongoRepository<Dados, String>{

}
