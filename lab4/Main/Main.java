package Main;

import RemoveException.RemoveException;
import SortedIntegerList.SortedIntegerList;

class Main
{

    public static void main(String[] args)
    {
        SortedIntegerList list1 = new SortedIntegerList(false); 
        SortedIntegerList list2 = new SortedIntegerList(true);  
        
        list1.add(5);
        list1.add(3);
        list1.add(7);
        list1.add(5); 
        
        list2.add(5);
        list2.add(3);
        list2.add(7);
        list2.add(5); 

        System.out.println("List1 (without duplicates): " + list1);
        System.out.println("List2 (with duplicates): " + list2);

        list1.remove(3);
        System.out.println("List1 after removing 3: " + list1);
        
        System.out.println("Are list1 and list2 equal? " + list1.equals(list2));

        list1.add(7);
        System.out.println("List1 after adding 7: " + list1);
        System.out.println("Are list1 and list2 equal now? " + list1.equals(list2));

        SortedIntegerList list3 = new SortedIntegerList(false);
        SortedIntegerList list4 = new SortedIntegerList(false);
        System.out.println("List 4: ");
        System.out.println(list4);
        System.out.println("Can remove from list3: " + list3);
        list3.remove(0);
        System.out.println(list3);
        System.out.println("List 4 equals list3? " + list3.equals(list4));


        SortedIntegerList list5 = new SortedIntegerList(true); 
        list5.add(1);
        list5.add(2);
        list5.add(2);
        list5.add(3);
        System.out.println("List 5: " + list5);
        SortedIntegerList list6 = new SortedIntegerList(true); 
        list6.add(2);
        list6.add(3);
        System.out.println("List 6: " + list6);

        SortedIntegerList result = list5.remove(list6);
        System.out.println("Result with duplicates allowed: " + result);

        SortedIntegerList list7 = new SortedIntegerList(false); 
        list7.add(1);
        list7.add(2);
        list7.add(3);
        System.out.println("List 7: " + list7);

        SortedIntegerList list8 = new SortedIntegerList(false); 
        list8.add(2);
        list8.add(3);
        System.out.println("List 8: " + list8);

        SortedIntegerList result2 = list7.remove(list8);
        System.out.println("Result with duplicates not allowed: " + result2);

        try{
            System.out.println("tryins removing with different dups");
            System.out.println("List 6: " + list6  + " List7: " + list7);
            SortedIntegerList re = list6.remove(list7);
            System.out.println("Res of diff dups: " + re);
        }
        catch (RemoveException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}