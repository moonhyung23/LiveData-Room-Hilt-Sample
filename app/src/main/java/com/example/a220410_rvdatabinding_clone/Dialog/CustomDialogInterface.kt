package com.example.a220410_rvdatabinding_clone.Dialog

interface CustomDialogInterface {
    //다이얼로그에서 추가 버튼
    fun onAddButtonClicked(name: String, age: Int)


    //취소 버튼
    fun onCancelButtonClicked()

}