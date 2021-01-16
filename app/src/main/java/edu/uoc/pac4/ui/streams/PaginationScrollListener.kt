package edu.uoc.pac4.ui.streams

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by alex on 07/09/2020.
 */


//Con la solución que había antes cargabas varias paginas a la vez. Lo he modificado para que funcione mejor.
abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
//        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val visibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
//        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
//            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
//                && firstVisibleItemPosition >= 0
//            ) {
//                loadMoreItems()
//            }
            if (visibleItemPosition == totalItemCount - 1) {
                recyclerView.post {
                    loadMoreItems()
                }
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean

}