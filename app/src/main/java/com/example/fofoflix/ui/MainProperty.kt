package com.example.fofoflix.ui

import PropertyAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fofoflix.R

class MainProperty : AppCompatActivity() {

    private lateinit var propertyRecyclerView: RecyclerView
    private lateinit var propertyAdapter: PropertyAdapter
    private val properties = mutableListOf<Property>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        propertyRecyclerView = findViewById(R.id.propertyRecyclerView)
        propertyRecyclerView.layoutManager = LinearLayoutManager(this)
        propertyAdapter = PropertyAdapter(properties) {
            showAddPropertyDialog()
        }
        propertyRecyclerView.adapter = propertyAdapter

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            showAddPropertyDialog()
        }
    }

    private fun showAddPropertyDialog() {
        val dialogView = layoutInflater.inflate(R.layout.modal_property, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val titleInput = dialogView.findViewById<EditText>(R.id.titleInput)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.descriptionInput)
        val locationInput = dialogView.findViewById<EditText>(R.id.locationInput)
        val priceNightInput = dialogView.findViewById<EditText>(R.id.priceNightInput)
        val maxGuestsInput = dialogView.findViewById<EditText>(R.id.maxGuestsInput)
        val saveButton = dialogView.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()
            val location = locationInput.text.toString()
            val priceNight = priceNightInput.text.toString().toDoubleOrNull() ?: 0.0
            val maxGuests = maxGuestsInput.text.toString().toIntOrNull() ?: 0

            if (title.isNotEmpty() && description.isNotEmpty() && location.isNotEmpty() && priceNight > 0 && maxGuests > 0) {
                val property = Property(title, description, location, priceNight, maxGuests)
                properties.add(property)
                propertyAdapter.notifyDataSetChanged()
                alertDialog.dismiss()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
    }

    data class Property(
        val title: String,
        val description: String,
        val location: String,
        val priceNight: Double,
        val maxGuests: Int
    )
}
