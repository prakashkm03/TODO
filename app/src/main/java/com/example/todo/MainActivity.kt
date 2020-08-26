package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todolist = ArrayList<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, todolist)

        list_view.adapter = adapter

        add.setOnClickListener {

            if(edit_text.text.isEmpty())
            {
                Toast.makeText(this, "You haven't entered anything.",Toast.LENGTH_SHORT).show()
            }
            else
            {
                todolist.add(edit_text.text.toString())
                adapter.notifyDataSetChanged()
                text_view2.visibility = View.GONE

                edit_text.text.clear()
            }
        }

        delete.setOnClickListener {

            // pos contains each item checked or not
            val pos : SparseBooleanArray = list_view.checkedItemPositions
            var count  = list_view.count
            var itempos : Int = count -1

            while(itempos >= 0)
            {
                //if item checked
                if(pos.get(itempos))
                {
                    todolist.removeAt(itempos)
                }
                itempos--
            }
            if(todolist.isEmpty())
            {
                text_view2.visibility = View.VISIBLE
            }
            pos.clear()
            adapter.notifyDataSetChanged()

        }

        clear.setOnClickListener {
            todolist.clear()
            adapter.notifyDataSetChanged()
            text_view2.visibility = View.VISIBLE
        }

        list_view.setOnItemClickListener { adapterView, view, i, l ->

            val pos : SparseBooleanArray = list_view.checkedItemPositions

            if(pos.get(i))
            {
            Toast.makeText(this, "Nice!! Your did ${todolist[i]}.",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Good luck for ${todolist[i]}.",Toast.LENGTH_SHORT).show()
            }

        }





    }
}