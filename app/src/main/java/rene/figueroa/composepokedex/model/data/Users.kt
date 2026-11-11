package rene.figueroa.composepokedex.model.data

import android.content.Context
import rene.figueroa.composepokedex.model.preferences.UserPreferences

class Users(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        UserPreferences.ARCHIVO,
        Context.MODE_PRIVATE
    )

    private val usersList = listOf(
        User("rene@correo.com", "rene123", "Rene"),
        User("mario@cerulean.com", "mario123", "Mario"),
        User("juan@pewter.com", "juan123", "Juan")
    )

    fun getUserByMail(mail: String): User? {
        return usersList.find { it.email.equals(mail, ignoreCase = true) }
    }

    fun loginService(email: String, pass: String): User? {
        val user = getUserByMail(email)
        return if (user != null && user.pass == pass) user else null
    }

    fun saveUser(user: String, name: String) {
        sharedPreferences.edit().apply {
            putString(UserPreferences.USER, user)
            putString(UserPreferences.USERNAME, name)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        val email = sharedPreferences.getString(UserPreferences.USER, null)
        val name = sharedPreferences.getString(UserPreferences.USERNAME, null)
        return !email.isNullOrEmpty() && !name.isNullOrEmpty()
    }

    fun getCurrentUser(): User? {
        val email = sharedPreferences.getString(UserPreferences.USER, null)
        val name = sharedPreferences.getString(UserPreferences.USERNAME, null)
        if (!email.isNullOrEmpty()) {
            return getUserByMail(email) ?: User(email, "", name ?: "")
        }
        return null
    }

    fun logout() {
        sharedPreferences.edit().apply {
            putString(UserPreferences.USER, null)
            putString(UserPreferences.USERNAME, null)
            apply()
        }
    }
}