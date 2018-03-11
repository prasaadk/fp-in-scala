import patmat.Huffman._

val listy = List('a', 'b', 'a')
listy.groupBy(_.toChar).map(p=>(p._1,p._2.length))