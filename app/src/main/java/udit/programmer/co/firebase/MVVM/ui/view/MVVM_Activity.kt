package udit.programmer.co.firebase.MVVM.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mvvm_.*
import udit.programmer.co.firebase.MVVM.data.models.User
import udit.programmer.co.firebase.MVVM.ui.adapter.UserAdapter
import udit.programmer.co.firebase.MVVM.ui.viewmodel.GitHubViewModel
import udit.programmer.co.firebase.R

class MVVM_Activity : AppCompatActivity() {

    val vm by lazy {
        ViewModelProvider(this).get(GitHubViewModel::class.java)
    }

    val data = arrayListOf<User>()
    val adapter = UserAdapter(data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_)

        rv_layout.apply {
            layoutManager = LinearLayoutManager(this@MVVM_Activity)
            adapter = this@MVVM_Activity.adapter
        }

        vm.fetchUsers()
        vm.users.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                data.addAll(it)
            }
        })

    }
}
