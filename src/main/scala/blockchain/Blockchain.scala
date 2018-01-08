package blockchain

import block.{Block, BlockData}
import transaction.{InputTransaction, OutputTransaction, Transaction}

/**
  * An abstraction that encapsulates the blockchain model.  A blockchain is a
  * List of blocks (List[Block]).  This class wraps and manages that List of blocks.
  *
  * This blockchain is a first-in-last-out (FILO) data structure.  It is a loose implementation of the
  * abstract Stack data structure.
  *
  * As a result, new blocks are added to the head (top) of the blockchain (stack).
  */
class Blockchain(var blockchain: List[Block]) {
  // Proof-of-work difficulty settings
  val BASE_DIFFICULTY = Integer.MAX_VALUE;
  val EVERY_X_BLOCKS = 5;
  val POW_CURVE = 5;

  /**
    * Add blocks to the head of the blockchain
    */
  def addBlock(block: Block): List[Block] = {
    if (isValidBlock(block, blockchain.head)) {
      blockchain = block :: blockchain
    }
    blockchain
  }

  /**
    * Returns all of the blocks in the blockchain
    */
  def getAllBlocks: List[Block] = {
    blockchain
  }

  /**
    * Returns a block, given its index
    */
  def getBlockByIndex(index: Int): Block = {
    blockchain(index)
  }

  /**
    * Returns a block, given its hash value
    */
  def getBlockByHash(hash: String): Block = {
    blockchain.find(_.hash == hash) match {
      case Some(block) => block
      case None => null
    }
  }

  /**
    * Returns the last block in the blockchain.  The last block in our blockchain
    * is at the tail of the list, since we always add new blocks to the head of
    * the blockchain.
    */
  def getLastBlock: Block = {
    blockchain.last
  }

  /**
    * Returns the proof of work difficulty for a given index.
    *
    * Calculates the difficulty based on the index since
    * the difficulty value increases EVERY_X_BLOCKS.
    */
  def getDifficulty(index: Int): Double = {
    Math.max(
      Math.floor(BASE_DIFFICULTY / Math.pow(Math.floor(((blockchain.length + 1) / EVERY_X_BLOCKS) + 1), POW_CURVE)),
      0
    )
  }

  /**
    * Returns true if block is valid, else return false
    */
  def isValidBlock(block: Block, previousBlock: Block): Boolean = {
    if (block.index != previousBlock.index + 1) return false
    if (block.previousHash != previousBlock.hash) return false
    true
  }

  /**
    * Returns true if the Blockchain is valid, by checking the hashes of adjacent Blocks,
    * else false
    */
  def isValidChain(blockchainToValidate: List[Block]): Boolean = {
    if (blockchainToValidate.last != Blockchain.getGenesisBlock) false

    def loop(blockchain: List[Block]): Boolean = blockchain match {
      case Nil => false
      case x :: Nil => true
      case x :: xs =>
        if (x.previousHash != xs.head.hash) return false
        loop(xs)
    }

    loop(blockchainToValidate)
  }

  /**
    * Returns the new Blockchain, if the existing one was replaced.
    * Else, return the existing Blockchain
    */
  def replaceChain(newBlocks: List[Block]): List[Block] = {
    if (isValidChain(newBlocks) && (newBlocks.length > blockchain.length)) {
      blockchain = newBlocks
    }
    blockchain
  }
}

/**
  * A helper singleton for the Blockchain
  */
object Blockchain {

  /**
    * @return the Genesis block, which contains fixed data
    */
  def getGenesisBlock: Block = {
    val index = 0
    val timestamp: Long = System.currentTimeMillis
    val previousHash = "0"
    val transaction = Transaction(
      "63ec3ac02f822450039df13ddf7c3c0f19bab4acd4dc928c62fcd78d5ebc6dba", // random hash
      null,
      BlockData(List[InputTransaction](), List[OutputTransaction]())
    )

    new Block(index, timestamp, previousHash, transaction :: Nil)
  }
}

