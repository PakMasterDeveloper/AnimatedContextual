package com.example.animatedtextual.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import com.example.animatedtextual.MainActivity
import com.example.animatedtextual.Models.CustomModel
import com.example.animatedtextual.ViewHolders.CustomViewHolder
import com.example.animatedtextual.databinding.CustomRowBinding

class CustomAdapter(private val context: Context,private val myList: ArrayList<CustomModel>):RecyclerView.Adapter<CustomViewHolder>() {
    private var myActivity: MainActivity=context as MainActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding=CustomRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.text.text=myList[position].UserName
        if(myActivity.position==position)
        {
            holder.binding.checkBox.isChecked=true
            myActivity.position=-1
        }
        if(myActivity.isActionMode)
        {
            val myAnim=Anim(100,holder.binding.linearLayout)
            myAnim.duration=300
            holder.binding.linearLayout.animation=myAnim
        }
        else
        {
            val myAnim=Anim(0,holder.binding.linearLayout)
            myAnim.duration=300
            holder.binding.linearLayout.animation=myAnim
            holder.binding.checkBox.isChecked=false
        }
        holder.binding.myCard.setOnLongClickListener {
            myActivity.StartSelection(position)
            return@setOnLongClickListener true
        }
        holder.binding.checkBox.setOnClickListener {
            myActivity.Check(it,position)
        }
    }

    override fun getItemCount(): Int=myList.size
}
class Anim(private val width:Int,private val myView:View): Animation()
{
    private val startWidth=myView.width
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val newWidth:Int=startWidth+ ((width-startWidth)*interpolatedTime).toInt()
        myView.layoutParams.width=newWidth
        myView.requestLayout()
        super.applyTransformation(interpolatedTime, t)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}