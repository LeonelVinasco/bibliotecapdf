package taller4;

import java.util.*;

class ArbolGenalogico {
    
 public static class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
	{
	    /**
	     * Construct the tree.
	     */
	    public BinarySearchTree( )
	    {
	        root = null;
	    }

	    /**
	     * Insert into the tree; duplicates are ignored.
	     * @param x the item to insert.
	     */
	    public void insert( AnyType x )
	    {
	        root = insert( x, root );
	    }

	    /**
	     * Remove from the tree. Nothing is done if x is not found.
	     * @param x the item to remove.
	     */
	    public void remove( AnyType x )
	    {
	        root = remove( x, root );
	    }

	    /**
	     * Find the smallest item in the tree.
	     * @return smallest item or null if empty.
	     */
	    public AnyType findMin( ) throws Exception
	    {
	        if( isEmpty( ) )
	            throw new Exception( );
	        return findMin( root ).element;
	    }

	    /**
	     * Find the largest item in the tree.
	     * @return the largest item of null if empty.
	     */
	    public AnyType findMax( ) throws Exception
	    {
	        if( isEmpty( ) )
	            throw new Exception( );
	        return findMax( root ).element;
	    }

	    /**
	     * Find an item in the tree.
	     * @param x the item to search for.
	     * @return true if not found.
	     */
	    public boolean contains( AnyType x )
	    {
	        return contains( x, root );
	    }

	    /**
	     * Make the tree logically empty.
	     */
	    public void makeEmpty( )
	    {
	        root = null;
	    }

	    /**
	     * Test if the tree is logically empty.
	     * @return true if empty, false otherwise.
	     */
	    public boolean isEmpty( )
	    {
	        return root == null;
	    }

	    /**
	     * Print the tree contents in sorted order.
	     */
	    public void printTree( )
	    {
	        if( isEmpty( ) )
	            System.out.println( "Empty tree" );
	        else
	            printTree( root );
	    }

	    /**
	     * Internal method to insert into a subtree.
	     * @param x the item to insert.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return new BinaryNode<>( x, null, null );
	        
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            t.left = insert( x, t.left );
	        else if( compareResult > 0 )
	            t.right = insert( x, t.right );
	        else
	            ;  // Duplicate; do nothing
	        return t;
	    }

	    /**
	     * Internal method to remove from a subtree.
	     * @param x the item to remove.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return t;   // Item not found; do nothing
	            
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            t.left = remove( x, t.left );
	        else if( compareResult > 0 )
	            t.right = remove( x, t.right );
	        else if( t.left != null && t.right != null ) // Two children
	        {
	            t.element = findMin( t.right ).element;
	            t.right = remove( t.element, t.right );
	        }
	        else
	            t = ( t.left != null ) ? t.left : t.right;
	        return t;
	    }

	    /**
	     * Internal method to find the smallest item in a subtree.
	     * @param t the node that roots the subtree.
	     * @return node containing the smallest item.
	     */
	    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return null;
	        else if( t.left == null )
	            return t;
	        return findMin( t.left );
	    }

	    /**
	     * Internal method to find the largest item in a subtree.
	     * @param t the node that roots the subtree.
	     * @return node containing the largest item.
	     */
	    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	    {
	        if( t != null )
	            while( t.right != null )
	                t = t.right;

	        return t;
	    }

	    /**
	     * Internal method to find an item in a subtree.
	     * @param x is item to search for.
	     * @param t the node that roots the subtree.
	     * @return node containing the matched item.
	     */
	    private boolean contains( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return false;
	            
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            return contains( x, t.left );
	        else if( compareResult > 0 )
	            return contains( x, t.right );
	        else
	            return true;    // Match
	    }

	    /**
	     * Internal method to print a subtree in sorted order.
	     * @param t the node that roots the subtree.
	     */
	    private void printTree( BinaryNode<AnyType> t )
	    {
	        if( t != null )
	        {
	            printTree( t.left );
	            System.out.println( t.element );
	            printTree( t.right );
	        }
	    }

	    /**
	     * Internal method to compute height of a subtree.
	     * @param t the node that roots the subtree.
	     */
	    private int height( BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return -1;
	        else
	            return 1 + Math.max( height( t.left ), height( t.right ) );    
	    }
	    
	    //******************* My own methodes:
	   
	    
	    
