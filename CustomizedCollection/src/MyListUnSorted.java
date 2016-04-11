import java.util.ArrayList;

public class MyListUnSorted<Type> implements ICollection<Type>
{
	private Integer size;
	private LinkedListNode<Type> ptr;
	private LinkedListNode<Type> head;
	MyListUnSorted()
	{
		this.size = 0;
		this.head = this.ptr = null;
	}
	@Override
	public void add(Type element) 
	{
		if(this.size == 0)
		{
			this.head = new LinkedListNode<Type>(element);
			this.ptr = this.head;
			this.size++;
		}
		else
		{
			LinkedListNode<Type> newNode = new LinkedListNode<Type>(element);
			newNode.prev = this.ptr;
			this.ptr.next = newNode;
			this.ptr = newNode;
			this.size++;
		}
	}

	@Override
	public Boolean remove(Type element) 
	{
		LinkedListNode<Type> target = searchIndex(element);
		if(target == null)
			return false;
		else
		{
			if(target == this.head)
			{
				if(this.head.next != null)
					this.head.next.prev = null;
				this.head = this.head.next;
			}
			else if(target == this.ptr)
			{
				this.ptr = this.ptr.prev;
				this.ptr.next = null;
			}
			else
			{
				target.prev.next = target.next;
				target.next.prev = target.prev;
			}
			this.size--;
			return true;
		}
	}

	@Override
	public Boolean contains(Type element) 
	{
		LinkedListNode<Type> target = searchIndex(element);
		if(target != null)
			return true;
		else
			return false;
	}
	
	private LinkedListNode<Type> searchIndex(Type element)
	{
		LinkedListNode<Type> target = this.head;
		while(target != null && !target.val.equals(element))
		{
			target = target.next;
		}
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
	public Object[] toArray() 
	{
		ArrayList<Type> container = new ArrayList<Type>();
		LinkedListNode<Type> iterator = this.head;
		for(int i = 0; i != this.size; ++i)
		{
			container.add(iterator.val);
			iterator = iterator.next;
		}
		return container.toArray();
	}

	@Override
	public Boolean isEmpty() 
	{
		return (this.size == 0?true:false);
	}
	
	@Override
	public boolean equals(Object rhs)
	{
		if(this.size != ((MyListUnSorted<Type>)rhs).size)
			return false;
		LinkedListNode<Type> i1 = this.head;
		LinkedListNode<Type> i2 = ((MyListUnSorted<Type>)rhs).head;
		for(int i = 0; i != this.size; ++i)
		{
			if(!i1.val.equals(i2.val))
				return false;
			i1 = i1.next;
			i2 = i2.next;
		}
		return true;
	}
}
