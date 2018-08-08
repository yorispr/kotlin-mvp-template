package com.yorisprayogo.mvptemplate.view.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by YORIS on 06/03/18.
 */

abstract class BaseListAdapter<T>(private val context: Context?): RecyclerView.Adapter<BaseListAdapter<T>.ViewHolder>() {

    private var items: MutableList<T>? = mutableListOf()
    private var listener: OnViewHolderClick<T>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createView(context, parent, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindView(getItem(position), holder, position)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    protected abstract fun createView(context: Context?, viewGroup: ViewGroup?, viewType: Int): View

    protected abstract fun bindView(item: T?, viewHolder: ViewHolder?, position: Int)

    inner class ViewHolder internal constructor (val view: View?) : RecyclerView.ViewHolder(view){
        init{
            view?.setOnClickListener { listener?.onClick(adapterPosition, getItem(adapterPosition)) }
        }
    }

    fun getItem(index: Int): T? = items?.get(index)

    fun deleteItem(position: Int){
        items?.removeAt(position)
        notifyItemChanged(position)
    }

    fun getContext(): Context? = context

    fun getList(): List<T>? = items


    fun setListClearPrevious(list: List<T>){
        clearList()
        items?.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun setList(list: List<T>) {
        items?.addAll(list)
//        notifyDataSetChanged()
        notifyItemRangeInserted(0, list.size)
    }

    fun clearList(){
        items?.clear()
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnViewHolderClick<T>) {
        this.listener = listener
    }

    interface OnViewHolderClick<in T> {
        fun onClick(position: Int, item: T?)
    }
}