import patmat.Huffman.{CodeTable, _}

val listy = List('a', 'b', 'a')

listy.groupBy(_.toChar).map(p=>(p._1,p._2.length))



val table: CodeTable = List(('a',List(0,1,1)),('b',List(1,1,1)))

val bits: List[Bit] = table match {
  case ('a',bits)::rest => bits
  case first::('a',bits)::last => bits
}

