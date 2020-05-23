package udit.programmer.co.firebase.MVVM.data.repos

import udit.programmer.co.firebase.MVVM.data.api.RetrifitClient

object GithubRepository {

    suspend fun getUsers() = RetrifitClient.api.getUsers();

    suspend fun searchUsers(name: String) = RetrifitClient.api.searchUsers(name)

}