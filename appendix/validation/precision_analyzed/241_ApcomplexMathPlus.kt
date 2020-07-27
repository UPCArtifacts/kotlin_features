package com.nao20010128nao.DroidComplex

import org.apfloat.Apcomplex
import org.apfloat.Apfloat
import org.apfloat.ApfloatMath
import java.math.RoundingMode

const val PRECISION:Long = 100
val CALC_PI:Apfloat = ApfloatMath.pi(PRECISION)

/**
 * Makes Apcomplex by giving degrees to Apfloat
 */
inline fun Apfloat.deg(deg: Apfloat): Apcomplex = rad(toRad(deg))

/**
 * Makes Apcomplex by giving radians to Apfloat
 */
inline fun Apfloat.rad(deg: Apfloat): Apcomplex = Apcomplex(
        ApfloatMath.cos(deg)*this,
        ApfloatMath.sin(deg)*this)

inline operator fun Apfloat.plus(a:Apfloat): Apfloat = this.add(a)
inline operator fun Apfloat.minus(a:Apfloat): Apfloat = this.subtract(a)

inline operator fun Apfloat.times(a:Apfloat): Apfloat = this.multiply(a)
inline operator fun Apfloat.div(a:Apfloat): Apfloat = this.divide(a)

inline fun Apfloat.round(precision:Long,mode: RoundingMode): Apfloat=ApfloatMath.round(this,precision,mode)

/** Zero-proof arc-tangent function */
fun atan(x:Apfloat, y:Apfloat): Apfloat = when {
    x == Apfloat.ZERO && y == Apfloat.ZERO -> // no direction: assume 0
        Apfloat.ZERO.precision(PRECISION)
    y == Apfloat.ZERO -> // 0
        Apfloat.ZERO.precision(PRECISION)
    x == Apfloat.ZERO -> // 90
        CALC_PI/Apfloat(2).precision(PRECISION)
    else -> ApfloatMath.atan(y / x)
}

/** Zero-proof converter for rad->deg */
fun toDeg(r:Apfloat):Apfloat = when (r) {
    Apfloat.ZERO -> Apcomplex.ZERO
    else -> ApfloatMath.toDegrees(r)
}

/** Zero-proof converter for deg->rad */
fun toRad(r:Apfloat):Apfloat = when (r) {
    Apfloat.ZERO -> Apcomplex.ZERO
    else -> ApfloatMath.toRadians(r)
}

fun atan(p:Apcomplex):Apfloat = atan(p.real(),p.imag())
