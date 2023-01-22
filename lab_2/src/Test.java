public class Test {
	public static void main(String [] args) {
		Rectangle r1 = new Rectangle((short) 0, (short) 0, (short) 1, (short) 1);
		Rectangle r2 = new Rectangle((short) 2, (short) 2);
		Rectangle r3 = new Rectangle();
		r1.rect_print();
		r2.rect_print();
		r3.rect_print();
		Rectangle r4 = r2.Union(r1);
		r4.rect_print();
	}
}