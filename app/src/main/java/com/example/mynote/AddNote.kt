package com.example.mynote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.example.mynote.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note:com.example.mynote.models.Note
    private lateinit var old_note:com.example.mynote.models.Note
    var isUpdate=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

       try {
           old_note=intent.getSerializableExtra("current_note") as com.example.mynote.models.Note
           binding.tvTitle.setText(old_note.title)
           binding.tvNote.setText(old_note.note)
           isUpdate=true
       }catch (e:java.lang.Exception){
           e.printStackTrace()
       }
        binding.imgCheck.setOnClickListener {
            val title=binding.tvTitle.text.toString()
            val note_desc=binding.tvNote.text.toString()
            if(title.isNotEmpty()||note_desc.isNotEmpty()){
                val formatter=SimpleDateFormat("EEE ,d MMM yyyy HH:mm a")

                if(isUpdate){
                    note=com.example.mynote.models.Note(
                        old_note.id,title,note_desc,formatter.format(Date())
                    )

                }else{
                    note=com.example.mynote.models.Note(null,title,note_desc,formatter.format(Date()))
                }
                val intent =Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else {
                Toast.makeText(this@AddNote,"please enter some data",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrow.setOnClickListener {
            onBackPressed()

        }
    }
}