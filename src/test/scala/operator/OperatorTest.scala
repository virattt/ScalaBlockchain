package operator

import crypto.Crypto
import org.scalatest.FlatSpec
import wallet.Wallet

/**
  * Created by virat_singh on 1/9/18.
  */
class OperatorTest extends FlatSpec {

  "addWallet" should "only add unique Wallets" in {
    val wallet1: Wallet = new Wallet("1", Crypto.randomString, Nil)
    val wallet2: Wallet = new Wallet("2", Crypto.randomString, Nil)

    val operator: Operator = new Operator(null, List(wallet1))

    // Add wallet2 for the first time and assert true
    var isWalletAdded: Boolean = operator.addWallet(wallet2)
    assert(isWalletAdded)

    // Add wallet2 again and ensure it wasn't added
    isWalletAdded = operator.addWallet(wallet2)
    assert(!isWalletAdded)
  }

}
