class Lab_1_4 {
	public static void main (String [] args) {
		int a = 1, b = 1;
		System.out.print(a + " " + b + " ");
		for (byte i = 0; i < 25; i++) {
			b = a + b;
			a = b - a;
			System.out.print(b + " ");
		}
	}
}