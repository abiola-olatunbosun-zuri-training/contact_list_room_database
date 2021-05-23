package com.example.contactlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactlist.adapters.ContactsAdapter
import com.example.contactlist.fragments.CreateContactDialog
import com.example.contactlist.models.Contact

class ContactListActivity : AppCompatActivity(), CreateContactDialog.OnSaveContactListener {

    private lateinit var binding: com.example.contactlist.databinding.ActivityContactListBinding
    private lateinit var contacts: MutableList<Contact>
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.contactlist.databinding.ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.extras?.getString(CATEGORY_NAME_INTENT_EXTRA_KEY)
        categoryName?.let {
            title = "$it Contacts"
        }
        contacts = mutableListOf()
        adapter = ContactsAdapter(contacts)
        binding.contactListRecyclerView.adapter = adapter

        binding.addNewContactFab.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val fragmentManager = supportFragmentManager
        val createContactAlertDialog = CreateContactDialog()
        createContactAlertDialog.show(fragmentManager, null)
    }

    override fun onSave(contact: Contact) {
        contacts.add(contact)
        adapter.notifyItemInserted(contacts.size - 1)
    }
}