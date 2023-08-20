import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        // 表示するアイテムが足りなくなった際に追加で取得する
        if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            loadMoreItems()
        }
    }

    abstract fun loadMoreItems()
}