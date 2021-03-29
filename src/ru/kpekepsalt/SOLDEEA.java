package ru.kpekepsalt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SOLDEEA {

    private SOLDEEA()
    {

    }

    /**
     * Key generation for encryption
     * @param b Key length in bits
     */
    public static BigInteger[][] createKey(int b)
    {
        return new BigInteger[][]
                {
                        new BigInteger[]{Utils.randomInt(b), Utils.randomInt(b), Utils.randomInt(b)},
                        new BigInteger[]{Utils.randomInt(b), Utils.randomInt(b), Utils.randomInt(b)}
                };
    }

    /**
     * Encryption of bigramm
     */
    public static BigInteger[] encrypt(BigInteger[][] key, int x, int y)
    {
        BigInteger[] c = new BigInteger[]
                {
                        key[0][0], key[0][1], key[0][2],
                        key[1][0], key[1][1], key[1][2]
                };
        BigInteger M = Utils.linearCombination(c);
        BigInteger[] result = new BigInteger[2];
        result[0] = key[0][0].multiply(key[0][2].add(BigInteger.valueOf(x)))
                .add(key[1][0].multiply(key[1][2].add(BigInteger.valueOf(y))));
        result[0] = M.xor(result[0]);
        result[1] = key[0][1].multiply(key[0][2].add(BigInteger.valueOf(x)))
                .add(key[1][1].multiply(key[1][2].add(BigInteger.valueOf(y))));
        result[1] = M.xor(result[1]);
        return result;
    }

    /**
     * Decryption of bigramm
     */
    public static BigInteger[] decrypt(BigInteger[][] key, BigInteger[] b)
    {
        BigInteger[] c = new BigInteger[]
                {
                        key[0][0], key[0][1], key[0][2],
                        key[1][0], key[1][1], key[1][2]
                };
        BigInteger M = Utils.linearCombination(c);
        b[0] = b[0].xor(M);
        b[1] = b[1].xor(M);

        BigInteger[][] matrix = new BigInteger[][]
                {
                        {key[0][0], key[1][0], b[0]},
                        {key[0][1], key[1][1], b[1]}
                };
        BigInteger[] result = Utils.GaussMethodFraction(matrix);
        BigInteger[] bigram = new BigInteger[2];
        bigram[0] = result[0].subtract(key[0][2]);
        bigram[1] = result[1].subtract(key[1][2]);
        return bigram;
    }

    /**
     * Encryption of text
     */
    public static List<BigInteger[]> encrypt(String text, BigInteger[][] key)
    {
        List<BigInteger[]> list = new ArrayList<>();
        if(text.length() % 2 != 0)
        {
            text = text + " ";
        }
        for(int k=0;k<text.length();k+=2)
        {
            list.add(encrypt(key, text.charAt(k), text.charAt(k+1)));
        }
        return list;
    }

    /**
     * Decryption of list of bigramms
     */
    public static String decrypt(List<BigInteger[]> encrypted, BigInteger[][] key)
    {
        StringBuilder builder = new StringBuilder();
        encrypted.forEach(x -> {
            BigInteger[] bigram = decrypt(key, x);
            builder.append((char)bigram[0].intValue());
            builder.append((char)bigram[1].intValue());
        });
        return builder.toString();
    }

    /**
    *Full encryption with XNOR
     **/
    public static byte[] encryption(String text, BigInteger[][] key)
    {
        StringBuilder strKeyBuilder = new StringBuilder();
        Arrays.stream(key).forEach(x -> strKeyBuilder.append(x[0]).append(x[1]).append(x[2]));
        return Utils.encodeXNOR(
                Utils.listOfArraysToString(
                        encrypt(text, key)
                ),
                strKeyBuilder.toString()
        );
    }
    /**
     *Full decryption with XNOR
     **/
    public static String decryption(byte[] text, BigInteger[][] key, String sep)
    {
        StringBuilder strKeyBuilder = new StringBuilder();
        Arrays.stream(key).forEach(x -> strKeyBuilder.append(x[0]).append(x[1]).append(x[2]));
        String SOLDEEA = Utils.decodeXNOR(text, strKeyBuilder.toString());
        return decrypt(
                Utils.stringToBigIntegerArray(SOLDEEA, sep),
                key
        );
    }

}
