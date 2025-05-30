package com.example.texttwist;// File name: Trie.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie {

    private TrieNode root;

    /**
     * TrieNode
     * Default Constructor -- creates a Trie object with a root TrieNode that holds a space
     * character rather than a letter, indicating that it is the root of the Trie, and the flag of
     * the TrieNode being false.
     */
    public Trie() {
        root = new TrieNode(' ', false);
    }

    /**
     * insert
     * Inserts a word into the Trie object,
     * @param word -- The word to be inserted
     * pre: a TrieNode exists
     * post: inserts the word into the TrieNode, creating a branch off the "Trie"
     */
    public void insert(String word) {
        root.insert(word);
    }

    /**
     * loadFromFile
     * Inserts all words from a given file into the Trie object.
     * @param fileName -- Name of the file that contains the words to be inserted into the Trie.
     * pre: a TrieNode exists
     * post: inserts all words from the file into the Trie object, creating the respective branches
     * with TrieNodes holding characters. If a file with the specified file name does not exist, a
     * FileNotFoundException exception is thrown.
     */
    public void loadFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner fileScnr = new Scanner(file);
        while (fileScnr.hasNext()) {
            String word = fileScnr.next();
            root.insert(word);
        }

        fileScnr.close();
    }

    /**
     * isWord
     * Checks if a word exists in the Trie
     * @param word -- The word to be checked to see if it is in the tree
     * pre: a Trie exists
     * post: returns true if the word exists, false if it doesn't.
     */
    public boolean isWord(String word) {
        return root.isWord(word);
    }

    /**
     * isPrefix
     * Checks if a prefix exists in the Trie
     * @param str -- The prefix to be checked to see if it is in the tree
     * pre: a Trie exists
     * post: Returns true if the prefix exists, false if it doesn't.
     */
    public boolean isPrefix(String str) {
        return root.isPrefix(str);
    }

    /**
     * toString
     * Prints all words contained in the Trie object.
     * pre: a Trie exists
     * post: prints all words branching off the Trie, with each word being printed on a newline.
     */
    public String toString() {
        return root.toString("");
    }

    /**
     * wordCount
     * Counts how many words exist in the Trie object / counts the amount of true flags exist in
     * the TrieNode branches of the tree.
     * pre: a Trie exists
     * post: returns the number of words existing in the Trie.
     */
    public int wordCount() {
        return root.wordCount();
    }

    /**
     * clone
     * Clones the Trie object, keeping all branches/subtrees the same, with the clone containing
     * all words/information that the original contained.
     * pre: a Trie exists
     * post: returns a clone of the Trie
     */
    public Trie clone() {
        Trie clone = new Trie();

        clone.root = root.clone();

        return clone;
    }

    /**
     * equals
     * Checks if the object given in the method parameter is equal to the Trie the method was
     * called on, checking if it has the same flags/letters, and branches.
     * pre: a Trie exists
     * post: returns true if the object equals the Trie, false if it doesn't
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Trie)) {
            return false;
        }
        Trie other = (Trie) obj;

        return root.equals(other.root);
    }
}
