package ru.kpekepsalt;

import java.math.BigInteger;
import java.util.*;

public class Utils {

    /**
     * Randomly negates number
     */
    public static BigInteger randomSign(BigInteger a)
    {
        Random rnd = new Random(System.currentTimeMillis()+a.longValue());
        if(!rnd.nextBoolean())
        {
            return a.negate();
        }
        return a;
    }

    /**
     * Generate random BigInteger
     * @param b Length in bits
     */
    public static BigInteger randomInt(int b)
    {
        return randomSign(
                BigInteger.probablePrime(
                        b,
                        new Random(
                                System.currentTimeMillis()
                        )
                )
        );
    }

    /**
     * Reverse number (123 => 321)
     */
    public static BigInteger reverse(BigInteger a)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(a.abs().toString());
        builder.reverse();
        return new BigInteger(builder.toString());
    }

    /**
     * Sum of multiplication of all components of array
     */
    public static BigInteger linearCombination(BigInteger[] a)
    {
        BigInteger result = BigInteger.ONE;
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a.length;j++)
            {
                a[i] = reverse(a[i]);
                a[j] = reverse(a[j]);
                result = result.add(a[i].multiply(a[j]));
            }
        }
        return result;
    }

    /**
     *Gauss method for system solving
     * @param Matrix Extended matrix
     */
    public static BigInteger[] GaussMethodFraction(BigInteger[][] Matrix) throws ArithmeticException {
        Fraction[][] M = Fraction.toFractionMatrix(Matrix);
        int rows = M.length - 1;
        int columns = M[0].length - 1;
        int b = 0;
        if (columns - rows > 1)
            throw new ArithmeticException("Wrong matrix");
        if (rows - columns >= 0) throw  new ArithmeticException("Wrong matrix");
        while (b < columns) {
            if (M[b][0].abs().isZero()) b++;
            else break;
        }
        if (b > 0 && b < columns) {
            Fraction[] t = M[0];
            M[0] = M[b];
            M[b] = t;
        }
        if (b >= columns) {
            throw new ArithmeticException("System can't be solved");
        }
        for (int k = 0; k < rows; k++) {
            for (int i = k + 1; i <= rows; i++) {
                Fraction t = M[i][k].divide(M[k][k]);
                M[i][columns] = M[i][columns].subtract(
                        t.multiply(
                                M[k][columns]
                        )
                );
                for (int j = k; j <= rows; j++) {
                    M[i][j] = M[i][j].subtract(
                            t.multiply(
                                    M[k][j]
                            )
                    );
                }
            }
        }
        Fraction[] x = new Fraction[rows + 1];
        x[rows] = M[rows][columns].divide(M[rows][columns - 1]);
        for (int k = rows - 1; k > -1; k--) {
            Fraction s = new Fraction(BigInteger.ZERO, BigInteger.ONE);
            for (int j = k + 1; j <= rows; j++) {
                s = s.add(M[k][j].multiply(x[j]));
            }
            Fraction xk = M[k][columns].subtract(s).divide(M[k][k]);
            x[k] = xk;
        }
        BigInteger[] r = new BigInteger[x.length];
        for(int i=0;i<r.length;i++)
        {
            r[i] = x[i].toInteger();
        }
        return r;
    }

    /**
     * XNOR operation
     */
    public static int xnor(int a, int b) {
        return ~(a ^ b);
    }

    /**
     * Encode string with XNOR operation
     * @param pText Text to encode
     * @param pKey Key
     */
    public static byte[] encodeXNOR(String pText, String pKey) {
        byte[] txt = pText.getBytes();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) xnor(txt[i],key[i % key.length]);
        }
        return res;
    }

    /**
     * Decode byte array with XNOR
     * @param pText Bytes to decode
     * @param pKey Key
     */
    public static String decodeXNOR(byte[] pText, String pKey) {
        byte[] res = new byte[pText.length];
        byte[] key = pKey.getBytes();

        for (int i = 0; i < pText.length; i++) {
            res[i] = (byte) xnor(pText[i], key[i % key.length]);
        }
        return new String(res);
    }

    /**
     * Base64 encoding
     */
    public static String bytesToBase64(byte[] b)
    {
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * Converts list of encrypted bigramms to string
     */
    public static <T> String listOfArraysToString(List<T[]> list)
    {
        StringBuilder builder = new StringBuilder();
        list.forEach(x -> {
            Arrays.stream(x).forEach(xk -> {
                builder.append(xk.toString());
                builder.append("/");
            });
        });
        return builder.toString();
    }

    /**
     * Converts string of encrypted bigramms to list
     * @param sep Separator
     */
    public static List<BigInteger[]> stringToBigIntegerArray(String text, String sep) throws NumberFormatException
    {
        String[] arr = text.split(sep);
        List<BigInteger[]> list = new ArrayList<>();
        for(int i=0;i<arr.length-1;i+=2)
        {
            list.add(new BigInteger[]{
                    new BigInteger(arr[i]),
                    new BigInteger(arr[i+1])
            });
        }
        return list;
    }
}
