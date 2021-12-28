package br.com.liebersonsantos.githubkotlinstars.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import br.com.liebersonsantos.githubkotlinstars.R
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import com.bumptech.glide.Glide

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
class CustomFlipper(context: Context?, attrs: AttributeSet?) : ViewFlipper(context, attrs) {

    private var list = mutableListOf<RepositoryDomain>()

    fun setList(sliderList: MutableList<RepositoryDomain>) {
        this.list = sliderList
    }

    fun setLayout() {
        val flipperList = mutableListOf<RepositoryDomain>()
        list.forEach {
            flipperList.add(it)
        }

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        this.setInAnimation(context, android.R.anim.slide_in_left)
        this.setOutAnimation(context, android.R.anim.slide_out_right)

        for (i in flipperList.indices) {
            val view: View = inflater.inflate(R.layout.item_flipper, this, false)

            val img = view.findViewById(R.id.imgFlipper) as ImageView
            val tvName = view.findViewById(R.id.nameFlipper) as TextView
            val tvForks = view.findViewById(R.id.numberForks) as TextView
            val tvStars = view.findViewById(R.id.numberStar) as TextView

            tvName.text = flipperList[i].repositoryName
            tvForks.text = flipperList[i].forks.toString()
            tvStars.text = flipperList[i].stars.toString()

            Glide.with(context).load(flipperList[i].avatar).into(img)

            this.addView(view, i)
        }
    }
}