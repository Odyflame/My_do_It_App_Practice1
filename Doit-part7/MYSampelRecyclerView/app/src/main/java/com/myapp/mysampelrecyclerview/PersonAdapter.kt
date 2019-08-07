package com.myapp.mysampelrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.viewHolder>() {

    var items = ArrayList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        //뷰홀더 객체가 만들어질 때 사용됨
        //각 아이템을 위해 정의한 xml레이아웃을 이용해서 뷰 객체를 만들어줍니다. 그리고 뷰 객체를 새로 만든 뷰 홀더에 담아 반환하다.
        //뷰 타입을 위한 정수값이 파라미터로 주어진다. 이는 각 아이템을 위한 뷰를 여러 가지로 나누어 보여주고 싶을 때 사용된다. 어떤 때는 이미지를 보여주고
        //어떤 때는 이비지와 텍스트를 같이 보여주고 싶다면 뷰 타입을 정하고 각각의 뷰 타입에 따라 다른 xml레이아웃을 인플레이션하여 보여줄 수 있다.
        //일반적인 경우에는 뷰타입을 한가지로 하는 경우가 많기 때문에 여기서는 뷰타입 파라미터를 따로 사용하지는 않는다.

        var inflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.person_item, parent, false)

        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        //ArrayList아넹 들어있는 전체 아이템의 개수를 알아야 하므로 size를 호출한다,
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        //뷰홀더 객체가 재사용될 때 사용된다.
        //뷰홀더에 현재 아이템에 맞는 데이터만 설정한다. 데이터는 Person객체로 만드는데 여러 아이템을 이 어댑터에서 관라해야 하기 때문에 클래스 안에 ArrayList
        //자료형으로 된 items라는 변수를 이용해서 onBindViewHolder메서드로 전달된 position파라미터를 이용해 ArrayLiist에서 Person객체를 꺼내어 설정할 수 있다.

        var item = items[position]
        holder.setItem(item)
    }

    lateinit var textView: TextView
    lateinit var textView2: TextView

    inner class viewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {
            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
        }

        fun setItem(item: Person) {
            textView.setText(item.name)
            textView2.setText(item.moblie)
        }
    }

    fun addItem(item : Person){
        items.add(item)
    }

    fun setItems1(items : ArrayList<Person>) {
        this.items = items
    }

    fun getItem(position: Int) : Person{
        return items[position]
    }

    fun setItem(position: Int, item: Person){
        items.set(position, item)
    }
}