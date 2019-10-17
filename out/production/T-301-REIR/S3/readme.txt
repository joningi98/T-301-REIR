/**********************************************************************
 *  readme.txt template                                                   
 *  Kd-tree
**********************************************************************/

Name:    Jón Ingi Ólafsson
Login:   jonio18@ru.is
Section instructor:

Partner name:
Partner login:    
Partner section instructor:

/**********************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **********************************************************************/
for the node data type i gave it four private variables,
Point2D p: for the x and y coordinates
Node left, right: to keep track of which are next nodes from the current one
Boolean vertical: to see if the node if vertical or horizontal
RectHV rect: a rectangle corresponding the area it has

/**********************************************************************
 *  Describe your method for range search in a kd-tree.
 **********************************************************************/
to find the set of points in the given rectangle i used a recursive function to check it.
first i see if the node that im on is null if so then i will return my stack which is the
iterable set that im using. I see if the rectangle im looking at intersects the node that im on if not
then i return the stack, i also check is the rectangle contains the point is so i push the node onto
the stack, then i do this for the left and the right subtrees.

/**********************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **********************************************************************/
i use a recursive function to go down the tree to look for the nearest neighbour. As always we start by
checking weather the node is null or not, the i check is the node that im on has a smaller distance to the target
is so then the node is the closest node so far. the i look ata if the node is vertical or horizontal.
if the node is vertical then i look first at the left subtree then the right, opposite for horizontal.


/**********************************************************************
 *  Give the total memory usage in bytes (using tilde notation and 
 *  the standard 64-bit memory cost model) of your 2d-tree data
 *  structure as a function of the number of points N. Justify your
 *  answer below.
 *
 *  Include the memory for all referenced objects (deep memory),
 *  including memory for the nodes, points, and rectangles.
 **********************************************************************/

bytes per Point2D: 32 bytes
2 * 64-bit double = 16 bytes

overhead = 16 bytes
bytes per RectHV:48 bytes
 4 * 64-bit double = 256 bit = 32 bytes
 overhead = 16 bytes

 bytes per Node: 105 bytes
 1 * Point2d = 32 bytes
 1 * RectHV = 48 bytes
 bool vertical = 1 byte
 left, right = 16 bytes
 static overhead = 8 bytes

I store the memory of the nodes like this because i want to know if the node vertical, i use that in insert.
the RectHV is use also in insert to know what is the area that is point uses.

bytes per KdTree of N points (using tilde notation):   ~105N
[include the memory for any referenced Node, Point2D and RectHV objects]


/**********************************************************************
 *  Give the expected running time in seconds (using tilde notation)
 *  to build a 2d-tree on N random points in the unit square.
 *  Use empirical evidence by creating a table of different values of N
 *  and the timing results. (Do not count the time to generate the N 
 *  points or to read them in from standard input.)
 **********************************************************************/
| N |               | Kdtree |    | Ratio |
-------------------------------------------
16000                   0.032         -
32000                   0.058        1.812
64000                   0.13         2.241
128000                  0.223        1.715
256000                  0.333        1.493
512000                  0.722        2.168
1024000                 1.547        2.142
                                Average: 1.928


/**********************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 **********************************************************************/
| N |    | brute |   | KdTree |
-------------------------------
 100K       342          8452
 1M         120          5648
                     calls to nearest() per second
                     brute force           2d-tree
input100K.txt
input1M.txt

I measured this by setting a timer and counting operations, stop when the
timer hits 1 sec

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and dæmatímar, but do
 *  include any help from people (including course staff, 
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
I got help form google

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
One big problem that i has was when i tried to insert nodes with the rectangle and finding the current rectangle
for that node.

/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/






/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
