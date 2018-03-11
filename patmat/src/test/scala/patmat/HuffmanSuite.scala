package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times") {
    assert(times(List('a', 'b', 'a')).toSet === List(('a', 2), ('b', 1)).toSet)
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of some leaf list with two elements") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3)))
  }

  test("combine of some leaf list with less than 2 elements") {
    val leaflist = List(Leaf('e', 1))
    assert(combine(leaflist) === leaflist)
  }

  test("singleton check for list with 1 value") {
    val leaflist = List(Leaf('e', 1))
    assert(singleton(leaflist) == true)
  }

  test("singleton check for list with 2 values") {
    val leaflist = List(Leaf('e', 1), Fork(Leaf('e', 1),Leaf('f', 1),List('e','f'),2))
    assert(singleton(leaflist) == false)
  }

  test("singleton check for empty list") {
    val leaflist = List()
    assert(singleton(leaflist) == false)
  }

  test("until t1") {
    new TestTrees {
      val leaflist = List(Leaf('a',2), Leaf('b',3),Leaf('d',4))
      assert(until(singleton, combine)(leaflist) === t2)
    }
  }

  test("until t2") {
    new TestTrees {
      val leaflist = List(Leaf('a',2), Leaf('b',3))
      assert(until(singleton, combine)(leaflist) === t1)
    }
  }

  test("createCodeTree") {
    new TestTrees {
      assert(createCodeTree("aabbbdddd".toList) === t2)
    }
  }

  test("decode secret") {
    assert (decode(frenchCode, encode(frenchCode)("huffmanestcool".toList)) === "huffmanestcool".toList)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("code bits with char first in codetable") {
    val table: CodeTable = List(('a',List(0,1,1)),('b',List(1,1,1)))
    assert (codeBits(table)('a') == List(0,1,1))
  }

  test("code bits with char last in codetable") {
    val table: CodeTable = List(('a',List(0,1,1)),('b',List(1,1,1)))
    assert (codeBits(table)('b') == List(1,1,1))
  }

  test("code table with char last in codetable") {
    new TestTrees {
      val table: CodeTable = List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1)))
      assert (convert(t2) == table)
    }
  }

  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
