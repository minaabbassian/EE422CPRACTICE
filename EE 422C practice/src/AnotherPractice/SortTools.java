//SortTools.java
/*
 * EE 422C Project 1 submission by 
 * Replace <...> with your actual data.
 * <Mina Abbassian>
 * <mea2947>
 * <75535>
 * Spring 2020
 * Slip days used: none
 */

package AnotherPractice;

import java.util.Arrays;

public class SortTools {
	
	/**
	  * Return whether the first n elements of x are sorted in non-decreasing
      * order.
      * @param x is the array
      * @param n is the size of the input to be checked
      * @return true if array is sorted
      */
	public static boolean isSorted(int[] x, int n) {
		// O(n) runtime complexity
        // stub only, you write this!
        // TODO: complete it
		
		// Preconditions:
		//	0 <= n <= x.length
		// 	x != null
		
		// check whether n is 1
		// an array of length 1 is always already sorted 
		if(n == 1) {
			return (true);
		}
		
		// check edge cases:
		// check whether n is greater than the length of the array
		int len = x.length;
		if(n > len) {
			return (false);
		}
		// check whether the array is empty 
		if(len == 0) {
			return (false);
		}
		// check whether n is 0
		if(n == 0) {
			return (false);
		}
		
		// iterate through the x array and check if it is sorted in nondecreasing order
		// if a next variable is less than a previous variable in the array, then return false
		int nLen = n-1;
		for(int i = 0; i < nLen; i++) {
			if(x[i] > x[i+1]) {
				return (false);	
			}
		}
		
		// the first n elements of array x are sorted in nondecreasing order
		return (true);	
	}
	
	
	/**
	 * Return an index of value v within the first n elements of x.
     * @param x is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be searched for
     * @return any index k such that k < n and x[k] == v, or -1 if no such k exists
     */
    public static int find(int[] x, int n, int v) {
    	// O(log(n)) runtime complexity
        // stub only, you write this!
        // TODO: complete it
    	
    	// Preconditions: 
    	//	isSorted(x, n) is true
    	//	0 < n <= x.length
    	//	x != null
    	
    	// Binary Search implementation
    	// n is the right bound for the Binary Search implementation
    	
    	// Binary Search implementation steps:
    	//	1. Compare v with the middle element.
    	//	2. If v matches with the middle element, we return the mid index.
    	//	3. Else If v is greater than the mid element, then v can only lie in the right half subarray after the mid element. So we recur for right half.
    	//	4. Else (v is smaller) recur for the left half.
    	
    	
    	// variables for the left and right bounds of the binary search
    	int r = n-1;
    	int l = 0;
    	
    	while(r >= l) {
    		
    		// if v matches with the middle element
    		// then we return the middleIndex
    		int middleIndex = (l + r)/2;
    		if(v == x[middleIndex]) {
    			return (middleIndex);
    		}
    		
    		// if v is smaller than the mid element
    		// then recur for the left half
    		if(v < x[middleIndex]) {
    			r = middleIndex - 1;
    		}
    		
    		// if v is greater than the mid element
    		// then v can only lie in the right half subarray after the middle element
    		// so we recur for the right half
    		if(v > x[middleIndex]) {
    			l = middleIndex + 1;
    		}	
    	}
    	
    	// v is not contained within the first n elements of x
    	return (-1);		
    }
    
    
    /**
     * Return a sorted, newly created array containing the first n elements of x
     * and ensuring that v is in the new array.
     * @param x is the array
     * @param n is the number of elements to be copied from x
     * @param v is the value to be added to the new array if necessary
     * @return a new array containing the first n elements of x as well as v
     */
    public static int[] copyAndInsert(int[] x, int n, int v) {
    	// O(n) runtime complexity
        // stub only, you write this!
        // TODO: complete it
    	
    	// Preconditions:
    	//	isSorted(x, n) is true
    	//	0 < x.length
    	//	0 <= n <= x.length
    	//	x != null
    	
    	int finalArr[] = {};
    	
    	// Case 1:
    	// the number of elements to be copied from x is 0
    	if(n == 0) {
    		return (finalArr);
    	}
    	
    	// Case 2:
    	// v is greater than the last element of array x
    	// then put it as the last element of the finalArr
    	int len = x.length - 1;
    	int nLen = n + 1;
    	if(x[len] < v) {
    		finalArr = Arrays.copyOfRange(x, 0, nLen);
    		finalArr[finalArr.length - 1] = v;
    	}
    
    	// Case 3:
    	// if v is smaller than the first element of array x
    	// then put it as the first element of the finalArr
    	else if(x[0] > v) {
    		finalArr = new int[nLen];
    		finalArr[0] = v;
    		for(int i = 1; i < nLen; i++) {
    			finalArr[i] = x[i-1];
    		}
    	}
    	
    	// Case 4:
    	// if the value of v is contained within array x
    	// use the find function to see if v is in the first n elements of array x
    	// if v is found, then return array x of size n
    	// if v is not found, then return n elements of array x with v inserted into it
    	else {
    		
    		// first, use the find function to check if v is within the first n elements of array x
    		int nIndex = find(x, n, v);
    		
    		// if v does not lie within the first n elements of array x
    		if(nIndex == -1) {
    			boolean isInArray = false;
    			finalArr = new int[nLen];
    			
    			// traverse the n elements of array x
    			for(int i = 0; i < nLen; i++)
    				// insert v into its sorted position in the finalArr
    				if ((!isInArray) && (i > 0) && (v <= x[i]) && (v >= x[i-1])) {
    					finalArr[i] = v;
    					// update isInArray to true because v is now inserted into the finalArr
    					isInArray = true;		
    				}
    			
    				else if(!isInArray) {
    					// if v has not yet been inserted into the array, add the next element of array x
    					finalArr[i] = x[i];
    				}
    			
    				else {
    					// once v has been inserted into the array, add the previous element of array x
    					finalArr[i] = x[i - 1];
    				}					
    		}
    	
    		// if v is found within the first n elements of array x
    		// then return array x of size n
    		else {
    			finalArr = Arrays.copyOfRange(x, 0, n);
    		}  		
    	}
    	
    	return (finalArr);
    }
    	
    
    /**
     * Insert the value v in the first n elements of x if it is not already
     * there, ensuring those elements are still sorted.
     * @param x is the array
     * @param n is the number of elements in the array
     * @param v is the value to be added
     * @return n if v is already in x, otherwise returns n+1
     */
    public static int insertInPlace(int[] x, int n, int v) {
    	// O(n) runtime complexity
        // stub only, you write this!
        // TODO: complete it
    	
    	// Preconditions:
    	//	isSorted(x, n) is true
    	//	0 < n < x.length
    	// 	x != null
    	
    	// create a new array that will contain the value of v 
    	int[] finalArr;
    	
    	// check to see if v is contained within the first n elements of array x
    	int vIndex = find(x, n, v);
    	
    	
    	// if v is contained within the first n elements of array x
    	// then return n
    	if(vIndex >= 0) {
    		return (n);
    	}
    	
    	// if v is not contained within the first n elements of array x
    	else {
    		
    		// if v is greater than the last element of array x
        	// then put it as the last element of the finalArr
    		int len = x.length - 1;
        	int nLen = n + 1;
        	if(x[len] < v) {
        		finalArr = Arrays.copyOfRange(x, 0, nLen);
        		finalArr[finalArr.length - 1] = v;
        	}
    		
        	// if v is smaller than the first element of array x
        	// then put it as the first element of the finalArr
        	else if(x[0] > v) {
        		finalArr = new int[nLen];
        		finalArr[0] = v;
        		for(int i = 1; i < nLen; i++) {
        			finalArr[i] = x[i-1];
        		}
        	}
        	
        	// if the value of v is contained within array x
        	// use the find function to see if v is in the first n elements of array x
        	// if v is found, then return array x of size n
        	// if v is not found, then return n elements of array x with v inserted into it
        	else {
        		
        		boolean isInArray = false;
        		finalArr = new int[nLen];
        			
        		// traverse the n elements of array x
        		for(int i = 0; i < nLen; i++) {
        			// insert v into its sorted position in the finalArr
    				if ((!isInArray) && (i > 0) && (v <= x[i]) && (v >= x[i-1])) {
        				finalArr[i] = v;
        				// update isInArray to true because v is now inserted into the finalArr
        				isInArray = true; 
        			}
        			
        			else if(!isInArray) {
        				// if v has not yet been inserted into the array, add the next element of array x
        				finalArr[i] = x[i];
        			}
        			
        			else {
        				// once v has been inserted into the array, add the previous element of array x
        				finalArr[i] = x[i - 1];
        			}
        		}
        	}
        	
        	// update the array x to include the element v
        	x = finalArr;
        	
        	// return n+1 since v was not contained within the first n elements of array x
        	return (nLen);
    	}
    }
    
    
    /**
     * Sort the first n elements of x in-place in non-decreasing order using
     * insertion sort.
     * @param x is the array to be sorted
     * @param n is the number of elements of the array to be sorted
     */
    public static void insertSort(int[] x, int n) {
    	// O(n^2) runtime complexity
        // stub only, you write this!
        // TODO: complete it
    	
    	// Preconditions:
    	//	0 < x.length
    	//	0 < n <= x.length
    	//	x != null
    	
    	// traverse through the first n elements of array x
    	for(int i = 0; i < n; i++) {
    		int currentValue = x[i];
    		int lastIndex = i - 1;
    		
    		// while loop until the currentValue is at its right index in array x
    		// move elements that are greater than currentValue to one position ahead of their current position
    		while((lastIndex >= 0) && (x[lastIndex] > currentValue)){
    			x[lastIndex + 1] = x[lastIndex];
    			lastIndex = lastIndex - 1;
    		}
    		x[lastIndex + 1] = currentValue;
    	}
    }
    	
	
}










