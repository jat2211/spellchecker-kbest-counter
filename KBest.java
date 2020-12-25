import java.util.*;

public interface KBest<T>
{
	public void count(T x);
	public List<T> kbest();
}
