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
import com.example.heretochess.model.cards.Card
import com.example.heretochess.model.chess.ChessPiece
import com.example.heretochess.vm.MainViewModel


class MainFragment : Fragment(), ChessViewInterface {

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
        binding.chessView.chessViewInterface = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardObserver = Observer<ArrayList<ArrayList<ChessPiece?>>>{setBoard(it)}
        viewModel.getBoardLiveData().observe(viewLifecycleOwner, boardObserver)
        viewModel.getBoard()

        val handObserver = Observer<ArrayList<Card>>{setHand(it)}
        viewModel.getHandLiveData().observe(viewLifecycleOwner, handObserver)
        viewModel.getHand()
    }

    private fun setHand(hand: ArrayList<Card>) = with(binding) {
        chessView.setHand(hand)
        chessView.invalidate()
    }

    private fun setBoard(board: ArrayList<ArrayList<ChessPiece?>>) = with(binding) {
        chessView.setBoard(board)
        chessView.invalidate()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onRegularMove(movingPiece: ChessPiece, toRow: Int, toCol: Int) {
        viewModel.regularMove(movingPiece, toRow, toCol)
    }

    override fun onRemovePiece(row: Int, col: Int) {
        viewModel.removePiece(row, col)
    }
}
