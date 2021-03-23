package ru.kpekepsalt;

import java.math.BigInteger;
import java.util.*;

public class Main {

    private final static int BIT_LENGTH = 32;

    public static void main(String[] args) {
        /*
            *Encryption of bigramm with the greatest values
         */
        System.out.println("========BIGRAM ENCRYPTION========");
        char t = 65535;
        char d = 65535;
        String text = t+""+d;
        int[] toEncryption = new int[]
                {
                        text.charAt(0),
                        text.charAt(1)
                };
        System.out.println("PLAIN TEXT: "+text);
        System.out.println("CHAR TEXT: "+Arrays.toString(toEncryption));

        BigInteger[][] key = generateKey(BIT_LENGTH);
        /*
        BigInteger[][] key = new BigInteger[][]
                {
                        {BigInteger.valueOf(-4193763878L), BigInteger.valueOf(-3531458023L), BigInteger.valueOf(2446681631L)},
                        {BigInteger.valueOf(3456383597L), BigInteger.valueOf(-2556928529L), BigInteger.valueOf(-4185305983L)}
                };

         */
        System.out.println("KEY: "+Arrays.deepToString(key));

        BigInteger[] encrypted = encryptBigramWithSystem(key, toEncryption[0], toEncryption[1]);
        System.out.println("ENCRYPTED: "+Arrays.toString(encrypted));

        BigInteger[] decrypted = decryptBigramWithSystem(key, encrypted);
        System.out.println("DECRYPTED: "+Arrays.toString(decrypted));
        System.out.println("DECRYPTED TEXT: "+(char)decrypted[0].intValue()+" "+(char)decrypted[1].intValue());

        /*
            *Encryption of long text
         */

        System.out.println("\n========TEXT ENCRYPTION========");
        String longText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut eros et nisl sagittis vestibulum. Nullam nulla eros, ultricies sit amet, nonummy id, imperdiet feugiat, pede. Sed lectus. Donec mollis hendrerit risus. Phasellus nec sem in justo pellentesque facilisis. Etiam imperdiet imperdiet orci. Nunc nec neque. Phasellus leo dolor, tempus non, auctor et, hendrerit quis, nisi. Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor congue, elit erat euismod orci, ac placerat dolor lectus quis orci. Phasellus consectetuer vestibulum elit. Aenean tellus metus, bibendum sed, posuere ac, mattis non, nunc. Vestibulum fringilla pede sit amet augue. In turpis. Pellentesque posuere. Praesent turpis. Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc, eu sollicitudin urna dolor sagittis lacus. Donec elit libero, sodales nec, volutpat a, suscipit non, turpis. Nullam sagittis. Suspendisse pulvinar, augue ac venenatis condimentum, sem libero volutpat nibh, nec pellentesque velit pede quis nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce id purus. Ut varius tincidunt libero. Phasellus dolor. Maecenas vestibulum mollis diam. Pellentesque ut neque. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In dui magna, posuere eget, vestibulum et, tempor auctor, justo. In ac felis quis tortor malesuada pretium. Pellentesque auctor neque nec urna. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Aenean viverra rhoncus pede. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut non enim eleifend felis pretium feugiat. Vivamus quis mi. Phasellus a est. Phasellus magna. In hac habitasse platea dictumst. Curabitur at lacus ac velit ornare lobortis. Curabitur a felis in nunc fringilla tristique. Morbi mattis ullamcorper velit. Phasellus gravida semper nisi. Nullam vel sem. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Sed hendrerit. Morbi ac felis. Nunc egestas, augue at pellentesque laoreet, felis eros vehicula leo, at malesuada velit leo quis pede. Donec interdum, metus et hendrerit aliquet, dolor diam sagittis ligula, eget egestas libero turpis vel mi. Nunc nulla. Fusce risus nisl, viverra et, tempor et, pretium in, sapien. Donec venenatis vulputate lorem. Morbi nec metus. Phasellus blandit leo ut odio. Maecenas ullamcorper, dui et placerat feugiat, eros pede varius nisi, condimentum viverra felis nunc et lorem. Sed magna purus, fermentum eu, tincidunt eu, varius ut, felis. In auctor lobortis lacus. Quisque libero metus, condimentum nec, tempor a, commodo mollis, magna. Vestibulum ullamcorper mauris at ligula. Fusce fermentum. Nullam cursus lacinia erat. Praesent blandit laoreet nibh. Fusce convallis metus id felis luctus adipiscing. Pellentesque egestas, neque sit amet convallis pulvinar, justo nulla eleifend augue, ac auctor orci leo non est. Quisque id mi. Ut tincidunt tincidunt erat. Etiam feugiat lorem non metus. Vestibulum dapibus nunc ac augue. Curabitur vestibulum aliquam leo. Praesent egestas neque eu enim. In hac habitasse platea dictumst. Fusce a quam. Etiam ut purus mattis mauris sodales aliquam. Curabitur nisi. Quisque malesuada placerat nisl. Nam ipsum risus, rutrum vitae, vestibulum eu, molestie vel, lacus. Sed augue ipsum, egestas nec, vestibulum et, malesuada adipiscing, dui. Vestibulum facilisis, purus nec pulvinar iaculis, ligula mi congue nunc, vitae euismod ligula urna in dolor. Mauris sollicitudin fermentum libero. Praesent nonummy mi in odio. Nunc interdum lacus sit amet orci. Vestibulum rutrum, mi nec elementum vehicula, eros quam gravida nisl, id fringilla neque ante vel mi. Morbi mollis tellus ac sapien. Phasellus volutpat, metus eget egestas mollis, lacus lacus blandit dui, id egestas quam mauris ut lacus. Fusce vel dui. Sed in libero ut nibh placerat accumsan. Proin faucibus arcu quis ante. In consectetuer turpis ut velit. Nulla sit amet est. Praesent metus tellus, elementum eu, semper a, adipiscing nec, purus. Cras risus ipsum, faucibus ut, ullamcorper id, varius ac, leo. Suspendisse feugiat. Suspendisse enim turpis, dictum sed, iaculis a, condimentum nec, nisi. Praesent nec nisl a purus blandit viverra. Praesent ac massa at ligula laoreet iaculis. Nulla neque dolor, sagittis eget, iaculis quis, molestie non, velit. Mauris turpis nunc, blandit et, volutpat molestie, porta ut, ligula. Fusce pharetra convallis urna. Quisque ut nisi. Donec mi odio, faucibus at, scelerisque quis, convallis in, nisi. Suspendisse non nisl sit amet velit hendrerit rutrum. Ut leo. Ut a nisl id ante tempus hendrerit. Proin pretium, leo ac pellentesque mollis, felis nunc ultrices eros, sed gravida augue augue mollis justo. Suspendisse eu ligula. Nulla facilisi. Donec id justo. Praesent porttitor, nulla vitae posuere iaculis, arcu nisl dignissim dolor, a pretium mi sem ut ipsum. Curabitur suscipit suscipit tellus. Praesent vestibulum dapibus nibh. Etiam iaculis nunc ac metus. Ut id nisl quis enim dignissim sagittis. Etiam sollicitudin, ipsum eu pulvinar rutrum, tellus ipsum laoreet sapien, quis venenatis ante odio sit amet eros. Proin magna. Duis vel nibh at velit scelerisque suscipit. Curabitur turpis. Vestibulum suscipit nulla quis orci. Fusce ac felis sit amet ligula pharetra condimentum. Maecenas egestas arcu quis ligula mattis placerat. Duis lobortis massa imperdiet quam. Suspendisse potenti. Pellentesque commodo eros a enim. Vestibulum turpis sem, aliquet eget, lobortis pellentesque, rutrum eu, nisl. Sed libero. Aliquam erat volutpat. Etiam vitae tortor. Morbi vestibulum volutpat enim. Aliquam eu nunc. Nunc sed turpis. Sed mollis, eros et ultrices tempus, mauris ipsum aliquam libero, non adipiscing dolor urna a orci. Nulla porta dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Pellentesque dapibus hendrerit tortor. Praesent egestas tristique nibh. Sed a libero. Cras varius. Donec vitae orci sed dolor rutrum auctor. Fusce egestas elit eget lorem. Suspendisse nisl elit, rhoncus eget, elementum ac, condimentum eget, diam. Nam at tortor in tellus interdum sagittis. Aliquam lobortis. Donec orci lectus, aliquam ut, faucibus non, euismod id, nulla. Curabitur blandit mollis lacus. Nam adipiscing. Vestibulum eu odio. Vivamus laoreet. Nullam tincidunt adipiscing enim. Phasellus tempus. Proin viverra, ligula sit amet ultrices semper, ligula arcu tristique sapien, a accumsan nisi mauris ac eros. Fusce neque. Suspendisse faucibus, nunc et pellentesque egestas, lacus ante convallis tellus, vitae iaculis lacus elit id tortor. Vivamus aliquet elit ac nisl. Fusce fermentum odio nec arcu. Vivamus euismod mauris. In ut quam vitae odio lacinia tincidunt. Praesent ut ligula non mi varius sagittis. Cras sagittis. Praesent ac sem eget est egestas volutpat. Vivamus consectetuer hendrerit lacus. Cras non dolor. Vivamus in erat ut urna cursus vestibulum. Fusce commodo aliquam arcu. Nam commodo suscipit quam. Quisque id odio. Praesent venenatis metus at tortor pulvinar varius. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vita";
        //System.out.println("PLAIN: "+longText);
        System.out.println("SYMBOLS: "+longText.length());
        System.out.println("BYTES: "+longText.getBytes().length);
        long startTime = System.currentTimeMillis();
        List<BigInteger[]> list = encrypt(longText, key);
        long time = System.currentTimeMillis() - startTime;
        System.out.println("ENCRYPTION TIME (ms): "+time);
        //System.out.println("ENCRYPTED: "+encryptedToString(list));
        System.out.println("ENCRYPTED BYTES: "+encryptedToString(list).getBytes().length);
        startTime = System.currentTimeMillis();
        String decryptedLongText = decrypt(list, key);
        time = System.currentTimeMillis() - startTime;
        //System.out.println("DECRYPTED: "+decryptedLongText);
        System.out.println("DECRYPTION EQUALITY: "+longText.equals(decryptedLongText));
        System.out.println("DECRYPTION TIME (ms): "+time);

    }

