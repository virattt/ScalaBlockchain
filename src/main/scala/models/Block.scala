package models

import java.math.BigInteger
import java.security.MessageDigest

/**
 * @param index - the index of the Block in the Blockchain
 * @param timestamp - the timestamp in seconds, since January 1, 1970
 * @param data - the Data that this Block contains
 * @param previousHash - the Hash of the previous Block in the Blockchain
 */
class Block(index: Long, timestamp: Long, data: Data, previousHash: String) {

  def hash: String = hash(index, timestamp, data, previousHash)

  private def hash(index: Long, timestamp: Long, data: Data, previousHash: String): String = {
    val input = index.toString + timestamp.toString + data.toString + previousHash.toString
    sha256(input)
  }

  private def sha256(text: String) : String = {
    val message: Array[Byte] = MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"))
    String.format("%064x", new BigInteger(1, message))
  }
}
