package transaction

import block.BlockData
import crypto.Crypto

/**
 * Transaction structure:
 * { // Transaction
 *     "id": "84286bba8d...7477efdae1", // random id (64 bytes)
 *     "hash": "f697d4ae63...c1e85f0ac3", // hash taken from the contents of the transaction: sha256 (id + data) (64 bytes)
 *     "type": "regular", // transaction type (regular, fee, reward)
 *     "data": {
 *         "inputs": [ // Transaction inputs
 *             {
 *                 "transaction": "9e765ad30c...e908b32f0c", // transaction hash taken from a previous unspent transaction output (64 bytes)
 *                 "index": "0", // index of the transaction taken from a previous unspent transaction output
 *                 "amount": 5000000000, // amount of satoshis
 *                 "address": "dda3ce5aa5...b409bf3fdc", // from address (64 bytes)
 *                 "signature": "27d911cac0...6486adbf05" // transaction input hash: sha256 (transaction + index + amount + address) signed with owner address's secret key (128 bytes)
 *             }
 *         ],
 *         "outputs": [ // Transaction outputs
 *             {
 *                 "amount": 10000, // amount of satoshis
 *                 "address": "4f8293356d...b53e8c5b25" // to address (64 bytes)
 *             },
 *             {
 *                 "amount": 4999989999, // amount of satoshis
 *                 "address": "dda3ce5aa5...b409bf3fdc" // change address (64 bytes)
 *             }
 *         ]
 *     }
 * }
*/

case class Transaction(id: String, hash: String, blockData: BlockData) {

  private val FEE_PER_TRANSACTION = 1 // Can change this to whichever fee desired

  /**
    * Return the hash value of this Transaction
    */
  def toHash = {
    Crypto.hash(id + hash + blockData.toString)
  }

  def isValidTransaction: Boolean = {
    if (hash != toHash) return false

    // Check that the signatures of all of the input transactions are correct
    val areInputsValid = blockData.inputs.forall(inputTransaction => {
      val inputHash = Crypto.hash(
        inputTransaction.transaction.toString
          + inputTransaction.index
          + inputTransaction.fromAddress)

      val isValidSignature = Crypto.isValidSignature( //
        BigInt(inputTransaction.fromAddress), //
        BigInt(inputTransaction.signature), //
        inputHash //
      )

      if (!isValidSignature) return false

      return true
    })

    if (!areInputsValid) return false

    // Check if the sum of input transactions are greater than output transactions,
    // it needs to leave some room for the transaction fee
    val sumOfInputAmounts: Long = sumInputs(blockData.inputs)
    val sumOfOutputAmounts: Long = sumOutputs(blockData.outputs)
    if (sumOfInputAmounts < sumOfOutputAmounts) return false

    // Check if there is enough of a difference between the input amounts and
    // output amounts for the transaction fee
    if ((sumOfInputAmounts - sumOfOutputAmounts) < FEE_PER_TRANSACTION) return false

    true
  }

  private def sumInputs(xs: List[InputTransaction]): Long = {
    xs match {
      case x :: tail => x.amount + sumInputs(tail) // if there is an element, add it to the sum of the tail
      case Nil => 0 // if there are no elements, then the sum is 0
    }
  }

  private def sumOutputs(xs: List[OutputTransaction]): Long = {
    xs match {
      case x :: tail => x.amount + sumOutputs(tail) // if there is an element, add it to the sum of the tail
      case Nil => 0 // if there are no elements, then the sum is 0
    }
  }
}

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
case class InputTransaction(transaction: Transaction, index: Int, amount: Long, fromAddress: String, signature: String)

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
case class OutputTransaction(amount: Long, toAddress: String)
