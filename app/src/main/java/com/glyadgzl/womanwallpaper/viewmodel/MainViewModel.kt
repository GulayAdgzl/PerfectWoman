import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val repository = MainRepository()
    
    fun loadUpcoming(): LiveData<MutableList<WomanItem>> {
        return repository.loadUpcoming()
    }

    fun loadItems(): LiveData<MutableList<WomanItem>> {
        return repository.loadItems()
    }
}