package us.nilesh.bpbo.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import us.nilesh.bpbo.R;
import us.nilesh.bpbo.ui.MainActivity;

public class VerifyFragment extends Fragment {
    View view;
//    private static final String TAG = "VerifyFragment";
    private FirebaseAuth mAuth;
    private ExtendedFloatingActionButton vbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_verify, container, false);

        mAuth = FirebaseAuth.getInstance();
        vbtn=(ExtendedFloatingActionButton)view.findViewById(R.id.retryFab);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        vbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(VerifyFragment.this)
                        .navigate(R.id.action_verifyFragment_to_loginFragment);
            }
        });

        if (currentUser!=null && currentUser.isEmailVerified()){
            Intent intent=new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }else {
            NavHostFragment.findNavController(VerifyFragment.this)
                    .navigate(R.id.action_verifyFragment_to_loginFragment);
        }
    }
}