package com.github.bluecatlee.cib.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FOXDesUtil {
    private static byte[] enKey = new byte[] { 1, 16, 2, 32, 51, 103, -119, -17 };

    private Cipher cipher;

    private SecretKey key;

    private String getPrefix() {
        return "{DES}";
    }

    public FOXDesUtil() throws Exception {
        instanceSimpleDESCrypto(enKey);
    }

    private void instanceSimpleDESCrypto(byte[] keyBuf) throws Exception {
        this.cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        this.key = new SecretKeySpec(keyBuf, "DES");
    }

    public String doEncrypt(String plainPwd) throws Exception {
        try {
            this.cipher.init(1, this.key);
            byte[] raw = this.cipher.doFinal(plainPwd.getBytes());
            String base64 = Base64.encode(raw);
            return String.valueOf(getPrefix()) + base64;
        } catch (Exception e) {
            throw new Exception("Do encrypt occurs Exception.[" + e.getMessage() + "]", e);
        }
    }

    public String doDecrypt(String cipherPwd) throws Exception {
        try {
            if (!cipherPwd.startsWith(getPrefix()))
                return cipherPwd;
            cipherPwd = cipherPwd.substring(getPrefix().length(), cipherPwd.length());
            this.cipher.init(2, this.key);
            byte[] raw = Base64.decode(cipherPwd);
            byte[] data = this.cipher.doFinal(raw);
            return new String(data);
        } catch (Exception e) {
            throw new Exception("Do decrypt occurs Exception.[" + e.getMessage() + "]", e);
        }
    }

    public static void main(String[] args) throws Exception {
        FOXDesUtil util = new FOXDesUtil();
        String encode = util.doEncrypt("a1111111");
        System.err.println("encode:" + encode);
        String decode = util.doDecrypt(encode);
        System.err.println("decode:" + decode);
    }
}
