package wallet

import crypto.ecdsa.{ECDSA, Key}
import io.github.nremond.SecureHash

import scala.util.Random

/**
  * A key pair value of secret key and public key
  * @param secretKey this KeyPair's secret key
  * @param publicKey this KeyPair's public key
  */
class KeyPair(val secretKey: String, val publicKey: String) {}

/**
  * Helper class for KeyPair
  */
object KeyPair {

  /**
    * Generates a public key for
    */
  def generatePublicKey(text: String): String = {
    val secret = BigInt(192, new Random)
    val key = Key.sec(secret, ECDSA.p192)
    ECDSA.sign(key, text, "SHA-256").toString(16)
  }

  /**
    * @return a secret for a new Wallet
    */
  def generateSecret(password: String): String = {
    SecureHash.createHash(password)
  }
}
