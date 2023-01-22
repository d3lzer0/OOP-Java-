class Lab_1_5 {
	public static void main (String [] args) {
		int a = Integer.parseInt(args[0]), fact = 1;
		if (a > 0) {
			for (int i = a; i > 0; i--) {
				fact *= i; 
			}
			System.out.println("Факториал числа " + a + " равен " + fact +".");
		} else {
			System.out.println("Введите число больше 0.");
		}
	}
}