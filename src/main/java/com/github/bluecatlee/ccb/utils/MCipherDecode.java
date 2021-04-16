package com.github.bluecatlee.ccb.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.*;

public class MCipherDecode {

	
	static {
		if(Security.getProvider("BC")==null)
		{
			Security.addProvider(new BouncyCastleProvider());
		}
	}
	
	
	private String encryptKey = "12345678";
	
	public MCipherDecode(String key)
	{
		encryptKey = key.substring(0, 8);
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey.substring(0,8);
	}
	
	private static byte[] getSrcBytes(byte[] srcBytes, byte[] wrapKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException
	{
		SecretKeySpec key = new SecretKeySpec(wrapKey, "DES");

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding","BC");

		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] cipherText =  cipher.doFinal(srcBytes);


		return cipherText;
	}
	

	

	
	public static byte[] DecodeBase64String(String base64Src) throws IOException
	{
		BASE64Decoder de = new BASE64Decoder();
		byte[] base64Result = de.decodeBuffer(base64Src);
		return base64Result;

	}
	
	public String getDecodeString(String urlString) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException
	{
		String tempString = URLDecoder.decode(urlString, "iso-8859-1");
		String basedString = tempString.replaceAll(",", "+");
		byte[] tempBytes = DecodeBase64String(basedString);
		byte[] tempSrcBytes = getSrcBytes(tempBytes,encryptKey.getBytes("iso-8859-1"));
		return new String(tempSrcBytes,"iso-8859-1");
		
	}

	public static void main(String[] agrs){
		String key = "f6528d5c335b7092fc9ec1b3020111";
		String str = "梅九六|6214662020019275";
		String cipherdURL = "AWWo2KKeATj6XxRglo7uaR0yZ2QQtCW%2C";
		//使用MCipherDecode.java类中的getDecodeString(String urlString)方法进行解密，主要步骤如下：
		try {
			MCipherDecode mcd = new MCipherDecode(key);//设置密钥
			String decodedString = mcd.getDecodeString(cipherdURL);//解密
			byte[] tempByte = decodedString.getBytes("ISO-8859-1");
			decodedString = new String(tempByte,"GBK");
			System.out.println("decodedString-- " + decodedString);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	

}
