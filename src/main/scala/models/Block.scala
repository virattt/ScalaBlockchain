package models

import java.math.BigInteger
import java.security.MessageDigest

class Block(index: Long, timestamp: Long, data: Data, previousHash: String, hash: String) {

  def hashcode: Int = hash.hashCode()
}

object Block {

  def calculateHash(index: Long, timestamp: Long, data: Data, previousHash: String): String = {
    sha256(index.toString + timestamp.toString + data.toString + previousHash.toString)
  }

  private def sha256(text: String) : String = String.format("%064x",
    new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8")))
  )
}
