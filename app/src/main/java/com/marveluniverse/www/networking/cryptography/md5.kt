package com.marveluniverse.www.networking.cryptography

import com.marveluniverse.www.BuildConfig
import java.security.MessageDigest

fun md5(ts: Long): String {
    val input = "$ts${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_KEY}"
    // Convert input string to bytes
    val bytes = input.toByteArray()

    // Create MD5 hash instance
    val md5Digest = MessageDigest.getInstance("MD5")

    // Update digest with input bytes
    val hashedBytes = md5Digest.digest(bytes)

    // Convert hashed bytes to hexadecimal representation
    val hashedString = StringBuilder()
    for (hashedByte in hashedBytes) {
        val hex = Integer.toHexString(0xff and hashedByte.toInt())
        if (hex.length == 1) {
            hashedString.append('0')
        }
        hashedString.append(hex)
    }
    return hashedString.toString()
}