	    public AnyType ancestor( AnyType u, AnyType v )
	    {
	    	
	    	if(contains(u) & contains(v)){
	    		return ancestor( u, v, root ).element;
	    	}else
	    		return null;   
	    	
	    }
	    
	    
	    private BinaryNode<AnyType> ancestor(AnyType u, AnyType v, BinaryNode<AnyType> t){
	
	    	boolean signal = false;
	    	
	    	while(signal == false){
	    		
	    		int compareResult_u = u.compareTo(t.element);
	    		int compareResult_v = v.compareTo(t.element);
	    		
	    		if(compareResult_u==0 & compareResult_v >0   ||  compareResult_u==0 & compareResult_v <0  ){
	    			signal=true;
	    			return t;	    			
	    		}
	    		
	    		if(compareResult_v==0 & compareResult_u >0   ||  compareResult_v==0 & compareResult_u <0  ){
	    			signal=true;
	    			return t;	    			
	    		}
	    		
	    		if(compareResult_u ==0 & compareResult_v ==0){
	    			signal=true;
	    			return t;	
	    		}
	    		
	    		if( (compareResult_u > 0  &  compareResult_v < 0) || (compareResult_u < 0  &  compareResult_v > 0)  ){	    			
	    			signal=true;
	    			return t;
	    		}
	    		
	    		if(compareResult_u > 0){
	    			t = t.right;
	    		}
	    		
	    		if(compareResult_u < 0){
	    			t = t.left;
	    		}
	    		
	    	}
	    	
	       return t;
	    }
	    
	    
	    
	    public int distance( AnyType u, AnyType v )
	    {
	    	
	    	if(contains(u) & contains(v)){
	    		return  distance (u,v, ancestor(u, v, root) );
	    	}else
	    		return -1;   
	    	
	    }	    
	    
	    private int distance(AnyType u, AnyType v, BinaryNode<AnyType> t){   
	    	int dist=0;
	    	boolean signal=false;
	    	
	    	int compareResult_u =0;
	    	int compareResult_v =0;
	    	
	    	BinaryNode<AnyType> aux = t;
	    	
	    	if (u==v){
	    		return 0;
	    	}
	    	
	    	while(signal==false){
	    		
	    		compareResult_u = u.compareTo(t.element);
	    		
	    		if(compareResult_u == 0){
	    			//signal=true;
	    			break;
	    		}
	
	    		if(compareResult_u > 0){
	    			dist++;
	    			t = t.right;
	    		}
	    		
	    		if(compareResult_u < 0){
	    			dist++;
	    			t = t.left;
	    		}
	    	}
	    	
	    	while(signal==false){
	    		
	    		compareResult_v = v.compareTo(aux.element);
	    		
	    		if(compareResult_v == 0){
	    			//signal=true;
	    			break;
	    		}
	
	    		if(compareResult_v > 0){
	    			dist++;
	    			aux = aux.right;
	    		}
	    		
	    		if(compareResult_v < 0){
	    			dist++;
	    			aux = aux.left;
	    		}
	    	}	    	
	
	    	return dist;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    
	    // Basic node stored in unbalanced binary search trees
	    private static class BinaryNode<AnyType>
	    {
	            // Constructors
	        BinaryNode( AnyType theElement )
	        {
	            this( theElement, null, null );
	        }

	        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
	        {
	            element  = theElement;
	            left     = lt;
	            right    = rt;
	        }

	        AnyType element;            // The data in the node
	        BinaryNode<AnyType> left;   // Left child
	        BinaryNode<AnyType> right;  // Right child
	    }


	      /** The tree root. */
	    private BinaryNode<AnyType> root;

	    
	    


	}

	
	
	public static void main(String[] args) throws Exception {
		
		BinarySearchTree<Integer> Tree; 
        
		
		StringBuilder chain = new StringBuilder();
		String line;
		
		Scanner scan;
		scan = new Scanner(System.in);
		
		String datos[];		
		int casos = Integer.parseInt(scan.nextLine());	

		

		for (int contadorDeCasos=1; contadorDeCasos<=casos; contadorDeCasos++){
			
			String split[];
	    	split = scan.nextLine().split(" ");
			Tree = new BinarySearchTree<>( );
			
			int cantComandos = Integer.parseInt(split[0]);	
			System.out.println("Caso #"+contadorDeCasos+":");
			
			for (int contadorCasos =1; contadorCasos <= cantComandos; contadorCasos++){
					    split = scan.nextLine().split(" ");
						if (split[0].equals("agregar")){
							Tree.insert(  Integer.parseInt(split[1])  );
						}else if (split[0].compareTo("ancestro")==0){
							Integer answer =Tree.ancestor(  Integer.parseInt(split[1]), Integer.parseInt(split[2]) );
							if (answer == null){
								System.out.println("ancestro: -1");
							}else{
								System.out.println("ancestro: "+ answer);
							}
						}else if (split[0].equals("distancia")){
							System.out.println("distancia: "+ Tree.distance(Integer.parseInt(split[1]), Integer.parseInt(split[2])) );
						}	
			}
			
		}
	}
    }
