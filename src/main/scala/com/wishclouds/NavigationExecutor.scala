package com.wishclouds

import akka.actor.Actor
import com.wishclouds.nav.MainNav


/**
 * User: kristen
 * Date: 10/22/13
 * Time: 11:48 AM
 */
class NavigationExecutor extends Actor with Global {

  def receive = {
    case Work(personality, login, password) ⇒ {
      MainNav.login(login, password)
   //   MainNav.acceptFriendRequests()
      MainNav.initializeFriendsStack()
    }
  }
}