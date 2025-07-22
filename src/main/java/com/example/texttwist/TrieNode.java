package com.example.texttwist;// File name: TrieNode.java

public class TrieNode {

    private char letter;
    private boolean flag;
    private TrieNode[] nextLetter;

    /**
     * TrieNode
     * Default Constructor -- creates a TrieNode object that hold a letter, a flag marking whether
     * the node is the last letter of a word, and an array holding references to next letters. Each
     * node is one node in a tree.
     * @param letter -- The letter the node is initialized with.
     * @param flag -- Boolean value that tells whether the node marks the end of the word.
     */
    public TrieNode(char letter, boolean flag) {
        this.letter = letter;
        this.flag = flag;
        nextLetter = new TrieNode[26];
    }

    /**
     * insert
     * Inserts a word into a TrieNode, starting in the next letter references of the TrieNode the
     * method was called on.
     * @param str -- The word to be inserted
     * pre: a TrieNode exists
     * post: inserts the word into the TrieNode, creating a branch off the "this" TrieNode. The
     * last letter of the word will have their flag marked as true, signifying the end of a word
     */
    public void insert(String str) {
        if (str.isEmpty()) {
            return;
        }
        int charRef = str.charAt(0) - 'a';

        if (nextLetter[charRef] == null) {
            if (str.length() != 1) {
                nextLetter[charRef] = new TrieNode(str.charAt(0), false);
                nextLetter[charRef].insert(str.substring(1));
            } else {
                nextLetter[charRef] = new TrieNode(str.charAt(0), true);

            }
        } else {
            if (str.length() != 1) {
                nextLetter[charRef].insert(str.substring(1));
            } else {
                nextLetter[charRef].flag = true;
            }
        }
    }

    /**
     * isWord
     * Checks if a word exists, starting at the next references of the node
     * of the node which the method was called on.
     * @param str -- The word to be checked to see if it is in the tree
     * pre: a TrieNode exists
     * post: returns true if the word exists, false if it doesn't.
     */
    public boolean isWord(String str) {
        if (str.isEmpty()) {
            return false;
        }

        int charRef = str.charAt(0) - 'a';

        if (str.length() == 1) {
            if (nextLetter[charRef] == null) {
                return false;
            } else {
                return nextLetter[charRef].flag;
            }
        } else {
            if (nextLetter[charRef] == null) {
                return false;
            } else {
                return nextLetter[charRef].isWord(str.substring(1));
            }
        }
    }

    /**
     * isPrefix
     * Checks if a prefix exists, starting at the next references of the node
     * of the node which the method was called on.
     * @param str -- The prefix to be checked to see if it is in the tree
     * pre: a TrieNode exists
     * post: Returns true if the prefix exists, false if it doesn't.
     */
    public boolean isPrefix(String str) {
        if (str.isEmpty()) {
            return true;
        }

        int charRef = str.charAt(0) - 'a';

        if (str.length() != 1) {
            if (nextLetter[charRef] == null) {
                return false;
            } else {
                return nextLetter[charRef].isPrefix(str.substring(1));
            }
        } else {
            if (nextLetter[charRef] == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * toString
     * Prints all words branching off the TrieNode that the method is called on.
     * @param pre -- stores the letters of all previous
     * TrieNode objects up to the node currently being processed.
     * pre: a TrieNode exists
     * post: prints all words branching off the TrieNode the method is called on, with each word
     * being printed on a newline.
     */
    public String toString(String pre) {
        String result = "";

        if (flag) {
            result += pre + "\n";
        }

        for (int i = 0; i < nextLetter.length; i++) {
            if (nextLetter[i] != null) {
                char charRef = nextLetter[i].letter;
                result += nextLetter[i].toString(pre + charRef);
            }
        }
        return result;
    }

    /**
     * wordCount
     * Counts how many words branch off the TrieNode the method is called on / how many true flags
     * there are branching off the node the method is called on.
     * pre: a TrieNode exists
     * post: returns the number of words existing off the branch of the TrieNode the method is
     * called on.
     */
    public int wordCount() {
        int count = 0;

        for (int i = 0; i < nextLetter.length; i++) {
            if (nextLetter[i] != null) {
                if (nextLetter[i].flag) {
                    count += 1 + nextLetter[i].wordCount();
                } else {
                    count += nextLetter[i].wordCount();
                }
            }
        }
        return count;
    }

    /**
     * clone
     * Clones the TrieNode the method is called on alongside all other nodes that branch off the
     * node the method is called on, keeping all letters/flags the same
     * pre: a TrieNode exists
     * post: returns a clone of the TrieNode, keeping its flag, letter, and nodes that branch
     * off it.
     */
    public TrieNode clone() {
        TrieNode clone = new TrieNode(letter, flag);

        for (int i = 0; i < nextLetter.length; i++) {
            if (nextLetter[i] != null) {
                clone.nextLetter[i] = nextLetter[i].clone();
            }
        }
        return clone;
    }

    /**
     * equals
     * Checks if the object given in the method parameter is equal to the TrieNode the method was
     * called on, checking if it has the same flags/letters, and branches.
     * pre: a TrieNode exists
     * post: returns true if the object equals the TrieNode, false if it doesn't
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof TrieNode)) {
            return false;
        }
        TrieNode other = (TrieNode) obj;
        if (!(this.letter == other.letter && this.flag == other.flag)) {
            return false;
        }
        for (int i = 0; i < nextLetter.length; i++) {
            if ((this.nextLetter[i] == null && other.nextLetter[i] != null) ||
                    (this.nextLetter[i] != null && other.nextLetter[i] == null)) {
                return false;
            }
            if (this.nextLetter[i] != null && other.nextLetter[i] != null) {
                if (!this.nextLetter[i].equals(other.nextLetter[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
