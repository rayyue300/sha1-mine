import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Collections;

public class Mine {

	public static void main(String[] args) throws Exception {

		String studentID = "17047558d"; // Change it to your student ID
		int noOfLeadingZeros = 4; // Increment it to find a hash of more leading zeros
		String leadingZeros = String.join("", Collections.nCopies(noOfLeadingZeros, "0"));
		String h; // the hash value
		SecureRandom random = new SecureRandom();
		int nonce;
		long currentTime;
		long startTime = System.currentTimeMillis();
		/*
		 * Implement your code here
		 */
		h = "initial hash"; 		// Dummy Initialization
		nonce = 0;					// Dummy Initialization
		
		// Try to hash until h starts with some no. of 0s
		currentTime = startTime;	// Save the time when start hashing
		while ( !h.startsWith(leadingZeros) ) {
			// Generate a new nonce for new hash
			nonce = random.nextInt();
			h = hash(studentID, startTime, nonce);
		}	
		/**
		 * Implement your code end
		 */

		double duration = (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.println("Hash value found! " + h);
		System.out.println("Hashing my Student ID, " + studentID + ", current time, " + currentTime + ", and nonce, " + nonce + " with " + noOfLeadingZeros
				+ " leading zero(s) requires " + duration + " second(s).");
		
	}

	public static String hash(String studentID, long currentTime, int nonce) throws Exception {

		String h = null;
		/*
		 * Implement your code here
		 */

		// Concat of (1),(2),(3) as the input of SHA-1
		String input = studentID + currentTime + nonce;

		// Hash function (in: input, out: sb)
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		// Convert sb back to string for return
		h = sb.toString();
		 /**
		  * Implement your code end
		  */
		return h;

	}
}