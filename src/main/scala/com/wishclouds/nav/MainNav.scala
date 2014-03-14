package com.wishclouds.nav

import org.openqa.selenium.{By, WebDriver}
import org.scalatest.concurrent.Eventually._
import org.scalatest._
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Seconds, Span}
import org.openqa.selenium.firefox.FirefoxDriver
import com.wishclouds.utils.FailRule

/**
 * Created with IntelliJ IDEA.
 * User: kristen
 * Date: 03/14/14
 * Time: 3:50 PM

 */
object MainNav extends WebBrowser with FailRule with Matchers with BeforeAndAfterAll with BeforeAndAfterEach {

  val homepage = "http://www.facebook.com";
  val friendRequests = "http://www.facebook.com/friends/requests/"
  val friends = "http://www.facebook.com/friends"
  var stack = List(friends)
  var counter = 0

  def makeDriver: WebDriver = {
    val driver = new FirefoxDriver()
    driver
  }

  implicit val driver = makeDriver

  implicitlyWait(Span(4, Seconds))
  patienceConfig.copy(Span(4, Seconds), Span(2, Seconds))

  def login(login:String, password:String): Unit = {
    go to homepage
    Thread.sleep(3000)
    val userElement = driver.findElement(By.id("email"))
    userElement.sendKeys(login)
    Thread.sleep(3000)
    val passwordElement = driver.findElement(By.id("pass"))
    passwordElement.sendKeys(password)

    Thread.sleep(3000)
    val signInElement = driver.findElement(By.id("loginbutton"))
    click on signInElement
  }

  def acceptFriendRequests(): Unit = {
    go to friendRequests
    val confirmButtons = driver.findElements(By.cssSelector(".uiButtonConfirm"))
    val iterate = confirmButtons.iterator()
    while (iterate.hasNext) {
      Thread.sleep(3000)
      val confirmButton = iterate.next()
      click on confirmButton
    }
  }


  def initializeFriendsStack(): Unit = {
    //wait for 5 minutes on each friend's page
    Thread.sleep(300000)
    if (counter > 3000) {
      //every time we've done 1000 friend requests, wait half hour (perhaps should be lower...)
      Thread.sleep(1200000)
      stack = List(friends)
    }
    val seed = stack(0)
    stack = stack.slice(1, stack.size)
    go to seed
    //wait 5 seconds
    Thread.sleep(5000)
    val addFriendButtons = driver.findElements(By.cssSelector(".FriendRequestAdd"))
    val addIterate = addFriendButtons.iterator()
    while(addIterate.hasNext) {
      //wait 5 seconds
      Thread.sleep(3000)
      val addFriendButton = addIterate.next()
      if (!addFriendButton.getAttribute("class").contains("hidden_elem")) {
        click on addFriendButton
        counter = counter + 1
      }
    }
    val addedFriendsLinks = driver.findElements(By.cssSelector(".uiProfileBlockContent"))
    val friendsIterate = addedFriendsLinks.iterator()
    while (friendsIterate.hasNext && stack.size < 3000) {
      val friendInfo = friendsIterate.next()
        .findElement(By.tagName("div"))
        .findElements(By.tagName("div")).get(1)
        .findElement(By.tagName("div"))
        .findElement(By.tagName("a"))
        .getAttribute("href")
      val friendUrl = friendInfo.substring(0, friendInfo.indexOf('?'))
      stack = stack ++ List(friendUrl + "/friends")
    }
    initializeFriendsStack()
  }


  override protected def afterEach() {
    super.afterEach()
    Thread.sleep(5000)
  }

  override protected def afterAll() {
    super.afterAll()
  }
}
