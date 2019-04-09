package cn.hacz.edu.util;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/3 时间:20:46
 * @JDK 1.8
 * @Description 功能模块：RSA
 */
public class RSAUtils {
    public static String data = "hello world";

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = genKeyPair(1024);

        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));

        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));

        // 公钥加密
        byte[] encryptedBytes = encrypt(data.getBytes(), publicKey);
        System.out.println("加密后乱码：" + new String(encryptedBytes)); //打印出来是乱码

        String encoded = Base64.encodeBase64String(encryptedBytes); // Base64编码
        System.out.println("加密后_Base64编码：" + encoded);

        // 私钥解密
        byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
        System.out.println("解密后：" + new String(decryptedBytes));
    }

    // 生成密钥对
    public static KeyPair genKeyPair(int keyLength) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    // 公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    // 私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 字符串转为公钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 字符串转私钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
