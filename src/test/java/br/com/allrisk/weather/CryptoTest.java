package br.com.allrisk.weather;

import br.com.allrisk.weather.core.CryptoUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CryptoTest {

    @Test
    public void givenPlainTextWhenEncryptedAndThenDecryptedShouldReturnOriginalText() throws Exception {
        String key = CryptoUtils.generateKey();

        String originalSecret = "mySecretValue";

        String encryptedSecret = CryptoUtils.encrypt(originalSecret, key);

        String decrypted = CryptoUtils.decrypt(encryptedSecret, key);

        assertNotNull(encryptedSecret);
        assertEquals(originalSecret, decrypted);
    }

}
