package flcxilove.auth.api.request;

/**
 * 请求消息：获取JWT
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class FetchTokenRequestMessage {

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String secretKey;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
