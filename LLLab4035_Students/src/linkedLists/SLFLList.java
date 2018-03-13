package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		if(length==0) {
			first = (SNode<E>) nuevo;
			last = (SNode<E>) nuevo;
		}
		else {
			((SNode<E>) nuevo).setNext(first); 
			first = (SNode<E>) nuevo;
		}
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		if(target==last) {
			((SNode<E>) target).setNext((SNode<E>) nuevo);
			last = (SNode<E>) nuevo;
		}
		else {
			((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
			((SNode<E>) target).setNext((SNode<E>) nuevo); 
		}
		length++;
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if(target==first) {
			this.addFirstNode(nuevo);
		}
		else {
			Node<E> prevNode = findNodePrevTo(target);
			this.addNodeAfter(prevNode, nuevo);
		}
		
	}
	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if(first==null)
			throw new NoSuchElementException("getFirstNode(): linked list is empty.");
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if(first == null)
			throw new NoSuchElementException("getLastNode(): Empty List.");
		else {
			return last;
		}
		
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		
		if(target == last)
			throw new NoSuchElementException("getNextNode(..): target is the last node.");
		else {
			return ((SNode<E>)target).getNext();
		}
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(target == first)
			throw new NoSuchElementException("getNodeBefore(...): target is the first Node.");
		else{
			return findNodePrevTo(target);}
	}

	public int length() {
		
		return this.length;
	}

	public void removeNode(Node<E> target) {
		if(target==first) {
			first = first.getNext();
		}
		else if(target == last) {
			last = (SNode<E>) findNodePrevTo(last);
		}
		else {
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target);
			prevNode.setNext(((SNode<E>) target).getNext());
		}
		((SNode<E>) target).clean();
		length--;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
