package org.example.triesearchdemo.controller;

import org.example.triesearchdemo.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trie")
public class TrieController {

    @Autowired
    private TrieService trieService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertWord(@RequestBody String word) {
        trieService.insert(word);
        return ResponseEntity.ok("Word '" + word + "' inserted into the Trie and MongoDB.");
    }

    @GetMapping("/search/{prefix}")
    public ResponseEntity<List<String>> searchWords(@PathVariable String prefix) {
        List<String> words = trieService.search(prefix);
        return ResponseEntity.ok(words);
    }

}
