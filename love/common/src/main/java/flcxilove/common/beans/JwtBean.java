package flcxilove.common.beans;

import java.util.Date;

/**
 * JWT信息Bean
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class JwtBean {

    /**
     * JWT签发者
     */
    private String iss;

    /**
     * JWT所面向的用户
     */
    private String sub;

    /**
     * 接收JWT的一方
     */
    private String aud;

    /**
     * JWT的过期时间，这个过期时间必须要大于签发时间
     */
    private Date exp;

    /**
     * 定义在什么时间之前，该JWT都是不可用的
     */
    private Date nbf;

    /**
     * JWT的签发时间
     */
    private Date iat;

    /**
     * JWT的唯一身份标识(主要用来作为一次性token,从而回避重放攻击)
     */
    private String id;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Date getNbf() {
        return nbf;
    }

    public void setNbf(Date nbf) {
        this.nbf = nbf;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