    /**
     * Base64 encoding
     */
    public static String bytesToBase64(byte[] b)
    {
        return Base64.getEncoder().encodeToString(b);
    }

    public static byte[] fullEncryption(String text, BigInteger[][] key)
    {
        StringBuilder strKeyBuilder = new StringBuilder();
        Arrays.stream(key).forEach(x -> strKeyBuilder.append(x[0]).append(x[1]).append(x[2]));
        return encode(
                encryptedToString(
                        encrypt(text, key)
                ),
                strKeyBuilder.toString()
        );
    }

    public static String fullDecryption(byte[] text, BigInteger[][] key)
    {
        StringBuilder strKeyBuilder = new StringBuilder();
        Arrays.stream(key).forEach(x -> strKeyBuilder.append(x[0]).append(x[1]).append(x[2]));
        String SOLDEEA = decode(text, strKeyBuilder.toString());
        return decrypt(
                encryptedToList(SOLDEEA),
                key
        );
    }

    /**
     * Key generation for encryption
     */
    public static BigInteger[][] generateKey(int b)
    {
        return new BigInteger[][]
                {
                        new BigInteger[]{randomInt(b), randomInt(b), randomInt(b)},
                        new BigInteger[]{randomInt(b), randomInt(b), randomInt(b)}
                };
    }

