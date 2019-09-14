package com.sbia.dataStructuresDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 红黑树
 * <AnyType extends Comparable<? super AnyType>>:数组是协变的；泛型集合不是协变得；
 *      假设Person继承了Comparable，因为Comparable是个泛型，所以Person IS A Comparable<Person>;
 *      Student继承了Person,那么Student IS A Comparable<Person>,而且Student IS NOT A Comparable<Student>;
 *      所以AnyType extends Comparable<T>,T是AnyType的父类；
 */
public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {

    /**
     * 构造一棵空树
     */
    public RedBlackTree( ){
        nullNode = new RedBlackNode<>( null );
        nullNode.left = nullNode.right = nullNode;
        header      = new RedBlackNode<>( null );
        header.left = header.right = nullNode;
    }

    /**
     * 红黑树节点
     */
    private static class RedBlackNode<AnyType>{

        RedBlackNode( AnyType theElement ){
            this( theElement, null, null );
        }

        RedBlackNode( AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            color    = RedBlackTree.BLACK;
        }

        AnyType               element;    // 节点中的数据
        RedBlackNode<AnyType> left;       // 左儿子
        RedBlackNode<AnyType> right;      // 右儿子
        int                   color;      // 颜色
    }

    private static final int BLACK = 1;    // 红黑树节点颜色；黑色是1，红色是0；
    private static final int RED   = 0;

    private RedBlackNode<AnyType> header;       //根标记，右儿子指向真正的根
    private RedBlackNode<AnyType> nullNode;     //空节点标记

    private RedBlackNode<AnyType> current;      //当前节点
    private RedBlackNode<AnyType> parent;       //父节点
    private RedBlackNode<AnyType> grand;        //祖父节点
    private RedBlackNode<AnyType> great;        //曾祖父节点

    /**
     * 中序遍历打印树
     */
    public void printTree( ){
        if( isEmpty( ) )
            System.out.println( "空树" );
        else
            printTree( header.right );
    }
    private void printTree( RedBlackNode<AnyType> t ){
        // 层序遍历
        List<RedBlackNode> list=new ArrayList<>();
        list.add(t);
        while (list.size()!=0){
            RedBlackNode current=list.get(0);
            System.out.println(current.element+","+(current.color==1?"黑色":"红色"));
            if(current.left!=nullNode){
                list.add(current.left);
            }
            if(current.right!=nullNode){
                list.add(current.right);
            }
            list.remove(0);
        }

        /*
        if( t != nullNode ){
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
        */
    }
    /**
     * 检测数是否为空
     * @return  空，返回true；不空，返回false；
     */
    public boolean isEmpty( ){
        return header.right == nullNode;
    }

    /**
     * 将当前数据与目标数据进行比较
     * @param item      当前数据
     * @param t         目标节点
     * @return      如果目标节点是根标记，始终返回1（大于）；否则调用数据类型本身的compareTo（）方法；
     */
    private int compare( AnyType item, RedBlackNode<AnyType> t ){
        if( t == header )
            return 1;
        else
            return item.compareTo( t.element );
    }

    /**
     * 自顶向下插入过程
     * @param item 插入节点数据
     */
    public void insert( AnyType item ){
        current = parent = grand = header;
        nullNode.element = item;

        while( compare( item, current ) != 0 ){
            //整体向下移动一个节点
            great = grand;
            grand = parent;
            parent = current;
            current = compare( item, current ) < 0 ?
                    current.left : current.right;

            // 如果当前节点有两个红儿子，进行转换
            if( current.left.color == RED && current.right.color == RED )
                handleReorient( item );
        }

        // 当前元素已存在，插入失败
        if( current != nullNode )
            return;
        current = new RedBlackNode<>( item, nullNode, nullNode );

        // 把当前节点挂到父节点上
        if( compare( item, parent ) < 0 )
            parent.left = current;
        else
            parent.right = current;
        handleReorient( item );
    }
    private void handleReorient( AnyType item )
    {
        // 将当前节点颜色变为红色，两个儿子节点变为黑色
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        //如果当前节点的父节点是红色，进行旋转
        if( parent.color == RED ){
            grand.color = RED;
            if( ( compare( item, grand ) < 0 ) !=
                    ( compare( item, parent ) < 0 ) )
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate( item, great );
            current.color = BLACK;
        }
        //令根节点为黑色
        header.right.color = BLACK;
    }
    private RedBlackNode<AnyType> rotate( AnyType item, RedBlackNode<AnyType> parent )
    {
        if( compare( item, parent ) < 0 )
            return parent.left = compare( item, parent.left ) < 0 ?
                    rotateWithLeftChild( parent.left )  :  // LL
                    rotateWithRightChild( parent.left ) ;  // LR
        else
            return parent.right = compare( item, parent.right ) < 0 ?
                    rotateWithLeftChild( parent.right ) :  // RL
                    rotateWithRightChild( parent.right );  // RR
    }
    private RedBlackNode<AnyType> rotateWithLeftChild( RedBlackNode<AnyType> k2 )
    {
        RedBlackNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    private RedBlackNode<AnyType> rotateWithRightChild( RedBlackNode<AnyType> k1 )
    {
        RedBlackNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    public static void main(String[] args){
        RedBlackTree<Integer> t = new RedBlackTree<>( );

        final int NUMS = 400000;
        final int GAP  =  35461;

        for( int i = 1; i < 9; i ++ )
            t.insert( i );

        t.printTree();

    }
}
