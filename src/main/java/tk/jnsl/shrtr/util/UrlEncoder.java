package tk.jnsl.shrtr.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class UrlEncoder {
    public static String encodeUrl(String url) {
        HashFunction hashFunction = Hashing.murmur3_32_fixed();
        return hashFunction.hashString(url, StandardCharsets.UTF_8).toString();
    }
}
