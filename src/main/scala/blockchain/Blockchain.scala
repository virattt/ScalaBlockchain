package blockchain

import block.{BlockData, Transaction, Block}


class Blockchain(var blockchain: List[Block]) {

  /**
    * Add blocks to the head of the Blockchain list
    */
  def addBlock(block: Block): List[Block] = {
    if (isValidBlock(block, blockchain.head)) {
      blockchain = block :: blockchain
    }
    blockchain
  }

  /**
   *
   * @param block
   * @param previousBlock
   * @return
   */
  def isValidBlock(block: Block, previousBlock: Block): Boolean = {
    if (previousBlock.index + 1 != block.index) false
    if (previousBlock.hash != block.previousHash) false
    if (Block.hash(block) != block.hash) false
    true
  }

  /**
   * Checks if a Blockchain is valid, by checking the hashes of adjacent Blocks.
   *
   * @return true if the Blockchain is valid, else false
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
   * Replaces the Blockchain with a new chain.
   *
   * @return the new Blockchain, if the existing one was replaced.  Else, return the
   *         existing Blockchain
   */
  def replaceChain(newBlocks: List[Block]): List[Block] = {
    if (isValidChain(newBlocks) && newBlocks.length > blockchain.length) {
      blockchain = newBlocks
    }
    blockchain
  }
}

/**
 *
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
      BlockData(List[Transaction](), List[Transaction]())
    )

    new Block(index, timestamp, previousHash, transaction :: Nil)
  }
}
