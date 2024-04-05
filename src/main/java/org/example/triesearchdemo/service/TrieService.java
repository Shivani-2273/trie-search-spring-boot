package org.example.triesearchdemo.service;

import org.example.triesearchdemo.TrieNode;
import org.example.triesearchdemo.model.TrieWord;
import org.example.triesearchdemo.repo.TrieRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class TrieService {

    @Autowired
    private TrieRepository trieRepository;


    public TrieService(TrieRepository trieRepository) {
        this.trieRepository = trieRepository;
    }

    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;

        // Save the word in MongoDB
        trieRepository.save(new TrieWord(word));
    }

    public List<String> search(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return Collections.emptyList();
            }
            node = node.children.get(ch);
        }
        return getAllWordsWithPrefix(node, prefix);
    }

    private List<String> getAllWordsWithPrefix(TrieNode node, String currentPrefix) {
        List<String> words = new ArrayList<>();
        if (node.isEndOfWord) {
            words.add(currentPrefix);
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            words.addAll(getAllWordsWithPrefix(entry.getValue(), currentPrefix + entry.getKey()));
        }

        return words;
    }
}
