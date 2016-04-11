import java.lang.Integer;
import java.lang.reflect.Array;
import java.lang.Class;

class LinkedListNode<Type>
{
	public Type val;
	public LinkedListNode<Type> prev;
	public LinkedListNode<Type> next;
	
	LinkedListNode()
	{
		this.val = null;
		this.prev = null;
		this.next = null;
	}
	LinkedListNode(Type val)
	{
		this.val = val;
		this.prev = null;
		this.next = null;
	}
}


public class MyListSorted<Type extends Object & Comparable<Type>> implements ICollection<Type>//
{
	private Integer size;
	private LinkedListNode<Type> head;
	
	MyListSorted()
	{
		this.size = 0;
		this.head = null;
	}

	@Override
	public void add(Type element) 
	{
		if(this.size == 0)
		{
			this.head = new LinkedListNode<Type>(element);
			++this.size;
			return;
		}
		
		++this.size;
		LinkedListNode<Type> potential;
		if(element.compareTo(head.val) == -1)//new head establish required!
		{
			potential = new LinkedListNode<Type>(element);
			this.head.prev = potential;
			potential.next = this.head;
			this.head = potential;
		}
		else
		{
			potential = this.head;
			while(potential.next != null && potential.next.val.compareTo(element) == -1)
				potential = potential.next;
			LinkedListNode<Type> newNode = new LinkedListNode<Type>(element);
			newNode.prev = potential;
			newNode.next = potential.next;
			if(potential.next != null)
			{
				potential.next.prev = newNode;
			}
			potential.next = newNode;
		}
	}

	@Override
	public Boolean remove(Type element) 
	{
		LinkedListNode<Type> target = searchIndex(element);
		if(target == null)
			return false;
		else if(target == this.head)
		{
			this.head.next.prev = null;
			this.head = this.head.next;
			--this.size;
			return true;
		}
		else
		{
			--this.size;
			if(target.prev != null)
				target.prev.next = target.next;
			if(target.next != null)
				target.next.prev = target.prev;
			return true;
		}
	}

	@Override
	public Boolean contains(Type element) 
	{
		LinkedListNode<Type> searchResult = searchIndex(element);
		if(searchResult == null)
			return false;
		else
			return true;
	}

	private LinkedListNode<Type> searchIndex(Type element)
	{
		LinkedListNode<Type> target = this.head;
		while(target != null && target.val.compareTo(element) != 0)
			target = target.next;
		return target;
	}
	
	@Override
	public Integer size() 
	{
		return this.size;
	}

	@Override
	public void clear() 
	{
		this.head = null;
		this.size = 0;
	}

	@Override
	public Type[] toArray(Class<Type> type) 
	{
		Type[] returnVal = (Type[]) Array.newInstance(type, this.size);
		LinkedListNode<Type> iterator = this.head;
		for(int i = 0; i != this.size; ++i)
		{
			returnVal[i] = iterator.val;
			iterator = iterator.next;
		}
		return returnVal;
	}

	@Override
	public Boolean isEmpty() 
	{
		return (this.size == 0?true:false);
	}
}