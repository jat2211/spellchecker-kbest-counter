import java.io.*;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface {
    //dictionary to reference check words
    HashSet<String> dict;   

    //constructor with try/catch
    public SpellChecker(String filename) { //filename is dictionary file
        dict = new HashSet<>();
        File dictFile = new File(filename);
        Scanner sc = new Scanner(dictFile);

        try {
            
            while (sc.hasNextLine()) {
                String dictionaryWord = sc.nextLine().trim().toLowerCase();
                dict.add(dictionaryWord);                
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getIncorrectWords(String filename) {
        File mainFile = new File(filename);
        List<String> incorrectWords = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(mainFile);
            
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().toLowerCase().split("\\s+");
                
                for(String currWord : line){
                    //removes punctuation of word and any numbers
                    String word = currWord.replaceAll("[^a-z0-9]", "")

                    //if not in dictionary, it is added to misspelled words list
                    if (dict.contains(word) == false && word.length() > 0) {
                        incorrectWords.add(word);
                    }
                }
            }
        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return incorrectWords;
    }
    
    public Set<String> getSuggestions(String word) {
        Set<String> suggestions = new HashSet<>();
        String suggWord = "";
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        //add one character
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                suggWord = word.toLowerCase().substring(0,i) + alphabet[j] + word.toLowerCase().substring(i,word.length());
                
                if (dict.contains(suggWord)) {
                    suggestions.add(suggWord);
                }
                
                if (i == word.length() - 1) {
                    suggWord = word + alphabet[j];
                    
                    if (dict.contains(suggWord)) {
                        suggestions.add(suggWord);
                    }
                }
            }
        }
        
        //remove one character
        for (int i = 0; i < word.length(); i++) {
            suggWord = word.toLowerCase().substring(0,i) + word.toLowerCase().substring(i+1,word.length());
            
            if (dict.contains(suggWord)) {
                suggestions.add(suggWord);
            }
        }

        //swap one character
        for (int i = 1; i < word.length(); i++) {
            if (i != word.length() - 1) {
                suggWord = word.toLowerCase().substring(0,i-1) + word.toLowerCase().substring(i,i+1) + word.toLowerCase().substring(i-1,i) + word.toLowerCase().substring(i+1,word.length());
            }
            
            else {
                suggWord = word.toLowerCase().substring(0,i-1) + word.toLowerCase().substring(i,i+1) + word.toLowerCase().substring(i-1,i);
            }
            if (dict.contains(suggWord)) {
                suggestions.add(suggWord);
            }
        }
        return suggestions;
    }
}