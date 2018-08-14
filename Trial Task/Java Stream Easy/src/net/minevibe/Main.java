package net.minevibe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main 
{
	
	// This is the list.  ALSO, by filter, you mean filter out? I personally don't know lol, so just add an exclamation mark to the statement if you mean make sure all the numbers are even. 
    public static List<Integer> randomNumberList() 
    {
    	final Random random = new Random();
    	final List<Integer> numbers = new ArrayList<>();
    	for (int i = 0; i < 10; i++)
    	{
        	int current = random.nextInt() & Integer.MAX_VALUE;
    		if (current % 2 == 0) //Here is the check to see if the number is even.
    		{
    			numbers.add(current);
    			Collections.sort(numbers);
    			current = random.nextInt() & Integer.MAX_VALUE;		
    		}
    	}
		return numbers;
    }
    
    public static void main(String[] args)
    {
    	System.out.print("Sorting numbers: " + randomNumberList());
    }
}