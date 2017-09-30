package wallet

class Wallet(id: String, password: String, secret: String, keyPairs: List[KeyPair]) {

  /**
   *
   * @param index
   * @return
   */
  def getAddressByIndex(index: Int): String = {
    null
  }

  /**
   *
   * @param publicKey
   * @return
   */
  def getAddressByPublicKey(publicKey: String): String = {
    null
  }

  /**
   *
   * @param address
   * @return
   */
  def getSecretByAddress(address: String): String = {
    null
  }

  /**
   *
   * @return
   */
  def getAddresses: List[String] = {
    null
  }

}

object Wallet {

  /**
   * @return an address for a new Wallet
   */
  def generateAddress: String = {
    null
  }

  /**
   * @return a secret for a new Wallet
   */
  def generateSecret: String = {
    null
  }
}