    /**
     * Generate random BigInteger
     * @b bits
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
     * Returns -1 or 1 randomly
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
     * Converts list of encrypted bigramms to string
     */
    public static String encryptedToString(List<BigInteger[]> list)
    {
        StringBuilder builder = new StringBuilder();
        list.forEach(x -> {
            builder.append(x[0].toString());
            builder.append("/");
            builder.append(x[1].toString());
            builder.append("/");
        });
        return builder.toString();
    }

    /**
     * Converts string of encrypted bigramms to list
     */
    public static List<BigInteger[]> encryptedToList(String text)
    {
        String[] arr = text.split("/");
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
            list.add(encryptBigramWithSystem(key, text.charAt(k), text.charAt(k+1)));
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
            BigInteger[] bigram = decryptBigramWithSystem(key, x);
            builder.append((char)bigram[0].intValue());
            builder.append((char)bigram[1].intValue());
        });
        return builder.toString();
    }

    /**
     * Encryption of bigramm
     */
    public static BigInteger[] encryptBigramWithSystem(BigInteger[][] key, int x, int y)
    {
        BigInteger[] c = new BigInteger[]
                {
                   key[0][0], key[0][1], key[0][2],
                   key[1][0], key[1][1], key[1][2]
                };
        BigInteger M = linearCombination(c);
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
    public static BigInteger[] decryptBigramWithSystem(BigInteger[][] key, BigInteger[] b)
    {
        BigInteger[] c = new BigInteger[]
                {
                        key[0][0], key[0][1], key[0][2],
                        key[1][0], key[1][1], key[1][2]
                };
        BigInteger M = linearCombination(c);
        b[0] = b[0].xor(M);
        b[1] = b[1].xor(M);

        BigInteger[][] matrix = new BigInteger[][]
                {
                        {key[0][0], key[1][0], b[0]},
                        {key[0][1], key[1][1], b[1]}
                };
        BigInteger[] result = GaussMethodFraction(matrix);
        BigInteger[] bigram = new BigInteger[2];
        bigram[0] = result[0].subtract(key[0][2]);
        bigram[1] = result[1].subtract(key[1][2]);
        return bigram;
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
    *Gauss method for system solving
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
     * Encode string with XNOR operation
     */
    public static byte[] encode(String pText, String pKey) {
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
     */
    public static String decode(byte[] pText, String pKey) {
        byte[] res = new byte[pText.length];
        byte[] key = pKey.getBytes();

        for (int i = 0; i < pText.length; i++) {
            res[i] = (byte) xnor(pText[i], key[i % key.length]);
        }
        return new String(res);
    }

    /**
     * XNOR operation
     */
    public static int xnor(int a, int b) {
        return ~(a ^ b);
    }
}
