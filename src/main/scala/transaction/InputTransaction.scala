package transaction

/**
  * An input transaction to a given Transaction.
  *
  * Example structure of an InputTransaction:
  * {
  *     "transaction": "9e765ad30c...e908b32f0c", // transaction hash taken from a previous unspent transaction output (64 bytes)
  *     "index": "0", // index of the transaction taken from a previous unspent transaction output
  *     "amount": 5000000000, // amount of satoshis
  *     "address": "dda3ce5aa5...b409bf3fdc", // from address (64 bytes)
  *     "signature": "27d911cac0...6486adbf05" // transaction input hash: sha256 (transaction + index + amount + address) signed with owner address's secret key (128 bytes)
  * }
  */
case class InputTransaction(transaction: Transaction,
                            index: Int,
                            amount: Long,
                            fromAddress: String,
                            signature: String) {


}
