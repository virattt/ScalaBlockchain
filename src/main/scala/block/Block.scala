package block

import java.math.BigInteger
import java.security.MessageDigest

import crypto.Crypto
import transaction.Transaction

/**
 * {  Sample Block JSON
 *  "index": 0, // (first block: 0)
 *  "previousHash": "0", // (hash of previous block, first block is 0) (64 bytes)
 *  "timestamp": 1465154705, // number of seconds since January 1, 1970
 *  "nonce": 0, // nonce used to identify the proof-of-work step.
 *  "transactions": [ // list of transactions inside the block
 *      { // transaction 0
 *          "id": "63ec3ac02f...8d5ebc6dba", // random id (64 bytes)
 *          "hash": "563b8aa350...3eecfbd26b", // hash taken from the contents of the transaction: sha256 (id + data) (64 bytes)
 *          "type": "regular", // transaction type (regular, fee, reward)
 *          "data": {
 *              "inputs": [], // list of input transactions
 *              "outputs": [] // list of output transactions
 *          }
 *      }
 *  ],
 *  "hash": "c4e0b8df46...199754d1ed" // hash taken from the contents of the block: sha256 (index + previousHash + timestamp + nonce + transactions) (64 bytes)
 * }
 */
case class Block(val index: Int, //
                 val timestamp: Long, //
                 val previousHash: String, //
                 val transactions: List[Transaction]) {

  /**
   * Return the hash value for this Block
   */
  def hash: String = Block.hash(this)

  /**
   * Returns true if block is valid, else return false
   */
  def isValid(previousBlock: Block): Boolean = {
    if (index != previousBlock.index + 1) return false
    if (previousHash != previousBlock.hash) return false
    true
  }
}

/**
 * Helper methods for the Block class
 */
object Block {

  /**
   * @return a hashed value of a Block
   */
  def hash(block: Block): String = {
    hash(block.index.toString
        + block.timestamp.toString
        + block.previousHash.toString
        + block.transactions.toString)
  }

  /**
   * @return a hashed value of some String input
   */
  def hash(input: String): String = {
    Crypto.hash(input)
  }
}
