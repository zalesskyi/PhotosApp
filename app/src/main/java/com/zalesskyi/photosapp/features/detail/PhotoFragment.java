package com.zalesskyi.photosapp.features.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.TransitionInflater;

import com.zalesskyi.domain.models.Photo;
import com.zalesskyi.photosapp.R;
import com.zalesskyi.photosapp.base.BaseFragment;
import com.zalesskyi.photosapp.databinding.FragmentPhotoBinding;
import com.zalesskyi.photosapp.extensions.ImageVIewExtKt;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhotoFragment extends BaseFragment<PhotoViewModel> {

    private FragmentPhotoBinding binding;

    public PhotoFragment() {
        super(R.layout.fragment_photo);
    }

    @NotNull
    @Override
    protected PhotoViewModel getViewModel() {
        return new ViewModelProvider(this).get(PhotoViewModelImpl.class);
    }

    @Override
    protected void observeViewModel() {
        getViewModel().getFavoriteLiveData().observe(getViewLifecycleOwner(), photo -> {
            binding.ivFavorite.setSelected(photo.isFavorite());
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(
                TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPhotoBinding.bind(view);
        Photo photo;
        if ((photo = getPhotoUrl()) != null) {
            binding.ivPreview.setTransitionName(photo.getUrl());
            ImageVIewExtKt.image(binding.ivPreview, photo.getUrl(), R.drawable.ic_image);
            binding.ivFavorite.setSelected(photo.isFavorite());
        }
        binding.ivPreview.setOnClickListener(v -> {
            getViewModel().toggleFavorite(photo);
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    @Nullable
    private Photo getPhotoUrl() {
        return getArguments() != null
                ? PhotoFragmentArgs.fromBundle(getArguments()).getPhoto()
                : null;
    }
}
