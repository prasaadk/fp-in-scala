package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if(c==0 && r==0) 1
      else if (c<0 || c>r) 0
      else pascal(c-1,r-1)+pascal(c,r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def sub(chars: List[Char], openCount: Int): Boolean = {
        if (chars.isEmpty) {
          openCount == 0
        } else {
          val h = chars.head
          val n = chars.head match {
            case '(' => openCount + 1
            case ')' => openCount - 1
            case _ => openCount
          }
          if (n >= 0) sub(chars.tail, n)
          else false
        }
      }

      sub(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def f(money: Int, coins: List[Int]): Int = {
        if (coins.isEmpty || money < 0) 0
        else if (money == 0 ) 1
        else f(money - coins.head, coins) + f(money, coins.tail)
      }

      f(money, coins)
    }

  }
