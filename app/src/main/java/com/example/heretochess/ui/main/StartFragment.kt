package com.example.heretochess.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.heretochess.App
import com.example.heretochess.dagger.AppComponent
import com.example.heretochess.databinding.FragmentStartBinding
import com.example.heretochess.ui.vm.StartViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StartViewModel
    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (requireActivity().application as App).appComponent
        viewModel = appComponent.getStartViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            createBtn.setOnClickListener {
                if (lobbyNameEdit.text.toString() == ""){
                    Toast.makeText(requireContext(), "Fill lobby's name", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.createLobby(lobbyNameEdit.text.toString())
                    lobbyNameEdit.text.clear()
                }
            }
            joinBtn.setOnClickListener {
                if (lobbyNameEdit.text.toString() == ""){
                    Toast.makeText(requireContext(), "Fill lobby's name", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.joinLobby(lobbyNameEdit.text.toString())
                    lobbyNameEdit.text.clear()
                }
            }
            startBtn.setOnClickListener {
                viewModel.startGame()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }
}