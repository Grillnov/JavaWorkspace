import java.lang.reflect.Array;

public class MyListUnSorted<Type> implements ICollection<Type>
{
	private Integer size;
	private LinkedListNode<Type> ptr;
	private LinkedListNode<Type> root;
	MyListUnSorted()
	{
		this.size = 0;
		this.root = this.ptr = null;
	}
	@Override
	public void add(Type element) 
	{
		if(this.size == 0)
		{
			this.root = new LinkedListNode<Type>(element);
			this.ptr = this.root;
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
			if(target == this.root)
			{
				if(this.root.next != null)
					this.root.next.prev = null;
				this.root = this.root.next;
			}
			else if(target == this.ptr)
			{
				this.ptr = this.ptr.prev;
				this.ptr.next = null;
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
		LinkedListNode<Type> target = this.root;
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
		this.root = null;
		this.size = 0;
	}

	@Override
	public Type[] toArray(Class<Type> type) 
	{
		Type[] returnVal = (Type[]) Array.newInstance(type, this.size);
		LinkedListNode<Type> iterator = this.root;
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
