package com.example.a220410_rvdatabinding_clone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a220410_rvdatabinding_clone.databinding.ItemBinding


/* 리사이클러뷰 어댑터 클래스*/
class MyAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //아이템뷰 리스트
    var List_user = emptyList<User>()

    init {
        setHasStableIds(true)
    }

    //아이템클릭 리스너 객체
    //리사이클러뷰를 클릭 할 수 있는 리스너 객체
    //리사이클러뷰의 아이템뷰의 클릭을 감지해준다.
    private var listener: OnItemClickListener? = null

    //아이템 클릭 인터페이스
    interface OnItemClickListener {
        fun onItemClick(v: View, data: User, pos: Int)
    }

    //아이템 클릭 인터페이스를 전달
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setData(List_user: List<User>) {
        this.List_user = List_user
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyAdapter.ViewHolder).bind(List_user[position])
    }

    //아이템(모집 글) 갯수 출력
    override fun getItemCount(): Int = List_user.size


    //아이템 조회
    fun getItem(position: Int): User? {
        return List_user.get(position)
    }

    //아이템 갯수 조회
    fun getSize(): Int {
        return this.List_user.size
    }

    /*//아이템 리스트 조회
    fun getList(): ArrayList<User> {
        return this.List_user
    }

    //아이템 리스트 초기화
    fun clear() {
        this.List_user.clear()
    }

    //아이템 추가
    fun addItem(item: User?) {
        if (item != null) {
            List_user.add(item)
        } //item:리스트에들어갈데이터객체
    }

    //리사이클러뷰 가장 위에 추가
    fun addItemFirst(item: User?) {
        if (item != null) {
            List_user.add(0, item)
        } //item:리스트에들어갈데이터객체
    }

    //아이템 수정
    fun edit_Item(position: Int, item: User?) {
        if (item != null) {
            List_user.set(position, item)
        }
    }

    //아이템 수정 후 리사이클러뷰 맨 위에 배치
    fun edit_Item_first(position: Int, item: User?) {
        if (item != null) {
            //수정해야 할 아이템뷰 삭제
            List_user.removeAt(position)
            //리사이클러뷰 맨위에 추가
            List_user.add(0, item)
        }
    }

    //아이템 삭제
    fun removeItem(position: Int) {
        List_user.removeAt(position)
    }*/
    //매개변수에 현재 binding하고 있는 레이아웃 변수를 입력
    //반환값으로 아이템뷰를 반환한다.
    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //아이템 View(모집 글)에 모집 글 정보 입력
        //매개변수에 현재 binding하고 있는 레이아웃 변수를 입력
        fun bind(item: User) {
            //레이아웃에 User 객체를 바인딩 해준다.
            //하나하나 레이아웃에서 정의할 필요없이 데이터변경되면 알아서 반영된다.
            binding.user = item

            //리사이클러뷰 아이템뷰 리스너
            itemView.setOnClickListener {
                //클릭한 아이템뷰의 인덱스 번호 변수에 저장
                val pos = adapterPosition
                //클릭이 되서 아이템뷰의 인덱스 번호를 받은 경우에만 실행
                if (pos != RecyclerView.NO_POSITION) {
                    //1.리사이클러뷰 수정, 삭제 팝업뷰 옵션버튼
                    listener?.onItemClick(itemView, item, pos)
                }
            }
        }
    }
}

