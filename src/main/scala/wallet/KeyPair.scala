package wallet

import crypto.Crypto

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
    Crypto.generatePublicKey(text)
  }

  /**
    * @return a secret for a new Wallet
    */
  def generateSecret(password: String): String = {
    Crypto.generateSecret(password)
  }
}
