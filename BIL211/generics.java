public class Pair <T>{
  private T first;
  private T second;

  public Pair(){
    first = null;
    second = null;
  }

  public Pair(T firstItem, T secondItem){
    first = firstItem;
    second = secondItem;
  }

  public void setFirst(T newFirst){
    first = newFirst;
  }

  public void setSecond(T newSecond){
    second = newSecond;
  }

  public T getFirst(){
    return first;
  }

  public T getSecond(){
    return second;
  }

  public String toString(){
    return "first: " + first.toString() + "\n" + "second: " + second.toString();
  }

  public boolean equals(object other){
    if(other == null)
      return false;
    else if(!(other instanceof Pair)) // instanceof cant run on type parameters
      return false;
    else{
      Pair<T> otherPair = (Pair<T>) other; // there may be a warning here
      return (first.equals(otherPair.first)) && (second.equals(otherPair.second));
    }
  }
}

public class GenericDemo{
  public static void main(String[] args){
    Pair<String> p1 = new Pair<String>("Happy", "Day");
    Scanner k = new Scanner(System.in);
    String s1 = k.next();
    String s2 = k.next();
    Pair<String> p2 = new Pair<String>(s1, s2);
    if(p2.equals(p1))
      System.out.println("Eşit");
    else
      System.out.println(p2);
  }
}
/*terminal*/
ali veli =>  output: first: ali  second: veli
Happy Day => Eşit


T obj = new T(); incorrect
T[] a = new T[10]; incorrect
/*can not use new with T ! */

Pair<String>[] a = new Pair<String>[10]; incorrect -can use with arrayList
/* generic instaintiation arrays are not allowed ! */

/*----------------------------------------------------------------------------------------------------------------------------------------*/
/*more than one type parameters*/
/*public class .......("ali", 1)*/
public class twoTypePair<T1,T2>{
  private T1 first;
  private T2 second;

  public boolean equals(Object other){
    if(other == null)
      return false;
    else if (getClass() != other.getClass())
      return false;
    else{
      twoTypePair<T1, T2> p = (twoTypePair<T1, T2>) other;
      return (first.equals(p.first) && second.equals(p.second));
      // this two equals method different from each other. because of maybe parameter can be different type.As T1 is integer and T2 is String
    }
  }
}

public class Demo{
  public static void main(String[] args){
    twoTypePair<String, Integer> rating = new twoTypePair<String, Integer>("Lord of the rings", 9);
    System.out.println("Rating for" + rating.getFirst() + " is" + rating.getSecond());
    rating.setSecond(5);
  }
}

/* cant write generic class extends from throwable class */
/*public class myException<T> extends Exception{} incorrect*/


/*------------------------------------------------------------------------------------------------------*/

/*bounds for type parameters*/
/* add a method max() to Pair? */
/*as first < second a cant be compare because of those variables type is object.*/

/*
comparable interface
  public int compareTo(Object other);
    negative if this < other
    zero if this == other
    positive if this > other
*/

public T max(){
  if(first.compareTo(second) <= 0)
    return first;
  return second;
}

T must implement Comparable class.

public class Pair<T extends Comparable>{ // both for implements and extends. because of comparable is interface. this sytnax same as implements.

}

public class C1 <T extends Employee>{}
public class C2 <T extends Employee & Comparable>{}
public class C3 <T1 extends & Comporable, T2 extends Comporable>{}
public class C4 <T extends Employee & Class2>{} /*this is incorrect*/



/*Generic Methods*/
use type parameter in method => generic method

public class Utility{
  .
  .
  .
  public static <T> T getMidPoint(T[] arr){
    return arr[arr.length/2];
  }

  public static <T> T getFirst(T[] arr){
    return arr[0];
  }
}

String[] b = {"ali", "veli", "49", "50"};
String mid = Utility.<String>getMidPoint(b);

Double[] c = {3.2, 5.6, 5.7};
Double first = Utility.<Double>getFirst(c); // type belongs to method name


public class Sample<T>{
  private T data;
  public Sample(T newData){
    data = newData;
  }

  public<V> void show(V v){
    System.out.println("Hello "+ v);
    System.out.println("Data "+ data);
  }
}

Sample <Integer> obj = new Sample<Integer>(42);
obj.<String> show("Ali"); output=> Hello Ali \n Data 42

/*if the compiler can figure out the type:*/
obj.show("Ali");

Utility.getFirst(c); // we did this above


/*Inheritance with Generics*/
public class UnorderedPair<T> extends Pair<T>{
  public UnorderedPair(){
    setFirst(null);
    setSecond(null);
  }

  public boolean equals(Object other){
    .
    .
    .
    UnorderedPair<T>p = (UnorderedPair<T>) other;
    return(getFirst().equals(other.getFirst()) && getSecond().equals(other.getSecond()) || (getFirst().equals(other.getSecond()) && getSecond().equals(other.getFirst())));
  }
}

UnorderedPair<String> p1 = new UnorderedPair<String>("peanuts", "beer");
UnorderedPair<String> p2 = new UnorderedPair<String>("beer", "peanuts");

if(p1.equals(p2)); => true
