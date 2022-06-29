package encode_and_decode_tinyurl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/6/29
 */
public class Codec {
    private Map<String,String> urlMap;
    private String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Encodes a URL to a shortened URL.
    public Codec(){
        urlMap = new HashMap<>();
    }
    public String encode(String longUrl) {
        char[] shorts = new char[6];
        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = str.charAt(new Random().nextInt(62));
        }
        String shortSuffix = new String(shorts);
        if (urlMap.containsKey(shortSuffix)){
            return encode(longUrl);
        }
        urlMap.put(shortSuffix,longUrl);
        return "http://tinyurl.com/" + shortSuffix;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl.length() < 6){
            return null;
        }
        String shortSuffix =shortUrl.substring(shortUrl.length()-6);
        if (urlMap.containsKey(shortSuffix)){
            return urlMap.get(shortSuffix);
        }
        return null;
    }

    public static void main(String[] args) {
        Codec obj = new Codec();
        String shortUrl = obj.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(shortUrl);
        System.out.println(obj.decode(shortUrl));

        String shortUrl1 = obj.encode("https://leetcode.com/problems/design-tinyurl11");
        System.out.println(shortUrl1);
        System.out.println(obj.decode(shortUrl1));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
