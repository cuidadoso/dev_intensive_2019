package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnit
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        //val user = User("1")
        val user2 = User("2", "John", "Wick")
//        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

//        user.printMe()
//        user2.printMe()
//        user3.printMe()

//        println("$user")
    }

    @Test
    fun test_factory() {
        // val user = User.makeUser("John Cena")
        // val user2 = User.makeUser("John Wick")
        val user3 = User.makeUser("John Silverhand")
        val user4 = user3.copy(id = "2", lastName = "Cena", lastVisit = Date())
        print("$user3 \n$user4")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id $firstName $lastName")

        user.component1()
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(id = "2", lastVisit = Date())
        val user3 = user.copy(id = "2", lastVisit = Date().add(-2))
        val user4 = user.copy(id = "2", lastVisit = Date().add(2, TimeUnit.HOUR))

        println(
            """
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_maping() {
        val user = User.makeUser("John Wick")
        val newUser = user.copy(lastVisit = Date().add(-7))
        val userView = user.toUserView()
        val newUserView = newUser.toUserView()
        user.printMe()
        userView.printMe()

        newUser.printMe()
        newUserView.printMe()
    }
}