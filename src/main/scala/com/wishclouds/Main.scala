package com.wishclouds

import akka.actor._
import com.wishclouds.nav.MainNav
import akka.actor.Props


case class Personality(personality: String,
                       login: String,
                       password: String)

case class Work(personality: String,
                login: String,
                password: String)

/**
 * User: kristen
 * Date: 03/14/14
 * Time: 11:31 AM
 */
object Main extends App with Global {

  args.foreach {
    personalities =>
      val personality = personalities match {
        case "fitness" => Personality("fitness",
          "workout_girl@aol.com",
          "wishclouds123")
      }
      val system = ActorSystem("Execution")
      //  // default Actor constructor
      val navigationExecutor = system.actorOf(Props[NavigationExecutor], name = "navigationExecutor")

      personality match {
        case Personality(personality: String, login: String, password: String) => {
          navigationExecutor ! Work(personality,login,password)
        }
      }

  }
}
