import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn


class MainRepository{
    private val firebaseDatabase= FirebaseDatabase.getInstance()

    fun loadUpcoming(): LiveData<MutableList<WomanItemModel>> {
        val listData= MutableLiveData<MutableList<WomanItemModel>>()
        val ref=  firebaseDatabase.getReference("Upcoming")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists=mutableListOf<WomanItemModel>()
                for (i in snapshot.children){
                    val item=i.getValue(WomanItemModel::class.java)
                    item?.let{lists.add(it)}
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository",error.message)
            }

        })
        return listData
    }

    fun loadItems():LiveData<MutableList<WomanItemModel>>{
        val listData=MutableLiveData<MutableList<WomanItemModel>>()
        val ref=  firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists=mutableListOf<WomanItemModel>()
                for (i in snapshot.children){
                    val item=i.getValue(WomanItemModel::class.java)
                    item?.let{lists.add(it)}
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository",error.message)
            }

        })
        return listData
    }
}