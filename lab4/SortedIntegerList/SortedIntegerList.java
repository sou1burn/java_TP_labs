package SortedIntegerList;

import java.util.LinkedList;
import java.util.ListIterator;
import RemoveException.RemoveException;


public class SortedIntegerList
{
    private boolean dups;
    private LinkedList<Integer> lst;


    public SortedIntegerList(boolean dups) 
    {
        this.dups = dups;
        this.lst = new LinkedList<>();
    }


    public void add(int num)
    {
        ListIterator<Integer> it = lst.listIterator();

        while(it.hasNext())
            {
                int curr = it.next();
                if (num < curr)
                {
                    it.previous();
                    break;
                }
                else if (num == curr && !dups)
                {
                    return;
                }
            }

        it.add(num);
    }

    public void remove(int num)
    {
        if (lst.isEmpty())
        {
            return;
        }

        ListIterator<Integer> it = lst.listIterator();

        while (it.hasNext())
        {
            int curr = it.next();
            if (curr == num)
            {
                it.remove();
                return;
            }
        }  
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || o.getClass() != getClass())
        {
            return false;
        }

        SortedIntegerList other = (SortedIntegerList) o;


        return lst.equals(other.lst);
    }

    @Override
    public String toString()
    {
        return lst.toString();
    }


    public SortedIntegerList remove(SortedIntegerList other)
    {
        if (this.dups != other.dups)
        {
            throw new RemoveException("Dups for both lists must be equal");
        }
        
        SortedIntegerList res = new SortedIntegerList(this.dups);

        ListIterator<Integer> it1 = this.lst.listIterator();
        ListIterator<Integer> it2 = other.lst.listIterator();

        Integer item1 = it1.hasNext() ? it1.next() : null;
        Integer item2 = it2.hasNext() ? it2.next() : null;

        while (item1 != null)
        {
            if (item2 == null || item1 < item2)
            {
                res.add(item1);
                item1 = it1.hasNext() ? it1.next() : null;
            }
            else if (item1.equals(item2))
            {
                item1 = it1.hasNext() ? it1.next() : null;
                item2 = it2.hasNext() ? it2.next() : null;
            }
            else
            {
                item2 = it2.hasNext() ? it2.next() : null;
            }
        }
        return res;
    }
}