package com.gpetuhov.android.sampleprotobuf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pawegio.kandroid.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savePerson.setOnClickListener { savePerson() }
    }

    private fun savePerson() {
        val name = personName.text.toString()
        val age = personAge.text.toString().toIntOrNull() ?: 0
        val email = personEmail.text.toString()

        if (name != "" && email != "") {
            // Create Person by using builder
            val person = PersonProtoClass.Person.newBuilder()
                .setName(name)
                .setAge(age)
                .setEmail(email)
                .build()

            try {
                // Serialize person and write to file
                val path = filesDir.path
                val file = File("$path/person.txt")
                val output = FileOutputStream(file)
                person.writeTo(output)

                toast("Person saved to file")

            } catch (e: Exception) {
                toast("Error saving person to file")
            }

        } else {
            toast("Name and email must not be empty")
        }
    }
}
