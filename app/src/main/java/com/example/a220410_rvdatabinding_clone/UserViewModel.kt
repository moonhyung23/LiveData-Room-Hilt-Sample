package com.example.a220410_rvdatabinding_clone

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//@HiltViewModel 어노테이션이 붙은 ViewModel은 HiltViewModelFactory에
//의해 생생되고
//@HiltViewModel에서 @Inject 어노테이션이 붙은 생성자는
//생성자 파라미터가 HIlt에 의해서 주입받으르 거라는 거라고
//정의내리는 종속성을 갖는다.
class UserViewModel @Inject internal constructor(private val repository: UserRepository) :
    ViewModel() {
    //LiveData 리사이클러뷰의 값의 상태변경을 알림
//    private val repository: UserRepository

// 리스트 대신에 flow를 사용해서 flow에서 List를 사용
//    private var List_user = ArrayList<User>()


    val readAllData: LiveData<List<User>>

    init {
        /*   val userDao = UserDatabase.getDatabase(application)!!.userDao()
           repository = UserRepository(userDao)*/
        //Flow를 통해서 DB에 갖고온 데이터를 LiveData 형식으로 변경
        readAllData = repository.readAllData.asLiveData()
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    //쿼리를 입력값으로 사용해서 LiveData를 반환
    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    /*//팩토리 메서드 패턴을 사용해 ViewModel을 반환
    //UserViewModel을 Factory안에서 생성해서 생성자의 수정이 일어나도
    //코드의 수정이 여러곳에서 생기지 않게 의존성을 줄임.
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(application) as T
        }
    }*/
}