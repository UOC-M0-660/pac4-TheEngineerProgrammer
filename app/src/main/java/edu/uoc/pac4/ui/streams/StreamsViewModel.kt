package edu.uoc.pac4.ui.streams

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import edu.uoc.pac4.R
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.streams.Stream
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.StreamsResponse
import edu.uoc.pac4.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_streams.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class StreamsViewModel(
        private val streamsRepository: StreamsRepository
) : ViewModel(){
    private val TAG = "StreamsViewModel"

    var nextCursor: String? = null
    val streams = MutableLiveData<List<Stream>>()
    //val streamsResponse = MutableLiveData<StreamsResponse?>()

    @Throws(UnauthorizedException::class)
    fun getStreams(){
//        viewModelScope.launch {
//            streamsResponse.postValue(streamsRepository.getStreams(cursor))
//        }
        viewModelScope.launch {
            streamsRepository.getStreams(nextCursor)?.let { response ->
                val newStreams = response.data.orEmpty()
                val currentStreams = mutableListOf<Stream>()
                nextCursor?.let {
                    streams.value?.let { currentStreams.addAll(it) }
                }
                currentStreams.addAll(newStreams)
                streams.value = currentStreams

                nextCursor = response.pagination?.cursor
            }
        }
    }

}