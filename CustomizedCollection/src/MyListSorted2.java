import java.util.Stack;
//import java.util.ArrayList;
class TreeNode<Type extends Comparable<Type>>
{
	public TreeNode<Type> left, right, ancestor;
	public Type val;
	TreeNode(Type element)
	{
		left = right = ancestor = null;
		val = element;
	}
}

public class MyListSorted2<Type extends Object & Comparable<Type>> implements ICollection<Type>
{
	private TreeNode<Type> root;
	private Integer size;
	MyListSorted2()
	{
		this.root = null;
		this.size = 0;
	}
	@Override
	public void add(Type element) 
	{
		if(size == 0)//empty list
		{
			this.root = new TreeNode<Type>(element);
			++this.size;
			return;
		}
		++this.size;
		TreeNode<Type> emplace = this.root;
		while(true)
		{
			TreeNode<Type> backup = emplace;
			if(emplace.val.compareTo(element) > 0)//element is smaller than the current emplacement node
			{
				emplace = emplace.left;
				if(emplace == null)
				{
					emplace = new TreeNode<Type>(element);
					emplace.ancestor = backup;
					backup.left = emplace;
					return;
				}
			}
			else
			{
				emplace = emplace.right;
				if(emplace == null)
				{
					emplace = new TreeNode<Type>(element);
					emplace.ancestor = backup;
					backup.right = emplace;
					return;
				}
			}
		}
	}

	@Override
	public Boolean remove(Type element) 
	{
		TreeNode<Type> target = searchIndex(element);
		if(target == null)//no such element, unable to proceed removing
			return false;
		--size;
		
		if(target.left == null)
		{
			if(target.ancestor == null)//root removal!
			{
				this.root = target.right;
				if(target.right != null)
					this.root.ancestor = null;
				
				return true;
			}
			if(target == target.ancestor.left)
				target.ancestor.left = target.right;
			else
				target.ancestor.right = target.right;
			if(target.right != null)
				target.right.ancestor = target.ancestor;
		}
		else if(target.right == null)
		{
			if(target.ancestor == null)//root removal!
			{
				this.root = target.left;
				this.root.ancestor = null;
				
				return true;
			}
			if(target == target.ancestor.left)
				target.ancestor.left = target.left;
			else
				target.ancestor.right = target.left;
			target.left.ancestor = target.ancestor;
		}
		else
		{
			TreeNode<Type> successor = target.right;
			while(successor.left != null)
				successor = successor.left;
			if(successor == target.right)
			{
				if(target.ancestor == null)
				{
					this.root = successor;
					this.root.ancestor = null;
					
					this.root.left = target.left;
					target.left.ancestor = this.root;
					
					return true;
				}
				if(target == target.ancestor.left)
					target.ancestor.left = successor;
				else
					target.ancestor.right = successor;
				successor.ancestor = target.ancestor;
				
				successor.left = target.left;
				target.left.ancestor = successor;
			}
			else
			{
				successor.ancestor.left = successor.right;
				if(successor.right != null)
					successor.right.ancestor = successor.ancestor;
				if(target.ancestor == null)
				{
					this.root = successor;
					this.root.ancestor = null;
					
					this.root.left = target.left;
					target.left.ancestor = this.root;
					
					this.root.right = target.right;
					target.right.ancestor = this.root;
					
					return true;
				}
				if(target == target.ancestor.left)
					target.ancestor.left = successor;
				else
					target.ancestor.right = successor;
				successor.ancestor = target.ancestor;
				
				successor.left = target.left;
				target.left.ancestor = successor;
				
				successor.right = target.right;
				target.right.ancestor = successor;
			}
		}
		return true;
	}

	@Override
	public Boolean contains(Type element) 
	{
		if(searchIndex(element) != null)
			return true;
		else
			return false;
	}

	public TreeNode<Type> searchIndex(Type element)
	{
		TreeNode<Type> target = this.root;
		while(target != null)
		{
			if(target.val.equals(element))
				break;
			else if(target.val.compareTo(element) > 0)//element is smaller than the current target
				target = target.left;
			else
				target = target.right;
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
	public Object[] toArray() 
	{
		Stack<Type> container = new Stack<Type>();
		inOrderTraverse(container, this.root);
		return container.toArray();
	}

	private void inOrderTraverse(Stack<Type> container, TreeNode<Type> current)
	{
		if(current == null)
			return;
		inOrderTraverse(container, current.left);
		container.push(current.val);
		inOrderTraverse(container, current.right);
	}
	
	@Override
	public Boolean isEmpty() 
	{
		return (this.size == 0?true:false);
	}
	
	@Override
	public boolean equals(Object rhs)
	{
		if(this.size != ((MyListSorted2<Type>)rhs).size)
			return false;
		Object[] arr1 = this.toArray();
		Object[] arr2 = ((MyListSorted2<Type>)rhs).toArray();
		for(int i = 0; i != this.size; ++i)
		{
			if(!arr1[i].equals(arr2[i]))
				return false;
		}
		return true;
	}
}
