package org.nico.kata.stringcalculatorkata

import org.specs2.mutable._

class StringCalculatorSpec extends Specification {

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      "Hello world" must have size(11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }

  "The string calculator" should {
    "return 0 for empty string" in {
      StringCalculator.calculate("") must beEqualTo(0)
    }
    "return the value for single number string" in {
      StringCalculator.calculate("1") must beEqualTo(1)
    }
    "return the sum for two number comma separated" in {
      StringCalculator.calculate("1,2") must beEqualTo(3)
    }
    "return the sum for two number newline separated" in {
      StringCalculator.calculate("1\n2") must beEqualTo(3)
    }
    "return the sum for three numbers separated either way" in {
      StringCalculator.calculate("1,2\n3") must beEqualTo(6)
    }
    "Negative numbers throw an exception" in {
      StringCalculator.calculate("-1,-2") must throwA[IllegalArgumentException]
    }
    "Numbers greater than 1000 are ignored" in {
      StringCalculator.calculate("1000,2,3") must beEqualTo(5)

    }
    "A single char delimited can be defined on the first line" in {
      StringCalculator.calculate("//#\n2#3") must beEqualTo(5)

    }

  }
}
