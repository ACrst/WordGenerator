
import java.net.*;
import java.util.*;
import java.io.*;


public class WordGeneratorSolution {

	
	
	private static List<String> transform1(Set<String> words, String word) {
        List<String> candidates = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);

        for (int i = 0; i < sb.length(); i++) 
        {
            char temp = sb.charAt(i);
            
            for (char c = 'a'; c <= 'z'; c++) 
            {
                if (temp == c) 
                    continue;
                
                sb.setCharAt(i, c);
                String newWord = sb.toString();

                if (words.remove(newWord)) 
                    candidates.add(newWord);

            }
            sb.setCharAt(i, temp);
        }    
        
        return candidates;
        //returns the correct replacement for same length
    }

	private static List<String> transform2(Set<String> words, String word, int endsize) {
        List<String> candidates = new ArrayList<>();
        StringBuffer sb = new StringBuffer(word);
      

    	//Case2: consider a letter being added

        
        for(int i=0;i<sb.length();i++)
        {
        	char temp=sb.charAt(i);
        	for(char c='a';c<='z';c++)
        	{
        		if(temp==c)
        			continue;
        		sb.setCharAt(i,c);
        		String newWord=sb.toString();
//        		System.out.println(sb.equals(newWord));
//        		if(sb.equals(newWord))
//        		{
        			if(words.remove(newWord))
            		{
            			candidates.add(newWord);
            			System.out.print(newWord+"-");
            		}
//        		}
        	}
        	sb.setCharAt(i, temp);
        	//check if the endsize criteria is matching
            //if not then add a new character to sb such that
            // while adding the character, traverse through each position of sb
            // and add the character at that position and check whether the newWord is in the words set.
	   
        }
        
        return candidates;
    }

	private static List<String> transform3(Set<String> words, String word, int endsize) {
        List<String> candidates=new ArrayList<>();
        StringBuffer sb=new StringBuffer(word);

    	//Case3: consider deleting a letter
     
        System.out.println(sb);

       	
//    	System.out.println(sb);    
        for(int i=0;i<sb.length();i++)
        {
        	char temp=sb.charAt(i);
        	for(char c='a';c<='z';c++)
        	{
        		if(temp==c)
        			continue;
        		sb.setCharAt(i,c);
        		String newWord=sb.toString();
        		if(words.remove(newWord))
        		{
        			candidates.add(newWord);
        			System.out.print(newWord.length()+"-");
        		}
        	}
        	sb.setCharAt(i, temp);
        	//check if the endsize criteria is matching
            //if not then delete a character from sb such that
            // while deleting the character, traverse through each position of sb
            // and remove the character at that position and check whether the newWord is in the words set.
   
        	
        }
        return candidates;
    }

	
	public static int create_wordlist(String beginWord, String endWord,List<String> worddict) throws Exception
	{

		if(worddict==null ||!worddict.contains(endWord))
			return 0;
		
		Queue<String> q=new LinkedList<>();
		q.offer(beginWord);
		Set<String> words=new HashSet<>(worddict);
		int count=1;
		while(!q.isEmpty())
		{
			int size=q.size();
			count++;
			for(int i=0;i<size;i++)
			{
				String word=q.poll();
				 
				List<String> candidates=new ArrayList<>();
				if(beginWord.length()==endWord.length())
				{
					//Case1: consider a letter being modified. Size of word doesn't change
	           		candidates=transform1(words,word);
				}
				else if(beginWord.length()<endWord.length())
                {
                	//Case2: consider a letter being added
				
                	candidates=transform2(words,word,endWord.length());
                }
                else if(beginWord.length()>endWord.length())
                {
                	candidates=transform3(words,word, endWord.length());
                }
				for(String candidate:candidates)
				{
					if(endWord.equals(candidate))
						return count;
					q.offer(candidate);
				}
			}
		}
		
		return 0;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("My Solution");
		try {

			URL url=new URL("https://www.wordgamedictionary.com/twl06/download/twl06.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
			String i;
			List<String> worddict=new ArrayList<>();
			Set<String> worddict_set=new HashSet<>();
			HashMap<String,ArrayList<String>> dictMap=new HashMap<>();
			br.readLine();
			br.readLine();
			while((i=br.readLine())!=null)
			{

				if(!i.contains(" "))
				{
					dictMap.put(i, new ArrayList<String>());
					worddict.add(i);	
				}
					
			}
			br.close();
			worddict_set.addAll(worddict);
			int shortest_length=create_wordlist(args[0],args[1],worddict);

			System.out.println(shortest_length); //shortest distance
		}catch(Exception e) {;
			e.printStackTrace();
		}
	}

}



/*
 * 
 * Here I have made an assumption that the beginWord and endWord are of the same length.
 * This has been implemented using invocation transform1 to represent case1. In case 2, the shortest length between
 * "mouse" and "elephant" can be generated by the logic mentioned under transform2 invocation.
 * Similarly, the shortest distance between "crawl" and "run" can be implemented using the logic mentioned in transform3 invocation.
 * Here, the program was tested by passing in two command-line parameters:love hate
 * The BigO complexity of this program is assumed to be O(n*d) for transform1, which will be d=1, transform2 and transform3 where d=endlist_size.
 * 
 * 
 * 
 * */
