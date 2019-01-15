package flcxilove.common.tools;

import flcxilove.common.beans.CryptoBean;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 对称加解密公共处理类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class CryptoUtil {

    /**
     * 对称加解密算法信息Bean
     */
    private CryptoBean cryptoBean;

    /**
     * 字符串可用字符范围
     */
    private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CryptoUtil(CryptoBean cryptoBean) {
        this.cryptoBean = cryptoBean;
    }

    /**
     * BASE64Encoder 加密
     *
     * @param data 要加密的数据
     * @return 加密后的字符串
     */
    public static String encryptBASE64(byte[] data) {
        // BASE64Encoder encoder = new BASE64Encoder();
        // String encode = encoder.encode(data);
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Encoder
        Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(data);
        return encode;
    }

    /**
     * BASE64Decoder 解密
     *
     * @param data 要解密的字符串
     * @return 解密后的byte[]
     */
    public static String decryptBASE64(byte[] data) {
        // BASE64Decoder decoder = new BASE64Decoder();
        // byte[] buffer = decoder.decodeBuffer(data);
        // 从JKD 9开始rt.jar包已废除，从JDK 1.8开始使用java.util.Base64.Decoder
        Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        return new String(buffer);
    }

    /**
     * 加密处理
     *
     * @param target         加密对象
     * @param encodingFormat 编码格式
     * @param iv             CBC补充模式初始向量
     * @param keySpec        对称密钥
     * @return 加密后字符串
     * @throws Exception 处理异常
     */
    public String encrypt(String target, String encodingFormat, AlgorithmParameters iv, SecretKeySpec keySpec) throws Exception {

        Cipher cipher = Cipher.getInstance(this.buildCipher());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encrypted = cipher.doFinal(target.getBytes(encodingFormat));
        // 此处使用BASE64做转码。
        return decryptBASE64(encrypted);
    }

    /**
     * 解密处理
     *
     * @param target         解密对象
     * @param encodingFormat 编码格式
     * @param iv             CBC补充模式初始向量
     * @param keySpec        对称密钥
     * @return 解密后字符串
     * @throws Exception 处理异常
     */
    public String decrypt(String target, String encodingFormat, AlgorithmParameters iv, SecretKeySpec keySpec) throws Exception {

        Cipher cipher = Cipher.getInstance(this.buildCipher());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        // 先用base64解密
        byte[] decrypted = cipher.doFinal(decryptBASE64(target.getBytes()).getBytes());

        return new String(decrypted, encodingFormat);
    }

    /**
     * 密钥生成处理
     *
     * @param key 密钥生成参数
     * @return 对称密钥
     * @throws NoSuchAlgorithmException 算法缺失异常
     */
    public SecretKeySpec generateKey(String key) throws NoSuchAlgorithmException {

        // "AES"：请求的密钥算法的标准名称
        KeyGenerator kgen = KeyGenerator.getInstance(this.cryptoBean.getAlgorithm());

        // 256：密钥生成参数；securerandom：密钥生成器的随机源
        SecureRandom securerandom = new SecureRandom(this.digestKey(key));

        kgen.init(this.cryptoBean.getKey_digit(), securerandom);

        // 生成秘密（对称）密钥
        SecretKey secretKey = kgen.generateKey();

        // 返回基本编码格式的密钥
        byte[] enCodeFormat = secretKey.getEncoded();

        // 根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
        return new SecretKeySpec(enCodeFormat, this.cryptoBean.getAlgorithm());
    }

    /**
     * 初始向量生成处理
     *
     * @return 初始向量
     * @throws Exception 处理异常
     */
    public AlgorithmParameters generateIV() throws Exception {
        // AES加密参数（初始向量）
        AlgorithmParameters params = AlgorithmParameters.getInstance(this.cryptoBean.getAlgorithm());
        params.init(new IvParameterSpec(this.generateRandomString(this.cryptoBean.getIv_digit())));

        return params;
    }

    /**
     * 对称加密密钥摘要处理
     *
     * @param key 对称加密密钥
     * @return 对称加密密钥摘要
     */
    private byte[] digestKey(String key) {
        try {
            // 创建消息摘要算法
            MessageDigest digester = MessageDigest.getInstance(this.cryptoBean.getDigest());
            // 数据摘要处理
            digester.update(key.getBytes());

            return digester.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 指定长度随机字符串生成处理
     *
     * @param length 字符串长度
     * @return 指定长度随机字符串
     */
    private byte[] generateRandomString(Integer length) {
        StringBuilder randomStr = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            randomStr.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));
        }
        return randomStr.toString().getBytes();
    }

    /**
     * 构建加解密算法模式（算法/模式/填充）
     *
     * @return 算法模式
     */
    private String buildCipher() {

        // 对称加解密算法
        String algorithm = this.cryptoBean.getAlgorithm();
        // 对称加解密模式
        String mode = this.cryptoBean.getMode();
        // 对称加解密填充
        String padding = this.cryptoBean.getPadding();

        if (StringUtils.isNotEmpty(algorithm) && StringUtils.isNotEmpty(mode) && StringUtils.isNotEmpty(padding)) {
            return MessageFormat.format("{0}/{1}/{2}", algorithm, mode, padding);
        }

        return algorithm;
    }
}
