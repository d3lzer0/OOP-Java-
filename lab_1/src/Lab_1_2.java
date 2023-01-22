class Lab_1_2 {
	public static void main (String [] args) {
		String arr[] = new String[500];
		short i;
		for (i = 1; i < arr.length + 1; i++) {
			arr[i-1] = Short.toString(i);
			if ((i % 5 == 0) & (i % 7 == 0)) {
				arr[i - 1] = "fizzbuzz";
			} else {
				if (i % 5 == 0) {
					arr[i - 1] = "fizz";
				} else {
					if (i % 7 == 0) {
						arr[i - 1] = "buzz";
					}
				}
			}
			System.out.println(arr[i - 1]);
		}
	}
}