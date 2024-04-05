package org.example.triesearchdemo.repo;

import org.example.triesearchdemo.model.TrieWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface TrieRepository extends MongoRepository<TrieWord, String>{
}
