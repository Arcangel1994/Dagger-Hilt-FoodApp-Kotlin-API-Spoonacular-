package mx.com.gilsa.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.com.gilsa.foody.R
import mx.com.gilsa.foody.adapters.IngredientsAdapter
import mx.com.gilsa.foody.databinding.FragmentIngredientsBinding
import mx.com.gilsa.foody.models.Result
import mx.com.gilsa.foody.util.Constants

class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private val ingredientsAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        setupRecyclerView()
        myBundle?.extendedIngredients?.let { ingredientsAdapter.setData(it) }

    }

    private fun setupRecyclerView(){
        binding.ingredientsRecyclerView.adapter = ingredientsAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}