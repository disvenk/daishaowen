package com.daishaowen.test.chinaMobile.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author luyao
 * @date 2015年10月28日 Sha1 Util
 */
public class Sha1Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sha1Util.class);

    public static String Encrypt(final String strSrc) {

        MessageDigest md = null;
        String strDes = null;

        final byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.error("Sha1Util -Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(final byte[] bts) {

        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
