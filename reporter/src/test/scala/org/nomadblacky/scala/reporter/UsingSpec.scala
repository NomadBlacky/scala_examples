package org.nomadblacky.scala.reporter

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UsingSpec extends AnyFunSpec with Matchers {

  class Resource extends AutoCloseable {
    var isClosed               = false
    override def close(): Unit = {
      isClosed = true
    }
  }

  describe("#foreach") {
    it("should be closing resources when finished operation") {
      val resource1 = new Resource
      val resource2 = new Resource

      for {
        r1 <- resource1
        r2 <- resource2
      } {
        (r1.isClosed, r2.isClosed) shouldBe (false, false)
      }

      (resource1.isClosed, resource2.isClosed) shouldBe (true, true)
    }
  }
}
