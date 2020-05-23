package udit.programmer.co.firebase.MVVM.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import udit.programmer.co.firebase.MVVM.data.models.User
import udit.programmer.co.firebase.MVVM.data.repos.GithubRepository

class GitHubViewModel : ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun fetchUsers() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                GithubRepository.getUsers()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it)
                }
            }
        }

        fun searchUsers(name: String) = liveData(Dispatchers.IO) {
            val response = withContext(Dispatchers.IO) {
                GithubRepository.searchUsers(name)
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it.items)
                }
            }
        }
    }
}