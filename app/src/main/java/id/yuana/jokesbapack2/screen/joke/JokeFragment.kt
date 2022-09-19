package id.yuana.jokesbapack2.screen.joke

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.jokesbapack2.R
import id.yuana.jokesbapack2.util.RandomColor

@Deprecated("migrate using compose")
@AndroidEntryPoint
class JokeFragment : Fragment(R.layout.fragment_joke) {

    private val viewModel by viewModels<JokeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvContent = view.findViewById<TextView>(R.id.tvJokeContent).apply {
            setOnClickListener {
                viewModel.getRandomJoke()
            }
        }

//        viewModel.jokeContent.observe(viewLifecycleOwner, Observer { content ->
//            tvContent.apply {
//                text = content
//                background = RandomColor.get()
//            }
//        })

    }

}