package wade.wei.utils;

import io.jsonwebtoken.*;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import wade.wei.entity.User;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author bystander
 * @date 2018/10/1
 */
public class JwtUtils {

    private static final String USER_KEY = "user";

    /**
     * 生成Token
     *
     * @param user
     * @param privateKey
     * @param expireMinutes
     * @return
     */
    public static String generateToken(User user, PrivateKey privateKey, int expireMinutes) {
        return Jwts.builder()
                .claim(USER_KEY, JsonMapperUtil.obj2String(user))
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 生成Token
     *
     * @param user
     * @param privateKey
     * @param expireMinutes
     * @return
     * @throws Exception
     */
    public static String generateToken(User user, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(USER_KEY, user)
                .setExpiration(DateTime.now().plus(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.ES256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析Token
     *
     * @param publicKey
     * @param token
     * @return
     */
    public static Jws<Claims> parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }


    /**
     * 公钥解析Token
     *
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parseToken(byte[] publicKey, String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }


    /**
     * 从Token中获取用户信息（使用公钥解析）
     *
     * @param publicKey
     * @param token
     * @return
     */
    public static User getUser(PublicKey publicKey, String token) {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        return JsonMapperUtil.string2Obj(
                body.get(USER_KEY).toString(),
                new TypeReference<User>() {
                });
    }

    /**
     * 从Token中获取用户信息（使用公钥解析）
     *
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static User getUser(byte[] publicKey, String token) throws Exception {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        return JsonMapperUtil.string2Obj(
                body.get(USER_KEY).toString(),
                new TypeReference<User>() {
                });
    }
}
