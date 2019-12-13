package com.wyl.wegame.gallery


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wyl.wegame.R
import com.wyl.wegame.bean.GirlItem
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {
    companion object {
        const val TAG = "GalleryFragment"
    }

    private lateinit var tempData: List<GirlItem>

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(GalleryViewModel::class.java)
    }

    private val mLayoutManager by lazy { GridLayoutManager(requireContext(), 2) }

    private val mAdapter by lazy { GalleryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")

        viewModel.fetchData(true)

        viewModel.photoListLive.observe(this, Observer {
            tempData = if (viewModel.isRefresh) {
                refreshLayout.finishRefresh()
                it
            } else {
                refreshLayout.finishLoadMore()
                tempData + it
            }
            mAdapter.submitList(tempData)
        })

        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        refreshLayout.apply {
            setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    viewModel.fetchData(false)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    viewModel.fetchData(true)
                }
            })
        }
    }

}
