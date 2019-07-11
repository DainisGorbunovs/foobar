package encrypted_message;


import java.util.Base64;

public class Decrypt {
    static String encryptMessage(String decryptedMessage, String key) {
        byte[] byteArray = decryptedMessage.getBytes();

        for (int index = 0; index < byteArray.length; ++index) {
            byte symbol = (byte) (byteArray[index] ^ key.charAt(index % key.length()));
            byteArray[index] = symbol;
        }

        return Base64.getEncoder().encodeToString(byteArray);
    }

    static String decryptMessage(String encryptedMessage, String key) {
        byte[] byteArray = Base64.getDecoder().decode(encryptedMessage);

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < byteArray.length; ++index) {
            char symbol = (char) (byteArray[index] ^ key.charAt(index % key.length()));
            stringBuilder.append(symbol);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String encryptedMessage = "NUYeEE0vBAAHSUFXRWkGHwBPOEZfVEkCAgkiBAwCWylGU05ORggWOgQICEsoRl9USQQLAyETGRYJ" +
                "bFtTUwcPDhcrBQQHQilGX1RJAA4NJwQbAEMpDwdTTltNQjsPAQpNJwQXU0JBShcvAw8MWj9GU05O" +
                "Rh4EKARKSQ5rBxwbSUFXRWkWBAsPaxw=";

        String key = "Name.Lastname";

        String decoded = decryptMessage(encryptedMessage, key);
        System.out.println(decoded);

        String encoded = encryptMessage(decoded, "Name.Lastname");
        System.out.println(encoded);
    }
}
