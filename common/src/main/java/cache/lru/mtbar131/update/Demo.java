package cache.lru.mtbar131.update;

public class Demo {
    public static void main(String args[]) {
	LFUCache<Integer, Integer> cache = new LFUCache<Integer, Integer>(10);

	cache.set(10,13);
	cache.set(3,17);
	cache.set(6,11);
	cache.set(10,5);
	cache.set(9,10);
	System.out.println(cache.get(13));
	cache.set(2,19);
	System.out.println(cache.get(2));
	System.out.println(cache.get(3));
	cache.set(5,25);
	System.out.println(cache.get(8));
	cache.set(9,22);
	cache.set(5,5);
	cache.set(1,30);
	System.out.println(cache.get(11));
	cache.set(9,12);
	System.out.println(cache.get(7));
	System.out.println(cache.get(5));
	System.out.println(cache.get(8));
	System.out.println(cache.get(9));
	cache.set(4,30);
	cache.set(9,3);
	System.out.println(cache.get(9));
	System.out.println(cache.get(10));
	System.out.println(cache.get(10));
	cache.set(6,14);
	cache.set(3,1);
	System.out.println(cache.get(3));
	cache.set(10,11);
	System.out.println(cache.get(8));
	cache.set(2,14);
	System.out.println(cache.get(1));
	System.out.println(cache.get(5));
	System.out.println(cache.get(4));
	cache.set(11,4);
	cache.set(12,24);
	cache.set(5,18);
	System.out.println(cache.get(13));
	cache.set(7,23);
	System.out.println(cache.get(8));
	System.out.println(cache.get(12));
	cache.set(3,27);
	cache.set(2,12);
	System.out.println(cache.get(5));
	cache.set(2,9);
	cache.set(13,4);
	cache.set(8,18);
	cache.set(1,7);
	System.out.println(cache.get(6));
	cache.set(9,29);
	cache.set(8,21);
	System.out.println(cache.get(5));
	cache.set(6,30);
	cache.set(1,12);
	System.out.println(cache.get(10));
	cache.set(4,15);
	cache.set(7,22);
	cache.set(11,26);
	cache.set(8,17);
	cache.set(9,29);
	System.out.println(cache.get(5));
	cache.set(3,4);
	cache.set(11,30);
	System.out.println(cache.get(12));
	cache.set(4,29);
	System.out.println(cache.get(3));
	System.out.println(cache.get(9));
	System.out.println(cache.get(6));
	cache.set(3,4);
	System.out.println(cache.get(1));
	System.out.println(cache.get(10));
	cache.set(3,29);
	cache.set(10,28);
	cache.set(1,20);
	cache.set(11,13);
	System.out.println(cache.get(3));
	cache.set(3,12);
	cache.set(3,8);
	cache.set(10,9);
	cache.set(3,26);
	System.out.println(cache.get(8));
	System.out.println(cache.get(7));
	System.out.println(cache.get(5));
	cache.set(13,17);
	cache.set(2,27);
	cache.set(11,15);
	System.out.println(cache.get(12));
	cache.set(9,19);
	cache.set(2,15);
	cache.set(3,16);
	System.out.println(cache.get(1));
	cache.set(12,17);
	cache.set(9,1);
	cache.set(6,19);
	System.out.println(cache.get(4));
	System.out.println(cache.get(5));
	System.out.println(cache.get(5));
	cache.set(8,1);
	cache.set(11,7);
	cache.set(5,2);
	cache.set(9,28);
	System.out.println(cache.get(1));
	cache.set(2,2);
	cache.set(7,4);
	cache.set(4,22);
	cache.set(7,24);
	cache.set(9,26);
	cache.set(13,28);
	cache.set(11,26);


    }
}
 



