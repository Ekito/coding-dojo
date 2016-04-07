package org.nico.kata.stringcalculatorkata

object StringCalculator {
  def calculate(s: String) = {

    val negativeNumber: Int => Boolean = x => x < 0
    val lowerThan: Int => Boolean = x => x < 1000

    s match {
      case "" => 0
      case _ =>
      val values = s split "[,\\n]" map Integer.parseInt

      if (values exists negativeNumber)
        throw new IllegalArgumentException()
      else
        values filter lowerThan sum
    }
  }

}
