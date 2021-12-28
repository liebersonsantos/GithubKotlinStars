package br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import br.com.liebersonsantos.githubkotlinstars.databinding.ItemBinding
import com.bumptech.glide.Glide

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
class HomeAdapter(private val clickedItem: ((domain: RepositoryDomain) -> Unit)) :
    ListAdapter<RepositoryDomain, HomeAdapter.AdapterViewHolder>(DIFF_CALLBACK) {
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(itemBinding, clickedItem)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
        animation(holder.itemView, position)
    }


    class AdapterViewHolder(
        private val item: ItemBinding,
        private val clickedItem: (repository: RepositoryDomain) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(domain: RepositoryDomain) {
            item.run {
                userName.text = domain.userName
                repositoryName.text = domain.repositoryName
                description.text = domain.description
                forks.text = domain.forks.toString()
                star.text = domain.stars.toString()
                Glide.with(itemView).load(domain.avatar).into(image)

                itemView.setOnClickListener {
                    clickedItem.invoke(domain)
                }
            }
        }

    }

    private fun animation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(view.context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
            lastPosition = position
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepositoryDomain>() {
            override fun areItemsTheSame(oldItem: RepositoryDomain, newItem: RepositoryDomain): Boolean {
                return oldItem.htmlUrl == newItem.htmlUrl
            }

            override fun areContentsTheSame(oldItem: RepositoryDomain, newItem: RepositoryDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}