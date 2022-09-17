package id.yuana.jokesbapack2.screen.splash

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.jokesbapack2.R

@Deprecated("please remove this")
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.next.observe(viewLifecycleOwner, Observer {
            when (it) {
                SplashViewModel.LOADING -> {
                    view.findViewById<TextView>(R.id.tvTitleApp).apply {
                        text = "Loading..."
                    }
                }
                SplashViewModel.GOTO_JOKE -> findNavController().navigate(R.id.action_splashFragment_to_jokeFragment)
            }
        })
    }
}