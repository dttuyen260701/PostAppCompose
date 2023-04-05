package com.example.postappcompose.ui.newfeed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postappcompose.data.models.FavoritePost
import com.example.postappcompose.data.models.Post
import com.example.postappcompose.data.models.PostWithFavorite
import com.example.postappcompose.data.repository.FavoriteRepository
import com.example.postappcompose.data.repository.PostRepository
import com.example.postappcompose.extension.page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/*
 * Created by tuyenpc on 10/9/2022
 */

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    companion object {
        private const val step = 10
        private val listPost = MutableLiveData<MutableList<PostWithFavorite>>()
        private val listPostFavorite = MutableLiveData<MutableList<PostWithFavorite>>()
        private val listPostDetails = MutableLiveData<MutableList<PostWithFavorite>>()
        private var isEndPost = false
        private var isEndFavorite = false
    }

    internal val content = mutableStateOf("")

    fun getPostData(): LiveData<MutableList<PostWithFavorite>> = listPost

    fun getPostDataFavorite(): LiveData<MutableList<PostWithFavorite>> = listPostFavorite

    fun getPostDataDetail(): LiveData<MutableList<PostWithFavorite>> = listPostDetails

    @OptIn(DelicateCoroutinesApi::class)
    fun getPostByID(
        id: Int,
        onStart: () -> Unit,
        onResult: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = GlobalScope.launch(Dispatchers.Main) {
                onStart()
            }
            job.join()
            val result = postRepository.getPostByIDPost(id)
            withContext(Dispatchers.Main) {
                listPostDetails.value = mutableListOf()
                listPostDetails.value?.addAll(result)
                onResult()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getPost(
        isReload: Boolean,
        id: Int,
        onStart: () -> Unit,
        onResult: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = GlobalScope.launch(Dispatchers.Main) {
                onStart()
                if (isReload) {
                    listPost.value = mutableListOf()
                    isEndPost = false
                }
            }
            job.join()

            val result =
                postRepository.getPostByIDUser(id, page(listPost.value?.size ?: 0, step), step)
            withContext(Dispatchers.Main) {
                if (!isEndPost) {
                    val temp = mutableListOf<PostWithFavorite>()
                    listPost.value?.let { temp.addAll(it) }
                    temp.addAll(result)
                    listPost.value = mutableListOf()
                    listPost.value?.addAll(temp.distinct())
                }
                isEndPost = (result.size < step)
                onResult()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getPostFavorite(
        isReload: Boolean,
        id: Int,
        onStart: () -> Unit,
        onResult: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = GlobalScope.launch(Dispatchers.Main) {
                onStart()
                if (isReload) {
                    listPostFavorite.value = mutableListOf()
                    isEndFavorite = false
                }
            }

            job.join()

            val result = postRepository.getPostFavoriteByIDUser(
                id,
                page(listPostFavorite.value?.size ?: 0, step),
                step
            )
            withContext(Dispatchers.Main) {
                if (!isEndFavorite) {
                    val temp = mutableListOf<PostWithFavorite>()
                    listPostFavorite.value?.let { temp.addAll(it) }
                    temp.addAll(result)
                    listPostFavorite.value = mutableListOf()
                    listPostFavorite.value?.addAll(temp)
                }
                isEndFavorite = (result.size < step)
                onResult()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun insertPost(vararg post: Post, onStart: () -> Unit, onResult: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val job = GlobalScope.launch(Dispatchers.Main) {
                onStart()
            }
            job.join()

            postRepository.insertPost(*post)

            withContext(Dispatchers.Main) {
                onResult()
            }
        }
    }

    fun addFavorite(favoritePost: FavoritePost) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favoritePost)
        }
    }

    fun deleteFavorite(favoritePost: FavoritePost) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(favoritePost)
        }
    }

}
