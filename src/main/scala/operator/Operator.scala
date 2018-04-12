package operator

import blockchain.Blockchain
import transaction.Transaction
import wallet.Wallet

/**
  * The operator's primary function is to handle wallets and addresses, as well as transaction creation.
  * Most of its operations are CRUD related. Each operator has its own list of wallets and addresses,
  * meaning that it isn't synchronized between nodes on the network.
  *
  * @param blockchain
  * @param wallets
  */
class Operator(blockchain: Blockchain, var wallets: List[Wallet]) {

  /**
   *
   * @param wallet
   * @return
   */
  def addWallet(wallet: Wallet): Boolean = {
    if (wallets.contains(wallet)) return false
    wallets = wallet :: wallets
    true
  }

  /**
   *
   * @param wallet
   * @param password
   * @return
   */
  def isWalletPasswordValid(wallet: Wallet, password: String): Boolean = {
    wallet.password == password
  }

  /**
   *
   * @return
   */
  def getWallets: List[Wallet] = {
    wallets
  }

  /**
   *
   * @param walletId
   * @return
   */
  def getWalletById(walletId: String): Wallet = {
    wallets.find(wallet => wallet.id == walletId) match {
      case Some(wallet) => wallet
      case None => null
    }
  }

  /**
   *
   * @param wallet
   * @return
   */
  def generateAddressForWallet(wallet: Wallet): String = {
    null
  }

  /**
   *
   * @param wallet
   * @return
   */
  def getAddressForWallet(wallet: Wallet): String = {
    null
  }

  /**
   *
   * @param wallet
   * @param address
   * @return
   */
  def isWalletAddressValid(wallet: Wallet, address: String): String = {
    null
  }

  /**
   *
   * @param wallet
   * @param address
   * @return
   */
  def getWalletBalance(wallet: Wallet, address: String): Long = {
    0
  }

  /**
   *
   * @param wallet
   * @param fromAddress
   * @param toAddress
   * @param amount
   * @param changeAddress
   * @return
   */
  def createTransaction(wallet: Wallet, fromAddress: String, toAddress: String, amount: Long, changeAddress: String): Transaction = {
    null
  }
}
