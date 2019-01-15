package flcxilove.common.beans;

import flcxilove.common.tools.CryptoUtil;

/**
 * 对称加解密算法信息Bean
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public abstract class CryptoBean {

    /**
     * 对称加密-算法
     */
    private String algorithm;

    /**
     * 对称加密-模式
     */
    private String mode;

    /**
     * 对称加密-填充
     */
    private String padding;

    /**
     * 摘要算法
     */
    private String digest;

    /**
     * 密钥位数
     */
    private int key_digit;

    /**
     * 初始向量位数
     */
    private int iv_digit;

    /**
     * 对称加密处理入口
     *
     * @return 对称加密处理类
     */
    abstract CryptoUtil cryptoUtil();

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getKey_digit() {
        return key_digit;
    }

    public void setKey_digit(int key_digit) {
        this.key_digit = key_digit;
    }

    public int getIv_digit() {
        return iv_digit;
    }

    public void setIv_digit(int iv_digit) {
        this.iv_digit = iv_digit;
    }
}
