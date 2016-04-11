import java.lang.Integer;

public interface ICollection<Type>
{
	public abstract void add(Type element);
	public abstract Boolean remove(Type element);
	public abstract Boolean contains(Type element);
	public abstract Integer size();
	public abstract void clear();
	public abstract Object[] toArray();
	public abstract Boolean isEmpty();
}