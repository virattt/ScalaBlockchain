package blockchain

import models.Block


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

  def isValidBlock(block: Block, previousBlock: Block): Boolean = {
    if (previousBlock.index + 1 != block.index) false
    if (previousBlock.hash != block.previousHash) false
    if (Block.hash(block.index, block.timestamp, block.data, block.previousHash) != block.hash) false
    true
  }

  def isValidChain(blockchainToValidate: List[Block]): Boolean = {
    if (blockchainToValidate.last != Blockchain.getGenesisBlock) false

    loop(blockchainToValidate)

    def loop(blockchain: List[Block]): Boolean = blockchain match {
      case Nil => false
      case x :: Nil => true
      case x :: xs => {
        if (x.previousHash != xs.head.hash) false
        loop(xs)
      }
    }
  }

  def replaceChain(newBlocks: List[Block]): List[Block] = {
    if (isValidChain(newBlocks) && newBlocks.length > blockchain.length) {
      blockchain = newBlocks
    }
    blockchain
  }
}

object Blockchain {

  import models.Data

  def getGenesisBlock: Block = {
    new Block(
      0,
      System.currentTimeMillis(),
      new Data("This is the Genesis block!"),
      "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7"
    )
  }
}
