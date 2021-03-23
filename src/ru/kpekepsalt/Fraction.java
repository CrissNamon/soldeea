package ru.kpekepsalt;

import java.math.BigInteger;

/**
 * Fraction class with basic operations
 */
public class Fraction {
    private BigInteger nominator;
    private BigInteger denominator;

    public Fraction(BigInteger n, BigInteger d)
    {
        nominator = n;
        denominator = d;
    }

    public Fraction multiply(Fraction f)
    {
        Fraction r = new Fraction(nominator, denominator);
        r.nominator = r.nominator.multiply(f.nominator);
        r.denominator = r.denominator.multiply(f.denominator);
        return r;
    }

    public Fraction divide(Fraction f)
    {
        Fraction r = new Fraction(nominator, denominator);
        r.nominator = r.nominator.multiply(f.denominator);
        r.denominator = r.denominator.multiply(f.nominator);
        return r;
    }

    public Fraction add(Fraction f)
    {
        Fraction r = new Fraction(nominator, denominator);
        if(f.isZero())
        {
            return r;
        }
        BigInteger lcm = lcm(denominator, f.denominator);
        r.nominator = r.nominator.multiply(
                lcm.divide(r.denominator)
        );
        f.nominator = f.nominator.multiply(
                lcm.divide(f.denominator)
        );
        r.nominator = r.nominator.add(f.nominator);
        r.denominator = lcm;
        return r;
    }

    public Fraction subtract(Fraction f)
    {
        Fraction r = new Fraction(nominator, denominator);
        BigInteger lcm = lcm(denominator, f.denominator);
        r.nominator = r.nominator.multiply(
                lcm.divide(denominator)
        );
        f.nominator = f.nominator.multiply(
                lcm.divide(f.denominator)
        );
        r.nominator = r.nominator.subtract(f.nominator);
        r.denominator = lcm;
        return r;
    }

    public Fraction abs()
    {
        Fraction r = new Fraction(nominator, denominator);
        r.nominator = r.nominator.abs();
        r.denominator = r.denominator.abs();
        return r;
    }

    public boolean isZero()
    {
        return nominator.compareTo(BigInteger.ZERO) == 0;
    }

    public BigInteger toInteger()
    {
        return nominator.divide(denominator);
    }

    /**
     * BigInteger matrix to Fraction matrix
     */
    public static Fraction[][] toFractionMatrix(BigInteger[][] m)
    {
        Fraction[][] f = new Fraction[m.length][m[0].length];
        for(int i=0;i<m.length;i++)
        {
            for(int j=0;j<m[0].length;j++)
            {
                f[i][j] = new Fraction(m[i][j], BigInteger.ONE);
            }
        }
        return f;
    }

    /**
     * BigInteger number to Fraction
     */
    public static Fraction toFraction(BigInteger a)
    {
        return new Fraction(a, BigInteger.ONE);
    }

    private BigInteger gcd(BigInteger a, BigInteger b)
    {
        if (a.compareTo(BigInteger.ZERO) == 0)
            return b;

        return gcd(b.mod(a), a);
    }

    private BigInteger lcm(BigInteger a, BigInteger b)
    {
        return a.multiply(b).divide(gcd(a, b));
    }

    public BigInteger getNominator()
    {
        return nominator;
    }

    public BigInteger getDenominator()
    {
        return denominator;
    }

    @Override
    public String toString()
    {
        return nominator.toString() +
                "/" +
                denominator.toString();
    }
}
