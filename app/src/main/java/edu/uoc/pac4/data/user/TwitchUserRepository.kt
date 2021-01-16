package edu.uoc.pac4.data.user

import edu.uoc.pac4.data.network.UnauthorizedException

/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
    private val userDataSource: UserDataSource
) : UserRepository {

    @Throws(UnauthorizedException::class)
    override suspend fun getUser(): User? {
        return userDataSource.getUser()
    }

    @Throws(UnauthorizedException::class)
    override suspend fun updateUser(description: String): User? {
        return userDataSource.updateUserDescription(description)
    }
}