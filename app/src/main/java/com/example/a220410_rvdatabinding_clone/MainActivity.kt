package com.example.a220410_rvdatabinding_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a220410_rvdatabinding_clone.Dialog.CustomDialog
import com.example.a220410_rvdatabinding_clone.Dialog.CustomDialogInterface
import com.example.a220410_rvdatabinding_clone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//@Android Entry Point 어노테이션이
//추가된 안드로이드 클래스에 DI 컨테이너를 추가해준다.
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CustomDialogInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter

    //    private lateinit var userViewModel: UserViewModel
    private var customDialog: CustomDialog? = null
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myAdapter = MyAdapter()
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.setHasFixedSize(true)

        /*     userViewModel =
                 ViewModelProvider(this, UserViewModel.Factory(application))[UserViewModel::class.java]
             binding.viewModel = userViewModel*/


        customDialog = CustomDialog(this, this)

        //처음에만 List 반환
        userViewModel.readAllData.observe(this@MainActivity,
            Observer {
                //List의 상태변화가 생기면 setData()메서드 실행
                myAdapter.setData(it)
            })

    }

    // Fab 클릭시 다이얼로그 띄움
    fun onFabClicked(view: View) {
        //커스텀 다이얼로그 생성
        customDialog?.show()
    }

    override fun onAddButtonClicked(name: String, age: Int) {
        val user = User(0, name, age)
        userViewModel.addUser(user)
        Toast.makeText(this, "이름 : $name , 나이 : $age 추가", Toast.LENGTH_SHORT).show()
    }

    override fun onCancelButtonClicked() {
        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
    }
}