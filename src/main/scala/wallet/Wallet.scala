package wallet

/**
  * A wallet class, which represents a list of KeyPair values
  *
  * @param id the ID of this wallet
  * @param keyPairs the list of KeyPairs in this wallet
  */
class Wallet(val id: String, val password: String, var keyPairs: List[KeyPair]) {

  /**
    * Returns all public keys for this wallet
    */
  def getPublicKeys: List[String] = {
    keyPairs.map(keyPair => keyPair.publicKey)
  }

  /**
    * Returns a public key, given a KeyPair's index
    */
  def getPublicKeyByIndex(index: Int): String = {
    keyPairs(index).publicKey
  }

  /**
    * Returns a secret, given a KeyPair's public key
    */
  def getSecretByPublicKey(publicKey: String): String = {
    keyPairs.find(keypair => keypair.publicKey == publicKey) match {
      case Some(keyPair) => keyPair.publicKey
      case None => null
    }
  }

  /**
    * Returns true if the public key exists in this wallet, else false
    */
  def doesPublicKeyExist(publicKey: String): Boolean = {
    keyPairs.find(keypair => keypair.publicKey == publicKey) match {
      case Some(keyPair) => true
      case None => false
    }
  }

  def addKeyPair(keyPair: KeyPair): Boolean = {
    if (keyPairs.contains(keyPair)) return false
    keyPairs = keyPair :: keyPairs
    true
  }
}
