package br.senai.sp.cotia.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.senai.sp.cotia.myapplication.R;
import br.senai.sp.cotia.myapplication.databinding.FragmentInicioBinding;

public class FragmentInicio extends Fragment {
    private FragmentInicioBinding binding;
    private Button btJogar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        btJogar = binding.btJogar;

        btJogar.setOnClickListener(view -> {
            NavHostFragment.findNavController(FragmentInicio.this)
                        .navigate(R.id.action_fragmentInicio_to_fragmentJogo);
        });
        return binding.getRoot();
    }

}