class Lab_1_3 {
	public static void main (String [] args) {
		int i, j;
		for (i = args.length - 1; i >= 0; i--) {
			for (j = args[i].length() - 1; j >= 0; j--) {
				System.out.print(args[i].charAt(j));
			}
			System.out.print(' ');
		}
	}
}