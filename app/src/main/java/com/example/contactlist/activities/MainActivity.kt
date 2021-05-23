package com.example.contactlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactlist.R
import com.example.contactlist.adapters.ContactCategoryAdapter
import com.example.contactlist.models.ContactCategory

const val CATEGORY_NAME_INTENT_EXTRA_KEY = "cat_name"
class MainActivity : AppCompatActivity(), ContactCategoryAdapter.OnItemClickListener {

    private lateinit var binding: com.example.contactlist.databinding.ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.contactlist.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpCategories()

    }

    private fun setUpCategories() {
        binding.apply {
            contactCategoryRecyclerView.adapter = ContactCategoryAdapter(getCategories(), this@MainActivity)
            contactCategoryRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            contactCategoryRecyclerView.setHasFixedSize(true)
        }
    }


    private fun getCategories() = listOf(
        ContactCategory("Family", R.drawable.ic_family),
        ContactCategory("Work", R.drawable.ic_work),
        ContactCategory("Friends", R.drawable.ic_friends),
        ContactCategory("School", R.drawable.ic_school)
    )

    override fun onItemClick(category: ContactCategory) {
        val categoryIntent = Intent(this, ContactListActivity::class.java)
        categoryIntent.putExtra(CATEGORY_NAME_INTENT_EXTRA_KEY, category.name)
        startActivity(categoryIntent)
    }

    companion object {
        lateinit var USER_KEY: String
    }
}