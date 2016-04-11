#Created by Bowen(Grillnov)
Homework 5 for Java curriculum.
MyListSorted, MyListSorted2 and MyListUnSorted generic types are implementing the universal interface ICollection.
MyDate is a class performing several utility function about date deriving.
#Data structure
MyListSorted & MyListUnSorted types are using linked lists as their underlying data structure. Thus to maintain a in-order structure for MyListSorted, a method somewhat like inserting sorting algorithm is used.
MyListSorted2 type is using binary search tree as its underlying data structure, and inserting/deleting while maintaining its order of elements is of course way faster than type MyListSorted. By simply doing a in-order traversing, the tree can be transformed into an linear structure and that's how toArray() method is implemented.