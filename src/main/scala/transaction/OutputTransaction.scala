package transaction

/**
  *
  * An output transaction from a given Transaction.
  *
  * Example structure of an OutputTransaction:
  *
  * {
  *     "amount": 4999989999, // amount of satoshis
  *     "address": "dda3ce5aa5...b409bf3fdc" // to or change address (64 bytes)
  * }
  */
case class OutputTransaction(amount: Long, toAddress: String) {

}
