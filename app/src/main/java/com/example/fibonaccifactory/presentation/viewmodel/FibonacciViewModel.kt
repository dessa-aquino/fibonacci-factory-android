import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fibonaccifactory.domain.model.FibonacciResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FibonacciViewModel : ViewModel() {
    private val _fibonacciResults = MutableStateFlow<List<FibonacciResult>>(emptyList())
    val fibonacciResults: StateFlow<List<FibonacciResult>> = _fibonacciResults.asStateFlow()

    fun calculateAndUpdateSequence(n: Int) {
        viewModelScope.launch {
            val sequence = generateFibonacciSequence(n)
            _fibonacciResults.value = sequence
        }
    }

    private fun generateFibonacciSequence(n: Int): List<FibonacciResult> {
        if (n < 0) return emptyList()

        val results = mutableListOf<FibonacciResult>()
        results.add(FibonacciResult(0, 0))

        if (n == 0) return results

        results.add(FibonacciResult(1, 1))
        if (n == 1) return results

        var a = 0
        var b = 1

        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
            results.add(FibonacciResult(i, b))
        }

        return results
    }
}