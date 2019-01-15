package flcxilove.auth.api.response.data;

/**
 * 消息主体：获取JWT
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class FetchTokenData {

    /**
     * JWT
     */
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
