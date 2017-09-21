package blockchain
import models.Block


class Blockchain(var blockchain: List[Block]) {

  def addBlock(block: Block): List[Block] = {
    if (isValidBlock(block, blockchain.head())) blockchain = block :: blockchain
    blockchain
  }

  def isValidBlock(block: Block, previousBlock: Block): Boolean = {
    if (previousBlock.index + 1 != block.index) false
    else if (previousBlock.hash != block.previousHash) false
    else if (Block.hash(block.index, block.timestamp, block.data, block.previousHash) != block.hash) false
    else true
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
