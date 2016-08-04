package com.nuclearthinking.myheroagency.utils;

import com.nuclearthinking.myheroagency.model.skills.funcs.Func;

import java.lang.reflect.Array;

/**
 * Created by Izonami on 04.08.2016.
 */
public class ArrayUtil {

    public static final Func[] EMPTY_FUNCTION_SET = new Func[0];

    public static <T> T[] add(final T[] array, final T element)
    {
        final Class type = array != null ? array.getClass().getComponentType() : element != null ? element.getClass() : Object.class;
        final T[] newArray = (T[]) copyArrayGrow(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static <T> T[] remove(final T[] array, final T value)
    {
        if(array == null)
            return null;

        final int index = arrayIndexOf(array, value);
        if(index == -1)
            return array;

        final int length = array.length;

        final T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, newArray, 0, index);
        if(index < length - 1)
            System.arraycopy(array, index + 1, newArray, index, length - index - 1);

        return newArray;
    }

    private static <T> T[] copyArrayGrow(final T[] array, final Class<? extends T> type)
    {
        if(array != null)
        {
            final int arrayLength = Array.getLength(array);
            final T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return (T[]) Array.newInstance(type, 1);
    }

    public static final <T> int arrayIndexOf(final T[] array, final T value)
    {
        return arrayFirstIndexOf(array, value);
    }

    public static final <T> int arrayFirstIndexOf(final T[] array, final T value)
    {
        if(value == null)
        {
            for(int i = 0; i < array.length; i++)
            {
                if(array[i] == null)
                    return i;
            }
        }
        else
        {
            for(int i = 0; i < array.length; i++)
            {
                if(value == array[i] || value.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> void eqSort(final T[] a)
    {
        eqSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void eqSort(final T[] a, final int lo0, final int hi0)
    {
        int lo = lo0;
        int hi = hi0;
        if(hi - lo <= 3)
        {
            eqBrute(a, lo, hi);
            return;
        } /* * Pick a pivot and move it out of the way */
        final T pivot = a[(lo + hi) / 2];
        a[(lo + hi) / 2] = a[hi];
        a[hi] = pivot;
        while (lo < hi)
        { /* * Search forward from a[lo] until an element is found that * is greater than the pivot or lo >= hi */
            while (a[lo].compareTo(pivot) <= 0 && lo < hi)
                lo++;
            while (pivot.compareTo(a[hi]) <= 0 && lo < hi)
                hi--;
            if(lo < hi)
            {
                final T e = a[lo];
                a[lo] = a[hi];
                a[hi] = e;
            }
        } /* * Put the median in the "center" of the list */
        a[hi0] = a[hi];
        a[hi] = pivot; /* * Recursive calls, elements a[lo0] to a[lo-1] are less than or * equal to pivot, elements a[hi+1] to a[hi0] are greater than * pivot. */
        eqSort(a, lo0, lo - 1);
        eqSort(a, hi + 1, hi0);
    }

    private static <T extends Comparable<T>> void eqBrute(final T[] a, final int lo, final int hi)
    {
        if(hi - lo == 1)
        {
            if(a[hi].compareTo(a[lo]) < 0)
            {
                final T e = a[lo];
                a[lo] = a[hi];
                a[hi] = e;
            }
        }
        else if(hi - lo == 2)
        {
            int pmin = a[lo].compareTo(a[lo + 1]) < 0 ? lo : lo + 1;
            pmin = a[pmin].compareTo(a[lo + 2]) < 0 ? pmin : lo + 2;
            if(pmin != lo)
            {
                final T e = a[lo];
                a[lo] = a[pmin];
                a[pmin] = e;
            }
            eqBrute(a, lo + 1, hi);
        }
        else if(hi - lo == 3)
        {
            int pmin = a[lo].compareTo(a[lo + 1]) < 0 ? lo : lo + 1;
            pmin = a[pmin].compareTo(a[lo + 2]) < 0 ? pmin : lo + 2;
            pmin = a[pmin].compareTo(a[lo + 3]) < 0 ? pmin : lo + 3;
            if(pmin != lo)
            {
                final T e = a[lo];
                a[lo] = a[pmin];
                a[pmin] = e;
            }
            int pmax = a[hi].compareTo(a[hi - 1]) > 0 ? hi : hi - 1;
            pmax = a[pmax].compareTo(a[hi - 2]) > 0 ? pmax : hi - 2;
            if(pmax != hi)
            {
                final T e = a[hi];
                a[hi] = a[pmax];
                a[pmax] = e;
            }
            eqBrute(a, lo + 1, hi - 1);
        }
    }

}
