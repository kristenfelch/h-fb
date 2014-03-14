package com.wishclouds.utils

import java.io.FileWriter
import org.scalatest.{Failed, Outcome, Suite}

/**
 * Created with IntelliJ IDEA.
 * User: greg123
 * Date: 6/14/13
 * Time: 11:30 AM
 */
trait FailRule extends Suite {

  //  val ou

  def mail(name: String, msg: String) {
    printMsg(name, msg)
  }

  def printMsg(text: String, name: String) {
    val fw = new FileWriter("errors.txt", true)
    try {
      fw.write("\n%s:\t%s\n%s".format(text, name, "*" * 30))
    }
    finally fw.close()
  }

  override def withFixture(test: NoArgTest): Outcome = {
    //    printMsg("STARTING", test.name)
    try {
      super.withFixture(test) match {
        case f: Failed =>
          printMsg(test.name, f.toOption.get.getMessage)
          f
        case o: Outcome => o
      }
    }

    catch {
      case e: Exception =>
        printMsg(test.name, e.getMessage)
        throw e
    } finally {
    }
  }
}
