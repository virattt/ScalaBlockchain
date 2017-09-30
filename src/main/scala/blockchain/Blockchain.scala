package blockchain
import block.Block


class Blockchain(var blockchain: List[Block]) {

  /**
   *
   * @param block
   * @return
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
    if (Block.hash(block.index, block.timestamp, block.data, block.previousHash) != block.hash) false
    true
  }
}

/**
 *
 */
object Blockchain {
  import block.Data

  /**
   *
   * @return
   */
  def getGenesisBlock: Block = {
    new Block(
      0,
      System.currentTimeMillis(),
      new Data("This is the Genesis block!"),
      "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7"
    )
  }
}
