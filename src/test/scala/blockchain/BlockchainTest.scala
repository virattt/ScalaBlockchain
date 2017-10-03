package blockchain

import block.{Transaction, Block}
import org.scalatest.{FlatSpec, FunSuite}

class BlockchainTest extends FlatSpec {

  "getBlockByHash" should "return the correct block" in {
    val block: Block = Block(0, 0, "0", List[Transaction]())
    val hash: String = block.hash

    val blockchain: Blockchain = new Blockchain(List[Block](block))
    val blockByHash: Block = blockchain.getBlockByHash(hash)

    assert(blockByHash.hash == block.hash)
  }

  "getLastBlock" should "return the last block" in {
    val z: Block = Block(0, 100, "0", List[Transaction]())
    val y: Block = Block(1, 200, z.hash, List[Transaction]())
    val x: Block = Block(2, 300, y.hash, List[Transaction]())

    // We add new elements to the head of the Blockchain => [ x, y, z ]
    val blockchain: Blockchain = new Blockchain(List[Block](x, y, z))
    val lastBlock: Block = blockchain.getLastBlock

    assert(lastBlock.hash == z.hash)
  }
}
