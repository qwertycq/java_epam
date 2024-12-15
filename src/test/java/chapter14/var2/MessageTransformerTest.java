package chapter14.var2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTransformerTest {

    @Test
    void testEncrypt() {
        String originalMessage = "HelloWorld";
        String encryptedMessage = MessageTransformer.encrypt(originalMessage);
        assertEquals("KhoorZruog", encryptedMessage);
    }

    @Test
    void testDecrypt() {
        String encryptedMessage = "KhoorZruog";
        String decryptedMessage = MessageTransformer.decrypt(encryptedMessage);
        assertEquals("HelloWorld", decryptedMessage);
    }

    @Test
    void testEncryptAndDecrypt() {
        String originalMessage = "JavaTest123";
        String encryptedMessage = MessageTransformer.encrypt(originalMessage);
        String decryptedMessage = MessageTransformer.decrypt(encryptedMessage);
        assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    void testEdgeCases() {
        assertEquals("", MessageTransformer.encrypt(""));
        assertEquals("", MessageTransformer.decrypt(""));
        assertEquals("1234567890", MessageTransformer.encrypt("1234567890"));
    }
}
