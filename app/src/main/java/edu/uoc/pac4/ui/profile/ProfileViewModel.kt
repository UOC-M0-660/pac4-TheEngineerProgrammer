package edu.uoc.pac4.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.user.User
import edu.uoc.pac4.data.user.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val authenticationRepository: AuthenticationRepository
): ViewModel() {

    val user = MutableLiveData<User?>()

    @Throws(UnauthorizedException::class)
    fun getUser(){
        viewModelScope.launch {
            user.postValue(userRepository.getUser())
        }
    }

    @Throws(UnauthorizedException::class)
    fun updateUser(description: String){
        viewModelScope.launch {
            user.postValue(userRepository.updateUser(description))
        }
    }

    fun logout(){
        viewModelScope.launch {
            authenticationRepository.logout()
        }
    }

}