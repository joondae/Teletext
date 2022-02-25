import java.awt.Graphics;

/**
 * Implements the list of messages for teletext
 */
public class TeletextList
{
  private ListNode2 heading, topNode;

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order.  Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines)
  {
	  // YOUR CODE GOES HERE
	  heading = new ListNode2("Today's headlines:");
	  topNode = heading;
	  
	  //not needed???:
	  heading.setNext(heading);
	  heading.setPrevious(heading);
	  
	  ListNode2 currentNode = addAfter(heading, "");
	  addBefore(heading, "");
	  //addAfter(heading.getNext(), "");
	  
	  //(basically above 2 helper methods):
	  /*
	  ListNode2 emptyBefore = new ListNode2("", null, heading);
	  ListNode2 emptyAfter = new ListNode2("", heading, emptyBefore);
	  
	  emptyBefore.setPrevious(emptyAfter);
	  heading.setPrevious(emptyBefore);
	  heading.setNext(emptyAfter);
	  */
	  
	  for(int i = 0; i < headlines.length; i++)
	  {
		  currentNode = addAfter(currentNode, headlines[i]);
		  
		  //(basically above helper method):
		  /*
		  ListNode2 newNode = new ListNode2(headlines[i], node, null);
		  node.setNext(newNode);
		  */
	  }
  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg)
  {
	  // YOUR CODE GOES HERE
	  addAfter(heading.getNext(), msg);
  }

  /**
   * Deletes the node that follows topNode from the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete()
  {
	  // YOUR CODE GOES HERE
	  if(!topNode.getNext().equals(heading)
			  && !topNode.getNext().equals(heading.getNext()) 
			  && !topNode.getNext().equals(heading.getPrevious()))
	  {
		  remove(topNode.getNext());
	  }
		  
  }

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp()
  {
	  // YOUR CODE GOES HERE
	  topNode = topNode.getNext();
  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg)
  {
	  // YOUR CODE GOES HERE
	  ListNode2 nodeToBeAdded = new ListNode2(msg, node.getPrevious(), node);
	  
	  //does the order of these 2 statements matter???
	  node.getPrevious().setNext(nodeToBeAdded);
	  node.setPrevious(nodeToBeAdded);
	 
	  return nodeToBeAdded;
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg)
  {
	  // YOUR CODE GOES HERE
	  ListNode2 nodeToBeAdded = new ListNode2(msg, node, node.getNext());
	  
	 //does the order of these 2 statements matter???
	  node.getNext().setPrevious(nodeToBeAdded);
	  node.setNext(nodeToBeAdded);
	  
	  return nodeToBeAdded;
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node)
  {
	  // YOUR CODE GOES HERE
	  node.getPrevious().setNext(node.getNext());
	  node.getNext().setPrevious(node.getPrevious());
  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines)
  {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++)
    {
      g.drawString((String)node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
}
