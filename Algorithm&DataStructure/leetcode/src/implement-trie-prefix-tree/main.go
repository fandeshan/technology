package main

import "fmt"

func main() {
	trie := Constructor()
	trie.Insert("apple")
	fmt.Println(trie.Search("apple"))
	fmt.Println(trie.Search("app"))
	fmt.Println(trie.StartsWith("app"))
	trie.Insert("app")
	fmt.Println(trie.Search("app"))

}

type Trie struct {
	c byte
	children map[byte]*Trie
	isWord bool
}


/** Initialize your data structure here. */
func Constructor() Trie {
	return Trie{
		c:        0,
		children: make(map[byte]*Trie),
		isWord:   false,
	}
}


/** Inserts a word into the trie. */
func (this *Trie) Insert(word string)  {
	if len(word) < 1 {
		return
	}
	curr := this
	wordBytes := []byte(word)

	for i := 0; i < len(wordBytes) ; i++  {
		if next,ok := curr.children[wordBytes[i]];ok {
			curr = next
		} else {
			next := &Trie{
				c:        wordBytes[i],
				children: make(map[byte]*Trie),
				isWord:   false,
			}
			curr.children[wordBytes[i]] = next
			curr = next
		}
	}
	curr.isWord = true
}


/** Returns if the word is in the trie. */
func (this *Trie) Search(word string) bool {
	if len(word) < 1 {
		return true
	}
	wordBytes := []byte(word)
	curr := this
	for i := 0;i<len(wordBytes) ; i++  {
		if next,ok := curr.children[wordBytes[i]];ok {
			curr = next
		} else {
			return false
		}
	}
	return curr.isWord
}


/** Returns if there is any word in the trie that starts with the given prefix. */
func (this *Trie) StartsWith(prefix string) bool {
	if len(prefix) < 1 {
		return true
	}
	prefixBytes := []byte(prefix)
	curr := this
	for i := 0;i<len(prefixBytes) ; i++  {
		if next,ok := curr.children[prefixBytes[i]];ok {
			curr = next
		} else {
			return false
		}
	}
	return true
}


/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */