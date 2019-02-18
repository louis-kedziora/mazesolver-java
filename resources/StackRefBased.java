/*
* Name: 	Louis Kedziora 
* ID:		V00820695 
* Date: 	March 5, 2016
* Filename:	StackRefBased.java 
* Details: 	CSC115\-Assignment-3
*/

public class StackRefBased<T> implements Stack<T> {
	private StackNode<T> head;
	private int count;
	/*
	*	StackRefBased
	*	Constructor for the StackRefBased class
	*	used to create an instance of StackRefBased
	*/
    public StackRefBased() {
		head = null;
		count = 0;
    }
	
	/*
	*	size
	*	Used to get the number of elements in the stack
	*	@return the number of elements in an int
	*/
    public int size() {
		if (count == 0) {
			 return 0;
		}
        return count;
    }

	/*
	*	isEmpty
	*	Used to see if the stack is empty 
	*	@return a boolean of if the stack is empty
	*/
    public boolean isEmpty() {
        return (count == 0);
    }

	/*
	*	push
	*	Pushes an element onto the stack
	*	@param any type of generic value 
	*/
    public void push(T element) {
		StackNode<T> tmp = new StackNode<T>(element);
		if (count == 0) {
			head = tmp;
			count++;
			return;
		}
        tmp.setNext(head);
		head = tmp;
		count++;
    }

	/*
	*	pop
	*	Removes the element from the top of the stack
	*	and returns it 
	*	@return the element on the top of the stack 
	*/
    public T pop() throws StackEmptyException {
        if (count == 0) {
			throw new StackEmptyException();
		} else {
			StackNode<T> ex = head;
			head = head.next;
			count--;
			return ex.getValue();
		}
    }

	/*
	*	peek
	*	Looks at the top element without removing it
	*	@return the element on the top of the stack 
	*/
    public T peek() throws StackEmptyException {
        if (count == 0) {
			throw new StackEmptyException();
		} else {
			return head.getValue();
		}
    }

	/*
	*	makeEmpty
	*	Makes the stack empty of all elements
	*/
    public void makeEmpty() {
		count = 0;
		head = null;
		return;
    }

	/*
	*	toString
	*	Converts the stack to a string
	*	@return A String representation of the 
	*	stack
	*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
		StackNode<T> ex = head;
		sb.append("----");
		sb.append("\n");
		while (ex != null) {
			sb.append(ex.getValue());
			sb.append("\n");
			ex = ex.next;
		}
		sb.append("----");
		return sb.toString();
	}
	/*
	*	main
	*	Used for internal testing
	*/
	public static void main(String [] args) {
		Stack<Integer> k = new StackRefBased<Integer>(); 
		if (k.isEmpty()) 
		{
			System.out.println("The Stack is empty");
		}
		try {
			k.peek();
			System.out.println("your exception does not work");
		}
		catch (StackEmptyException ex) {
			System.out.println("The Stack is empty");
		}
		
		try {
			k.pop();
			System.out.println("your exception does not work");
		}
		catch (StackEmptyException ex) {
			System.out.println("The Stack is empty");
		}
		System.out.println("!...Done...!");
		k.push(22);
		k.push(37);
		k.push(95);
		k.push(11);
		k.push(22);
		k.push(33);
		k.push(44);
		k.push(77);
		k.push(88);
		try {
		while (!k.isEmpty()) 
		{
			System.out.println("Popping: " + k.pop());
		}
		System.out.println("Done");
		} catch (StackEmptyException ex) {
			System.out.println("The Stack is empty");
		}
	}
}

