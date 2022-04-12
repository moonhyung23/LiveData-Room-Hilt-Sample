package com.example.a220410_rvdatabinding_clone.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.a220410_rvdatabinding_clone.databinding.DialogCustomBinding

class CustomDialog(context: Context, C_Interface: CustomDialogInterface) : Dialog(context) {
    //class name {
    val binding by lazy { DialogCustomBinding.inflate(layoutInflater) }

    //다이얼로그인터페이스를 생성한 MainActivity에서 생성자로 받아옴
    private var c_interface: CustomDialogInterface = C_Interface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 배경을 투명하게함
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //추가 버튼
        binding.addBtn.setOnClickListener {
            val name = binding.nameEditView.text.toString()
            val age = binding.ageEditView.text.toString()
            //둘 중에 하나라도 입력 되었을 때
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age)) {
                Toast.makeText(context, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                //입력 된 경우
                c_interface.onAddButtonClicked(name, Integer.parseInt(age))
                dismiss()
            }
        }
        //취소 버튼
        binding.cancelBtn.setOnClickListener {
            c_interface.onCancelButtonClicked()
            dismiss()
        }
    }
}