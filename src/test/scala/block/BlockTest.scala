package block

import org.scalatest.FlatSpec
import transaction.Transaction

class BlockTest extends FlatSpec {


  "isValidBlock" should "return true if the block is valid" in {
    val y: Block = Block(0, 100, "0", List[Transaction]())
    val x: Block = Block(1, 200, y.hash, List[Transaction]())

    val isValidBlock: Boolean = x.isValid(y)

    assert(isValidBlock)
  }

  "isValidBlock" should "return false if the block is NOT valid" in {
    val y: Block = Block(0, 100, "0", List[Transaction]())
    val x: Block = Block(1, 200, "invalid hash", List[Transaction]())

    val isValidBlock: Boolean = x.isValid(y)

    assert(!isValidBlock)
  }
}
