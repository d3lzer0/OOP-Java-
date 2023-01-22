class Lab_1_6 {
	public static void main (String [] args) {
		int n = Integer.parseInt(args[0]);
		boolean prime[] = new boolean[n + 1];
        	for (int i = 0; i <= n; i++) {
            		prime[i] = true;
		}
        	for (int j = 2; j * j <= n; j++) {
            		if (prime[j]) {
                		for (int i = j * j; i <= n; i += j) {
                    			prime[i] = false;
				}
            		}
        	}
        	for (int i = 2; i <= n; i++) {
            		if (prime[i]) {
                		System.out.print(i + " ");
			}
        	}
    	}
}
