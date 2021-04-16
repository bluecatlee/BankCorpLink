package com.github.bluecatlee.ccb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
   private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);
   private static final String[] strDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

   private static String byteToArrayString(byte bByte) {
      int iRet = bByte;
      if (bByte < 0) {
         iRet = bByte + 256;
      }

      int iD1 = iRet / 16;
      int iD2 = iRet % 16;
      return strDigits[iD1] + strDigits[iD2];
   }

   private static String byteToNum(byte bByte) {
      int iRet = bByte;
      LOGGER.info("iRet1={}", Integer.valueOf(bByte));
      if (bByte < 0) {
         iRet = bByte + 256;
      }

      return String.valueOf(iRet);
   }

   private static String byteToString(byte[] bByte) {
      StringBuffer sBuffer = new StringBuffer();

      for(int i = 0; i < bByte.length; ++i) {
         sBuffer.append(byteToArrayString(bByte[i]));
      }

      return sBuffer.toString();
   }

   public static String getMD5Code(String strObj) {
      String resultString = null;

      try {
         resultString = strObj;
         MessageDigest md = MessageDigest.getInstance("MD5");

         try {
            resultString = byteToString(md.digest(strObj.getBytes("utf-8")));
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
         }
      } catch (NoSuchAlgorithmException var5) {
         LOGGER.error("ERROR:", var5);
      }

      return resultString;
   }
}
