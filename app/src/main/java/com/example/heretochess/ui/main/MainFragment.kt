package com.example.heretochess.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.heretochess.App
import com.example.heretochess.dagger.AppComponent
import com.example.heretochess.databinding.FragmentMainBinding
import com.example.heretochess.model.ChessModel
import com.example.heretochess.model.ChessPiece
import com.example.heretochess.vm.MainViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (requireActivity().application as App).appComponent
        viewModel = appComponent.getMainViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<ArrayList<ArrayList<ChessPiece?>>>{setBoard(it)}
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getBoard()
    }

    private fun setBoard(board: ArrayList<ArrayList<ChessPiece?>>) = with(binding) {
        chessView.setModel(board)
//        tv.text = board.chessModel.toString()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
