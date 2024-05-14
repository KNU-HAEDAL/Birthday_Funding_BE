package team.haedal.gifticionfunding.jwt;




public class TokenDto {
    public static String from(String accessToken, String refreshToken){
        return "?accessToken="+accessToken
                + "&refreshToken="+refreshToken
                + "&grantType="+"Bearer";
    }
}
