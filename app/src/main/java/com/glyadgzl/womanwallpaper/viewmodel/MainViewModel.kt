import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val repository = MainRepository()
    
    fun loadUpcoming(): LiveData<MutableList<WomanItemModel>> {
        return repository.loadUpcoming()
    }

    fun loadItems(): LiveData<MutableList<WomanItemModel>> {
        return repository.loadItems()
    }
}