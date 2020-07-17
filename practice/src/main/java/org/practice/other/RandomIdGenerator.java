package org.practice.other;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author yeyulin
 * @description:
 * @date 2020/6/29 10:44
 **/
public class RandomIdGenerator implements IdGenerator {
    private static Logger log = Logger.getLogger("RandomIdGenerator");

    public String generate() {
        String id = "";
        try {
            String hostName = getLastFieldOfHostName();
            String randomString = generateRandomAlphameric(8);
            id = String.format("%s-%d-%s", hostName,
                    System.currentTimeMillis(), randomString);
        } catch (Exception e) {
            log.warning("RandomIdGenerator Exception:" + e);
        }
        return id;
    }

    private String getLastFieldOfHostName() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
        } catch (UnknownHostException e) {
            log.warning("Failed to get the host name:" + e);
        }
        return hostName;

    }

    private String generateRandomAlphameric(Integer length) {
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit=randomAscii>='0' && randomAscii<='9';
            boolean isUppercase=randomAscii>='A' && randomAscii<='Z';
            boolean isLowercase=randomAscii>='a' && randomAscii<='z';
            if(isDigit || isUppercase || isLowercase){
                randomChars[count]=(char)randomAscii;
                count++;
            }
        }
        return new String(randomChars);
    }

    public static void main(String[] args) {
        RandomIdGenerator randomIdGenerator=new RandomIdGenerator();
        String generate = randomIdGenerator.generate();
        System.out.println(generate);
        // 48  57
        for (int i = 48; i <= 57; i++) {
            System.out.print((char)i);
        }
        System.out.println();
        // 65 90
        for (int i = 65; i <= 90; i++) {
            System.out.print((char)i);
        }
        System.out.println();
        //97 122
        for (int i = 97; i <= 122; i++) {
            System.out.print((char)i);
        }
        System.out.println();
    }
}
