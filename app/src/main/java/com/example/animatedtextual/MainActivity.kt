package com.example.animatedtextual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animatedtextual.Adapters.CustomAdapter
import com.example.animatedtextual.Models.CustomModel
import com.example.animatedtextual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private lateinit var list:ArrayList<CustomModel>
    private val selectList=ArrayList<CustomModel>()
    private var counter:Int=0
    var position:Int=-1
    var isActionMode:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list=ArrayList()
        list.add(CustomModel("Danish"))
        list.add(CustomModel("Hussain"))
        list.add(CustomModel("Ghulam"))
        list.add(CustomModel("Rahat"))
        list.add(CustomModel("Irfan"))
        list.add(CustomModel("Mughal"))
        with(binding)
        {
            customAdapter= CustomAdapter(this@MainActivity,list)
            myRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=customAdapter
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
        {
            ClearActionMode()
        }
        return super.onOptionsItemSelected(item)
    }
    fun ClearActionMode(){
        isActionMode=false
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        selectList.clear()
        customAdapter.notifyDataSetChanged()
    }
    fun StartSelection(index:Int)
    {
        if(!isActionMode)
        {
            isActionMode=true
            selectList.add(list[index])
            counter++
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            position=index
            customAdapter.notifyDataSetChanged()
        }
    }
    fun Check(myView:View,index:Int)
    {
        if((myView as CheckBox).isChecked)
        {
            selectList.add(list[index])
            counter++
            Log.i("Tag","Count: $counter")
        }
        else
        {
            selectList.remove(list[index])
            counter--
            Log.i("Tag","Count: $counter")
        }
    }
}