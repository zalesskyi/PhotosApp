package com.zalesskyi.photosapp.features.list

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.photosapp.R
import com.zalesskyi.photosapp.base.BaseFragment
import com.zalesskyi.photosapp.base.ProgressEvent
import com.zalesskyi.photosapp.databinding.FragmentPhotosListBinding
import com.zalesskyi.photosapp.extensions.skeleton
import com.zalesskyi.photosapp.navigation.MainNavigator
import com.zalesskyi.photosapp.tools.PaginationListener
import com.zalesskyi.photosapp.tools.view_binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotosListFragment : BaseFragment<PhotosListViewModel>(R.layout.fragment_photos_list) {

    override val viewModel: PhotosListViewModel by viewModels<PhotosListViewModelImpl>()

    private val binding by viewBinding(FragmentPhotosListBinding::bind)

    @Inject
    lateinit var navigator: dagger.Lazy<MainNavigator>

    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter(listOf(),
            onItemClick = { photo, view ->
                navigator.get().toPhotoDetail(photo,
                    FragmentNavigatorExtras(view to photo.url))
        },
            onFavoriteClick = viewModel::toggleFavorite)
    }

    private val paginationListener = object : PaginationListener() {

        override fun loadMoreItems() {
            viewModel.loadMorePhotos()
        }

        override fun isLoading(): Boolean =
            viewModel.progressLiveData.value == ProgressEvent.LoadMore

        override fun needToLoadMore(): Boolean =
            viewModel.needToLoadMore
    }

    override fun observeViewModel() {
        viewModel.run {
            photosLiveData.observe(::onArrived)
            morePhotosLiveData.observe(::onMoreArrived)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPhotos.adapter = adapter
        binding.rvPhotos.addOnScrollListener(paginationListener)
        viewModel.loadPhotos()
    }

    override fun skeletonVisible(visible: Boolean) {
        super.skeletonVisible(visible)
        if (visible) {
            adapter.updateAllNotify(listOf<Parcelable>().skeleton())
        }
    }

    private fun onArrived(data: List<Photo>) {
        binding.run {
            lawError.isVisible = false
            rvPhotos.isVisible = true
            adapter.updateAllNotify(data)
        }
    }

    private fun onMoreArrived(data: List<Photo>) {
        adapter.add(data)
    }
}