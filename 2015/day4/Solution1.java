import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Solution1 {
    private static final String INPUT_STRING = "ckczppom";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        int i = 0;
        for (i = 0; ; i++) {
            md5.update((INPUT_STRING + i).getBytes());
            byte[] hexArray = md5.digest();

            String hash = "";
            for (byte hex : hexArray) hash += String.format("%02X", hex);

            if (hash.startsWith("00000")) break;
        }

        System.out.println(i);
    }
}
